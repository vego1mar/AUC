package net.vego1mar.properties;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.vego1mar.properties.enumerators.LinksID;
import org.jetbrains.annotations.Contract;

public final class LinksProperty implements Serializable {

    private final Map<LinksID, String> links;

    public LinksProperty() {
        links = Collections.synchronizedMap(new HashMap<>());

        for (LinksID value : LinksID.values()) {
            links.put(value, "");
        }
    }

    public void setItem(LinksID id, String item) {
        if (id == LinksID.NO_LINK || !links.containsKey(id)) {
            return;
        }

        links.put(id, item);
    }

    public String getItem(LinksID id) {
        if (links.containsKey(id)) {
            return links.get(id);
        }

        return "";
    }

    @Contract(pure = true) public Map<LinksID, String> getLinks() {
        return links;
    }
}