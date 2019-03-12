package net.vego1mar.rules;

import static net.vego1mar.tests.TestVariables.getWorkingDirectory;
import static net.vego1mar.tests.TestVariables.readFile;

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

public class BattleNetExecutorTest extends ExecutorTest {

    public BattleNetExecutorTest() {
        final String relationalPath1 = "/src/test/resources/BlizzardBattleNet1_wp.txt";
        final String codePath1 = Paths.get(getWorkingDirectory(), relationalPath1).toString();
        setWebPage1(readFile(codePath1));
        final String relationalPath2 = "/src/test/resources/BlizzardBattleNet2_wp.txt";
        final String codePath2 = Paths.get(getWorkingDirectory(), relationalPath2).toString();
        setWebPage2(readFile(codePath2));
    }

    @Override public Deque<Rule> getRules(int setNo) {
        if (setNo == 1) {
            return getRulesFromSet1(6);
        }

        return getRulesFromSet2(7);
    }

    public Deque<Rule> getRulesFromSet1(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setText("gameProgram[bnetapp]");
        method1.setType(FirstOfType.STRING);

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method2 = (RetrieveTagsMethod) rule2.getMethod();
        method2.setTagname("a");
        method2.setType(RetrieveTagsType.ALL);

        Rule rule3 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method3 = (ExtractWordMethod) rule3.getMethod();
        method3.setPosition(11);

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        rule4.getTarget().linkID(LinksID.WINDOWS_X86_EXE);

        Rule rule5 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method5 = (ExtractWordMethod) rule5.getMethod();
        method5.setPosition(25);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        rule6.getTarget().linkID(LinksID.MAC_OS_X_ZIP);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    public Deque<Rule> getRulesFromSet2(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("version");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method2 = (GrabUntilMethod) rule2.getMethod();
        method2.setCharStop('<');

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method3 = (TrimMethod) rule3.getMethod();
        method3.setSide(TrimSide.LEFT);
        method3.setNumberOf(9);
        rule3.getTarget().version(Platforms.WINDOWS);

        Rule rule4 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method4 = (FirstOfMethod) rule4.getMethod();
        method4.setType(FirstOfType.STRING);
        method4.setText("Aktualizacja:");

        Rule rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method5 = (RetrieveTagsMethod) rule5.getMethod();
        method5.setType(RetrieveTagsType.FIRST);
        method5.setTagname("span");

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method6 = (TrimMethod) rule6.getMethod();
        method6.setSide(TrimSide.LEFT);
        method6.setNumberOf(6);

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method7 = (TrimMethod) rule7.getMethod();
        method7.setSide(TrimSide.RIGHT);
        method7.setNumberOf(7);
        rule7.getTarget().date(Platforms.WINDOWS);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @NotNull @Contract(pure = true) private String getExpectedMacOsXLinkFromSet1() {
        return "https://www.battle.net/download/getInstallerForGame?os=mac&amp;locale=plPL&amp;version=LIVE&amp;gameProgram=BATTLENET_APP";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsLinkFromSet1() {
        return "https://www.battle.net/download/getInstallerForGame?os=win&amp;locale=plPL&amp;version=LIVE&amp;gameProgram=BATTLENET_APP";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsVersionFromSet2() {
        return "1.16.0";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsDateFromSet2() {
        return "08.02.2019";
    }

    @Test public void testLinksGrabbingFromSet1() {
        // given
        // rules 1-6
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(6), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsLinkFromSet1(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedMacOsXLinkFromSet1(), data.getLinks().getItem(LinksID.MAC_OS_X_ZIP));
    }

    @Test public void testVersionsGrabbingFromSet2() {
        // given
        // rules 1-3
        RulesExecutor executor = new RulesExecutor(getRulesFromSet2(3), getWebPage2());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsVersionFromSet2(), data.getVersions().getItem(Platforms.WINDOWS));
    }

    @Test public void testDatesGrabbingFromSet2() {
        // given
        // rules 4-7
        RulesExecutor executor = new RulesExecutor(getRulesFromSet2(7), getWebPage2());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsDateFromSet2(), data.getDates().getItem(Platforms.WINDOWS));
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedWindowsLinkFromSet1(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedMacOsXLinkFromSet1(), data.getLinks().getItem(LinksID.MAC_OS_X_ZIP));
        Assert.assertEquals(getExpectedWindowsVersionFromSet2(), data.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedWindowsDateFromSet2(), data.getDates().getItem(Platforms.WINDOWS));
    }

}