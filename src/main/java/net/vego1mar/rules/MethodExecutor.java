package net.vego1mar.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
                throw new UnsupportedOperationException();
        }
    }

    private void firstOfInString(@NotNull String source, FirstOfType searchType, String text) {
        text = '<' + text;
        int startIndex = source.indexOf(text);

        if (searchType == FirstOfType.STRING) {
            inProperty.setContent(source.substring(startIndex));
            return;
        }

        if (searchType == FirstOfType.TAG) {
            String closingTag = "</" + text.substring(1) + '>';
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

    @Override public void retrieveAllTags(@NotNull String tag) {
        String content = inProperty.getContent();
        String openingTag = '<' + tag;
        String closingTag = "</" + tag + '>';
        List<String> listOfTags = new LinkedList<>();
        int startIndex = 0;
        int endIndex = 0;

        while (!content.isEmpty()) {
            startIndex = content.indexOf(openingTag);
            endIndex = content.indexOf(closingTag) + tag.length() + 3;

            if (startIndex < 0 || endIndex < 0) {
                break;
            }

            listOfTags.add(content.substring(startIndex, endIndex));
            content = content.substring(endIndex);
        }

        inProperty.setCollection(listOfTags);
    }

    @Override public void fetchHrefs(@NotNull Targetable target) {
        switch (target.in()) {
            case HTML:
            case CONTENT:
                // TODO: provide implementation variants
                throw new UnsupportedOperationException();
            case COLLECTION:
                fetchHrefs(inProperty.getCollection());
                break;
        }
    }

    private void fetchHrefs(@NotNull List<String> collection) {
        List<String> hrefs = new ArrayList<>();

        for (String item : collection) {
            int startIndex = item.indexOf("href=") + 6;
            int endIndex = item.indexOf('\"', startIndex);

            if (startIndex >= 0 && endIndex >= 0) {
                hrefs.add(item.substring(startIndex, endIndex));
            }
        }

        inProperty.setCollection(hrefs);
    }

    @Override public void prepend(@NotNull Targetable target, @NotNull String text) {
        switch (target.in()) {
            case HTML:
            case CONTENT:
                // TODO: provide implementation variants
                throw new UnsupportedOperationException();
            case COLLECTION:
                prepend(inProperty.getCollection(), text);
        }
    }

    private void prepend(@NotNull List<String> collection, @NotNull String text) {
        for (int i = 0; i < collection.size(); i++) {
            collection.set(i, text + collection.get(i));
        }
    }

}
