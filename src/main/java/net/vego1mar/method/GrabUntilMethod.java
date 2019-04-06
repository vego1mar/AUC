package net.vego1mar.method;

import net.vego1mar.method.enumerators.MethodType;
import net.vego1mar.properties.InProperty;
import net.vego1mar.target.Target;
import org.jetbrains.annotations.NotNull;

public class GrabUntilMethod extends Method {

    private Character charStop;

    public GrabUntilMethod() {
        super(MethodType.GRAB_UNTIL);
        charStop = '\0';
    }

    @Override public String toString() {
        return "{ " + MethodType.GRAB_UNTIL.name() + "; charStop='" + charStop + "' }";
    }

    public Character getCharStop() {
        return charStop;
    }

    public void setCharStop(@NotNull Character charStop) {
        this.charStop = charStop;
    }

    @Override public InProperty invoke(@NotNull Target target, @NotNull InProperty inProperty) {
        switch (target.in()) {
            case CODE:
                inProperty.setContent(grabUntil(inProperty.getCode()));
                break;
            case CONTENT:
                inProperty.setContent(grabUntil(inProperty.getContent()));
                break;
            case COLLECTION:
                throw new UnsupportedOperationException(METHOD_TARGET_NOT_SUPPORTED);
        }

        return inProperty;
    }

    private String grabUntil(@NotNull String source) {
        int charIndex = source.indexOf(charStop);

        if (charIndex >= 0) {
            return source.substring(0, charIndex);
        }

        return source;
    }

}
