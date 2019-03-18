package net.vego1mar.utils;

import net.vego1mar.method.AppendMethod;
import net.vego1mar.method.EmptyMethod;
import net.vego1mar.method.ExtractWordMethod;
import net.vego1mar.method.FetchHrefsMethod;
import net.vego1mar.method.FirstOfMethod;
import net.vego1mar.method.GrabUntilMethod;
import net.vego1mar.method.Methodable;
import net.vego1mar.method.PrependMethod;
import net.vego1mar.method.RemoveCharactersMethod;
import net.vego1mar.method.RemoveStringsMethod;
import net.vego1mar.method.RetrieveTagsMethod;
import net.vego1mar.method.SplitWordsMethod;
import net.vego1mar.method.TrimMethod;
import net.vego1mar.method.enumerators.MethodType;
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
            case REMOVE_STRINGS:
                return new RemoveStringsMethod();
            case RETRIEVE_TAGS:
                return new RetrieveTagsMethod();
            case PREPEND:
                return new PrependMethod();
            case APPEND:
                return new AppendMethod();
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
