package net.vego1mar.rules;

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
import net.vego1mar.tests.TestVariables;
import net.vego1mar.utils.MethodCreator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class SourceTreeExecutorTest implements ExecutorTestImpl {

    private final int SET1_SIZE = 16;
    private final String webPage;

    public SourceTreeExecutorTest() {
        final String relationalPath = "/src/test/resources/sourcetree_wp.txt";
        final String codePath = Paths.get(TestVariables.getWorkingDirectory(), relationalPath).toString();
        webPage = TestVariables.readFile(codePath);
    }

    private Deque<Rule> getRulesFromSet1(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method1 = (RetrieveTagsMethod) rule1.getMethod();
        method1.setType(RetrieveTagsType.ALL);
        method1.setTagname("a");

        Rule rule2 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FETCH_HREFS));

        Rule rule3 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method3 = (ExtractWordMethod) rule3.getMethod();
        method3.setPosition(3);
        rule3.getTarget().linkID(LinksID.MAC_OS_X_ZIP);

        Rule rule4 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method4 = (ExtractWordMethod) rule4.getMethod();
        method4.setPosition(4);
        rule4.getTarget().linkID(LinksID.WINDOWS_X86_EXE);

        Rule rule5 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method5 = (FirstOfMethod) rule5.getMethod();
        method5.setType(FirstOfType.STRING);
        method5.setText("LastRendered:");

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method6 = (GrabUntilMethod) rule6.getMethod();
        method6.setCharStop('-');

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method7 = (TrimMethod) rule7.getMethod();
        method7.setSide(TrimSide.LEFT);
        method7.setNumberOf(14);

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method8 = (TrimMethod) rule8.getMethod();
        method8.setSide(TrimSide.RIGHT);
        method8.setNumberOf(12);
        rule8.getTarget().date(Platforms.ALL_SUPPORTED);

        Rule rule9 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method9 = (ExtractWordMethod) rule9.getMethod();
        method9.setPosition(3);

        Rule rule10 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.REMOVE_CHARACTERS));
        RemoveCharactersMethod method10 = (RemoveCharactersMethod) rule10.getMethod();
        method10.setSigns("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ:/-");

        Rule rule11 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method11 = (TrimMethod) rule11.getMethod();
        method11.setSide(TrimSide.LEFT);
        method11.setNumberOf(3);

        Rule rule12 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method12 = (TrimMethod) rule12.getMethod();
        method12.setSide(TrimSide.RIGHT);
        method12.setNumberOf(1);
        rule12.getTarget().version(Platforms.MAC_OS_X);

        Rule rule13 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(4);

        Rule rule14 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.REMOVE_CHARACTERS));
        RemoveCharactersMethod method14 = (RemoveCharactersMethod) rule14.getMethod();
        method14.setSigns("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ:/-");

        Rule rule15 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method15 = (TrimMethod) rule15.getMethod();
        method15.setSide(TrimSide.LEFT);
        method15.setNumberOf(2);

        Rule rule16 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method16 = (TrimMethod) rule16.getMethod();
        method16.setSide(TrimSide.RIGHT);
        method16.setNumberOf(1);
        rule16.getTarget().version(Platforms.WINDOWS);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10,
            rule11, rule12, rule13, rule14, rule15, rule16);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @Override public Deque<Rule> getRules(int setNo) {
        return getRulesFromSet1(SET1_SIZE);
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsLinkFromSet1() {
        return "https://product-downloads.atlassian.com/software/sourcetree/windows/ga/SourceTreeSetup-3.0.17.exe";
    }

    @NotNull @Contract(pure = true) private String getExpectedMacOsXLinkFromSet1() {
        return "https://product-downloads.atlassian.com/software/sourcetree/ga/Sourcetree_3.1.1_213.zip";
    }

    @NotNull @Contract(pure = true) private String getExpectedAllSupportedDateFromSet1() {
        return "Mar 8, 2019";
    }

    @NotNull @Contract(pure = true) private String getExpectedMacOsXVersionFromSet1() {
        return "3.1.1_213";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsVersionFromSet1() {
        return "3.0.17";
    }

    @Test public void testLinksGrabbingFromSet1() {
        // given
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(4), webPage);

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsLinkFromSet1(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedMacOsXLinkFromSet1(), data.getLinks().getItem(LinksID.MAC_OS_X_ZIP));
    }

    @Test public void testDatesGrabbingFromSet1() {
        // given
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(8), webPage);

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsLinkFromSet1(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedMacOsXLinkFromSet1(), data.getLinks().getItem(LinksID.MAC_OS_X_ZIP));
        Assert.assertEquals(getExpectedAllSupportedDateFromSet1(), data.getDates().getItem(Platforms.ALL_SUPPORTED));
    }

    @Test public void testVersionsGrabbingFromSet1() {
        // given
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(SET1_SIZE), webPage);

        // when
        executor.execute();

        // then
        assertCollectedDataForExpectedOutput(executor.getResults());
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedWindowsLinkFromSet1(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedMacOsXLinkFromSet1(), data.getLinks().getItem(LinksID.MAC_OS_X_ZIP));
        Assert.assertEquals(getExpectedAllSupportedDateFromSet1(), data.getDates().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals(getExpectedMacOsXVersionFromSet1(), data.getVersions().getItem(Platforms.MAC_OS_X));
        Assert.assertEquals(getExpectedWindowsVersionFromSet1(), data.getVersions().getItem(Platforms.WINDOWS));
    }
}
