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
    private String retrieveAllTagsTag;
    private String prependText;
    private Character grabUntilCharStop;

    public Method(MethodTrait method) {
        selectedMethod = method;
        firstOfType = FirstOfType.TAG;
        firstOfText = "";
        extractWordPosition = 1;
        removeCharactersSigns = "";
        retrieveAllTagsTag = "";
        prependText = "";
        grabUntilCharStop = '\0';
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

    @Override public void retrieveAllTags(@NotNull String tag) {
        retrieveAllTagsTag = tag;
    }

    public String getRetrieveAllTagsTag() {
        return retrieveAllTagsTag;
    }

    @Override public void prepend(@NotNull String text) {
        prependText = text;
    }

    public String getPrependText() {
        return prependText;
    }

    @Override public void grabUntil(Character charStop) {
        grabUntilCharStop = charStop;
    }

    public Character getGrabUntilCharStop() {
        return grabUntilCharStop;
    }

    @Override public String toString() {
        String prepender = '{' + selectedMethod.name() + "; ";

        switch (selectedMethod) {
            case FIRST_OF:
                return prepender + "type=" + firstOfType.name() + "; text='" + firstOfText + "'}";
            case EXTRACT_WORD:
                return prepender + "extractWordPosition=" + extractWordPosition + '}';
            case REMOVE_CHARACTERS:
                return prepender + "removeCharactersSigns='" + removeCharactersSigns + "'}";
            case RETRIEVE_ALL_TAGS:
                return prepender + "retrieveAllTagsTag='" + retrieveAllTagsTag + "'}";
            case PREPEND:
                return prepender + "prependText='" + prependText + "'}";
            case SPLIT_WORDS:
            case FETCH_HREFS:
                return prepender.substring(0, prepender.length() - 2) + '}';
            case GRAB_UNTIL:
                return prepender + "charStop='" + grabUntilCharStop + "'}";
        }

        return getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this));
    }
}
