package net.vego1mar.xml;

import static net.vego1mar.tests.TestVariables.getWorkingDirectory;

import java.nio.file.Paths;
import java.util.Deque;
import net.vego1mar.rules.JetCleanExecutorTest;
import net.vego1mar.rules.Rule;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

public class JetCleanXmlTest extends XmlTest {

    public JetCleanXmlTest() {
        super(new JetCleanExecutorTest());
        setXmlRuntime1(Paths.get(getWorkingDirectory(), "/runtime/JetClean01_settings.xml").toString());
        setXmlRuntime2(Paths.get(getWorkingDirectory(), "/runtime/JetClean02_settings.xml").toString());
        setXmlPattern1(Paths.get(getWorkingDirectory(), "/src/test/resources/JetClean01_settings__ptrn.xml").toString());
        setXmlPattern2(Paths.get(getWorkingDirectory(), "/src/test/resources/JetClean02_settings__ptrn.xml").toString());
    }

    @Test @Override public void testXmlConstruction() {
        testLoadSettings();
        testSaveSettings();
    }

    @Test @Override public void testSaveSettings() {
        // given
        Deque<Rule> rulesSet1 = getAsserter().getRules(1);
        Deque<Rule> rulesSet2 = getAsserter().getRules(2);
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet1, getXmlRuntime1());
        writer.saveSettings(rulesSet2, getXmlRuntime2());

        // then
        String runtime1 = TestVariables.readFile(getXmlRuntime1());
        String runtime2 = TestVariables.readFile(getXmlRuntime2());
        String source1 = TestVariables.readFile(getXmlPattern1());
        String source2 = TestVariables.readFile(getXmlPattern2());
        Assert.assertEquals(source1, runtime1);
        Assert.assertEquals(source2, runtime2);
    }

    @Test @Override public void testLoadSettings() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet1 = reader.loadSettings(getXmlPattern1());
        Deque<Rule> rulesSet2 = reader.loadSettings(getXmlPattern2());

        // then
        Assert.assertEquals(getAsserter().getRules(1).toString(), rulesSet1.toString());
        Assert.assertEquals(getAsserter().getRules(2).toString(), rulesSet2.toString());
    }

}
