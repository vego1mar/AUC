package net.vego1mar.method;

import java.util.List;
import net.vego1mar.properties.InImpl;
import net.vego1mar.target.Targetable;
import net.vego1mar.enumerators.traits.MethodType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class PrependMethod extends Method {

    private String text;

    public PrependMethod() {
        super(MethodType.PREPEND);
        text = "";
    }

    @Override public String toString() {
        return "{ " + MethodType.PREPEND.name() + "; text='" + text + "' }";
    }

    public String getText() {
        return text;
    }

    public void setText(@NotNull String text) {
        this.text = text;
    }

    @Override public InImpl invoke(@NotNull Targetable target, @NotNull InImpl inProperty) {
        switch (target.in()) {
            case CODE:
                inProperty.setContent(prepend(inProperty.getCode()));
                break;
            case CONTENT:
                inProperty.setContent(prepend(inProperty.getContent()));
                break;
            case COLLECTION:
                inProperty.setCollection(prepend(inProperty.getCollection()));
        }

        return inProperty;
    }

    @NotNull @Contract(pure = true) private String prepend(@NotNull String source) {
        return text + source;
    }

    @Contract("_ -> param1") private List<String> prepend(@NotNull List<String> collection) {
        for (int i = 0; i < collection.size(); i++) {
            collection.set(i, text + collection.get(i));
        }

        return collection;
    }

}
