package net.vego1mar.rules;

import static net.vego1mar.tests.TestHelper.getWorkingDirectory;
import static net.vego1mar.tests.TestHelper.readFile;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import net.vego1mar.method.AppendMethod;
import net.vego1mar.method.ExtractWordMethod;
import net.vego1mar.method.FirstOfMethod;
import net.vego1mar.method.GrabUntilMethod;
import net.vego1mar.method.PrependMethod;
import net.vego1mar.method.RemoveCharactersMethod;
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

public class HashTabExecutorTest extends ExecutorTest {

    public HashTabExecutorTest() {
        final String relationalPath1 = "/src/test/resources/HashTab_webPage1.txt";
        final String codePath1 = Paths.get(getWorkingDirectory(), relationalPath1).toString();
        setFilePath1(codePath1);
        setWebPage1(readFile(getFilePath1()));
        final String relationalPath2 = "/src/test/resources/HashTab_webPage2.txt";
        final String codePath2 = Paths.get(getWorkingDirectory(), relationalPath2).toString();
        setFilePath2(codePath2);
        setWebPage2(readFile(getFilePath2()));
    }

    @Override public Deque<Rule> getRules(int setNo) {
        if (setNo == 1) {
            return getRulesFromSet1(10);
        }

        return getRulesFromSet2(5);
    }

    private Deque<Rule> getRulesFromSet1(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createAppend());
        AppendMethod method1 = (AppendMethod) rule1.getMethod();
        method1.setText(getExpectedGenericLink());
        rule1.getTarget().linkID(LinksID.GENERIC);

        Rule rule2 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodFactory.createRetrieveTags());
        RetrieveTagsMethod method2 = (RetrieveTagsMethod) rule2.getMethod();
        method2.setType(RetrieveTagsType.FIRST);
        method2.setTagname("section");

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createRetrieveTags());
        RetrieveTagsMethod method3 = (RetrieveTagsMethod) rule3.getMethod();
        method3.setType(RetrieveTagsType.ALL);
        method3.setTagname("a");

        Rule rule4 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createFetchHrefs());

        Rule rule5 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createExtractWord());
        ExtractWordMethod method5 = (ExtractWordMethod) rule5.getMethod();
        method5.setPosition(1);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method6 = (TrimMethod) rule6.getMethod();
        method6.setSide(TrimSide.LEFT);
        method6.setNumberOf(2);

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createPrepend());
        PrependMethod method7 = (PrependMethod) rule7.getMethod();
        method7.setText(getExpectedGenericLink());
        rule7.getTarget().linkID(LinksID.WINDOWS_X64_EXE);

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createRemoveCharacters());
        RemoveCharactersMethod method8 = (RemoveCharactersMethod) rule8.getMethod();
        method8.setSigns("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ:/_");

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method9 = (TrimMethod) rule9.getMethod();
        method9.setSide(TrimSide.LEFT);
        method9.setNumberOf(1);

        Rule rule10 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodFactory.createTrim());
        TrimMethod method10 = (TrimMethod) rule10.getMethod();
        method10.setSide(TrimSide.RIGHT);
        method10.setNumberOf(1);
        rule10.getTarget().version(Platforms.WINDOWS);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10);

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
        method1.setText("<meta itemprop=\"datePublished\"");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method2 = (TrimMethod) rule2.getMethod();
        method2.setSide(TrimSide.LEFT);
        method2.setNumberOf(30);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method3 = (FirstOfMethod) rule3.getMethod();
        method3.setType(FirstOfType.STRING);
        method3.setText("\"");

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method4 = (TrimMethod) rule4.getMethod();
        method4.setSide(TrimSide.LEFT);
        method4.setNumberOf(1);

        Rule rule5 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodFactory.createGrabUntil());
        GrabUntilMethod method5 = (GrabUntilMethod) rule5.getMethod();
        method5.setCharStop('\"');
        rule5.getTarget().date(Platforms.WINDOWS);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @NotNull @Contract(pure = true) private String getExpectedGenericLink() {
        return "http://implbits.com/products/hashtab/";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsAnyLink() {
        return "http://implbits.com/products/hashtab/HashTab_v6.0.0.34_Setup.exe";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsVersion() {
        return "6.0.0.34";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsDate() {
        return "07/21/2017 08:00 AM";
    }

    @Test public void testLinksGrabbing() {
        // given
        // rules 1-7
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(7), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedGenericLink(), data.getLinks().getItem(LinksID.GENERIC));
        Assert.assertEquals(getExpectedWindowsAnyLink(), data.getLinks().getItem(LinksID.WINDOWS_X64_EXE));
    }

    @Test public void testVersionGrabbing() {
        // given
        // rules 8-10
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(10), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsVersion(), data.getVersions().getItem(Platforms.WINDOWS));
    }

    @Test public void testDateGrabbing() {
        // given
        // rules 1-5
        RulesExecutor executor = new RulesExecutor(getRulesFromSet2(5), getWebPage2());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsDate(), data.getDates().getItem(Platforms.WINDOWS));
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedGenericLink(), data.getLinks().getItem(LinksID.GENERIC));
        Assert.assertEquals(getExpectedWindowsAnyLink(), data.getLinks().getItem(LinksID.WINDOWS_X64_EXE));
        Assert.assertEquals(getExpectedWindowsDate(), data.getDates().getItem(Platforms.WINDOWS));
    }

}
