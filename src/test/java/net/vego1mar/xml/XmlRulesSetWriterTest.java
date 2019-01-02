package net.vego1mar.xml;

import java.util.Deque;
import net.vego1mar.rules.RuleBased;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

public class XmlRulesSetWriterTest {

    @Test public void saveSettings_1() {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesForXmlRulesSetWriter();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet, TestVariables.XML_RUNTIME_RULESSETWRITER);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RUNTIME_RULESSETWRITER);
        String patternSource = TestVariables.readFile(TestVariables.XML_PATTERN_RULESSETWRITER);
        Assert.assertEquals(patternSource, runtime);
    }

    @Test public void saveSettings_7Zip() {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesFor7Zip_1();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet, TestVariables.XML_RUNTIME_7ZIP);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RUNTIME_7ZIP);
        String patternSource = TestVariables.readFile(TestVariables.XML_PATTERN_7ZIP);
        Assert.assertEquals(patternSource, runtime);
    }
}
