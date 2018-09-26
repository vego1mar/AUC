package net.vego1mar.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class TestVariables {

    public static final String WEBPAGE_OF_7ZIP = "runtime/tests/7zip_wp.txt";
    public static final String WEBPAGE_OF_AIMP = "runtime/tests/aimp_wp.txt";
    public static final String SOURCE_URL_OF_7ZIP = "https://www.7-zip.org/download.html";
    public static final String SERIALIZATION_FILE_NAME_OF_7ZIP = "runtime/7-Zip.ser";
    public static final String XML_RULES_SET_OF_7ZIP_RUNTIME = "runtime/7zip_settings.xml";
    public static final String XML_RULES_SET_OF_7ZIP_PATTERN = "runtime/tests/7zip_settings__pattern.xml";
    public static final String XML_RULES_SET_OF_AIMP_RUNTIME = "runtime/aimp_settings.xml";
    public static final String XML_RULES_SET_OF_AIMP_PATTERN = "runtime/tests/aimp_settings__pattern.xml";
    private static final Logger log = Logger.getLogger(TestVariables.class);

    private TestVariables() {
        // This should be a utility class.
    }

    public static String readFile(@NotNull String fileName) {
        log.debug("Working directory: " + System.getProperty("user.dir"));
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

}
