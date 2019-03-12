package net.vego1mar.xml;

import java.util.Deque;
import net.vego1mar.rules.ExecutorTest;
import net.vego1mar.rules.Rule;
import net.vego1mar.tests.TestVariables;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public abstract class XmlTest {

    private String xmlPattern;
    private String xmlRuntime;
    private ExecutorTest asserter;

    protected XmlTest(ExecutorTest asserter) {
        this.asserter = asserter;
        setXmlRuntime("");
        setXmlPattern("");
    }

    public String getXmlRuntime() {
        return xmlRuntime;
    }

    protected void setXmlRuntime(@NotNull String xmlRuntime) {
        this.xmlRuntime = xmlRuntime;
    }

    public String getXmlPattern() {
        return xmlPattern;
    }

    protected void setXmlPattern(@NotNull String xmlPattern) {
        this.xmlPattern = xmlPattern;
    }

    protected ExecutorTest getAsserter() {
        return asserter;
    }

    @Test public void testSaveSettings() {
        // given
        Deque<Rule> rulesSet = getAsserter().getRules(1);
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet, getXmlRuntime());

        // then
        String runtime = TestVariables.readFile(getXmlRuntime());
        String source = TestVariables.readFile(getXmlPattern());
        Assert.assertEquals(source, runtime);
    }

    @Test public void testLoadSettings() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet = reader.loadSettings(getXmlPattern());

        // then
        Assert.assertEquals(getAsserter().getRules(1).toString(), rulesSet.toString());
    }

    @Test public abstract void testXmlConstruction();

}
