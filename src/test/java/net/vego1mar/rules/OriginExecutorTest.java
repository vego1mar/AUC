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
import net.vego1mar.method.PrependMethod;
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

public class OriginExecutorTest extends ExecutorTest {

    public OriginExecutorTest() {
        final String relationalPath = "/src/test/resources/Origin_wp1.txt";
        final String codePath = Paths.get(getWorkingDirectory(), relationalPath).toString();
        setFilePath1(codePath);
        setWebPage1(readFile(getFilePath1()));
    }

    @Override public Deque<Rule> getRules(int setNo) {
        return getRulesFromSet1(16);
    }

    private Deque<Rule> getRulesFromSet1(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method1 = (RetrieveTagsMethod) rule1.getMethod();
        method1.setType(RetrieveTagsType.ALL);
        method1.setTagname("h1");

        Rule rule2 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method2 = (ExtractWordMethod) rule2.getMethod();
        method2.setPosition(2);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.REMOVE_CHARACTERS));
        RemoveCharactersMethod method3 = (RemoveCharactersMethod) rule3.getMethod();
        method3.setSigns("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/<=\"\n");

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.SPLIT_WORDS));

        Rule rule5 = new Rule(new Target(In.COLLECTION, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method5 = (ExtractWordMethod) rule5.getMethod();
        method5.setPosition(6);
        rule5.getTarget().version(Platforms.ALL_SUPPORTED);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        rule6.getTarget().version(Platforms.WINDOWS);

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        rule7.getTarget().version(Platforms.MAC_OS_X);

        Rule rule8 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method8 = (FirstOfMethod) rule8.getMethod();
        method8.setType(FirstOfType.STRING);
        method8.setText("<strong>Date:");

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method9 = (GrabUntilMethod) rule9.getMethod();
        method9.setCharStop('\n');

        Rule rule10 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method10 = (TrimMethod) rule10.getMethod();
        method10.setSide(TrimSide.LEFT);
        method10.setNumberOf(23);
        rule10.getTarget().date(Platforms.ALL_SUPPORTED);

        Rule rule11 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        rule11.getTarget().date(Platforms.WINDOWS);

        Rule rule12 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        rule12.getTarget().date(Platforms.MAC_OS_X);

        Rule rule13 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.REMOVE_CHARACTERS));
        RemoveCharactersMethod method13 = (RemoveCharactersMethod) rule13.getMethod();
        method13.setSigns("0123456789/");

        Rule rule14 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.PREPEND));
        PrependMethod method14 = (PrependMethod) rule14.getMethod();
        method14.setText("https://www.dm.origin.com/download/legacy");
        rule14.getTarget().linkID(LinksID.WINDOWS_X86_EXE);

        Rule rule15 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method15 = (ExtractWordMethod) rule15.getMethod();
        method15.setPosition(2);

        Rule rule16 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.PREPEND));
        PrependMethod method16 = (PrependMethod) rule16.getMethod();
        method16.setText("https://www.dm.origin.com/mac/download/legacy");
        rule16.getTarget().linkID(LinksID.MAC_OS_X_DMG);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10, rule11, rule12,
            rule13, rule14, rule15, rule16);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @NotNull @Contract(pure = true) private String getExpectedAllSupportedVersionFromSet1() {
        return "10.5.35";
    }

    @NotNull @Contract(pure = true) private String getExpectedAllSupportedDateFromSet1() {
        return "02/26/2019 08:04 AM";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsDateFromSet1() {
        return "02/26/2019";
    }

    @NotNull @Contract(pure = true) private String getExpectedMacOsXDateFromSet1() {
        return getExpectedWindowsDateFromSet1();
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsLinkFromSet1() {
        return "https://www.dm.origin.com/download/legacy";
    }

    @NotNull @Contract(pure = true) private String getExpectedMacOsXLinkFromSet1() {
        return "https://www.dm.origin.com/mac/download/legacy";
    }

    @Test public void testVersionsGrabbingFromSet1() {
        // given
        // rules 1-7
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(7), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAllSupportedVersionFromSet1(), data.getVersions().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals(getExpectedAllSupportedVersionFromSet1(), data.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedAllSupportedVersionFromSet1(), data.getVersions().getItem(Platforms.MAC_OS_X));
    }

    @Test public void testDatesGrabbingFromSet1() {
        // given
        // rules 8-12
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(12), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAllSupportedDateFromSet1(), data.getDates().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals(getExpectedWindowsDateFromSet1(), data.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedMacOsXDateFromSet1(), data.getDates().getItem(Platforms.MAC_OS_X));
    }

    @Test public void testLinksGrabbingFromSet1() {
        // given
        // rules 13-16
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(16), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsLinkFromSet1(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedMacOsXLinkFromSet1(), data.getLinks().getItem(LinksID.MAC_OS_X_DMG));
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedAllSupportedVersionFromSet1(), data.getVersions().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals(getExpectedAllSupportedVersionFromSet1(), data.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedAllSupportedVersionFromSet1(), data.getVersions().getItem(Platforms.MAC_OS_X));
        Assert.assertEquals(getExpectedAllSupportedDateFromSet1(), data.getDates().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals(getExpectedWindowsDateFromSet1(), data.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedMacOsXDateFromSet1(), data.getDates().getItem(Platforms.MAC_OS_X));
        Assert.assertEquals(getExpectedWindowsLinkFromSet1(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedMacOsXLinkFromSet1(), data.getLinks().getItem(LinksID.MAC_OS_X_DMG));
    }

}
