package net.vego1mar.collector;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Deque;
import java.util.LinkedList;
import net.vego1mar.auxiliary.properties.InProperty;
import net.vego1mar.enumerators.properties.Platforms;
import net.vego1mar.rules.RuleBased;
import net.vego1mar.rules.RulesExecutable;
import net.vego1mar.rules.RulesExecutor;
import net.vego1mar.utils.DownloadHelper;
import net.vego1mar.utils.ReflectionHelper;
import net.vego1mar.utils.XmlRulesSetManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class AppInfoCollector implements Serializable, AppInfoCollectible {

    private static final transient Logger log = Logger.getLogger(AppInfoCollector.class);
    private static final long serialVersionUID = 1L;
    private String htmlCode;
    private RulesExecutable executor;
    private String appName;
    private String sourceURL;
    private String currentAppVersion;

    public AppInfoCollector(@NotNull String appName, @NotNull String sourceURL) {
        htmlCode = "";
        executor = new RulesExecutor(new LinkedList<>(), htmlCode);
        this.appName = appName;
        this.sourceURL = sourceURL;
        currentAppVersion = "";
    }

    private static AppInfoCollectible readObject(@NotNull String fileName) {
        AppInfoCollectible collectorObject = null;

        try {
            try (FileInputStream fileStream = new FileInputStream(fileName)) {
                ObjectInputStream objectStream = new ObjectInputStream(fileStream);
                collectorObject = (AppInfoCollector) objectStream.readObject();
                objectStream.close();
            }
        } catch (IOException | ClassNotFoundException exp) {
            log.error(exp);
        }

        return collectorObject;
    }

    public static AppInfoCollectible load(@NotNull String objTarget, @NotNull String xmlTarget) {
        AppInfoCollectible object = readObject(objTarget);

        try {
            AppInfoCollector collector = (AppInfoCollector) object;
            Field htmlCodeField = ReflectionHelper.getField(AppInfoCollector.class, "htmlCode");
            String htmlCode = (String) htmlCodeField.get(collector);
            Field inProperty = ReflectionHelper.getField(RulesExecutor.class, "inProperty");
            inProperty.set(collector.getExecutor(), new InProperty());
            Field rulesSet = ReflectionHelper.getField(RulesExecutor.class, "rulesSet");
            rulesSet.set(collector.getExecutor(), new LinkedList<>());
            Deque<RuleBased> loadedRulesSet = XmlRulesSetManager.loadSettings(xmlTarget);
            collector.getExecutor().renew(loadedRulesSet, htmlCode);
        } catch (IllegalAccessException exp) {
            log.error(exp);
        }

        log.info(
            "Object state loaded from [objFile='" + objTarget + "', xmlFile='" + xmlTarget + "']");
        return object;
    }

    @Override public void gatherInformation(@NotNull Deque<RuleBased> rulesSet) {
        if (htmlCode.isEmpty()) {
            htmlCode = DownloadHelper.getHtml(sourceURL);
        }

        if (htmlCode.isEmpty()) {
            log.warn("Downloading the webpage resulted with an empty CODE.");
            return;
        }

        try {
            executor.renew(new LinkedList<>(rulesSet), htmlCode);
            executor.execute();
        } catch (UnsupportedOperationException | NullPointerException exp) {
            log.error(exp);
        }
    }

    @Override public boolean isUpdateAvailable() {
        String currentVersion = currentAppVersion.trim();
        String latestVersion = executor.getResults().getVersions().getItem(Platforms.WINDOWS).trim();
        int shorterLength = (currentVersion.length()
            < latestVersion.length()) ? currentVersion.length() : latestVersion.length();

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

    private void writeObject(@NotNull String fileName) {
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
    }

    @Override public void save(@NotNull String objDestination, @NotNull String xmlDestination) {
        writeObject(objDestination);
        XmlRulesSetManager.saveSettings(((RulesExecutor) executor).getRulesSet(), xmlDestination);
        log.info(
            "Object state saved at [objFile='" + objDestination + "', xmlFile='" + xmlDestination
                + "']");
    }

    public RulesExecutable getExecutor() {
        return executor;
    }

    public String getAppName() {
        return appName;
    }

}
