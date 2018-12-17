package net.vego1mar.auxiliary.properties;

import java.io.Serializable;

public class UseAsProperty implements UseAsImpl, Serializable {

    private PlatformsImpl versions;
    private PlatformsImpl dates;
    private LinksImpl links;
    private LinksImpl hashes;

    public UseAsProperty() {
        versions = new PlatformsProperty();
        dates = new PlatformsProperty();
        links = new LinksProperty();
        hashes = new LinksProperty();
    }

    @Override public PlatformsImpl getVersions() {
        return versions;
    }

    @Override public PlatformsImpl getDates() {
        return dates;
    }

    @Override public LinksImpl getLinks() {
        return links;
    }

    @Override public LinksImpl getHashes() {
        return hashes;
    }
}
