package net.vego1mar.collector;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.vego1mar.properties.PlatformsProperty;
import net.vego1mar.properties.UseAsProperty;
import net.vego1mar.properties.enumerators.LinksID;
import net.vego1mar.properties.enumerators.Platforms;
import net.vego1mar.rules.Rule;
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
    private transient RulesExecutor executor;
    private Map<String, String> executionOrder;
    private String appName;
    private PlatformsProperty appVersions;
    private String serialFileName;

    public AppInfoCollector(@NotNull String name, @NotNull Map<String, String> execOrder) {
        initializeTransientFields();
        executionOrder = Collections.synchronizedMap(new LinkedHashMap<>(execOrder));
        appName = name;
        appVersions = new PlatformsProperty();
        serialFileName = "";
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

    public static AppInfoCollector load(@NotNull String objTarget) {
        AppInfoCollector collector = readObject(objTarget);
        collector.initializeTransientFields();
        log.info(ReflectionHelper.getCurrentMethodName() + " : OBJ_FILE" + objTarget);
        return collector;
    }

    public void save(@NotNull String objDestinationPath, @NotNull String xmlDestinationPath) {
        String xmlFileName = Paths.get(xmlDestinationPath, appName.replace(' ', '_') + '@').toString();
        String xmlBaseName = xmlFileName + Integer.toHexString(System.identityHashCode(this)).toUpperCase() + '_';
        XmlRulesSetReader xmlReader = new XmlRulesSetReader();
        XmlRulesSetWriter xmlWriter = new XmlRulesSetWriter();
        int i = 0;
        List<String> xmlFileNames = new ArrayList<>(executionOrder.keySet());
        List<String> urlNames = new ArrayList<>(executionOrder.values());
        executionOrder.clear();

        for (int j = 0; j < xmlFileNames.size(); j++) {
            i++;
            Deque<Rule> rulesSet = xmlReader.loadSettings(xmlFileNames.get(j));
            String xmlFullName = xmlBaseName.concat(String.valueOf(i)).concat(".xml");
            xmlWriter.saveSettings(rulesSet, xmlFullName);
            executionOrder.put(xmlFullName, urlNames.get(j));
            log.debug(ReflectionHelper.getCurrentMethodName() + " : XML_FILE" + xmlFullName);
        }

        String objFileName = OBJ_FILENAME + '@' + Integer.toHexString(System.identityHashCode(this)).toUpperCase() + OBJ_FILEEXT;
        String objFullName = Paths.get(objDestinationPath, objFileName).toString();
        serialFileName = objFullName;
        writeObject(objFullName);
        log.debug(ReflectionHelper.getCurrentMethodName() + " : OBJ_FILE=" + objFullName);
    }

    private void initializeTransientFields() {
        executor = new RulesExecutor(new LinkedList<>(), "");
    }

    public String getAppName() {
        return appName;
    }

    public String getSerialFileName() {
        return serialFileName;
    }

    public List<String> getXMLFileNames() {
        return Collections.synchronizedList(new ArrayList<>(executionOrder.keySet()));
    }

    public List<String> getURLNames() {
        return Collections.synchronizedList(new ArrayList<>(executionOrder.values()));
    }

    public void gatherInformation() {
        try {
            Map<Deque<Rule>, String> preparedExecutionData = fetchExecutionData(executionOrder);

            for (Map.Entry<Deque<Rule>, String> entry : preparedExecutionData.entrySet()) {
                executor.renew(entry.getKey(), entry.getValue());
                executor.execute();
            }
        } catch (UnsupportedOperationException | NullPointerException exp) {
            log.error(exp);
        }

        updateAppVersions();
    }

    private Map<Deque<Rule>, String> fetchExecutionData(@NotNull Map<String, String> execOrder) {
        Map<Deque<Rule>, String> preparedExecutionData = Collections.synchronizedMap(new LinkedHashMap<>());
        XmlRulesSetReader xmlReader = new XmlRulesSetReader();

        for (Map.Entry<String, String> entry : execOrder.entrySet()) {
            Deque<Rule> rulesSet = xmlReader.loadSettings(entry.getKey());
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

    public UseAsProperty getCollectedData() {
        return executor.getResults();
    }

    public void addMutableEntry(String urlToExec, String xmlToExec, LinksID whereToFindEntryURL, String whereToFindEntryXML) {
        Map<String, String> execOrder = new LinkedHashMap<>();
        execOrder.put(xmlToExec, urlToExec);

        try {
            Map<Deque<Rule>, String> preparedExecutionData = fetchExecutionData(execOrder);
            RulesExecutor executable = new RulesExecutor(new LinkedList<>(), "");

            for (Map.Entry<Deque<Rule>, String> entry : preparedExecutionData.entrySet()) {
                executable = new RulesExecutor(entry.getKey(), entry.getValue());
                executable.execute();
            }

            executionOrder.put(whereToFindEntryXML, executable.getResults().getLinks().getItem(whereToFindEntryURL));
        } catch (UnsupportedOperationException | NullPointerException exp) {
            log.error(exp);
        }
    }

}
