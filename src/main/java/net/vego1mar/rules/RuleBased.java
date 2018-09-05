package net.vego1mar.rules;

import net.vego1mar.rules.auxiliary.method.Methodable;
import net.vego1mar.rules.auxiliary.target.Targetable;
import net.vego1mar.rules.enumerators.traits.AfterActionTrait;

public interface RuleBased {

    Targetable getTarget();
    Methodable getMethod();
    AfterActionTrait afterAction();

}
