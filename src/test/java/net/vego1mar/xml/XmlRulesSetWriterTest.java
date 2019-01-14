package net.vego1mar.xml;

import java.util.Deque;
import net.vego1mar.rules.RuleImpl;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

public class XmlRulesSetWriterTest {

    @Test public void saveSettings_1() {
        // given
        Deque<RuleImpl> rulesSet = TestCollections.getRulesForXmlRulesSetWriter();
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
        Deque<RuleImpl> rulesSet = TestCollections.getRulesFor7Zip_1();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet, TestVariables.XML_RUNTIME_7ZIP);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RUNTIME_7ZIP);
        String patternSource = TestVariables.readFile(TestVariables.XML_PATTERN_7ZIP);
        Assert.assertEquals(patternSource, runtime);
    }

    @Test public void saveSettings_AIMP() {
        // given
        Deque<RuleImpl> rulesSet = TestCollections.getRulesForAimp_1();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet, TestVariables.XML_RUNTIME_AIMP);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RUNTIME_AIMP);
        String patternSource = TestVariables.readFile(TestVariables.XML_PATTERN_AIMP);
        Assert.assertEquals(patternSource, runtime);
    }

    @Test public void saveSettings_SourceTree() {
        // given
        Deque<RuleImpl> rulesSet = TestCollections.getRulesForSourceTree_1();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet, TestVariables.XML_RUNTIME_SOURCETREE);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RUNTIME_SOURCETREE);
        String source = TestVariables.readFile(TestVariables.XML_PATTERN_SOURCETREE);
        Assert.assertEquals(source, runtime);
    }

    @Test public void saveSettings_JetClean() {
        // given
        Deque<RuleImpl> rulesSet1 = TestCollections.getRulesForJetClean_1();
        Deque<RuleImpl> rulesSet2 = TestCollections.getRulesForJetClean_2();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet1, TestVariables.XML_RUNTIME_JETCLEAN_1);
        writer.saveSettings(rulesSet2, TestVariables.XML_RUNTIME_JETCLEAN_2);

        // then
        String runtime1 = TestVariables.readFile(TestVariables.XML_RUNTIME_JETCLEAN_1);
        String runtime2 = TestVariables.readFile(TestVariables.XML_RUNTIME_JETCLEAN_2);
        String source1 = TestVariables.readFile(TestVariables.XML_PATTERN_JETCLEAN_1);
        String source2 = TestVariables.readFile(TestVariables.XML_PATTERN_JETCLEAN_2);
        Assert.assertEquals(source1, runtime1);
        Assert.assertEquals(source2, runtime2);
    }

    @Test public void saveSettings_BorderlessGaming() {
        // given
        Deque<RuleImpl> rulesSet = TestCollections.getRulesForBorderlessGaming_1();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet, TestVariables.XML_RUNTIME_BORDERLESSGAMING);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RUNTIME_BORDERLESSGAMING);
        String source = TestVariables.readFile(TestVariables.XML_PATTERN_BORDERLESSGAMING);
        Assert.assertEquals(source, runtime);
    }

    @Test public void saveSettings_TeraCopy() {
        // given
        Deque<RuleImpl> rulesSet = TestCollections.getRulesForTeraCopy_1();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet, TestVariables.XML_RUNTIME_TERACOPY);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RUNTIME_TERACOPY);
        String source = TestVariables.readFile(TestVariables.XML_PATTERN_TERACOPY);
        Assert.assertEquals(source, runtime);
    }

    @Test public void saveSettings_PotPlayer() {
        // given
        Deque<RuleImpl> rulesSet = TestCollections.getRulesForPotPlayer_1();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet, TestVariables.XML_RUNTIME_POTPLAYER);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RUNTIME_POTPLAYER);
        String source = TestVariables.readFile(TestVariables.XML_PATTERN_POTPLAYER);
        Assert.assertEquals(source, runtime);
    }
}
