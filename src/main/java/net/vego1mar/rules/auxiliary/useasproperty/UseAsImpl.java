package net.vego1mar.rules.auxiliary.useasproperty;

public interface UseAsImpl {

    String getLatestAppVersion();

    void setLatestAppVersion(String latestAppVersion);

    String getUpdateDate();

    void setUpdateDate(String updateDate);

    String getWindowsX86packageURL();

    void setWindowsX86packageURL(String windowsX86packageURL);

    String getWindowsX64packageURL();

    void setWindowsX64packageURL(String windowsX64packageURL);

    String getWindowsX86hash();

    void setWindowsX86hash(String windowsX86hash);

    String getWindowsX64hash();

    void setWindowsX64hash(String windowsX64hash);

}
