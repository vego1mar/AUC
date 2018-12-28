package net.vego1mar.xml;

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
import net.vego1mar.auxiliary.method.Methodable;
import net.vego1mar.auxiliary.method.PrependMethod;
import net.vego1mar.auxiliary.method.RemoveCharactersMethod;
import net.vego1mar.auxiliary.method.RetrieveTagsMethod;
import net.vego1mar.auxiliary.method.SplitWordsMethod;
import net.vego1mar.auxiliary.method.TrimMethod;
import net.vego1mar.auxiliary.target.Target;
import net.vego1mar.auxiliary.target.Targetable;
import net.vego1mar.enumerators.methods.FirstOfType;
import net.vego1mar.enumerators.methods.RetrieveTagsType;
import net.vego1mar.enumerators.methods.TrimSide;
import net.vego1mar.enumerators.traits.In;
import net.vego1mar.enumerators.traits.MethodType;
import net.vego1mar.enumerators.traits.UseAs;
import net.vego1mar.rules.Rule;
import net.vego1mar.rules.RuleBased;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class XmlRulesSetReader implements XmlRulesSetImpl {

    private static final Logger log = Logger.getLogger(XmlRulesSetReader.class);

    private Document parse(@NotNull String path) throws DocumentException, MalformedURLException {
        return new SAXReader().read(Paths.get(path).toUri().toURL());
    }

    private Deque<RuleBased> read(@NotNull Document document) {
        List<Element> elements = document.getRootElement().elements();
        Deque<RuleBased> rulesSet = new LinkedList<>();

        for (Element element : elements) {
            rulesSet.add(new Rule(readTargetNode(element), readMethodNode(element)));
        }

        return rulesSet;
    }

    @NotNull @Contract("_ -> new") private Targetable readTargetNode(@NotNull Element xmlRule) {
        Element target = xmlRule.element(TAG_TARGET);
        In in = In.CODE;
        UseAs useAs = UseAs.IGNORE;
        Element inElement = null;
        Element useAsElement = null;

        if (target != null) {
            inElement = target.element(TAG_IN);
            useAsElement = target.element(TAG_USEAS);
        }

        if (useAsElement != null) {
            useAs = UseAs.valueOf(useAsElement.getText());
        }

        if (inElement != null) {
            in = In.valueOf(inElement.getText());
        }

        return new Target(in, useAs);
    }

    private Methodable readMethodNode(@NotNull Element xmlRule) {
        Element method = xmlRule.element(TAG_METHOD);
        MethodType methodType = MethodType.EMPTY;
        Element methodTypeElement = null;
        Methodable objMethod = new EmptyMethod();

        if (method != null) {
            methodTypeElement = method.element(TAG_TYPE);

            if (methodTypeElement != null) {
                methodType = MethodType.valueOf(methodTypeElement.getText());
            }

            objMethod = readMethodAttributes(methodType, method);
        }

        return objMethod;
    }

    @Contract("_, _ -> new") @NotNull private Methodable readMethodAttributes(@NotNull MethodType methodType, @NotNull Element xmlMethod) {
        switch (methodType) {
            case EMPTY:
                break;
            case SPLIT_WORDS:
                return new SplitWordsMethod();
            case FETCH_HREFS:
                return new FetchHrefsMethod();
            case FIRST_OF:
                FirstOfMethod firstOf = new FirstOfMethod();
                Element type = xmlMethod.element(TAG_TYPE);
                Element text = xmlMethod.element(TAG_TEXT);
                firstOf.setType(type == null ? firstOf.getType() : FirstOfType.valueOf(type.getText()));
                firstOf.setText(text == null ? firstOf.getText() : text.getText());
                return firstOf;
            case EXTRACT_WORD:
                ExtractWordMethod extractWord = new ExtractWordMethod();
                Element position = xmlMethod.element(TAG_POSITION);
                extractWord.setPosition(position == null ? extractWord.getPosition() : Integer.valueOf(position.getText()));
                return extractWord;
            case REMOVE_CHARACTERS:
                RemoveCharactersMethod removeCharacters = new RemoveCharactersMethod();
                Element signs = xmlMethod.element(TAG_SIGNS);
                removeCharacters.setSigns(signs == null ? removeCharacters.getSigns() : signs.getText());
                return removeCharacters;
            case RETRIEVE_TAGS:
                RetrieveTagsMethod retrieveTags = new RetrieveTagsMethod();
                Element type2 = xmlMethod.element(TAG_TYPE);
                Element tagname = xmlMethod.element(TAG_TAGNAME);
                retrieveTags.setType(type2 == null ? retrieveTags.getType() : RetrieveTagsType.valueOf(type2.getText()));
                retrieveTags.setTagname(tagname == null ? retrieveTags.getTagname() : tagname.getText());
                return retrieveTags;
            case PREPEND:
                PrependMethod prepend = new PrependMethod();
                Element text2 = xmlMethod.element(TAG_TEXT);
                prepend.setText(text2 == null ? prepend.getText() : text2.getText());
                return prepend;
            case GRAB_UNTIL:
                GrabUntilMethod grabUntil = new GrabUntilMethod();
                Element charStop = xmlMethod.element(TAG_CHARSTOP);
                grabUntil.setCharStop(charStop == null ? grabUntil.getCharStop() : Character.valueOf(charStop.getText().charAt(0)));
                return grabUntil;
            case TRIM:
                TrimMethod trim = new TrimMethod();
                Element side = xmlMethod.element(TAG_SIDE);
                Element numberOf = xmlMethod.element(TAG_NUMBEROF);
                trim.setSide(side == null ? trim.getSide() : TrimSide.valueOf(side.getText()));
                trim.setNumberOf(numberOf == null ? trim.getNumberOf() : Integer.valueOf(numberOf.getText()));
                return trim;
        }

        return new EmptyMethod();
    }

    public Deque<RuleBased> loadSettings(@NotNull String xmlFile) {
        try {
            return read(parse(xmlFile));
        } catch (DocumentException | MalformedURLException exp) {
            log.error(exp.getMessage());
        }

        return new LinkedList<>();
    }

}
