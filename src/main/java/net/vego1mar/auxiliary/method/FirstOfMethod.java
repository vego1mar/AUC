package net.vego1mar.auxiliary.method;

import net.vego1mar.auxiliary.properties.InImpl;
import net.vego1mar.auxiliary.target.Targetable;
import net.vego1mar.enumerators.methods.FirstOfType;
import net.vego1mar.enumerators.traits.MethodType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FirstOfMethod extends Method {

    private FirstOfType type;
    private String text;

    public FirstOfMethod() {
        super(MethodType.FIRST_OF);
        type = FirstOfType.TAG;
        text = "a";
    }

    @Override public String toString() {
        return "{ " + MethodType.FIRST_OF.name() + "; type=" + type.name() + "; text='" + text + "' }";
    }

    public FirstOfType getType() {
        return type;
    }

    public void setType(FirstOfType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(@NotNull String text) {
        this.text = text;
    }

    @Override public InImpl invoke(@NotNull Targetable target, @NotNull InImpl inProperty) {
        switch (target.in()) {
            case CODE:
                inProperty.setContent(firstOf(inProperty.getCode()));
                break;
            case CONTENT:
                inProperty.setContent(firstOf(inProperty.getContent()));
                break;
            case COLLECTION:
                throw new UnsupportedOperationException(METHOD_TARGET_NOT_SUPPORTED);
        }

        return inProperty;
    }

    @Nullable private String firstOf(@NotNull String source) {
        String textTag = '<' + text;
        int startIndex = source.indexOf(textTag);

        if (type == FirstOfType.STRING) {
            startIndex = source.indexOf(text);

            if (startIndex < 0) {
                return "";
            }

            return source.substring(startIndex);
        }

        if (type == FirstOfType.TAG) {
            String closingTag = "</" + text + '>';
            int endIndex = source.indexOf(closingTag, startIndex);

            if (startIndex < 0 || endIndex < 0) {
                return "";
            }

            return source.substring(startIndex, endIndex + closingTag.length());
        }

        return null;
    }

}
