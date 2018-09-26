package net.vego1mar.rules;

import net.vego1mar.auxiliary.method.Methodable;
import net.vego1mar.auxiliary.target.Targetable;

public interface RuleBased {

    Targetable getTarget();

    Methodable getMethod();

    String toString();

}
