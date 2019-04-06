package net.vego1mar.utils;

import net.vego1mar.method.AppendMethod;
import net.vego1mar.method.ClearContentMethod;
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
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class MethodFactory {

    private MethodFactory() {
        // This should be a utility class.
    }

    @NotNull @Contract(" -> new") public static Methodable createEmpty() {
        return new EmptyMethod();
    }

    @NotNull @Contract(" -> new") public static Methodable createFirstOf() {
        return new FirstOfMethod();
    }

    @NotNull @Contract(" -> new") public static Methodable createExtractWord() {
        return new ExtractWordMethod();
    }

    @NotNull @Contract(" -> new") public static Methodable createRemoveCharacters() {
        return new RemoveCharactersMethod();
    }

    @NotNull @Contract(" -> new") public static Methodable createRemoveStrings() {
        return new RemoveStringsMethod();
    }

    @NotNull @Contract(" -> new") public static Methodable createRetrieveTags() {
        return new RetrieveTagsMethod();
    }

    @NotNull @Contract(" -> new") public static Methodable createPrepend() {
        return new PrependMethod();
    }

    @NotNull @Contract(" -> new") public static Methodable createAppend() {
        return new AppendMethod();
    }

    @NotNull @Contract(" -> new") public static Methodable createGrabUntil() {
        return new GrabUntilMethod();
    }

    @NotNull @Contract(" -> new") public static Methodable createSplitWords() {
        return new SplitWordsMethod();
    }

    @NotNull @Contract(" -> new") public static Methodable createFetchHrefs() {
        return new FetchHrefsMethod();
    }

    @NotNull @Contract(" -> new") public static Methodable createTrim() {
        return new TrimMethod();
    }

    @NotNull @Contract(" -> new") public static Methodable createClearContent() {
        return new ClearContentMethod();
    }

}
