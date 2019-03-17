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
import net.vego1mar.method.RemoveCharactersMethod;
import net.vego1mar.method.RetrieveTagsMethod;
import net.vego1mar.method.TrimMethod;
import net.vego1mar.method.enumerators.FirstOfType;
import net.vego1mar.method.enumerators.MethodType;
import net.vego1mar.method.enumerators.RetrieveTagsType;
import net.vego1mar.method.enumerators.TrimSide;
import net.vego1mar.properties.UseAsProperty;
import net.vego1mar.properties.enumerators.LinksID;
import net.vego1mar.properties.enumerators.Platforms;
import net.vego1mar.target.Target;
import net.vego1mar.target.enumerators.In;
import net.vego1mar.target.enumerators.UseAs;
import net.vego1mar.utils.MethodCreator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class PotPlayerExecutorTest extends ExecutorTest {

    public PotPlayerExecutorTest() {
        final String relationalPath = "/src/test/resources/PotPlayer_wp.txt";
        final String codePath = Paths.get(getWorkingDirectory(), relationalPath).toString();
        setFilePath1(codePath);
        setWebPage1(readFile(getFilePath1()));
    }

    @Override public Deque<Rule> getRules(int setNo) {
        return getRulesFromSet1(12);
    }

    private Deque<Rule> getRulesFromSet1(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("<strong>");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method2 = (GrabUntilMethod) rule2.getMethod();
        method2.setCharStop(')');

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.SPLIT_WORDS));

        Rule rule4 = new Rule(new Target(In.COLLECTION, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method4 = (ExtractWordMethod) rule4.getMethod();
        method4.setPosition(3);
        rule4.getTarget().version(Platforms.WINDOWS);

        Rule rule5 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method5 = (ExtractWordMethod) rule5.getMethod();
        method5.setPosition(8);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        rule6.getTarget().linkID(LinksID.WINDOWS_X86_EXE);

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.REMOVE_CHARACTERS));
        RemoveCharactersMethod method7 = (RemoveCharactersMethod) rule7.getMethod();
        method7.setSigns("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.:-");

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method8 = (TrimMethod) rule8.getMethod();
        method8.setSide(TrimSide.LEFT);
        method8.setNumberOf(5);

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method9 = (TrimMethod) rule9.getMethod();
        method9.setSide(TrimSide.RIGHT);
        method9.setNumberOf(1);
        rule9.getTarget().date(Platforms.WINDOWS);

        Rule rule10 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method10 = (FirstOfMethod) rule10.getMethod();
        method10.setType(FirstOfType.STRING);
        method10.setText("for 64-bit Windows");

        Rule rule11 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method11 = (RetrieveTagsMethod) rule11.getMethod();
        method11.setType(RetrieveTagsType.FIRST);
        method11.setTagname("a");

        Rule rule12 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        rule12.getTarget().linkID(LinksID.WINDOWS_X64_EXE);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10, rule11, rule12);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsVersion() {
        return "1.7.17508";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsDate() {
        return "2019/02";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows32bitLink() {
        return "https://daumpotplayer.com/wp-content/uploads/2019/02/PotPlayerSetup.exe";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows64bitLink() {
        return "https://daumpotplayer.com/wp-content/uploads/2019/02/PotPlayerSetup64.exe";
    }

    @Test public void testVersionGrabbing() {
        // given
        // rules 1-4
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(4), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsVersion(), data.getVersions().getItem(Platforms.WINDOWS));
    }

    @Test public void testLink1Grabbing() {
        // given
        // rules 5-6
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(6), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindows32bitLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
    }

    @Test public void testDateGrabbing() {
        // given
        // rules 7-9
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(9), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsDate(), data.getDates().getItem(Platforms.WINDOWS));
    }

    @Test public void testLinksGrabbing() {
        // given
        // rules 10-12
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(12), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindows64bitLink(), data.getLinks().getItem(LinksID.WINDOWS_X64_EXE));
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedWindowsVersion(), data.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedWindows32bitLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedWindowsDate(), data.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedWindows64bitLink(), data.getLinks().getItem(LinksID.WINDOWS_X64_EXE));
    }

}
