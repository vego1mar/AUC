package net.vego1mar.auxiliary.method;

import java.util.List;
import net.vego1mar.auxiliary.properties.InImpl;
import net.vego1mar.auxiliary.target.Targetable;
import net.vego1mar.enumerators.traits.MethodType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ExtractWordMethod extends Method {

    private int position;

    public ExtractWordMethod() {
        super(MethodType.EXTRACT_WORD);
        position = 1;
    }

    @Override public String toString() {
        return "{ " + MethodType.EXTRACT_WORD.name() + "; position=" + position + " }";
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        if (position > 0) {
            this.position = position;
        }
    }

    @Override public InImpl invoke(@NotNull Targetable target, @NotNull InImpl inProperty) {
        switch (target.in()) {
            case CODE:
                inProperty.setContent(extractWord(inProperty.getCode()));
                break;
            case CONTENT:
                inProperty.setContent(extractWord(inProperty.getContent()));
                break;
            case COLLECTION:
                inProperty.setContent(extractWord(inProperty.getCollection()));
                break;
        }

        return inProperty;
    }

    @Contract(pure = true) private String extractWord(@NotNull String source) {
        String[] words = source.split("\\s", position + 1);
        return words[position - 1];
    }

    @Contract(pure = true) private String extractWord(@NotNull List<String> collection) {
        return collection.get(position - 1);
    }

}
