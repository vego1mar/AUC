package net.vego1mar.xml;

import net.vego1mar.method.AppendMethod;
import net.vego1mar.method.ExtractWordMethod;
import net.vego1mar.method.FirstOfMethod;
import net.vego1mar.method.GrabUntilMethod;
import net.vego1mar.method.Methodable;
import net.vego1mar.method.PrependMethod;
import net.vego1mar.method.RemoveCharactersMethod;
import net.vego1mar.method.RemoveStringsMethod;
import net.vego1mar.method.RetrieveTagsMethod;
import net.vego1mar.method.TrimMethod;
import net.vego1mar.method.enumerators.FirstOfType;
import net.vego1mar.method.enumerators.RetrieveTagsType;
import net.vego1mar.method.enumerators.TrimSide;
import net.vego1mar.method.enumerators.MethodType;
import net.vego1mar.utils.MethodFactory;
import org.dom4j.Element;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class XmlMethodNodeReader extends XmlRulesSetTags {

    private Element xmlMethod;
    private Methodable objMethod;

    public XmlMethodNodeReader(@NotNull Element methodNode) {
        xmlMethod = methodNode;
        objMethod = MethodFactory.createEmpty();
    }

    private MethodType fetchMethodType() {
        Element methodTypeElement = xmlMethod.element(TAG_METHODTYPE);

        if (methodTypeElement == null) {
            return MethodType.EMPTY;
        }

        return MethodType.valueOf(methodTypeElement.getText());
    }

    private void readMethodAttributes(@NotNull MethodType methodType) {
        switch (methodType) {
            case EMPTY:
                break;
            case SPLIT_WORDS:
                objMethod = MethodFactory.createSplitWords();
                break;
            case FETCH_HREFS:
                objMethod = MethodFactory.createFetchHrefs();
                break;
            case FIRST_OF:
                readFirstOfMethodAtrributes();
                break;
            case EXTRACT_WORD:
                readExtractWordMethodAttributes();
                break;
            case REMOVE_CHARACTERS:
                readRemoveCharactersMethodAttributes();
                break;
            case REMOVE_STRINGS:
                readRemoveStringsMethodAttributes();
                break;
            case RETRIEVE_TAGS:
                readRetrieveTagsMethodAttributes();
                break;
            case PREPEND:
                readPrependMethodAttributes();
                break;
            case APPEND:
                readAppendMethodAttributes();
                break;
            case GRAB_UNTIL:
                readGrabUntilMethodAttributes();
                break;
            case TRIM:
                readTrimMethodAttributes();
                break;
        }
    }

    private void readFirstOfMethodAtrributes() {
        objMethod = MethodFactory.createFirstOf();
        FirstOfMethod method = (FirstOfMethod) objMethod;
        Element type = xmlMethod.element(TAG_TYPE);
        Element text = xmlMethod.element(TAG_TEXT);

        if (isCorrect(type)) {
            method.setType(FirstOfType.valueOf(type.getText()));
        }

        if (isCorrect(text)) {
            method.setText(text.getText());
        }
    }

    private void readExtractWordMethodAttributes() {
        objMethod = MethodFactory.createExtractWord();
        ExtractWordMethod method = (ExtractWordMethod) objMethod;
        Element position = xmlMethod.element(TAG_POSITION);

        if (isCorrect(position)) {
            method.setPosition(Integer.valueOf(position.getText()));
        }
    }

    private void readRemoveCharactersMethodAttributes() {
        objMethod = MethodFactory.createRemoveCharacters();
        RemoveCharactersMethod method = (RemoveCharactersMethod) objMethod;
        Element signs = xmlMethod.element(TAG_SIGNS);

        if (isCorrect(signs)) {
            method.setSigns(signs.getText());
        }
    }

    private void readRemoveStringsMethodAttributes() {
        objMethod = MethodFactory.createRemoveStrings();
        RemoveStringsMethod method = (RemoveStringsMethod) objMethod;
        Element string = xmlMethod.element(TAG_STRING);

        if (isCorrect(string)) {
            method.setString(string.getText());
        }
    }

    private void readRetrieveTagsMethodAttributes() {
        objMethod = MethodFactory.createRetrieveTags();
        RetrieveTagsMethod method = (RetrieveTagsMethod) objMethod;
        Element type = xmlMethod.element(TAG_TYPE);
        Element tagname = xmlMethod.element(TAG_TAGNAME);

        if (isCorrect(type)) {
            method.setType(RetrieveTagsType.valueOf(type.getText()));
        }

        if (isCorrect(tagname)) {
            method.setTagname(tagname.getText());
        }
    }

    private void readPrependMethodAttributes() {
        objMethod = MethodFactory.createPrepend();
        PrependMethod method = (PrependMethod) objMethod;
        Element text = xmlMethod.element(TAG_TEXT);

        if (isCorrect(text)) {
            method.setText(text.getText());
        }
    }

    private void readAppendMethodAttributes() {
        objMethod = MethodFactory.createAppend();
        AppendMethod method = (AppendMethod) objMethod;
        Element text = xmlMethod.element(TAG_TEXT);

        if (isCorrect(text)) {
            method.setText(text.getText());
        }
    }

    private void readGrabUntilMethodAttributes() {
        objMethod = MethodFactory.createGrabUntil();
        GrabUntilMethod method = (GrabUntilMethod) objMethod;
        Element charStop = xmlMethod.element(TAG_CHARSTOP);

        if (isCorrect(charStop)) {
            method.setCharStop(charStop.getText().charAt(0));
        }
    }

    private void readTrimMethodAttributes() {
        objMethod = MethodFactory.createTrim();
        TrimMethod method = (TrimMethod) objMethod;
        Element side = xmlMethod.element(TAG_SIDE);
        Element numberOf = xmlMethod.element(TAG_NUMBEROF);

        if (isCorrect(side)) {
            method.setSide(TrimSide.valueOf(side.getText()));
        }

        if (isCorrect(numberOf)) {
            method.setNumberOf(Integer.valueOf(numberOf.getText()));
        }
    }

    @Contract(value = "null -> false; !null -> true", pure = true) private boolean isCorrect(Element element) {
        return element != null;
    }

    public void readXmlMethod() {
        readMethodAttributes(fetchMethodType());
    }

    public Methodable getObjMethod() {
        return objMethod;
    }
}
