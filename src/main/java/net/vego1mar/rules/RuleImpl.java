package net.vego1mar.rules;

import net.vego1mar.method.Methodable;
import net.vego1mar.target.Targetable;

public interface RuleImpl {

    Targetable getTarget();

    Methodable getMethod();

    String toString();

}
