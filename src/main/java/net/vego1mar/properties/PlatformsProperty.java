package net.vego1mar.properties;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.vego1mar.properties.enumerators.Platforms;
import org.jetbrains.annotations.Contract;

public final class PlatformsProperty implements Serializable {

    private final Map<Platforms, String> platforms;

    public PlatformsProperty() {
        platforms = Collections.synchronizedMap(new HashMap<>());

        for (Platforms platform : Platforms.values()) {
            platforms.put(platform, "");
        }
    }

    public String getItem(Platforms platform) {
        if (platforms.containsKey(platform)) {
            return platforms.get(platform);
        }

        return "";
    }

    public void setItem(Platforms platform, String item) {
        if (platform == Platforms.UNSPECIFIED || !platforms.containsKey(platform)) {
            return;
        }

        platforms.put(platform, item);
    }

    @Contract(pure = true) public Map<Platforms, String> getPlatforms() {
        return platforms;
    }
}
