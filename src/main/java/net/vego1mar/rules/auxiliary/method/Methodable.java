package net.vego1mar.rules.auxiliary.method;

import net.vego1mar.rules.auxiliary.target.Targetable;
import net.vego1mar.rules.enumerators.methods.firstof.FirstOfType;
import net.vego1mar.rules.enumerators.traits.MethodTrait;

public interface Methodable {

    MethodTrait getSelectedMethod();

    void setSelectedMethod(MethodTrait method);

    void firstOf(Targetable target, FirstOfType type, String text);

    void extractWord(Targetable target, int position);
}
