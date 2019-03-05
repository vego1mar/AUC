package net.vego1mar.xml;

import java.util.Deque;
import net.vego1mar.rules.Rule;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

public class XmlRulesSetWriterTest {

    @Test public void saveSettings_1() {
        // given
        Deque<Rule> rulesSet = TestCollections.getRulesForXmlRulesSetWriter();
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
        Deque<Rule> rulesSet = TestCollections.getRulesFor7Zip_1();
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
        Deque<Rule> rulesSet1 = TestCollections.getRulesForAimp_1();
        Deque<Rule> rulesSet2 = TestCollections.getRulesForAimp_2();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet1, TestVariables.XML_RUNTIME_AIMP_1);
        writer.saveSettings(rulesSet2, TestVariables.XML_RUNTIME_AIMP_2);

        // then
        String runtime1 = TestVariables.readFile(TestVariables.XML_RUNTIME_AIMP_1);
        String runtime2 = TestVariables.readFile(TestVariables.XML_RUNTIME_AIMP_2);
        String source1 = TestVariables.readFile(TestVariables.XML_PATTERN_AIMP_1);
        String source2 = TestVariables.readFile(TestVariables.XML_PATTERN_AIMP_2);
        Assert.assertEquals(source1, runtime1);
        Assert.assertEquals(source2, runtime2);
    }

    @Test public void saveSettings_SourceTree() {
        // given
        Deque<Rule> rulesSet = TestCollections.getRulesForSourceTree_1();
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
        Deque<Rule> rulesSet1 = TestCollections.getRulesForJetClean_1();
        Deque<Rule> rulesSet2 = TestCollections.getRulesForJetClean_2();
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
        Deque<Rule> rulesSet = TestCollections.getRulesForBorderlessGaming_1();
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
        Deque<Rule> rulesSet = TestCollections.getRulesForTeraCopy_1();
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
        Deque<Rule> rulesSet = TestCollections.getRulesForPotPlayer_1();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet, TestVariables.XML_RUNTIME_POTPLAYER);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RUNTIME_POTPLAYER);
        String source = TestVariables.readFile(TestVariables.XML_PATTERN_POTPLAYER);
        Assert.assertEquals(source, runtime);
    }

    @Test public void saveSettings_BlizzardBattleNet() {
        // given
        Deque<Rule> rulesSet1 = TestCollections.getRulesForBlizzardBattleNet_1();
        Deque<Rule> rulesSet2 = TestCollections.getRulesForBlizzardBattleNet_2();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet1, TestVariables.XML_RUNTIME_BLIZZARDBATTLENET_1);
        writer.saveSettings(rulesSet2, TestVariables.XML_RUNTIME_BLIZZARDBATTLENET_2);

        // then
        String runtime1 = TestVariables.readFile(TestVariables.XML_RUNTIME_BLIZZARDBATTLENET_1);
        String runtime2 = TestVariables.readFile(TestVariables.XML_RUNTIME_BLIZZARDBATTLENET_2);
        String source1 = TestVariables.readFile(TestVariables.XML_PATTERN_BLIZZARDBATTLENET_1);
        String source2 = TestVariables.readFile(TestVariables.XML_PATTERN_BLIZZARDBATTLENET_2);
        Assert.assertEquals(source1, runtime1);
        Assert.assertEquals(source2, runtime2);
    }

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

    @Test public void saveSettings_EAOrigin() {
        // given
        Deque<Rule> rulesSet1 = TestCollections.getRulesForEAOrigin_1();
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet1, TestVariables.XML_RUNTIME_ORIGIN_1);

        // then
        String runtime1 = TestVariables.readFile(TestVariables.XML_RUNTIME_ORIGIN_1);
        String source1 = TestVariables.readFile(TestVariables.XML_PATTERN_ORIGIN_1);
        Assert.assertEquals(source1, runtime1);
    }

}
