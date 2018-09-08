package net.vego1mar.rules.auxiliary.method;

import net.vego1mar.rules.enumerators.traits.MethodTrait;
import net.vego1mar.rules.enumerators.methods.firstof.FirstOfType;
import org.jetbrains.annotations.NotNull;

public class Method implements Methodable {

    private MethodTrait selectedMethod;
    private FirstOfType firstOfType;
    private String firstOfText;
    private int extractWordPosition;
    private String removeCharactersSigns;

    public Method(MethodTrait method) {
        selectedMethod = method;
        firstOfType = FirstOfType.TAG;
        firstOfText = "";
        extractWordPosition = 1;
        removeCharactersSigns = "";
    }

    @Override public MethodTrait getSelectedMethod() {
        return selectedMethod;
    }

    @Override public void firstOf(FirstOfType type, @NotNull String text) {
        firstOfType = type;
        firstOfText = text;
    }

    public FirstOfType getFirstOfType() {
        return firstOfType;
    }

    public String getFirstOfText() {
        return firstOfText;
    }

    @Override public void extractWord(int position) {
        extractWordPosition = position;
    }

    public int getExtractWordPosition() {
        return extractWordPosition;
    }

    @Override public void removeCharacters(@NotNull String signs) {
        removeCharactersSigns = signs;
    }

    public String getRemoveCharactersSigns() {
        return removeCharactersSigns;
    }
}
