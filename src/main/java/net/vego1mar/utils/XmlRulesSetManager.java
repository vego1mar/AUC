package net.vego1mar.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.vego1mar.rules.Rule;
import net.vego1mar.rules.RuleBased;
import net.vego1mar.rules.auxiliary.method.Method;
import net.vego1mar.rules.auxiliary.method.Methodable;
import net.vego1mar.rules.auxiliary.target.Target;
import net.vego1mar.rules.enumerators.methods.firstof.FirstOfType;
import net.vego1mar.rules.enumerators.traits.InTrait;
import net.vego1mar.rules.enumerators.traits.MethodTrait;
import net.vego1mar.rules.enumerators.traits.UseAsTrait;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jetbrains.annotations.NotNull;

public final class XmlRulesSetManager {

    private static final Logger log = Logger.getLogger(XmlRulesSetManager.class);
    private static final String TAGNAME_RULES_SET_ROOT = "rulesSet";
    private static final String TAGNAME_RULE_ELEMENT = "rule";
    private static final String TAGNAME_TARGET_NODE = "target";
    private static final String TAGNAME_IN_TRAIT = "inTrait";
    private static final String TAGNAME_USE_AS_TRAIT = "useAsTrait";
    private static final String TAGNAME_METHOD_NODE = "method";
    private static final String TAGNAME_METHOD_TRAIT = "methodTrait";
    private static final String TAGNAME_FIRST_OF_TYPE = "firstOfType";
    private static final String TAGNAME_FIRST_OF_TEXT = "firstOfText";
    private static final String TAGNAME_EXTRACT_WORD_POSITION = "extractWordPosition";
    private static final String TAGNAME_RETRIEVE_ALL_TAGS_TAG = "retrieveAllTagsTag";
    private static final String TAGNAME_REMOVE_CHARACTER_SIGNS = "removeCharactersSigns";
    private static final String TAGNAME_PREPEND_TEXT = "prependText";
    private static final String TAGNAME_GRAB_UNTIL_CHAR_STOP = "grabUntilCharStop";

    private XmlRulesSetManager() {
        // This should be a utility class.
    }

    private static Document createDocument(@NotNull Deque<RuleBased> rulesSet) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(TAGNAME_RULES_SET_ROOT);
        int i = 1;

        for (RuleBased item : rulesSet) {
            Element rule = root.addElement(TAGNAME_RULE_ELEMENT).addAttribute("no", String.valueOf(i));
            Element target = rule.addElement(TAGNAME_TARGET_NODE);
            target.addElement(TAGNAME_IN_TRAIT).addText(item.getTarget().in().toString());
            target.addElement(TAGNAME_USE_AS_TRAIT).addText(item.getTarget().useAs().toString());
            Element method = rule.addElement(TAGNAME_METHOD_NODE);
            Map<String, String> methodValues = getNonEmptyMethodValues(item.getMethod());

            for (Map.Entry<String, String> entry : methodValues.entrySet()) {
                method.addElement(entry.getKey()).addText(entry.getValue());
            }

            i++;
        }

        return document;
    }

    private static Map<String, String> getNonEmptyMethodValues(@NotNull Methodable method) {
        Map<String, String> valuesSet = new LinkedHashMap<>();
        Method trait = (Method) method;
        valuesSet.put(TAGNAME_METHOD_TRAIT, trait.getSelectedMethod().toString());
        valuesSet.put(TAGNAME_FIRST_OF_TYPE, trait.getFirstOfType().toString());
        valuesSet.put(TAGNAME_FIRST_OF_TEXT, trait.getFirstOfText());
        valuesSet.put(TAGNAME_EXTRACT_WORD_POSITION, String.valueOf(trait.getExtractWordPosition()));
        valuesSet.put(TAGNAME_REMOVE_CHARACTER_SIGNS, trait.getRemoveCharactersSigns());
        valuesSet.put(TAGNAME_RETRIEVE_ALL_TAGS_TAG, trait.getRetrieveAllTagsTag());
        valuesSet.put(TAGNAME_PREPEND_TEXT, trait.getPrependText());
        valuesSet.put(TAGNAME_GRAB_UNTIL_CHAR_STOP, trait.getGrabUntilCharStop().toString());
        Iterator it = valuesSet.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String value = (String) pair.getValue();

            if (value.isEmpty() || value.equals("\0")) {
                it.remove();
            }
        }

        return valuesSet;
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
            Element target = element.element(TAGNAME_TARGET_NODE);
            Element method = element.element(TAGNAME_METHOD_NODE);

            RuleBased rule = new Rule(
                new Target(InTrait.valueOf(target.element(TAGNAME_IN_TRAIT).getText()), UseAsTrait.valueOf(target.element(TAGNAME_USE_AS_TRAIT).getText())),
                new Method(MethodTrait.valueOf(method.element(TAGNAME_METHOD_TRAIT).getText()))
            );

            List<String> methodValues = getNonNullMethodValues(method);
            rule.getMethod().firstOf(FirstOfType.valueOf(methodValues.get(0)), methodValues.get(1));
            rule.getMethod().extractWord(Integer.valueOf(methodValues.get(2)));
            rule.getMethod().retrieveAllTags(methodValues.get(3));
            rule.getMethod().removeCharacters(methodValues.get(4));
            rule.getMethod().prepend(methodValues.get(5));
            rule.getMethod().grabUntil(methodValues.get(6).charAt(0));
            rulesSet.add(rule);
        }

        return rulesSet;
    }

    private static List<String> getNonNullMethodValues(@NotNull Element method) {
        List<String> values = new ArrayList<>();
        values.add(method.element(TAGNAME_FIRST_OF_TYPE) == null ? "TAG" : method.element(TAGNAME_FIRST_OF_TYPE).getText());
        values.add(method.element(TAGNAME_FIRST_OF_TEXT) == null ? "" : method.element(TAGNAME_FIRST_OF_TEXT).getText());
        values.add(method.element(TAGNAME_EXTRACT_WORD_POSITION) == null ? "1" : method.element(TAGNAME_EXTRACT_WORD_POSITION).getText());
        values.add(method.element(TAGNAME_RETRIEVE_ALL_TAGS_TAG) == null ? "a" : method.element(TAGNAME_RETRIEVE_ALL_TAGS_TAG).getText());
        values.add(method.element(TAGNAME_REMOVE_CHARACTER_SIGNS) == null ? "" : method.element(TAGNAME_REMOVE_CHARACTER_SIGNS).getText());
        values.add(method.element(TAGNAME_PREPEND_TEXT) == null ? "" : method.element(TAGNAME_PREPEND_TEXT).getText());
        values.add(method.element(TAGNAME_GRAB_UNTIL_CHAR_STOP) == null ? String.valueOf('\0') : method.element(TAGNAME_GRAB_UNTIL_CHAR_STOP).getText());
        return values;
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
