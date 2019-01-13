package net.vego1mar.xml;

import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import net.vego1mar.auxiliary.method.Methodable;
import net.vego1mar.auxiliary.target.Targetable;
import net.vego1mar.rules.Rule;
import net.vego1mar.rules.RuleImpl;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jetbrains.annotations.NotNull;

public class XmlRulesSetReader extends XmlRulesSetTags {

    private static final Logger log = Logger.getLogger(XmlRulesSetReader.class);

    private Document parse(@NotNull String path) throws DocumentException, MalformedURLException {
        return new SAXReader().read(Paths.get(path).toUri().toURL());
    }

    private Deque<RuleImpl> read(@NotNull Document document) {
        List<Element> elements = document.getRootElement().elements();
        Deque<RuleImpl> rulesSet = new LinkedList<>();

        for (Element element : elements) {
            rulesSet.add(new Rule(readTargetNode(element), readMethodNode(element)));
        }

        return rulesSet;
    }

    private Targetable readTargetNode(@NotNull Element xmlRule) {
        Element target = xmlRule.element(TAG_TARGET);
        XmlTargetNodeReader node = new XmlTargetNodeReader(target);
        node.readXmlTarget();
        return node.getObjTarget();
    }

    private Methodable readMethodNode(@NotNull Element xmlRule) {
        Element method = xmlRule.element(TAG_METHOD);
        XmlMethodNodeReader node = new XmlMethodNodeReader(method);
        node.readXmlMethod();
        return node.getObjMethod();
    }

    public Deque<RuleImpl> loadSettings(@NotNull String xmlFile) {
        try {
            return read(parse(xmlFile));
        } catch (DocumentException | MalformedURLException exp) {
            log.error(exp.getMessage());
        }

        return new LinkedList<>();
    }
}
