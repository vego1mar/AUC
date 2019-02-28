package net.vego1mar.method;

import net.vego1mar.properties.InImpl;
import net.vego1mar.target.Targetable;
import net.vego1mar.enumerators.traits.MethodType;
import org.jetbrains.annotations.NotNull;

public class RemoveCharactersMethod extends Method {

    private String signs;

    public RemoveCharactersMethod() {
        super(MethodType.REMOVE_CHARACTERS);
        signs = "";
    }

    @Override public String toString() {
        return "{ " + MethodType.REMOVE_CHARACTERS.name() + "; signs='" + signs + "' }";
    }

    public String getSigns() {
        return signs;
    }

    public void setSigns(@NotNull String signs) {
        this.signs = signs;
    }

    @Override public InImpl invoke(@NotNull Targetable target, @NotNull InImpl inProperty) {
        switch (target.in()) {
            case CODE:
                inProperty.setContent(removeCharacters(inProperty.getCode()));
                break;
            case CONTENT:
                inProperty.setContent(removeCharacters(inProperty.getContent()));
                break;
            case COLLECTION:
                throw new UnsupportedOperationException(METHOD_TARGET_NOT_SUPPORTED);
        }

        return inProperty;
    }

    private String removeCharacters(@NotNull String source) {
        for (char sign : signs.toCharArray()) {
            String signString = "" + sign;
            source = source.replace(signString, "");
        }

        return source;
    }

}
