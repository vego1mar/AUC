package net.vego1mar.auxiliary.properties;

public interface UseAsImpl {

    String getLatestAppVersion();

    void setLatestAppVersion(String latestAppVersion);

    String getUpdateDate();

    void setUpdateDate(String updateDate);

    LinksImpl getLinks();

    LinksImpl getHashes();
}
