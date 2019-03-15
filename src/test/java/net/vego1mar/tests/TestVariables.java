package net.vego1mar.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class TestVariables {

    public static final String CODE_VIRTUALBOX_1 = getWorkingDirectory() + "/src/test/resources/OracleVBox_wp1.txt";
    public static final String CODE_VIRTUALBOX_2 = getWorkingDirectory() + "/src/test/resources/OracleVBox_wp2.txt";
    public static final String CODE_VIRTUALBOX_3 = getWorkingDirectory() + "/src/test/resources/OracleVBox_wp3.txt";
    public static final String CODE_VIRTUALBOX_AB = getWorkingDirectory() + "/src/test/resources/OracleVBox_wpAB.txt";
    public static final String XML_RUNTIME_VIRTUALBOX_1 = getWorkingDirectory() + "/runtime/VBox1.xml";
    public static final String XML_PATTERN_VIRTUALBOX_1 = getWorkingDirectory() + "/src/test/resources/VBox1_ptrn.xml";
    public static final String XML_RUNTIME_VIRTUALBOX_2 = getWorkingDirectory() + "/runtime/VBox2.xml";
    public static final String XML_PATTERN_VIRTUALBOX_2 = getWorkingDirectory() + "/src/test/resources/VBox2_ptrn.xml";
    public static final String XML_RUNTIME_VIRTUALBOX_3 = getWorkingDirectory() + "/runtime/VBox3.xml";
    public static final String XML_PATTERN_VIRTUALBOX_3 = getWorkingDirectory() + "/src/test/resources/VBox3_ptrn.xml";
    public static final String XML_RUNTIME_VIRTUALBOX_A = getWorkingDirectory() + "/runtime/VBox_A.xml";
    public static final String XML_PATTERN_VIRTUALBOX_A = getWorkingDirectory() + "/src/test/resources/VBox_A_ptrn.xml";
    public static final String XML_RUNTIME_VIRTUALBOX_B = getWorkingDirectory() + "/runtime/VBox_B.xml";
    public static final String XML_PATTERN_VIRTUALBOX_B = getWorkingDirectory() + "/src/test/resources/VBox_B_ptrn.xml";
    public static final String XML_CONSOLE_RUNNER_DIR = getWorkingDirectory() + "/src/test/resources/";
    public static final String XML_CONSOLE_RUNNER_OUTFILE = getWorkingDirectory() + "/src/test/resources/runner.txt";
    private static final Logger log = Logger.getLogger(TestVariables.class);

    private TestVariables() {
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

}
