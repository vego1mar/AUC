package net.vego1mar.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Deque;
import net.vego1mar.method.AppendMethod;
import net.vego1mar.method.ExtractWordMethod;
import net.vego1mar.method.FirstOfMethod;
import net.vego1mar.method.GrabUntilMethod;
import net.vego1mar.method.PrependMethod;
import net.vego1mar.method.RemoveCharactersMethod;
import net.vego1mar.method.RemoveStringsMethod;
import net.vego1mar.method.RetrieveTagsMethod;
import net.vego1mar.method.TrimMethod;
import net.vego1mar.rules.Rule;
import net.vego1mar.target.Target;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jetbrains.annotations.NotNull;

public class XmlRulesSetWriter extends XmlRulesSetTags {

    private static final Logger log = Logger.getLogger(XmlRulesSetWriter.class);

    private Document createDocument(@NotNull Deque<Rule> rulesSet) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(TAG_RULESSET);
        int i = 1;

        for (Rule item : rulesSet) {
            Element rule = root.addElement(TAG_RULE).addAttribute("no", String.valueOf(i));
            createTargetNode(rule, item);
            createMethodNode(rule, item);
            i++;
        }

        return document;
    }

    private void createTargetNode(@NotNull Element xmlRule, @NotNull Rule objRule) {
        Element node = xmlRule.addElement(TAG_TARGET);
        Target target = objRule.getTarget();
        node.addElement(TAG_IN).addText(target.in().toString());
        node.addElement(TAG_USEAS).addText(target.useAs().toString());

        switch (target.useAs()) {
            case IGNORE:
                break;
            case VERSIONS:
                node.addElement(TAG_VERSION).addText(target.version().toString());
                break;
            case DATES:
                node.addElement(TAG_DATE).addText(target.date().toString());
                break;
            case LINKS:
                node.addElement(TAG_LINKID).addText(target.linkID().toString());
                break;
            case HASHES:
                node.addElement(TAG_HASHID).addText(target.hashID().toString());
        }
    }

    private void createMethodNode(@NotNull Element xmlRule, @NotNull Rule objRule) {
        Element node = xmlRule.addElement(TAG_METHOD);
        node.addElement(TAG_METHODTYPE).addText(objRule.getMethod().getMethodType().toString());

        switch (objRule.getMethod().getMethodType()) {
            case EMPTY:
            case FETCH_HREFS:
            case SPLIT_WORDS:
            case CLEAR_CONTENT:
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
            case REMOVE_STRINGS:
                RemoveStringsMethod removeStringsMethod = (RemoveStringsMethod) objRule.getMethod();
                node.addElement(TAG_STRING).addCDATA(removeStringsMethod.getString());
                break;
            case PREPEND:
                PrependMethod prependMethod = (PrependMethod) objRule.getMethod();
                node.addElement(TAG_TEXT).addCDATA(prependMethod.getText());
                break;
            case APPEND:
                AppendMethod appendMethod = (AppendMethod) objRule.getMethod();
                node.addElement(TAG_TEXT).addCDATA(appendMethod.getText());
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
            default:
                // If no valid method type has been chosen then do nothing.
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

    public void saveSettings(@NotNull Deque<Rule> rulesSet, @NotNull String xmlFile) {
        try {
            write(createDocument(rulesSet), xmlFile);
        } catch (IOException exp) {
            log.error(exp.getMessage());
        }
    }

}
