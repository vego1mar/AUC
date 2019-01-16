package net.vego1mar.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class FileReaderHelper {

    private static final Logger log = Logger.getLogger(FileReaderHelper.class);

    private FileReaderHelper() {
        // This should be a utility class.
    }

    private static String getWorkingDirectory() {
        return System.getProperty("user.dir");
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
}
