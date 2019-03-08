package net.vego1mar.xml;

import static net.vego1mar.tests.TestVariables.getWorkingDirectory;

import java.nio.file.Paths;
import java.util.Deque;
import net.vego1mar.rules.ExecutorTestImpl;
import net.vego1mar.rules.Rule;
import net.vego1mar.rules.SourceTreeExecutorTest;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SourceTreeXmlTest {

    public static final String XML_PATTERN = Paths.get(getWorkingDirectory(), "/src/test/resources/SourceTree_stgs__ptrn.xml").toString();
    private static final String XML_RUNTIME = Paths.get(getWorkingDirectory(), "/runtime/SourceTree_settings.xml").toString();
    private ExecutorTestImpl asserter;

    @Before public void before() {
        asserter = new SourceTreeExecutorTest();
    }

    @Test public void testSaveSettings() {
        // given
        Deque<Rule> rulesSet = asserter.getRules(1);
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet, XML_RUNTIME);

        // then
        String runtime = TestVariables.readFile(XML_RUNTIME);
        String source = TestVariables.readFile(XML_PATTERN);
        Assert.assertEquals(source, runtime);
    }

    @Test public void testLoadSettings() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet = reader.loadSettings(XML_PATTERN);

        // then
        Assert.assertEquals(asserter.getRules(1).toString(), rulesSet.toString());
    }
}
