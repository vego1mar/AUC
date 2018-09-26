package net.vego1mar.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import net.vego1mar.auxiliary.method.EmptyMethod;
import net.vego1mar.auxiliary.method.ExtractWordMethod;
import net.vego1mar.auxiliary.method.FetchHrefsMethod;
import net.vego1mar.auxiliary.method.FirstOfMethod;
import net.vego1mar.auxiliary.method.GrabUntilMethod;
import net.vego1mar.auxiliary.method.PrependMethod;
import net.vego1mar.auxiliary.method.RemoveCharactersMethod;
import net.vego1mar.auxiliary.method.RetrieveTagsMethod;
import net.vego1mar.auxiliary.method.SplitWordsMethod;
import net.vego1mar.auxiliary.target.Target;
import net.vego1mar.auxiliary.target.Targetable;
import net.vego1mar.enumerators.methods.FirstOfType;
import net.vego1mar.enumerators.methods.RetrieveTagsType;
import net.vego1mar.enumerators.traits.In;
import net.vego1mar.enumerators.traits.MethodType;
import net.vego1mar.enumerators.traits.UseAs;
import net.vego1mar.rules.Rule;
import net.vego1mar.rules.RuleBased;
import net.vego1mar.auxiliary.method.Methodable;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class XmlRulesSetManager {

    private static final Logger log = Logger.getLogger(XmlRulesSetManager.class);
    private static final String TAGNAME_RULES_SET_ROOT = "rulesSet";
    private static final String TAGNAME_RULE_ELEMENT = "rule";
    private static final String TAGNAME_TARGET_NODE = "target";
    private static final String TAGNAME_IN_ENUM = "in";
    private static final String TAGNAME_USE_AS_ENUM = "useAs";
    private static final String TAGNAME_METHOD_NODE = "method";
    private static final String TAGNAME_METHOD_TYPE = "methodType";
    private static final String TAGNAME_METHOD_ATTRIBUTE_TYPE = "type";
    private static final String TAGNAME_METHOD_ATTRIBUTE_TEXT = "text";
    private static final String TAGNAME_METHOD_ATTRIBUTE_POSITION = "position";
    private static final String TAGNAME_METHOD_ATTRIBUTE_TAGNAME = "tagname";
    private static final String TAGNAME_METHOD_ATTRIBUTE_SIGNS = "signs";
    private static final String TAGNAME_METHOD_ATTRIBUTE_CHARSTOP = "charStop";

    private XmlRulesSetManager() {
        // This should be a utility class.
    }

    private static Document createDocument(@NotNull Deque<RuleBased> rulesSet) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(TAGNAME_RULES_SET_ROOT);
        int i = 1;

        for (RuleBased item : rulesSet) {
            Element rule = root.addElement(TAGNAME_RULE_ELEMENT).addAttribute("no", String.valueOf(i));
            createTargetNode(rule, item);
            createMethodNode(rule, item);
            i++;
        }

        return document;
    }

    private static void createTargetNode(@NotNull Element xmlRule, @NotNull RuleBased objRule) {
        Element target = xmlRule.addElement(TAGNAME_TARGET_NODE);
        target.addElement(TAGNAME_IN_ENUM).addText(objRule.getTarget().in().toString());
        target.addElement(TAGNAME_USE_AS_ENUM).addText(objRule.getTarget().useAs().toString());
    }

    private static void createMethodNode(@NotNull Element xmlRule, @NotNull RuleBased objRule) {
        Element method = xmlRule.addElement(TAGNAME_METHOD_NODE);
        method.addElement(TAGNAME_METHOD_TYPE).addText(objRule.getMethod().getMethodType().toString());

        switch (objRule.getMethod().getMethodType()) {
            case EMPTY:
            case FETCH_HREFS:
            case SPLIT_WORDS:
                break;
            case FIRST_OF:
                FirstOfMethod firstOfMethod = (FirstOfMethod) objRule.getMethod();
                method.addElement(TAGNAME_METHOD_ATTRIBUTE_TYPE).addText(firstOfMethod.getType().name());
                method.addElement(TAGNAME_METHOD_ATTRIBUTE_TEXT).addText(firstOfMethod.getText());
                break;
            case EXTRACT_WORD:
                ExtractWordMethod extractWordMethod = (ExtractWordMethod) objRule.getMethod();
                method.addElement(TAGNAME_METHOD_ATTRIBUTE_POSITION).addText(String.valueOf(extractWordMethod.getPosition()));
                break;
            case RETRIEVE_TAGS:
                RetrieveTagsMethod retrieveTagsMethod = (RetrieveTagsMethod) objRule.getMethod();
                method.addElement(TAGNAME_METHOD_ATTRIBUTE_TYPE).addText(retrieveTagsMethod.getType().name());
                method.addElement(TAGNAME_METHOD_ATTRIBUTE_TAGNAME).addText(retrieveTagsMethod.getTagname());
                break;
            case REMOVE_CHARACTERS:
                RemoveCharactersMethod removeCharactersMethod = (RemoveCharactersMethod) objRule.getMethod();
                method.addElement(TAGNAME_METHOD_ATTRIBUTE_SIGNS).addText(removeCharactersMethod.getSigns());
                break;
            case PREPEND:
                PrependMethod prependMethod = (PrependMethod) objRule.getMethod();
                method.addElement(TAGNAME_METHOD_ATTRIBUTE_TEXT).addText(prependMethod.getText());
                break;
            case GRAB_UNTIL:
                GrabUntilMethod grabUntilMethod = (GrabUntilMethod) objRule.getMethod();
                method.addElement(TAGNAME_METHOD_ATTRIBUTE_CHARSTOP).addText(grabUntilMethod.getCharStop().toString());
                break;
        }
    }

    private static void write(@NotNull Document document, @NotNull String xmlFile) throws IOException {
        try (FileWriter fileWriter = new FileWriter(xmlFile)) {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter xmlWriter = new XMLWriter(fileWriter, format);
            xmlWriter.write(document);
            xmlWriter.close();
        }
    }

    public static void saveSettings(@NotNull Deque<RuleBased> rulesSet, @NotNull String xmlFile) {
        try {
            write(createDocument(rulesSet), xmlFile);
        } catch (IOException exp) {
            log.error(exp.getMessage());
        }
    }

    private static Document parse(@NotNull String path) throws DocumentException, MalformedURLException {
        return new SAXReader().read(Paths.get(path).toUri().toURL());
    }

    private static Deque<RuleBased> read(@NotNull Document document) {
        List<Element> elements = document.getRootElement().elements();
        Deque<RuleBased> rulesSet = new LinkedList<>();

        for (Element element : elements) {
            rulesSet.add(new Rule(readTargetNode(element), readMethodNode(element)));
        }

        return rulesSet;
    }

    @NotNull @Contract("_ -> new") private static Targetable readTargetNode(@NotNull Element xmlRule) {
        Element target = xmlRule.element(TAGNAME_TARGET_NODE);
        In in = In.CODE;
        UseAs useAs = UseAs.IGNORE;
        Element inElement = null;
        Element useAsElement = null;

        if (target != null) {
            inElement = target.element(TAGNAME_IN_ENUM);
            useAsElement = target.element(TAGNAME_USE_AS_ENUM);
        }

        if (useAsElement != null) {
            useAs = UseAs.valueOf(useAsElement.getText());
        }

        if (inElement != null) {
            in = In.valueOf(inElement.getText());
        }

        return new Target(in, useAs);
    }

    private static Methodable readMethodNode(@NotNull Element xmlRule) {
        Element method = xmlRule.element(TAGNAME_METHOD_NODE);
        MethodType methodType = MethodType.EMPTY;
        Element methodTypeElement = null;
        Methodable objMethod = new EmptyMethod();

        if (method != null) {
            methodTypeElement = method.element(TAGNAME_METHOD_TYPE);

            if (methodTypeElement != null) {
                methodType = MethodType.valueOf(methodTypeElement.getText());
            }

            objMethod = readMethodAttributes(methodType, method);
        }

        return objMethod;
    }

    @Contract("_, _ -> new") @NotNull private static Methodable readMethodAttributes(@NotNull MethodType methodType, @NotNull Element xmlMethod) {
        switch (methodType) {
            case EMPTY:
                break;
            case SPLIT_WORDS:
                return new SplitWordsMethod();
            case FETCH_HREFS:
                return new FetchHrefsMethod();
            case FIRST_OF:
                FirstOfMethod foMethod = new FirstOfMethod();
                Element type = xmlMethod.element(TAGNAME_METHOD_ATTRIBUTE_TYPE);
                Element text = xmlMethod.element(TAGNAME_METHOD_ATTRIBUTE_TEXT);
                foMethod.setType(type == null ? foMethod.getType() : FirstOfType.valueOf(type.getText()));
                foMethod.setText(text == null ? foMethod.getText() : text.getText());
                return foMethod;
            case EXTRACT_WORD:
                ExtractWordMethod ewMethod = new ExtractWordMethod();
                Element position = xmlMethod.element(TAGNAME_METHOD_ATTRIBUTE_POSITION);
                ewMethod.setPosition(position == null ? ewMethod.getPosition() : Integer.valueOf(position.getText()));
                return ewMethod;
            case REMOVE_CHARACTERS:
                RemoveCharactersMethod rchMethod = new RemoveCharactersMethod();
                Element signs = xmlMethod.element(TAGNAME_METHOD_ATTRIBUTE_SIGNS);
                rchMethod.setSigns(signs == null ? rchMethod.getSigns() : signs.getText());
                return rchMethod;
            case RETRIEVE_TAGS:
                RetrieveTagsMethod rtMethod = new RetrieveTagsMethod();
                Element type2 = xmlMethod.element(TAGNAME_METHOD_ATTRIBUTE_TYPE);
                Element tagname = xmlMethod.element(TAGNAME_METHOD_ATTRIBUTE_TAGNAME);
                rtMethod.setType(type2 == null ? rtMethod.getType() : RetrieveTagsType.valueOf(type2.getText()));
                rtMethod.setTagname(tagname == null ? rtMethod.getTagname() : tagname.getText());
                return rtMethod;
            case PREPEND:
                PrependMethod prMethod = new PrependMethod();
                Element text2 = xmlMethod.element(TAGNAME_METHOD_ATTRIBUTE_TEXT);
                prMethod.setText(text2 == null ? prMethod.getText() : text2.getText());
                return prMethod;
            case GRAB_UNTIL:
                GrabUntilMethod guMethod = new GrabUntilMethod();
                Element charStop = xmlMethod.element(TAGNAME_METHOD_ATTRIBUTE_CHARSTOP);
                guMethod.setCharStop(charStop == null ? guMethod.getCharStop() : Character.valueOf(charStop.getText().charAt(0)));
                return guMethod;
        }

        return new EmptyMethod();
    }

    public static Deque<RuleBased> loadSettings(@NotNull String xmlFile) {
        try {
            return read(parse(xmlFile));
        } catch (DocumentException | MalformedURLException exp) {
            log.error(exp.getMessage());
        }

        return new LinkedList<>();
    }

}
