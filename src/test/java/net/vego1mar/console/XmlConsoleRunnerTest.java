package net.vego1mar.console;

import java.io.File;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

public class XmlConsoleRunnerTest {

    @Test public void run1_online() {
        // given
        final int DEF_FILE_ENTRIES = 7;
        XmlConsoleRunner runner = new XmlConsoleRunner(TestVariables.XML_CONSOLE_RUNNER_DIR);
        File file = new File(TestVariables.XML_CONSOLE_RUNNER_OUTFILE);

        // when
        runner.run();

        // then
        Assert.assertNotNull(runner);
        Assert.assertEquals(DEF_FILE_ENTRIES, runner.getResults().size());
        Assert.assertTrue(file.exists() && file.isFile());

    }

}
