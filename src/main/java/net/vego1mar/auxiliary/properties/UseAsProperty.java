package net.vego1mar.auxiliary.properties;

import java.io.Serializable;

public class UseAsProperty implements UseAsImpl, Serializable {

    private static final long serialVersionUID = 2L;
    private String latestAppVersion;
    private String updateDate;
    private String windowsX86packageURL;
    private String windowsX64packageURL;
    private String windowsX86hash;
    private String windowsX64hash;

    public UseAsProperty() {
        latestAppVersion = "";
        updateDate = "";
        windowsX86packageURL = "";
        windowsX64packageURL = "";
        windowsX86hash = "";
        windowsX64hash = "";
    }

    @Override public String getLatestAppVersion() {
        return latestAppVersion;
    }

    @Override public void setLatestAppVersion(String latestAppVersion) {
        this.latestAppVersion = latestAppVersion;
    }

    @Override public String getUpdateDate() {
        return updateDate;
    }

    @Override public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override public String getWindowsX86packageURL() {
        return windowsX86packageURL;
    }

    @Override public void setWindowsX86packageURL(String windowsX86packageURL) {
        this.windowsX86packageURL = windowsX86packageURL;
    }

    @Override public String getWindowsX64packageURL() {
        return windowsX64packageURL;
    }

    @Override public void setWindowsX64packageURL(String windowsX64packageURL) {
        this.windowsX64packageURL = windowsX64packageURL;
    }

    @Override public String getWindowsX86hash() {
        return windowsX86hash;
    }

    @Override public void setWindowsX86hash(String windowsX86hash) {
        this.windowsX86hash = windowsX86hash;
    }

    @Override public String getWindowsX64hash() {
        return windowsX64hash;
    }

    @Override public void setWindowsX64hash(String windowsX64hash) {
        this.windowsX64hash = windowsX64hash;
    }

}
