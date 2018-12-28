package net.vego1mar.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Deque;
import net.vego1mar.auxiliary.method.ExtractWordMethod;
import net.vego1mar.auxiliary.method.FirstOfMethod;
import net.vego1mar.auxiliary.method.GrabUntilMethod;
import net.vego1mar.auxiliary.method.PrependMethod;
import net.vego1mar.auxiliary.method.RemoveCharactersMethod;
import net.vego1mar.auxiliary.method.RetrieveTagsMethod;
import net.vego1mar.auxiliary.method.TrimMethod;
import net.vego1mar.auxiliary.target.Target;
import net.vego1mar.rules.RuleBased;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jetbrains.annotations.NotNull;

public class XmlRulesSetWriter implements XmlRulesSetImpl {

    private static final Logger log = Logger.getLogger(XmlRulesSetWriter.class);

    private Document createDocument(@NotNull Deque<RuleBased> rulesSet) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(TAG_RULESSET);
        int i = 1;

        for (RuleBased item : rulesSet) {
            Element rule = root.addElement(TAG_RULE).addAttribute("no", String.valueOf(i));
            createTargetNode(rule, item);
            createMethodNode(rule, item);
            i++;
        }

        return document;
    }

    private void createTargetNode(@NotNull Element xmlRule, @NotNull RuleBased objRule) {
        Element node = xmlRule.addElement(TAG_TARGET);
        Target target = (Target) objRule.getTarget();
        node.addElement(TAG_IN).addText(target.in().toString());
        node.addElement(TAG_USEAS).addText(target.useAs().toString());

        switch (target.useAs()) {
            case IGNORE:
                break;
            case VERSIONS:
                node.addElement(TAG_VERSION).addText(target.getVersion().toString());
                break;
            case DATES:
                node.addElement(TAG_DATE).addText(target.getDate().toString());
                break;
            case LINKS:
                node.addElement(TAG_LINKID).addText(target.getLinkID().toString());
                break;
            case HASHES:
                node.addElement(TAG_HASHID).addText(target.getHashID().toString());
        }
    }

    private void createMethodNode(@NotNull Element xmlRule, @NotNull RuleBased objRule) {
        Element node = xmlRule.addElement(TAG_METHOD);
        node.addElement(TAG_METHODTYPE).addText(objRule.getMethod().getMethodType().toString());

        switch (objRule.getMethod().getMethodType()) {
            case EMPTY:
            case FETCH_HREFS:
            case SPLIT_WORDS:
                break;
            case FIRST_OF:
                FirstOfMethod firstOfMethod = (FirstOfMethod) objRule.getMethod();
                node.addElement(TAG_TYPE).addText(firstOfMethod.getType().name());
                node.addElement(TAG_TEXT).addCDATA(firstOfMethod.getText());
                break;
            case EXTRACT_WORD:
                ExtractWordMethod extractWordMethod = (ExtractWordMethod) objRule.getMethod();
                node.addElement(TAG_POSITION).addText(String.valueOf(extractWordMethod.getPosition()));
                break;
            case RETRIEVE_TAGS:
                RetrieveTagsMethod retrieveTagsMethod = (RetrieveTagsMethod) objRule.getMethod();
                node.addElement(TAG_TYPE).addText(retrieveTagsMethod.getType().name());
                node.addElement(TAG_TAGNAME).addText(retrieveTagsMethod.getTagname());
                break;
            case REMOVE_CHARACTERS:
                RemoveCharactersMethod removeCharactersMethod = (RemoveCharactersMethod) objRule.getMethod();
                node.addElement(TAG_SIGNS).addCDATA(removeCharactersMethod.getSigns());
                break;
            case PREPEND:
                PrependMethod prependMethod = (PrependMethod) objRule.getMethod();
                node.addElement(TAG_TEXT).addCDATA(prependMethod.getText());
                break;
            case GRAB_UNTIL:
                GrabUntilMethod grabUntilMethod = (GrabUntilMethod) objRule.getMethod();
                node.addElement(TAG_CHARSTOP).addCDATA(grabUntilMethod.getCharStop().toString());
                break;
            case TRIM:
                TrimMethod trimMethod = (TrimMethod) objRule.getMethod();
                node.addElement(TAG_SIDE).addText(trimMethod.getSide().name());
                node.addElement(TAG_NUMBEROF).addText(String.valueOf(trimMethod.getNumberOf()));
                break;
        }
    }

    private void write(@NotNull Document document, @NotNull String xmlFile) throws IOException {
        try (FileWriter fileWriter = new FileWriter(xmlFile)) {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter xmlWriter = new XMLWriter(fileWriter, format);
            xmlWriter.write(document);
            xmlWriter.close();
        }
    }

    public void saveSettings(@NotNull Deque<RuleBased> rulesSet, @NotNull String xmlFile) {
        try {
            write(createDocument(rulesSet), xmlFile);
        } catch (IOException exp) {
            log.error(exp.getMessage());
        }
    }

}
