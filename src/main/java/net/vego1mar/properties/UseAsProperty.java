package net.vego1mar.properties;

import java.io.Serializable;
import org.jetbrains.annotations.Contract;

public final class UseAsProperty implements Serializable {

    private final PlatformsProperty versions;
    private final PlatformsProperty dates;
    private final LinksProperty links;
    private final LinksProperty hashes;

    public UseAsProperty() {
        versions = new PlatformsProperty();
        dates = new PlatformsProperty();
        links = new LinksProperty();
        hashes = new LinksProperty();
    }

    @Contract(pure = true) public PlatformsProperty getVersions() {
        return versions;
    }

    @Contract(pure = true) public PlatformsProperty getDates() {
        return dates;
    }

    @Contract(pure = true) public LinksProperty getLinks() {
        return links;
    }

    @Contract(pure = true) public LinksProperty getHashes() {
        return hashes;
    }
}
