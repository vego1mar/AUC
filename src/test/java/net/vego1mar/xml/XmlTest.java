package net.vego1mar.xml;

import java.util.Deque;
import net.vego1mar.rules.ExecutorTest;
import net.vego1mar.rules.Rule;
import net.vego1mar.tests.TestHelper;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public abstract class XmlTest {

    private String xmlPattern1;
    private String xmlPattern2;
    private String xmlRuntime1;
    private String xmlRuntime2;
    private ExecutorTest asserter;

    protected XmlTest(ExecutorTest asserter) {
        this.asserter = asserter;
        setXmlRuntime1("");
        setXmlRuntime2("");
        setXmlPattern1("");
        setXmlPattern2("");
    }

    public String getXmlRuntime1() {
        return xmlRuntime1;
    }

    protected void setXmlRuntime1(@NotNull String xmlRuntime1) {
        this.xmlRuntime1 = xmlRuntime1;
    }

    public String getXmlPattern1() {
        return xmlPattern1;
    }

    protected void setXmlPattern1(@NotNull String xmlPattern1) {
        this.xmlPattern1 = xmlPattern1;
    }

    public String getXmlPattern2() {
        return xmlPattern2;
    }

    protected void setXmlPattern2(@NotNull String xmlPattern2) {
        this.xmlPattern2 = xmlPattern2;
    }

    public String getXmlRuntime2() {
        return xmlRuntime2;
    }

    protected void setXmlRuntime2(@NotNull String xmlRuntime2) {
        this.xmlRuntime2 = xmlRuntime2;
    }

    protected ExecutorTest getAsserter() {
        return asserter;
    }

    @Test public void testSaveSettings() {
        // given
        Deque<Rule> rulesSet = getAsserter().getRules(1);
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet, getXmlRuntime1());

        // then
        String runtime = TestHelper.readFile(getXmlRuntime1());
        String source = TestHelper.readFile(getXmlPattern1());
        Assert.assertEquals(source, runtime);
    }

    @Test public void testLoadSettings() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet = reader.loadSettings(getXmlPattern1());

        // then
        Assert.assertEquals(getAsserter().getRules(1).toString(), rulesSet.toString());
    }

    @Test public abstract void testXmlConstruction();

}
