package net.vego1mar.auxiliary.properties;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.vego1mar.enumerators.properties.Platforms;

public class PlatformsProperty implements PlatformsImpl, Serializable {

    private Map<Platforms, String> platforms;

    public PlatformsProperty() {
        platforms = Collections.synchronizedMap(new HashMap<>());

        for (Platforms platform : Platforms.values()) {
            platforms.put(platform, "");
        }
    }

    @Override public String getItem(Platforms platform) {
        if (platforms.containsKey(platform)) {
            return platforms.get(platform);
        }

        return "";
    }

    @Override public void setItem(Platforms platform, String item) {
        if (platform == Platforms.UNSPECIFIED || !platforms.containsKey(platform)) {
            return;
        }

        platforms.put(platform, item);
    }
}
