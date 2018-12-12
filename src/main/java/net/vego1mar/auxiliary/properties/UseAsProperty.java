package net.vego1mar.auxiliary.properties;

import java.io.Serializable;

public class UseAsProperty implements UseAsImpl, Serializable {

    private static final long serialVersionUID = 2L;
    private String latestAppVersion;
    private String updateDate;
    private LinksImpl links;
    private LinksImpl hashes;

    public UseAsProperty() {
        latestAppVersion = "";
        updateDate = "";
        links = new LinksProperty();
        hashes = new LinksProperty();
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

    @Override public LinksImpl getLinks() {
        return links;
    }

    @Override public LinksImpl getHashes() {
        return hashes;
    }
}
