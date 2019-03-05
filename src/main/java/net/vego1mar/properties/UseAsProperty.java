package net.vego1mar.properties;

public class UseAsProperty {

    private PlatformsProperty versions;
    private PlatformsProperty dates;
    private LinksProperty links;
    private LinksProperty hashes;

    public UseAsProperty() {
        versions = new PlatformsProperty();
        dates = new PlatformsProperty();
        links = new LinksProperty();
        hashes = new LinksProperty();
    }

    public PlatformsProperty getVersions() {
        return versions;
    }

    public PlatformsProperty getDates() {
        return dates;
    }

    public LinksProperty getLinks() {
        return links;
    }

    public LinksProperty getHashes() {
        return hashes;
    }
}
