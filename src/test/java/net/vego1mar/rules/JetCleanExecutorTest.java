package net.vego1mar.rules;

import static net.vego1mar.tests.TestHelper.getWorkingDirectory;
import static net.vego1mar.tests.TestHelper.readFile;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import net.vego1mar.method.ExtractWordMethod;
import net.vego1mar.method.FirstOfMethod;
import net.vego1mar.method.GrabUntilMethod;
import net.vego1mar.method.RetrieveTagsMethod;
import net.vego1mar.method.TrimMethod;
import net.vego1mar.method.enumerators.FirstOfType;
import net.vego1mar.method.enumerators.RetrieveTagsType;
import net.vego1mar.method.enumerators.TrimSide;
import net.vego1mar.properties.UseAsProperty;
import net.vego1mar.properties.enumerators.LinksID;
import net.vego1mar.properties.enumerators.Platforms;
import net.vego1mar.target.Target;
import net.vego1mar.target.enumerators.In;
import net.vego1mar.target.enumerators.UseAs;
import net.vego1mar.utils.MethodFactory;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class JetCleanExecutorTest extends ExecutorTest {

    public JetCleanExecutorTest() {
        final String relationalPath1 = "/src/test/resources/jetclean_wp.txt";
        final String codePath1 = Paths.get(getWorkingDirectory(), relationalPath1).toString();
        setFilePath1(codePath1);
        setWebPage1(readFile(getFilePath1()));
        final String relationalPath2 = "/src/test/resources/jetclean_wp2.txt";
        final String codePath2 = Paths.get(getWorkingDirectory(), relationalPath2).toString();
        setFilePath2(codePath2);
        setWebPage2(readFile(getFilePath2()));
    }

    @Override public Deque<Rule> getRules(int setNo) {
        if (setNo == 1) {
            return getRulesFromSet1(7);
        }

        return getRulesFromSet2(3);
    }

    private Deque<Rule> getRulesFromSet1(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodFactory.createRetrieveTags());
        RetrieveTagsMethod method1 = (RetrieveTagsMethod) rule1.getMethod();
        method1.setType(RetrieveTagsType.FIRST);
        method1.setTagname("title");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createExtractWord());
        ExtractWordMethod method2 = (ExtractWordMethod) rule2.getMethod();
        method2.setPosition(3);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodFactory.createTrim());
        TrimMethod method3 = (TrimMethod) rule3.getMethod();
        method3.setSide(TrimSide.RIGHT);
        method3.setNumberOf(8);
        rule3.getTarget().version(Platforms.WINDOWS);

        Rule rule4 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method4 = (FirstOfMethod) rule4.getMethod();
        method4.setType(FirstOfType.STRING);
        method4.setText("Debug:");

        Rule rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method5 = (TrimMethod) rule5.getMethod();
        method5.setSide(TrimSide.LEFT);
        method5.setNumberOf(7);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createGrabUntil());
        GrabUntilMethod method6 = (GrabUntilMethod) rule6.getMethod();
        method6.setCharStop('>');

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createTrim());
        TrimMethod method7 = (TrimMethod) rule7.getMethod();
        method7.setSide(TrimSide.RIGHT);
        method7.setNumberOf(3);
        rule7.getTarget().linkID(LinksID.WINDOWS_X86_EXE);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    private Deque<Rule> getRulesFromSet2(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("<strong>Date:");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createSplitWords());

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodFactory.createExtractWord());
        ExtractWordMethod method3 = (ExtractWordMethod) rule3.getMethod();
        method3.setPosition(2);
        rule3.getTarget().date(Platforms.WINDOWS);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsVersion() {
        return "1.5.0.129";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows32bitLink() {
        return "http://download.bluesprig.com/dl/jetclean-setup.exe";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsDate() {
        return "02/26/2016";
    }

    @Test public void testVersionGrabbing() {
        // given
        // rules 1-3
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(3), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsVersion(), data.getVersions().getItem(Platforms.WINDOWS));
    }

    @Test public void testLinkGrabbing() {
        // given
        // rules 4-7
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(7), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindows32bitLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
    }

    @Test public void testDateGrabbing() {
        // given
        // rules 1-3
        RulesExecutor executor = new RulesExecutor(getRulesFromSet2(3), getWebPage2());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsDate(), data.getDates().getItem(Platforms.WINDOWS));
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedWindowsVersion(), data.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedWindows32bitLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedWindowsDate(), data.getDates().getItem(Platforms.WINDOWS));
    }

}
