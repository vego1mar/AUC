package net.vego1mar.xml;

import java.util.Deque;
import net.vego1mar.rules.Rule;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

@Deprecated public class XmlRulesSetReaderTest {

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

}
