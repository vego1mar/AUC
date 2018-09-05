package net.vego1mar.rules.auxiliary.useasproperty;

public class UseAsProperty implements UseAsInterface {

    private String latestAppVersion;
    private String updateDate;
    private String windowsX86packageURL;
    private String windowsX64packageURL;

    public UseAsProperty() {
        latestAppVersion = "";
        updateDate = "";
        windowsX86packageURL = "";
        windowsX64packageURL = "";
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
}
