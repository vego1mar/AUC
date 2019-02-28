package net.vego1mar.method;

import java.util.ArrayList;
import java.util.List;
import net.vego1mar.properties.InImpl;
import net.vego1mar.target.Targetable;
import net.vego1mar.enumerators.traits.MethodType;
import org.jetbrains.annotations.NotNull;

public class FetchHrefsMethod extends Method {

    public FetchHrefsMethod() {
        super(MethodType.FETCH_HREFS);
    }

    @Override public String toString() {
        return "{ " + MethodType.FETCH_HREFS.name() + " }";
    }

    @Override public InImpl invoke(@NotNull Targetable target, @NotNull InImpl inProperty) {
        switch (target.in()) {
            case CODE:
                throw new UnsupportedOperationException(METHOD_TARGET_NOT_SUPPORTED);
            case CONTENT:
                inProperty.setContent(fetchHrefs(inProperty.getContent()));
                break;
            case COLLECTION:
                inProperty.setCollection(fetchHrefs(inProperty.getCollection()));
                break;
        }

        return inProperty;
    }

    @NotNull private String fetchHrefs(@NotNull String source) {
        int startIndex = source.indexOf("href=") + 6;
        int endIndex = source.indexOf('\"', startIndex);

        if (startIndex >= 0 && endIndex >= 0) {
            return source.substring(startIndex, endIndex);
        }

        return "";
    }

    private List<String> fetchHrefs(@NotNull List<String> collection) {
        List<String> hrefs = new ArrayList<>();

        for (String item : collection) {
            int startIndex = item.indexOf("href=") + 6;
            int endIndex = item.indexOf('\"', startIndex);

            if (startIndex >= 0 && endIndex >= 0) {
                hrefs.add(item.substring(startIndex, endIndex));
            }
        }

        return hrefs;
    }

}
