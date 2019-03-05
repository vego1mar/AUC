package net.vego1mar.console;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import net.vego1mar.properties.LinksProperty;
import net.vego1mar.properties.PlatformsProperty;
import net.vego1mar.properties.UseAsProperty;
import net.vego1mar.properties.enumerators.LinksID;
import net.vego1mar.properties.enumerators.Platforms;
import net.vego1mar.rules.Rule;
import net.vego1mar.rules.RulesExecutor;
import net.vego1mar.utils.DownloadHelper;
import net.vego1mar.utils.FileReaderHelper;
import net.vego1mar.xml.XmlRulesSetReader;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class XmlConsoleRunner {

    private static final Logger log = Logger.getLogger(XmlConsoleRunner.class);
    private static final String DEF_FILENAME = "runner.def";
    private static final String RESULTS_FILENAME = "runner.txt";
    private static final String ENTRY_STR = "/// ENTRY";
    private String rulesDirPath;
    private Map<String, UseAsProperty> results;

    public XmlConsoleRunner(@NotNull String dirPath) {
        rulesDirPath = dirPath;
        results = new LinkedHashMap<>();
    }

    public void run() {
        if (!isDefinitionFileExists()) {
            log.warn("Either the definition file nor the directory path does not exists.");
            return;
        }

        final List<DefinitionFileStruct> parsedDef = getParsedDefinitionFile();
        XmlRulesSetReader reader;
        Deque<Rule> rulesSet;
        RulesExecutor executor;
        String htmlCode;

        for (DefinitionFileStruct def : parsedDef) {
            reader = new XmlRulesSetReader();
            executor = new RulesExecutor(new LinkedList<>(), "");

            for (Map.Entry<String, String> entry : def.getExecLines().entrySet()) {
                rulesSet = reader.loadSettings(new File(rulesDirPath, entry.getKey()).getPath());
                htmlCode = DownloadHelper.getHtml(entry.getValue());
                executor.renew(rulesSet, htmlCode);
                executor.execute();
            }

            results.put(def.getEntryName(), executor.getResults());
        }

        save();
    }

    private boolean isDefinitionFileExists() {
        File dir = new File(rulesDirPath);
        File file = new File(rulesDirPath, DEF_FILENAME);
        return dir.isDirectory() && file.isFile();
    }

    private List<DefinitionFileStruct> getParsedDefinitionFile() {
        File defPath = new File(rulesDirPath, DEF_FILENAME);
        final String defFile = FileReaderHelper.readFile(defPath.getPath());
        String[] defTable = defFile.split("\\r?\\n|\\r");
        List<DefinitionFileStruct> defStruct = new ArrayList<>();
        final int SPLITS_NO = 2;
        int currentStruct = -1;

        for (String line : defTable) {
            if (line.contains(ENTRY_STR)) {
                String name = line.substring(ENTRY_STR.length()).trim();
                currentStruct++;
                defStruct.add(new DefinitionFileStruct());
                defStruct.get(currentStruct).setEntryName(name);
                continue;
            }

            if (!line.isEmpty() && line.contains(".xml")) {
                String[] lines = line.split("\\s+");

                if (lines.length == SPLITS_NO) {
                    defStruct.get(currentStruct).addFileName(lines[0], lines[1]);
                }
            }
        }

        return defStruct;
    }

    public Map<String, UseAsProperty> getResults() {
        return results;
    }

    private void save() {
        final String OUTER_FILENAME = Paths.get(rulesDirPath, RESULTS_FILENAME).toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTER_FILENAME))) {
            for (Map.Entry<String, UseAsProperty> entry : results.entrySet()) {
                writer.write(ENTRY_STR + ' ' + entry.getKey());
                writer.newLine();
                savePropertiesData(entry.getValue(), writer);
                writer.newLine();
            }
        } catch (IOException exp) {
            log.error(exp);
        }
    }

    private void savePropertiesData(@NotNull UseAsProperty useAsProperty, @NotNull BufferedWriter writer) throws IOException {
        savePlatforms(useAsProperty.getVersions(), writer);
        savePlatforms(useAsProperty.getDates(), writer);
        saveLinks(useAsProperty.getLinks(), writer);
        saveLinks(useAsProperty.getHashes(), writer);
    }

    private void savePlatforms(@NotNull PlatformsProperty platforms, @NotNull BufferedWriter writer) throws IOException {
        for (Map.Entry<Platforms, String> entry : platforms.getPlatforms().entrySet()) {
            if (!entry.getValue().isEmpty()) {
                writer.write(entry.getKey().toString() + '=' + entry.getValue());
                writer.newLine();
            }
        }

        writer.flush();
    }

    private void saveLinks(@NotNull LinksProperty links, @NotNull BufferedWriter writer) throws IOException {
        for (Map.Entry<LinksID, String> entry : links.getLinks().entrySet()) {
            if (!entry.getValue().isEmpty()) {
                writer.write(entry.getKey().toString() + '=' + entry.getValue());
                writer.newLine();
            }
        }

        writer.flush();
    }

    public static void main(String[] args) {
        String rulesDirPath = System.getProperty("user.dir");
        final int EXPECTED_ARGS = 1;

        if (args.length == EXPECTED_ARGS) {
            rulesDirPath = args[0];
        }

        final long startTime = System.nanoTime();
        XmlConsoleRunner runner = new XmlConsoleRunner(rulesDirPath);
        runner.run();
        final long seconds = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime);
        log.info("The program finished after " + seconds + " seconds.");
    }
}
