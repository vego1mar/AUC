package net.vego1mar.rules;

import net.vego1mar.rules.auxiliary.inproperty.InInterface;
import net.vego1mar.rules.auxiliary.inproperty.InProperty;
import net.vego1mar.rules.auxiliary.target.Targetable;
import net.vego1mar.rules.enumerators.methods.firstof.FirstOfType;
import net.vego1mar.rules.enumerators.traits.FromTrait;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class MethodExecutor implements MethodExecutable {

    private int cursorPosition;
    private InInterface inProperty;

    public MethodExecutor() {
        cursorPosition = 0;
        inProperty = new InProperty();
    }

    @Contract(pure = true) public InInterface getInProperty() {
        return inProperty;
    }

    @Override public void firstOf(@NotNull Targetable target, @NotNull FirstOfType type, @NotNull String text) {
        int fromPosition = (target.from() == FromTrait.START) ? 0 : cursorPosition;

        switch (target.in()) {
            case HTML:
                firstOfInString(inProperty.getCode(), fromPosition, type, text);
                break;
            case CONTENT:
                firstOfInString(inProperty.getContent(), fromPosition, type, text);
                break;
            case COLLECTION:
                // TODO: provide collection variant
                break;
        }
    }

    private void firstOfInString(@NotNull String source, int fromPosition, FirstOfType searchType, String text) {
        int startIndex = source.indexOf(text, fromPosition);
        // TODO: handle cursor position in different class

        if (searchType == FirstOfType.STRING) {
            inProperty.setContent(source.substring(startIndex));
            cursorPosition = startIndex;
            return;
        }

        if (searchType == FirstOfType.TAG) {
            String closingTag = "</" + text.substring(1, text.length() - 1) + '>';
            int endIndex = source.indexOf(closingTag, startIndex);
            inProperty.setContent(source.substring(startIndex, endIndex + closingTag.length()));
            cursorPosition = startIndex;
        }
    }

}
