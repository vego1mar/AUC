package net.vego1mar.auxiliary.properties;

import java.io.Serializable;
import net.vego1mar.enumerators.properties.Platforms;

public class PlatformsProperty implements PlatformsImpl, Serializable {

    private String windows;
    private String android;

    public PlatformsProperty() {
        windows = "";
        android = "";
    }

    @Override public String getItem(Platforms platform) {
        switch (platform) {
            case UNSPECIFIED:
                break;
            case WINDOWS:
                return this.windows;
            case ANDROID:
                return this.android;
        }

        return "";
    }

    @Override public void setItem(Platforms platform, String item) {
        switch (platform) {
            case UNSPECIFIED:
                break;
            case WINDOWS:
                this.windows = item;
                break;
            case ANDROID:
                this.android = item;
        }
    }
}
