package net.vego1mar.method;

import net.vego1mar.method.enumerators.MethodType;
import net.vego1mar.properties.InProperty;
import net.vego1mar.target.Target;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class EmptyMethod extends Method {

    public EmptyMethod() {
        super(MethodType.EMPTY);
    }

    @NotNull @Contract(pure = true) @Override public String toString() {
        return "{ " + MethodType.EMPTY.name() + " }";
    }

    @Contract(value = "_, _ -> param2", pure = true) @Override public InProperty invoke(@NotNull Target target, @NotNull InProperty inProperty) {
        return inProperty;
    }

}
