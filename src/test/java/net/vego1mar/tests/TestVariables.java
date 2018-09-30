package net.vego1mar.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class TestVariables {

    public static final String CODE_7ZIP = getWorkingDirectory() + "/src/test/resources/7zip_wp.txt";
    public static final String CODE_AIMP = getWorkingDirectory() + "/src/test/resources/aimp_wp.txt";
    public static final String CODE_SOURCETREE = getWorkingDirectory() + "/src/test/resources/sourcetree_wp.txt";
    public static final String CODE_JETCLEAN_1 = getWorkingDirectory() + "/src/test/resources/jetclean_wp.txt";
    public static final String CODE_JETCLEAN_2 = getWorkingDirectory() + "/src/test/resources/jetclean_wp2.txt";
    public static final String CODE_BORDERLESSGAMING = getWorkingDirectory() + "/src/test/resources/BorderlessGaming_wp.txt";
    public static final String CODE_TERACOPY = getWorkingDirectory() + "/src/test/resources/TeraCopy_wp.txt";
    public static final String SOURCE_URL_7ZIP = "https://www.7-zip.org/download.html";
    public static final String SERIALIZATION_FILENAME_7ZIP = getWorkingDirectory() + "/runtime/7-Zip.ser";
    public static final String XML_RUNTIME_7ZIP = getWorkingDirectory() + "/runtime/7zip_settings.xml";
    public static final String XML_PATTERN_7ZIP = getWorkingDirectory() + "/src/test/resources/7zip_settings__pattern.xml";
    public static final String XML_RUNTIME_AIMP = getWorkingDirectory() + "/runtime/aimp_settings.xml";
    public static final String XML_PATTERN_AIMP = getWorkingDirectory() + "/src/test/resources/aimp_settings__pattern.xml";
    public static final String XML_RUNTIME_SOURCETREE = getWorkingDirectory() + "/runtime/SourceTree_settings.xml";
    public static final String XML_PATTERN_SOURCETREE = getWorkingDirectory() + "/src/test/resources/SourceTree_settings__pattern.xml";
    public static final String XML_RUNTIME_JETCLEAN_1 = getWorkingDirectory() + "/runtime/JetClean01_settings.xml";
    public static final String XML_RUNTIME_JETCLEAN_2 = getWorkingDirectory() + "/runtime/JetClean02_settings.xml";
    public static final String XML_PATTERN_JETCLEAN_1 = getWorkingDirectory() + "/src/test/resources/JetClean01_settings__pattern.xml";
    public static final String XML_PATTERN_JETCLEAN_2 = getWorkingDirectory() + "/src/test/resources/JetClean02_settings__pattern.xml";
    public static final String XML_RUNTIME_BORDERLESSGAMING = getWorkingDirectory() + "/runtime/BorderlessGaming_settings.xml";
    public static final String XML_PATTERN_BORDERLESSGAMING = getWorkingDirectory() + "/src/test/resources/BorderlessGaming_settings__ptrn.xml";
    public static final String XML_RUNTIME_TERACOPY = getWorkingDirectory() + "/runtime/TeraCopy_settings.xml";
    public static final String XML_PATTERN_TERACOPY = getWorkingDirectory() + "/src/test/resources/TeraCopy_settings__pattern.xml";
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

}
