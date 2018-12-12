package net.vego1mar.auxiliary.method;

import java.util.Arrays;
import java.util.List;
import net.vego1mar.auxiliary.properties.InImpl;
import net.vego1mar.auxiliary.target.Targetable;
import net.vego1mar.enumerators.traits.MethodType;
import org.jetbrains.annotations.NotNull;

public class SplitWordsMethod extends Method {

    public SplitWordsMethod() {
        super(MethodType.SPLIT_WORDS);
    }

    @Override public String toString() {
        return "{ " + MethodType.SPLIT_WORDS.name() + " }";
    }

    @Override public InImpl invoke(@NotNull Targetable target, @NotNull InImpl inProperty) {
        switch (target.in()) {
            case CODE:
            case COLLECTION:
                throw new UnsupportedOperationException(METHOD_TARGET_NOT_SUPPORTED);
            case CONTENT:
                inProperty.setCollection(splitWords(inProperty.getContent()));
                break;
        }

        return inProperty;
    }

    @NotNull private List<String> splitWords(@NotNull String content) {
        return Arrays.asList(content.split("\\s"));
    }

}
