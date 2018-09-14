package net.vego1mar.collector;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import net.vego1mar.rules.RuleBased;
import net.vego1mar.rules.RulesExecutor;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsImpl;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsProperty;
import net.vego1mar.utils.WebPageDownloader;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class AppInfoCollector implements Serializable {

    private static final transient Logger log = Logger.getLogger(AppInfoCollector.class);
    private static final long serialVersionUID = 1L;
    private transient String htmlCode;
    private transient Deque<RuleBased> rulesSet;
    private String appName;
    private String sourceURL;
    private UseAsImpl useAsProperty;
    private String currentAppVersion;

    public AppInfoCollector(@NotNull String appName, @NotNull String sourceURL) {
        htmlCode = "";
        rulesSet = new LinkedList<>();
        this.appName = appName;
        this.sourceURL = sourceURL;
        useAsProperty = new UseAsProperty();
        currentAppVersion = "";
    }

    public static AppInfoCollector readObject(@NotNull String fileName) {
        AppInfoCollector collectorObject = null;

        try {
            try (FileInputStream fileStream = new FileInputStream(fileName)) {
                ObjectInputStream objectStream = new ObjectInputStream(fileStream);
                collectorObject = (AppInfoCollector) objectStream.readObject();
                objectStream.close();
            }
        } catch (IOException | ClassNotFoundException exp) {
            log.error(exp);
        }

        log.info("Object '" + collectorObject + "' deserialized from '" + fileName + "'.");
        return collectorObject;
    }

    public void gatherInformation() {
        if (htmlCode.isEmpty()) {
            htmlCode = WebPageDownloader.getHtml(sourceURL);
        }

        if (htmlCode.isEmpty()) {
            log.warn("Failed while downloading the HTML content.");
            return;
        }

        RulesExecutor executor = new RulesExecutor(rulesSet, htmlCode);

        try {
            executor.execute();
            useAsProperty = executor.getResults();
        } catch (UnsupportedOperationException | NullPointerException exp) {
            log.error(exp);
        }
    }

    public boolean isUpdateAvailable() {
        String currentVersion = currentAppVersion.trim();
        String latestVersion = useAsProperty.getLatestAppVersion().trim();
        int shorterLength = (currentVersion.length() < latestVersion.length()) ? currentVersion.length() : latestVersion.length();

        if (currentVersion.equals(latestVersion)) {
            return false;
        }

        for (int i = 0; i < shorterLength; i++) {
            if (currentVersion.toCharArray()[i] > latestVersion.toCharArray()[i]) {
                return false;
            }
        }

        return true;
    }

    public void setCurrentAppVersion(@NotNull String currentAppVersion) {
        this.currentAppVersion = currentAppVersion;
    }

    public void setRulesSet(@NotNull Deque<RuleBased> rulesSet) {
        this.rulesSet = rulesSet;
    }

    public void writeObject(@NotNull String fileName) {
        try {
            try (FileOutputStream fileStream = new FileOutputStream(fileName)) {
                ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
                objectStream.writeObject(this);
                objectStream.flush();
                objectStream.close();
            }
        } catch (IOException exp) {
            log.error(exp);
        }

        log.info("Object '" + this + "' serialized and saved under '" + fileName + "'.");
    }

}
