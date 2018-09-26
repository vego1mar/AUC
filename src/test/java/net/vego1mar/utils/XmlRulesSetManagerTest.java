package net.vego1mar.utils;

import java.util.Deque;
import net.vego1mar.rules.RuleBased;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

public class XmlRulesSetManagerTest {

    @Test public void saveSettings1() {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesSetFor7Zip();

        // when
        XmlRulesSetManager.saveSettings(rulesSet, TestVariables.XML_RULES_SET_OF_7ZIP_RUNTIME);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RULES_SET_OF_7ZIP_RUNTIME);
        String patternSource = TestVariables.readFile(TestVariables.XML_RULES_SET_OF_7ZIP_PATTERN);
        Assert.assertEquals(patternSource, runtime);
    }

    @Test public void loadSettings1() {
        // when
        Deque<RuleBased> rulesSet = XmlRulesSetManager.loadSettings(TestVariables.XML_RULES_SET_OF_7ZIP_PATTERN);

        // then
        Assert.assertEquals(TestCollections.getRulesSetFor7Zip().toString(), rulesSet.toString());
    }

    @Test public void saveSettings2() {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesSetForAimp();

        // when
        XmlRulesSetManager.saveSettings(rulesSet, TestVariables.XML_RULES_SET_OF_AIMP_RUNTIME);

        // then
        String runtime = TestVariables.readFile(TestVariables.XML_RULES_SET_OF_AIMP_RUNTIME);
        String patternSource = TestVariables.readFile(TestVariables.XML_RULES_SET_OF_AIMP_PATTERN);
        Assert.assertEquals(patternSource, runtime);
    }

    @Test public void loadSettings2() {
        // when
        Deque<RuleBased> rulesSet = XmlRulesSetManager.loadSettings(TestVariables.XML_RULES_SET_OF_AIMP_PATTERN);

        // then
        Assert.assertEquals(TestCollections.getRulesSetForAimp().toString(), rulesSet.toString());
    }

}
