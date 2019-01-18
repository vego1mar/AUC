package net.vego1mar.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import net.vego1mar.collector.AppInfoCollectible;
import net.vego1mar.collector.AppInfoCollector;
import net.vego1mar.utils.ReflectionHelper;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class TestVariables {

    public static final String CODE_7ZIP = getWorkingDirectory() + "/src/test/resources/7zip_wp.txt";
    public static final String CODE_AIMP_1 = getWorkingDirectory() + "/src/test/resources/aimp_wp_windows.txt";
    public static final String CODE_AIMP_2 = getWorkingDirectory() + "/src/test/resources/aimp_wp_android.txt";
    public static final String CODE_SOURCETREE = getWorkingDirectory() + "/src/test/resources/sourcetree_wp.txt";
    public static final String CODE_JETCLEAN_1 = getWorkingDirectory() + "/src/test/resources/jetclean_wp.txt";
    public static final String CODE_JETCLEAN_2 = getWorkingDirectory() + "/src/test/resources/jetclean_wp2.txt";
    public static final String CODE_BORDERLESSGAMING = getWorkingDirectory() + "/src/test/resources/BorderlessGaming_wp.txt";
    public static final String CODE_TERACOPY = getWorkingDirectory() + "/src/test/resources/TeraCopy_wp.txt";
    public static final String CODE_POTPLAYER = getWorkingDirectory() + "/src/test/resources/PotPlayer_wp.txt";
    public static final String XML_RUNTIME_7ZIP = getWorkingDirectory() + "/runtime/7zip_settings.xml";
    public static final String XML_PATTERN_7ZIP = getWorkingDirectory() + "/src/test/resources/7zip_settings__pattern.xml";
    public static final String XML_RUNTIME_AIMP_1 = getWorkingDirectory() + "/runtime/aimp1_settings.xml";
    public static final String XML_PATTERN_AIMP_1 = getWorkingDirectory() + "/src/test/resources/aimp1_settings__ptrn.xml";
    public static final String XML_RUNTIME_AIMP_2 = getWorkingDirectory() + "/runtime/aimp2_settings.xml";
    public static final String XML_PATTERN_AIMP_2 = getWorkingDirectory() + "/src/test/resources/aimp2_settings__ptrn.xml";
    public static final String XML_RUNTIME_SOURCETREE = getWorkingDirectory() + "/runtime/SourceTree_settings.xml";
    public static final String XML_PATTERN_SOURCETREE = getWorkingDirectory() + "/src/test/resources/SourceTree_stgs__ptrn.xml";
    public static final String XML_RUNTIME_JETCLEAN_1 = getWorkingDirectory() + "/runtime/JetClean01_settings.xml";
    public static final String XML_RUNTIME_JETCLEAN_2 = getWorkingDirectory() + "/runtime/JetClean02_settings.xml";
    public static final String XML_PATTERN_JETCLEAN_1 = getWorkingDirectory() + "/src/test/resources/JetClean01_settings__ptrn.xml";
    public static final String XML_PATTERN_JETCLEAN_2 = getWorkingDirectory() + "/src/test/resources/JetClean02_settings__ptrn.xml";
    public static final String XML_RUNTIME_BORDERLESSGAMING = getWorkingDirectory() + "/runtime/BorderlessGaming_settings.xml";
    public static final String XML_PATTERN_BORDERLESSGAMING = getWorkingDirectory() + "/src/test/resources/BorderlessGaming__ptrn.xml";
    public static final String XML_RUNTIME_TERACOPY = getWorkingDirectory() + "/runtime/TeraCopy_settings.xml";
    public static final String XML_PATTERN_TERACOPY = getWorkingDirectory() + "/src/test/resources/TeraCopy_set__ptrn.xml";
    public static final String XML_RUNTIME_POTPLAYER = getWorkingDirectory() + "/runtime/PotPlayer_settings.xml";
    public static final String XML_PATTERN_POTPLAYER = getWorkingDirectory() + "/src/test/resources/PotPlayer_set__ptrn.xml";
    public static final String XML_RUNTIME_RULESSETWRITER = getWorkingDirectory() + "/runtime/XmlRulesSetWriter_settings.xml";
    public static final String XML_PATTERN_RULESSETWRITER = getWorkingDirectory() + "/src/test/resources/XmlRulesSetWriter__ptrn.xml";
    public static final String OBJECT_RUNTIME_POTPLAYER = getWorkingDirectory() + "/runtime/PotPlayerOBJ.ser";
    public static final String XML_CONSOLE_RUNNER_DIR = getWorkingDirectory() + "/src/test/resources/";
    public static final String XML_CONSOLE_RUNNER_OUTFILE = getWorkingDirectory() + "/src/test/resources/runner.txt";
    private static final Logger log = Logger.getLogger(TestVariables.class);

    private TestVariables() {
        // This should be a utility class.
    }

    public static String readFile(@NotNull String fileName) {
        log.debug("Working directory: " + getWorkingDirectory());
        String result = "";

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                StringBuilder builder = new StringBuilder();
                String line = reader.readLine();

                while (line != null) {
                    builder.append(line);
                    builder.append(System.lineSeparator());
                    line = reader.readLine();
                }

                result = builder.toString();
            }
        } catch (IOException exp) {
            log.error(exp);
        }

        return result;
    }

    private static String getWorkingDirectory() {
        return System.getProperty("user.dir");
    }

    public static AppInfoCollectible getCollector(@NotNull String codeFileName) {
        AppInfoCollectible collector = new AppInfoCollector("collector#" + codeFileName, "");
        String htmlCode = readFile(codeFileName);
        Field field = ReflectionHelper.getField(AppInfoCollector.class, "htmlCode");

        try {
            field.set(collector, htmlCode);
        } catch (IllegalAccessException exp) {
            log.debug(exp.getMessage());
        }

        return collector;
    }

}
