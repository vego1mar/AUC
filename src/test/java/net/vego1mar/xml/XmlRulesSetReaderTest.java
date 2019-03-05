package net.vego1mar.xml;

import java.util.Deque;
import net.vego1mar.rules.Rule;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

public class XmlRulesSetReaderTest {

    @Test public void loadSettings_7Zip() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet = reader.loadSettings(TestVariables.XML_PATTERN_7ZIP);

        // then
        Assert.assertEquals(TestCollections.getRulesFor7Zip_1().toString(), rulesSet.toString());
    }

    @Test public void loadSettings_AIMP() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet = reader.loadSettings(TestVariables.XML_PATTERN_AIMP_1);

        // then
        Assert.assertEquals(TestCollections.getRulesForAimp_1().toString(), rulesSet.toString());
    }

    @Test public void loadSettings_SourceTree() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet = reader.loadSettings(TestVariables.XML_PATTERN_SOURCETREE);

        // then
        Assert.assertEquals(TestCollections.getRulesForSourceTree_1().toString(), rulesSet.toString());
    }

    @Test public void loadSettings_JetClean() {
        // given
        Deque<Rule> sourceSet1 = TestCollections.getRulesForJetClean_1();
        Deque<Rule> sourceSet2 = TestCollections.getRulesForJetClean_2();
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet1 = reader.loadSettings(TestVariables.XML_PATTERN_JETCLEAN_1);
        Deque<Rule> rulesSet2 = reader.loadSettings(TestVariables.XML_PATTERN_JETCLEAN_2);

        // then
        Assert.assertEquals(sourceSet1.toString(), rulesSet1.toString());
        Assert.assertEquals(sourceSet2.toString(), rulesSet2.toString());
    }

    @Test public void loadSettings_BorderlessGaming() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet = reader.loadSettings(TestVariables.XML_PATTERN_BORDERLESSGAMING);

        // then
        Assert.assertEquals(TestCollections.getRulesForBorderlessGaming_1().toString(), rulesSet.toString());
    }

    @Test public void loadSettings_TeraCopy() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet = reader.loadSettings(TestVariables.XML_PATTERN_TERACOPY);

        // then
        Assert.assertEquals(TestCollections.getRulesForTeraCopy_1().toString(), rulesSet.toString());
    }

    @Test public void loadSettings_PotPlayer() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet = reader.loadSettings(TestVariables.XML_PATTERN_POTPLAYER);

        // then
        Assert.assertEquals(TestCollections.getRulesForPotPlayer_1().toString(), rulesSet.toString());
    }

    @Test public void loadSettings_OracleVirtualBox() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet1 = reader.loadSettings(TestVariables.XML_PATTERN_VIRTUALBOX_1);
        Deque<Rule> rulesSet2 = reader.loadSettings(TestVariables.XML_PATTERN_VIRTUALBOX_2);
        Deque<Rule> rulesSet3 = reader.loadSettings(TestVariables.XML_PATTERN_VIRTUALBOX_3);

        // then
        Assert.assertEquals(TestCollections.getRulesForOracleVirtualBox_1().toString(), rulesSet1.toString());
        Assert.assertEquals(TestCollections.getRulesForOracleVirtualBox_2().toString(), rulesSet2.toString());
        Assert.assertEquals(TestCollections.getRulesForOracleVirtualBox_3().toString(), rulesSet3.toString());
    }

    @Test public void loadSettings_OracleVirtualBox_AB() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet1 = reader.loadSettings(TestVariables.XML_PATTERN_VIRTUALBOX_A);
        Deque<Rule> rulesSet2 = reader.loadSettings(TestVariables.XML_PATTERN_VIRTUALBOX_B);

        // then
        Assert.assertEquals(TestCollections.getRulesForOracleVirtualBox_A().toString(), rulesSet1.toString());
        Assert.assertEquals(TestCollections.getRulesForOracleVirtualBox_B().toString(), rulesSet2.toString());
    }

    @Test public void loadSettings_EAOrigin() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet1 = reader.loadSettings(TestVariables.XML_PATTERN_ORIGIN_1);

        // then
        Assert.assertEquals(TestCollections.getRulesForEAOrigin_1().toString(), rulesSet1.toString());
    }

}
