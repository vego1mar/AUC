package net.vego1mar.rules.auxiliary.method;

import net.vego1mar.rules.auxiliary.target.Target;
import net.vego1mar.rules.auxiliary.target.Targetable;
import net.vego1mar.rules.enumerators.traits.FromTrait;
import net.vego1mar.rules.enumerators.traits.InTrait;
import net.vego1mar.rules.enumerators.traits.MethodTrait;
import net.vego1mar.rules.enumerators.methods.firstof.FirstOfType;
import net.vego1mar.rules.enumerators.traits.UseAsTrait;

public class Method implements Methodable {

    private MethodTrait selectedMethod;

    private Targetable firstOfTarget;
    private FirstOfType firstOfType;
    private String firstOfText;

    private Targetable extractWordTarget;
    private int extractWordPosition;

    public Method(MethodTrait method) {
        selectedMethod = method;
        firstOfTarget = new Target(FromTrait.START, InTrait.HTML, UseAsTrait.IGNORE);
        firstOfType = FirstOfType.TAG;
        firstOfText = "";
        extractWordTarget = new Target(FromTrait.START, InTrait.CONTENT, UseAsTrait.IGNORE);
        extractWordPosition = 1;
    }

    @Override public MethodTrait getSelectedMethod() {
        return selectedMethod;
    }

    @Override public void setSelectedMethod(MethodTrait method) {
        selectedMethod = method;
    }

    @Override public void firstOf(Targetable target, FirstOfType type, String text) {
        firstOfTarget = target;
        firstOfType = type;
        firstOfText = text;
    }

    public FirstOfType getFirstOfType() {
        return firstOfType;
    }

    public String getFirstOfText() {
        return firstOfText;
    }

    @Override public void extractWord(Targetable target, int position) {
        extractWordTarget = target;
        extractWordPosition = position;
    }
}
