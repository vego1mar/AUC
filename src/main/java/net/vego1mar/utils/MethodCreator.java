package net.vego1mar.utils;

import net.vego1mar.auxiliary.method.EmptyMethod;
import net.vego1mar.auxiliary.method.ExtractWordMethod;
import net.vego1mar.auxiliary.method.FetchHrefsMethod;
import net.vego1mar.auxiliary.method.FirstOfMethod;
import net.vego1mar.auxiliary.method.GrabUntilMethod;
import net.vego1mar.auxiliary.method.Methodable;
import net.vego1mar.auxiliary.method.PrependMethod;
import net.vego1mar.auxiliary.method.RemoveCharactersMethod;
import net.vego1mar.auxiliary.method.RetrieveTagsMethod;
import net.vego1mar.auxiliary.method.SplitWordsMethod;
import net.vego1mar.auxiliary.method.TrimMethod;
import net.vego1mar.enumerators.traits.MethodType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class MethodCreator {

    private MethodCreator() {
        // This should be a utility class.
    }

    @NotNull @Contract("_ -> new") public static Methodable getMethod(@NotNull MethodType type) {
        switch (type) {
            case EMPTY:
                break;
            case FIRST_OF:
                return new FirstOfMethod();
            case EXTRACT_WORD:
                return new ExtractWordMethod();
            case REMOVE_CHARACTERS:
                return new RemoveCharactersMethod();
            case RETRIEVE_TAGS:
                return new RetrieveTagsMethod();
            case PREPEND:
                return new PrependMethod();
            case GRAB_UNTIL:
                return new GrabUntilMethod();
            case SPLIT_WORDS:
                return new SplitWordsMethod();
            case FETCH_HREFS:
                return new FetchHrefsMethod();
            case TRIM:
                return new TrimMethod();
        }

        return new EmptyMethod();
    }

}
