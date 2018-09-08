package net.vego1mar.rules;

import net.vego1mar.rules.auxiliary.method.Methodable;
import net.vego1mar.rules.auxiliary.target.Targetable;
import org.jetbrains.annotations.NotNull;

public final class Rule implements RuleBased {

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

    // TODO: override toString() for logging purposes
}
