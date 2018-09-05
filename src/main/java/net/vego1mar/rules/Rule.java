package net.vego1mar.rules;

import net.vego1mar.rules.auxiliary.method.Methodable;
import net.vego1mar.rules.auxiliary.target.Targetable;
import net.vego1mar.rules.enumerators.traits.AfterActionTrait;
import org.jetbrains.annotations.NotNull;

public final class Rule implements RuleBased {

    private Targetable target;
    private Methodable method;
    private AfterActionTrait afterAction;

    public Rule(@NotNull Targetable target, @NotNull Methodable method, @NotNull AfterActionTrait afterAction) {
        this.target = target;
        this.method = method;
        this.afterAction = afterAction;
    }

    @Override public Targetable getTarget() {
        return target;
    }

    @Override public Methodable getMethod() {
        return method;
    }

    @Override public AfterActionTrait afterAction() {
        return afterAction;
    }
}
