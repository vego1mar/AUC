package net.vego1mar.console;

import java.io.File;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.vego1mar.auxiliary.properties.UseAsImpl;
import net.vego1mar.rules.RuleImpl;
import net.vego1mar.rules.RulesExecutable;
import net.vego1mar.rules.RulesExecutor;
import net.vego1mar.utils.DownloadHelper;
import net.vego1mar.utils.FileReaderHelper;
import net.vego1mar.xml.XmlRulesSetReader;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class XmlConsoleRunner {

    private static final Logger log = Logger.getLogger(XmlConsoleRunner.class);
    private static final String DEF_FILENAME = "runner.def";
    private static final String ENTRY_STR = "/// ENTRY";
    private String rulesDirPath;
    private Map<String, UseAsImpl> results;

    public XmlConsoleRunner(@NotNull String dirPath) {
        rulesDirPath = dirPath;
        results = new LinkedHashMap<>();
    }

    public void run() {
        if (!isDefinitionFileExists()) {
            log.warn("Either the definition file or the directory path does not exists.");
            return;
        }

        final List<DefinitionFileStruct> parsedDef = getParsedDefinitionFile();
        XmlRulesSetReader reader;
        Deque<RuleImpl> rulesSet;
        RulesExecutable executor;
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
                final int SPLITS_NO = 2;

                if (lines.length == SPLITS_NO) {
                    defStruct.get(currentStruct).addFileName(lines[0], lines[1]);
                }
            }
        }

        return defStruct;
    }

    public Map<String, UseAsImpl> getResults() {
        return results;
    }
}
