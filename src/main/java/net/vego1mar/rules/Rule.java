package net.vego1mar.rules;

import net.vego1mar.auxiliary.method.Methodable;
import net.vego1mar.auxiliary.target.Targetable;
import org.jetbrains.annotations.NotNull;

public final class Rule implements RuleImpl {

    private Targetable target;
    private Methodable method;

    public Rule(@NotNull Targetable target, @NotNull Methodable method) {
        this.target = target;
        this.method = method;
    }

    @Override public Targetable getTarget() {
        return target;
    }

    @Override public Methodable getMethod() {
        return method;
    }

    @Override public String toString() {
        return "{target=" + target.toString() + "; method=" + method.toString() + '}';
    }

}
