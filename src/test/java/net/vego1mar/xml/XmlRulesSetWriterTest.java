package net.vego1mar.xml;

import java.util.Deque;
import net.vego1mar.rules.Rule;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

@Deprecated public class XmlRulesSetWriterTest {

    @Test public void saveSettings_OracleVirtualBox() {
        // given
        Deque<Rule> rulesSet1 = TestCollections.getRulesForOracleVirtualBox_1();
        Deque<Rule> rulesSet2 = TestCollections.getRulesForOracleVirtualBox_2();
        Deque<Rule> rulesSet3 = TestCollections.getRulesForOracleVirtualBox_3();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet1, TestVariables.XML_RUNTIME_VIRTUALBOX_1);
        writer.saveSettings(rulesSet2, TestVariables.XML_RUNTIME_VIRTUALBOX_2);
        writer.saveSettings(rulesSet3, TestVariables.XML_RUNTIME_VIRTUALBOX_3);

        // then
        String runtime1 = TestVariables.readFile(TestVariables.XML_RUNTIME_VIRTUALBOX_1);
        String runtime2 = TestVariables.readFile(TestVariables.XML_RUNTIME_VIRTUALBOX_2);
        String runtime3 = TestVariables.readFile(TestVariables.XML_RUNTIME_VIRTUALBOX_3);
        String source1 = TestVariables.readFile(TestVariables.XML_PATTERN_VIRTUALBOX_1);
        String source2 = TestVariables.readFile(TestVariables.XML_PATTERN_VIRTUALBOX_2);
        String source3 = TestVariables.readFile(TestVariables.XML_PATTERN_VIRTUALBOX_3);
        Assert.assertEquals(source1, runtime1);
        Assert.assertEquals(source2, runtime2);
        Assert.assertEquals(source3, runtime3);
    }

    @Test public void saveSettings_OracleVirtualBox_AB() {
        // given
        Deque<Rule> rulesSet1 = TestCollections.getRulesForOracleVirtualBox_A();
        Deque<Rule> rulesSet2 = TestCollections.getRulesForOracleVirtualBox_B();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet1, TestVariables.XML_RUNTIME_VIRTUALBOX_A);
        writer.saveSettings(rulesSet2, TestVariables.XML_RUNTIME_VIRTUALBOX_B);

        // then
        String runtime1 = TestVariables.readFile(TestVariables.XML_RUNTIME_VIRTUALBOX_A);
        String runtime2 = TestVariables.readFile(TestVariables.XML_RUNTIME_VIRTUALBOX_B);
        String source1 = TestVariables.readFile(TestVariables.XML_PATTERN_VIRTUALBOX_A);
        String source2 = TestVariables.readFile(TestVariables.XML_PATTERN_VIRTUALBOX_B);
        Assert.assertEquals(source1, runtime1);
        Assert.assertEquals(source2, runtime2);
    }

}
