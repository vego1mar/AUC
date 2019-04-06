package net.vego1mar.method;

import net.vego1mar.method.enumerators.MethodType;
import net.vego1mar.properties.InProperty;
import net.vego1mar.target.Target;
import net.vego1mar.target.enumerators.In;
import org.jetbrains.annotations.NotNull;

public class RemoveStringsMethod extends Method {

    private String string;

    public RemoveStringsMethod() {
        super(MethodType.REMOVE_STRINGS);
        string = "";
    }

    public String getString() {
        return string;
    }

    public void setString(@NotNull String string) {
        this.string = string;
    }

    @Override public String toString() {
        return "{ " + MethodType.REMOVE_STRINGS.name() + "; string='" + string + "' }";
    }

    @Override public InProperty invoke(@NotNull Target target, @NotNull InProperty inProperty) {
        if (target.in() == In.CONTENT) {
            inProperty.setContent(removeString(inProperty.getContent()));
            return inProperty;
        }

        throw new UnsupportedOperationException(METHOD_TARGET_NOT_SUPPORTED);
    }

    private String removeString(@NotNull String source) {
        if (source.contains(string)) {
            int indexOf = source.indexOf(string);
            String beforeString = source.substring(0, indexOf);
            String afterString = source.substring(indexOf + string.length());
            return beforeString.concat(afterString);
        }

        return source;
    }

}
