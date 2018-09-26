package net.vego1mar.auxiliary.method;

import net.vego1mar.auxiliary.properties.InImpl;
import net.vego1mar.auxiliary.target.Targetable;
import net.vego1mar.enumerators.traits.MethodType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class EmptyMethod extends Method {

    public EmptyMethod() {
        super(MethodType.EMPTY);
    }

    @NotNull @Contract(pure = true) @Override public String toString() {
        return "{ " + MethodType.EMPTY.name() + " }";
    }

    @Contract(value = "_, _ -> param2", pure = true) @Override public InImpl invoke(@NotNull Targetable target, @NotNull InImpl inProperty) {
        return inProperty;
    }

}
