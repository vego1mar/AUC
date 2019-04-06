package net.vego1mar.method;

import java.util.List;
import net.vego1mar.method.enumerators.MethodType;
import net.vego1mar.properties.InProperty;
import net.vego1mar.target.Target;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class AppendMethod extends Method {

    private String text;

    public AppendMethod() {
        super(MethodType.APPEND);
        text = "";
    }

    public String getText() {
        return text;
    }

    public void setText(@NotNull String text) {
        this.text = text;
    }

    @Override public String toString() {
        return "{ " + MethodType.APPEND.name() + "; text='" + text + "' }";
    }

    @Override public InProperty invoke(@NotNull Target target, @NotNull InProperty inProperty) {
        switch (target.in()) {
            case CONTENT:
                inProperty.setContent(append(inProperty.getContent()));
                break;
            case COLLECTION:
                inProperty.setCollection(append(inProperty.getCollection()));
                break;
            default:
                throw new UnsupportedOperationException(METHOD_TARGET_NOT_SUPPORTED);
        }

        return inProperty;
    }

    @NotNull @Contract(pure = true) private String append(@NotNull String source) {
        return source + text;
    }

    @Contract("_ -> param1") private List<String> append(@NotNull List<String> collection) {
        for (int i = 0; i < collection.size(); i++) {
            collection.set(i, append(collection.get(i)));
        }

        return collection;
    }

}
