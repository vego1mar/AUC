package net.vego1mar.method;

import net.vego1mar.method.enumerators.MethodType;
import net.vego1mar.properties.InProperty;
import net.vego1mar.target.Target;
import net.vego1mar.target.enumerators.In;
import org.jetbrains.annotations.NotNull;

public class ClearContentMethod extends Method {

    public ClearContentMethod() {
        super(MethodType.CLEAR_CONTENT);
    }

    @Override public String toString() {
        return "{ " + MethodType.CLEAR_CONTENT.name() + " }";
    }

    @Override public InProperty invoke(@NotNull Target target, @NotNull InProperty inProperty) {
        if (target.in() == In.CONTENT) {
            inProperty.setContent("");
            return inProperty;
        }

        throw new UnsupportedOperationException(METHOD_TARGET_NOT_SUPPORTED);
    }

}
