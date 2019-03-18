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
import net.vego1mar.utils.MethodCreator;
import org.dom4j.Element;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class XmlMethodNodeReader extends XmlRulesSetTags {

    private Element xmlMethod;
    private Methodable objMethod;

    public XmlMethodNodeReader(@NotNull Element methodNode) {
        xmlMethod = methodNode;
        objMethod = MethodCreator.getMethod(MethodType.EMPTY);
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
                objMethod = MethodCreator.getMethod(MethodType.SPLIT_WORDS);
                break;
            case FETCH_HREFS:
                objMethod = MethodCreator.getMethod(MethodType.FETCH_HREFS);
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
        objMethod = MethodCreator.getMethod(MethodType.FIRST_OF);
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
        objMethod = MethodCreator.getMethod(MethodType.EXTRACT_WORD);
        ExtractWordMethod method = (ExtractWordMethod) objMethod;
        Element position = xmlMethod.element(TAG_POSITION);

        if (isCorrect(position)) {
            method.setPosition(Integer.valueOf(position.getText()));
        }
    }

    private void readRemoveCharactersMethodAttributes() {
        objMethod = MethodCreator.getMethod(MethodType.REMOVE_CHARACTERS);
        RemoveCharactersMethod method = (RemoveCharactersMethod) objMethod;
        Element signs = xmlMethod.element(TAG_SIGNS);

        if (isCorrect(signs)) {
            method.setSigns(signs.getText());
        }
    }

    private void readRemoveStringsMethodAttributes() {
        objMethod = MethodCreator.getMethod(MethodType.REMOVE_STRINGS);
        RemoveStringsMethod method = (RemoveStringsMethod) objMethod;
        Element string = xmlMethod.element(TAG_STRING);

        if (isCorrect(string)) {
            method.setString(string.getText());
        }
    }

    private void readRetrieveTagsMethodAttributes() {
        objMethod = MethodCreator.getMethod(MethodType.RETRIEVE_TAGS);
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
        objMethod = MethodCreator.getMethod(MethodType.PREPEND);
        PrependMethod method = (PrependMethod) objMethod;
        Element text = xmlMethod.element(TAG_TEXT);

        if (isCorrect(text)) {
            method.setText(text.getText());
        }
    }

    private void readAppendMethodAttributes() {
        objMethod = MethodCreator.getMethod(MethodType.APPEND);
        AppendMethod method = (AppendMethod) objMethod;
        Element text = xmlMethod.element(TAG_TEXT);

        if (isCorrect(text)) {
            method.setText(text.getText());
        }
    }

    private void readGrabUntilMethodAttributes() {
        objMethod = MethodCreator.getMethod(MethodType.GRAB_UNTIL);
        GrabUntilMethod method = (GrabUntilMethod) objMethod;
        Element charStop = xmlMethod.element(TAG_CHARSTOP);

        if (isCorrect(charStop)) {
            method.setCharStop(charStop.getText().charAt(0));
        }
    }

    private void readTrimMethodAttributes() {
        objMethod = MethodCreator.getMethod(MethodType.TRIM);
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
