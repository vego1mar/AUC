package net.vego1mar.collector;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import net.vego1mar.auxiliary.properties.PlatformsImpl;
import net.vego1mar.auxiliary.properties.PlatformsProperty;
import net.vego1mar.enumerators.properties.Platforms;
import net.vego1mar.rules.RuleImpl;
import net.vego1mar.rules.RulesExecutable;
import net.vego1mar.rules.RulesExecutor;
import net.vego1mar.utils.DownloadHelper;
import net.vego1mar.utils.ReflectionHelper;
import net.vego1mar.xml.XmlRulesSetReader;
import net.vego1mar.xml.XmlRulesSetWriter;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class AppInfoCollector implements Serializable {

    private static final transient Logger log = Logger.getLogger(AppInfoCollector.class);
    private static final transient String OBJ_FILENAME = "collector";
    private static final transient String OBJ_FILEEXT = ".ser";
    private transient RulesExecutable executor;
    private Map<String, String> executionOrder;
    private String appName;
    private PlatformsImpl appVersions;

    public AppInfoCollector(@NotNull String name, @NotNull Map<String, String> execOrder) {
        executor = new RulesExecutor(new LinkedList<>(), "");
        executionOrder = Collections.synchronizedMap(new LinkedHashMap<>(execOrder));
        appName = name;
        appVersions = new PlatformsProperty();
    }

    private static AppInfoCollector readObject(@NotNull String fileName) {
        AppInfoCollector collector = null;

        try {
            try (FileInputStream fileStream = new FileInputStream(fileName)) {
                ObjectInputStream objectStream = new ObjectInputStream(fileStream);
                collector = (AppInfoCollector) objectStream.readObject();
                objectStream.close();
            }
        } catch (IOException | ClassNotFoundException exp) {
            log.error(exp);
        }

        return collector;
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

    public static AppInfoCollector load(@NotNull String objTarget, @NotNull String xmlTarget) {
        AppInfoCollector collector = readObject(objTarget);
        log.info(ReflectionHelper.getCurrentMethodName() + " : OBJ_FILE" + objTarget + " ; XML_FILE=" + xmlTarget);
        return collector;
    }

    public void save(@NotNull String objDestinationPath, @NotNull String xmlDestinationPath) {
        String objFileName = OBJ_FILENAME + '@' + Integer.toHexString(System.identityHashCode(this)).toUpperCase() + OBJ_FILEEXT;
        String objFullName = Paths.get(objDestinationPath, objFileName).toString();
        writeObject(objFullName);
        log.debug(ReflectionHelper.getCurrentMethodName() + " : OBJ_FILE=" + objFullName);

        String xmlFileName = Paths.get(xmlDestinationPath, appName.replace(' ', '_') + '@').toString();
        String xmlBaseName = xmlFileName + Integer.toHexString(System.identityHashCode(this)).toUpperCase() + '_';
        XmlRulesSetReader xmlReader = new XmlRulesSetReader();
        XmlRulesSetWriter xmlWriter = new XmlRulesSetWriter();
        int i = 0;

        for (String xmlFile : executionOrder.keySet()) {
            i++;
            Deque<RuleImpl> rulesSet = xmlReader.loadSettings(xmlFile);
            String xmlFullName = xmlBaseName.concat(String.valueOf(i));
            xmlWriter.saveSettings(rulesSet, xmlFullName);
            log.debug(ReflectionHelper.getCurrentMethodName() + " : XML_FILE" + xmlFullName);
        }
    }

    public String getAppName() {
        return appName;
    }

    public void gatherInformation() {
        Map<Deque<RuleImpl>, String> preparedExecutionData = fetchExecutionData();

        try {
            for (Map.Entry<Deque<RuleImpl>, String> entry : preparedExecutionData.entrySet()) {
                executor.renew(entry.getKey(), entry.getValue());
                executor.execute();
            }
        } catch (UnsupportedOperationException | NullPointerException exp) {
            log.error(exp);
        }

        updateAppVersions();
    }

    private Map<Deque<RuleImpl>, String> fetchExecutionData() {
        Map<Deque<RuleImpl>, String> preparedExecutionData = Collections.synchronizedMap(new LinkedHashMap<>());
        XmlRulesSetReader xmlReader = new XmlRulesSetReader();

        for (Map.Entry<String, String> entry : executionOrder.entrySet()) {
            Deque<RuleImpl> rulesSet = xmlReader.loadSettings(entry.getKey());
            String htmlCode = DownloadHelper.getHtml(entry.getValue());
            preparedExecutionData.put(rulesSet, htmlCode);
            log.debug("XML_RULES_SET=" + entry.getKey() + "; RULES_NO=" + rulesSet.size());
            log.debug("URL=" + entry.getValue() + "; CODE_CHARS=" + htmlCode.length());
        }

        return preparedExecutionData;
    }

    private void updateAppVersions() {
        PlatformsProperty versions = (PlatformsProperty) executor.getResults().getVersions();

        for (Map.Entry<Platforms, String> entry : versions.getPlatforms().entrySet()) {
            if (isUpdateAvailable(entry.getKey())) {
                appVersions.setItem(entry.getKey(), entry.getValue());
            }
        }
    }

    public boolean isUpdateAvailable(Platforms platform) {
        String currentVersion = appVersions.getItem(platform).trim();
        String latestVersion = executor.getResults().getVersions().getItem(platform).trim();
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

}
