package net.vego1mar.rules;

import java.util.Arrays;
import java.util.List;
import net.vego1mar.rules.auxiliary.inproperty.InInterface;
import net.vego1mar.rules.auxiliary.inproperty.InProperty;
import net.vego1mar.rules.auxiliary.target.Targetable;
import net.vego1mar.rules.enumerators.methods.firstof.FirstOfType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class MethodExecutor implements MethodExecutable {

    private InInterface inProperty;

    public MethodExecutor() {
        inProperty = new InProperty();
    }

    @Contract(pure = true) public InInterface getInProperty() {
        return inProperty;
    }

    @Override public void firstOf(@NotNull Targetable target, @NotNull FirstOfType type, @NotNull String text) {
        switch (target.in()) {
            case HTML:
                firstOfInString(inProperty.getCode(), type, text);
                break;
            case CONTENT:
                firstOfInString(inProperty.getContent(), type, text);
                break;
            case COLLECTION:
                // TODO: provide collection variant
                break;
        }
    }

    private void firstOfInString(@NotNull String source, FirstOfType searchType, String text) {
        int startIndex = source.indexOf(text);

        if (searchType == FirstOfType.STRING) {
            inProperty.setContent(source.substring(startIndex));
            return;
        }

        if (searchType == FirstOfType.TAG) {
            String closingTag = "</" + text.substring(1, text.length() - 1) + '>';
            int endIndex = source.indexOf(closingTag, startIndex);
            inProperty.setContent(source.substring(startIndex, endIndex + closingTag.length()));
        }
    }

    @Override public void extractWord(@NotNull Targetable target, int position) {
        switch (target.in()) {
            case HTML:
                extractWord(inProperty.getCode(), position);
                break;
            case CONTENT:
                extractWord(inProperty.getContent(), position);
                break;
            case COLLECTION:
                extractWord(inProperty.getCollection(), position);
                break;
        }
    }

    private void extractWord(@NotNull String source, int position) {
        String[] words = source.split("\\s", position + 1);
        inProperty.setContent(words[position - 1]);
    }

    private void extractWord(@NotNull List<String> collection, int position) {
        inProperty.setContent(collection.get(position - 1));
    }

    @Override public void splitWords() {
        inProperty.setCollection(Arrays.asList(inProperty.getContent().split("\\s")));
    }

    @Override public void removeCharacters(@NotNull Targetable target, @NotNull String characters) {
        switch (target.in()) {
            case HTML:
                removeCharacters(inProperty.getCode(), characters);
                break;
            case CONTENT:
                removeCharacters(inProperty.getContent(), characters);
                break;
            case COLLECTION:
                // TODO: provide implementation variant
                throw new UnsupportedOperationException();
        }
    }

    private void removeCharacters(@NotNull String source, @NotNull String characters) {
        for (char sign : characters.toCharArray()) {
            source = source.replace(sign, Character.MIN_VALUE);
        }

        inProperty.setContent(source);
    }
}
