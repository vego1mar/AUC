package net.vego1mar.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class TestHelper {

    public static final String XML_CONSOLE_RUNNER_DIR = getWorkingDirectory() + "/src/test/resources/";
    public static final String XML_CONSOLE_RUNNER_OUTFILE = getWorkingDirectory() + "/src/test/resources/runner.txt";
    private static final Logger log = Logger.getLogger(TestHelper.class);

    private TestHelper() {
        // This should be a utility class.
    }

    public static String readFile(@NotNull String fileName) {
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

    public static String getWorkingDirectory() {
        return System.getProperty("user.dir");
    }

    public static String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

}
