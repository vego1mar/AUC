package net.vego1mar.xml;

import java.util.Deque;
import net.vego1mar.rules.RuleImpl;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

public class XmlRulesSetReaderTest {

    @Test public void loadSettings_7Zip() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<RuleImpl> rulesSet = reader.loadSettings(TestVariables.XML_PATTERN_7ZIP);

        // then
        Assert.assertEquals(TestCollections.getRulesFor7Zip_1().toString(), rulesSet.toString());
    }

    @Test public void loadSettings_AIMP() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<RuleImpl> rulesSet = reader.loadSettings(TestVariables.XML_PATTERN_AIMP);

        // then
        Assert.assertEquals(TestCollections.getRulesForAimp_1().toString(), rulesSet.toString());
    }

    @Test public void loadSettings_SourceTree() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<RuleImpl> rulesSet = reader.loadSettings(TestVariables.XML_PATTERN_SOURCETREE);

        // then
        Assert.assertEquals(TestCollections.getRulesForSourceTree_1().toString(), rulesSet.toString());
    }

    @Test public void loadSettings_JetClean() {
        // given
        Deque<RuleImpl> sourceSet1 = TestCollections.getRulesForJetClean_1();
        Deque<RuleImpl> sourceSet2 = TestCollections.getRulesForJetClean_2();
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<RuleImpl> rulesSet1 = reader.loadSettings(TestVariables.XML_PATTERN_JETCLEAN_1);
        Deque<RuleImpl> rulesSet2 = reader.loadSettings(TestVariables.XML_PATTERN_JETCLEAN_2);

        // then
        Assert.assertEquals(sourceSet1.toString(), rulesSet1.toString());
        Assert.assertEquals(sourceSet2.toString(), rulesSet2.toString());
    }

    @Test public void loadSettings_BorderlessGaming() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<RuleImpl> rulesSet = reader.loadSettings(TestVariables.XML_PATTERN_BORDERLESSGAMING);

        // then
        Assert.assertEquals(TestCollections.getRulesForBorderlessGaming_1().toString(), rulesSet.toString());
    }

    @Test public void loadSettings_TeraCopy() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<RuleImpl> rulesSet = reader.loadSettings(TestVariables.XML_PATTERN_TERACOPY);

        // then
        Assert.assertEquals(TestCollections.getRulesForTeraCopy_1().toString(), rulesSet.toString());
    }

    @Test public void loadSettings_PotPlayer() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<RuleImpl> rulesSet = reader.loadSettings(TestVariables.XML_PATTERN_POTPLAYER);

        // then
        Assert.assertEquals(TestCollections.getRulesForPotPlayer_1().toString(), rulesSet.toString());
    }
}
