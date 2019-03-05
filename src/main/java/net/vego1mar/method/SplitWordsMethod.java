package net.vego1mar.method;

import java.util.Arrays;
import java.util.List;
import net.vego1mar.method.enumerators.MethodType;
import net.vego1mar.properties.InProperty;
import net.vego1mar.target.Target;
import org.jetbrains.annotations.NotNull;

public class SplitWordsMethod extends Method {

    public SplitWordsMethod() {
        super(MethodType.SPLIT_WORDS);
    }

    @Override public String toString() {
        return "{ " + MethodType.SPLIT_WORDS.name() + " }";
    }

    @Override public InProperty invoke(@NotNull Target target, @NotNull InProperty inProperty) {
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
