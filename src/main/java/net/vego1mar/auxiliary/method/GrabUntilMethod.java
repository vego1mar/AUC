package net.vego1mar.auxiliary.method;

import net.vego1mar.auxiliary.properties.InImpl;
import net.vego1mar.auxiliary.target.Targetable;
import net.vego1mar.enumerators.traits.MethodType;
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

    @Override public InImpl invoke(@NotNull Targetable target, @NotNull InImpl inProperty) {
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
