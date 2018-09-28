package net.vego1mar.utils;

import java.util.Deque;
import net.vego1mar.rules.RuleBased;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

public class XmlRulesSetManagerTest {

    @Test public void saveSettings_7Zip() {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesFor7Zip_1();

        // when
        XmlRulesSetManager.saveSettings(rulesSet, TestVariables.XML_RUNTIME_7ZIP);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RUNTIME_7ZIP);
        String patternSource = TestVariables.readFile(TestVariables.XML_PATTERN_7ZIP);
        Assert.assertEquals(patternSource, runtime);
    }

    @Test public void loadSettings_7Zip() {
        // when
        Deque<RuleBased> rulesSet = XmlRulesSetManager.loadSettings(TestVariables.XML_PATTERN_7ZIP);

        // then
        Assert.assertEquals(TestCollections.getRulesFor7Zip_1().toString(), rulesSet.toString());
    }

    @Test public void saveSettings_AIMP() {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesForAimp_1();

        // when
        XmlRulesSetManager.saveSettings(rulesSet, TestVariables.XML_RUNTIME_AIMP);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RUNTIME_AIMP);
        String patternSource = TestVariables.readFile(TestVariables.XML_PATTERN_AIMP);
        Assert.assertEquals(patternSource, runtime);
    }

    @Test public void loadSettings_AIMP() {
        // when
        Deque<RuleBased> rulesSet = XmlRulesSetManager.loadSettings(TestVariables.XML_PATTERN_AIMP);

        // then
        Assert.assertEquals(TestCollections.getRulesForAimp_1().toString(), rulesSet.toString());
    }

    @Test public void saveSettings_SourceTree() {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesForSourceTree_1();

        // when
        XmlRulesSetManager.saveSettings(rulesSet, TestVariables.XML_RUNTIME_SOURCETREE);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RUNTIME_SOURCETREE);
        String source = TestVariables.readFile(TestVariables.XML_PATTERN_SOURCETREE);
        Assert.assertEquals(source, runtime);
    }

    @Test public void loadSettings_SourceTree() {
        // when
        Deque<RuleBased> rulesSet = XmlRulesSetManager.loadSettings(TestVariables.XML_PATTERN_SOURCETREE);

        // then
        Assert.assertEquals(TestCollections.getRulesForSourceTree_1().toString(), rulesSet.toString());
    }

    @Test public void saveSettings_JetClean() {
        // given
        Deque<RuleBased> rulesSet1 = TestCollections.getRulesForJetClean_1();
        Deque<RuleBased> rulesSet2 = TestCollections.getRulesForJetClean_2();

        // when
        XmlRulesSetManager.saveSettings(rulesSet1, TestVariables.XML_RUNTIME_JETCLEAN_1);
        XmlRulesSetManager.saveSettings(rulesSet2, TestVariables.XML_RUNTIME_JETCLEAN_2);

        // then
        String runtime1 = TestVariables.readFile(TestVariables.XML_RUNTIME_JETCLEAN_1);
        String runtime2 = TestVariables.readFile(TestVariables.XML_RUNTIME_JETCLEAN_2);
        String source1 = TestVariables.readFile(TestVariables.XML_PATTERN_JETCLEAN_1);
        String source2 = TestVariables.readFile(TestVariables.XML_PATTERN_JETCLEAN_2);
        Assert.assertEquals(source1, runtime1);
        Assert.assertEquals(source2, runtime2);
    }

    @Test public void loadSettings_JetClean() {
        // given
        Deque<RuleBased> sourceSet1 = TestCollections.getRulesForJetClean_1();
        Deque<RuleBased> sourceSet2 = TestCollections.getRulesForJetClean_2();

        // when
        Deque<RuleBased> rulesSet1 = XmlRulesSetManager.loadSettings(TestVariables.XML_PATTERN_JETCLEAN_1);
        Deque<RuleBased> rulesSet2 = XmlRulesSetManager.loadSettings(TestVariables.XML_PATTERN_JETCLEAN_2);

        // then
        Assert.assertEquals(sourceSet1.toString(), rulesSet1.toString());
        Assert.assertEquals(sourceSet2.toString(), rulesSet2.toString());
    }

}
