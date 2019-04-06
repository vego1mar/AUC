package net.vego1mar.method;

import java.util.ArrayList;
import java.util.List;
import net.vego1mar.method.enumerators.MethodType;
import net.vego1mar.properties.InProperty;
import net.vego1mar.target.Target;
import net.vego1mar.method.enumerators.TrimSide;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class TrimMethod extends Method {

    private TrimSide side;
    private int numberOf;

    public TrimMethod() {
        super(MethodType.TRIM);
        side = TrimSide.LEFT;
        numberOf = 0;
    }

    public TrimSide getSide() {
        return side;
    }

    public void setSide(TrimSide side) {
        this.side = side;
    }

    public int getNumberOf() {
        return numberOf;
    }

    public void setNumberOf(int numberOf) {
        if (numberOf >= 0) {
            this.numberOf = numberOf;
        }
    }

    @Override public String toString() {
        return "{ " + MethodType.TRIM.name() + "; side=" + side.name() + "; numberOf=" + numberOf + " }";
    }

    @Override public InProperty invoke(@NotNull Target target, @NotNull InProperty inProperty) {
        switch (target.in()) {
            case CODE:
                throw new UnsupportedOperationException(METHOD_TARGET_NOT_SUPPORTED);
            case CONTENT:
                inProperty.setContent(trim(inProperty.getContent()));
                break;
            case COLLECTION:
                inProperty.setCollection(trim(inProperty.getCollection()));
                break;
        }

        return inProperty;
    }

    private String trim(@NotNull String source) {
        if (numberOf >= source.length()) {
            return "";
        }

        if (side == TrimSide.LEFT) {
            return trimFromLeft(source);
        }

        return trimFromRight(source);
    }

    @NotNull @Contract(pure = true) private String trimFromLeft(@NotNull String source) {
        return source.substring(numberOf);
    }

    @NotNull private String trimFromRight(@NotNull String source) {
        return source.substring(0, source.length() - numberOf);
    }

    private List<String> trim(@NotNull List<String> collection) {
        List<String> result = new ArrayList<>();

        for (String item : collection) {
            result.add(trim(item));
        }

        return result;
    }

}
