package net.vego1mar.rules;

import net.vego1mar.method.Methodable;
import net.vego1mar.target.Target;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class Rule {

    private Target target;
    private Methodable method;

    public Rule(@NotNull Target target, @NotNull Methodable method) {
        this.target = target;
        this.method = method;
    }

    @Contract(pure = true) public Target getTarget() {
        return target;
    }

    @Contract(pure = true) public Methodable getMethod() {
        return method;
    }

    @NotNull @Override public String toString() {
        return "{target=" + target.toString() + "; method=" + method.toString() + '}';
    }

}
