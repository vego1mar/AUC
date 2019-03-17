package net.vego1mar.xml;

import static net.vego1mar.tests.TestHelper.getWorkingDirectory;

import java.nio.file.Paths;
import java.util.Deque;
import net.vego1mar.rules.Rule;
import net.vego1mar.rules.VirtualBoxExecutorTest;
import net.vego1mar.tests.TestHelper;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class VirtualBoxXmlTest extends XmlTest {

    private String xmlRuntime3;
    private String xmlRuntimeA;
    private String xmlRuntimeB;
    private String xmlPattern3;
    private String xmlPatternA;
    private String xmlPatternB;

    public VirtualBoxXmlTest() {
        super(new VirtualBoxExecutorTest());
        setXmlRuntime1(Paths.get(getWorkingDirectory(), "/runtime/VBox1.xml").toString());
        setXmlRuntime2(Paths.get(getWorkingDirectory(), "/runtime/VBox2.xml").toString());
        setXmlRuntime3(Paths.get(getWorkingDirectory(), "/runtime/VBox3.xml").toString());
        setXmlRuntimeA(Paths.get(getWorkingDirectory(), "/runtime/VBox_A.xml").toString());
        setXmlRuntimeB(Paths.get(getWorkingDirectory(), "/runtime/VBox_B.xml").toString());
        setXmlPattern1(Paths.get(getWorkingDirectory(), "/src/test/resources/VBox1_ptrn.xml").toString());
        setXmlPattern2(Paths.get(getWorkingDirectory(), "/src/test/resources/VBox2_ptrn.xml").toString());
        setXmlPattern3(Paths.get(getWorkingDirectory(), "/src/test/resources/VBox3_ptrn.xml").toString());
        setXmlPatternA(Paths.get(getWorkingDirectory(), "/src/test/resources/VBox_A_ptrn.xml").toString());
        setXmlPatternB(Paths.get(getWorkingDirectory(), "/src/test/resources/VBox_B_ptrn.xml").toString());
    }

    @Test @Override public void testXmlConstruction() {
        testLoadSettings();
        testSaveSettings();
    }

    @Test @Override public void testSaveSettings() {
        // given
        Deque<Rule> rulesSet1 = getAsserter().getRules(1);
        Deque<Rule> rulesSet2 = getAsserter().getRules(2);
        Deque<Rule> rulesSet3 = getAsserter().getRules(3);
        Deque<Rule> rulesSetA = getAsserter().getRules('A');
        Deque<Rule> rulesSetB = getAsserter().getRules('B');
        XmlRulesSetWriter writer = new XmlRulesSetWriter();

        // when
        writer.saveSettings(rulesSet1, getXmlRuntime1());
        writer.saveSettings(rulesSet2, getXmlRuntime2());
        writer.saveSettings(rulesSet3, getXmlRuntime3());
        writer.saveSettings(rulesSetA, getXmlRuntimeA());
        writer.saveSettings(rulesSetB, getXmlRuntimeB());

        // then
        String runtime1 = TestHelper.readFile(getXmlRuntime1());
        String runtime2 = TestHelper.readFile(getXmlRuntime2());
        String runtime3 = TestHelper.readFile(getXmlRuntime3());
        String runtimeA = TestHelper.readFile(getXmlRuntimeA());
        String runtimeB = TestHelper.readFile(getXmlRuntimeB());
        String source1 = TestHelper.readFile(getXmlPattern1());
        String source2 = TestHelper.readFile(getXmlPattern2());
        String source3 = TestHelper.readFile(getXmlPattern3());
        String sourceA = TestHelper.readFile(getXmlPatternA());
        String sourceB = TestHelper.readFile(getXmlPatternB());
        Assert.assertEquals(source1, runtime1);
        Assert.assertEquals(source2, runtime2);
        Assert.assertEquals(source3, runtime3);
        Assert.assertEquals(sourceA, runtimeA);
        Assert.assertEquals(sourceB, runtimeB);
    }

    @Test @Override public void testLoadSettings() {
        // given
        XmlRulesSetReader reader = new XmlRulesSetReader();

        // when
        Deque<Rule> rulesSet1 = reader.loadSettings(getXmlPattern1());
        Deque<Rule> rulesSet2 = reader.loadSettings(getXmlPattern2());
        Deque<Rule> rulesSet3 = reader.loadSettings(getXmlPattern3());
        Deque<Rule> rulesSetA = reader.loadSettings(getXmlPatternA());
        Deque<Rule> rulesSetB = reader.loadSettings(getXmlPatternB());

        // then
        Assert.assertEquals(getAsserter().getRules(1).toString(), rulesSet1.toString());
        Assert.assertEquals(getAsserter().getRules(2).toString(), rulesSet2.toString());
        Assert.assertEquals(getAsserter().getRules(3).toString(), rulesSet3.toString());
        Assert.assertEquals(getAsserter().getRules('A').toString(), rulesSetA.toString());
        Assert.assertEquals(getAsserter().getRules('B').toString(), rulesSetB.toString());
    }

    public String getXmlRuntime3() {
        return xmlRuntime3;
    }

    protected void setXmlRuntime3(@NotNull String xmlRuntime3) {
        this.xmlRuntime3 = xmlRuntime3;
    }

    public String getXmlRuntimeA() {
        return xmlRuntimeA;
    }

    protected void setXmlRuntimeA(@NotNull String xmlRuntimeA) {
        this.xmlRuntimeA = xmlRuntimeA;
    }

    public String getXmlRuntimeB() {
        return xmlRuntimeB;
    }

    protected void setXmlRuntimeB(@NotNull String xmlRuntimeB) {
        this.xmlRuntimeB = xmlRuntimeB;
    }

    public String getXmlPattern3() {
        return xmlPattern3;
    }

    protected void setXmlPattern3(@NotNull String xmlPattern3) {
        this.xmlPattern3 = xmlPattern3;
    }

    public String getXmlPatternA() {
        return xmlPatternA;
    }

    protected void setXmlPatternA(@NotNull String xmlPatternA) {
        this.xmlPatternA = xmlPatternA;
    }

    public String getXmlPatternB() {
        return xmlPatternB;
    }

    protected void setXmlPatternB(@NotNull String xmlPatternB) {
        this.xmlPatternB = xmlPatternB;
    }

}
