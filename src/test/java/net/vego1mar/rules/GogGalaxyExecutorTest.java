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

public class GogGalaxyExecutorTest extends ExecutorTest {

    public GogGalaxyExecutorTest() {
        final String relationalPath1 = "/src/test/resources/GOG-Galaxy_webPage1.txt";
        final String codePath1 = Paths.get(getWorkingDirectory(), relationalPath1).toString();
        setFilePath1(codePath1);
        setWebPage1(readFile(getFilePath1()));
        final String relationalPath2 = "/src/test/resources/GOG-Galaxy_webPage2.txt";
        final String codePath2 = Paths.get(getWorkingDirectory(), relationalPath2).toString();
        setFilePath2(codePath2);
        setWebPage2(readFile(getFilePath2()));
    }

    @Override public Deque<Rule> getRules(int setNo) {
        if (setNo == 1) {
            return getRulesFromSet1(15);
        }

        return getRulesFromSet2(3);
    }

    private Deque<Rule> getRulesFromSet1(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("<section class=\"glx-section");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method2 = (TrimMethod) rule2.getMethod();
        method2.setSide(TrimSide.LEFT);
        method2.setNumberOf(27);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method3 = (FirstOfMethod) rule3.getMethod();
        method3.setType(FirstOfType.TAG);
        method3.setText("section");

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createRetrieveTags());
        RetrieveTagsMethod method4 = (RetrieveTagsMethod) rule4.getMethod();
        method4.setType(RetrieveTagsType.ALL);
        method4.setTagname("a");

        Rule rule5 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createFetchHrefs());

        Rule rule6 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method5 = (ExtractWordMethod) rule6.getMethod();
        method5.setPosition(1);
        rule6.getTarget().linkID(LinksID.MAC_OS_X_PKG);

        Rule rule7 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method7 = (ExtractWordMethod) rule7.getMethod();
        method7.setPosition(2);
        rule7.getTarget().linkID(LinksID.WINDOWS_X86_EXE);

        Rule rule8 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createExtractWord());
        ExtractWordMethod method8 = (ExtractWordMethod) rule8.getMethod();
        method8.setPosition(1);

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createRemoveCharacters());
        RemoveCharactersMethod method9 = (RemoveCharactersMethod) rule9.getMethod();
        method9.setSigns("abcdefghijklmnopqrstuvwxyz:/-_?=");

        Rule rule10 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method10 = (TrimMethod) rule10.getMethod();
        method10.setSide(TrimSide.LEFT);
        method10.setNumberOf(2);

        Rule rule11 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodFactory.createTrim());
        TrimMethod method11 = (TrimMethod) rule11.getMethod();
        method11.setSide(TrimSide.RIGHT);
        method11.setNumberOf(1);
        rule11.getTarget().version(Platforms.MAC_OS_X);

        Rule rule12 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createExtractWord());
        ExtractWordMethod method12 = (ExtractWordMethod) rule12.getMethod();
        method12.setPosition(2);

        Rule rule13 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createRemoveCharacters());
        RemoveCharactersMethod method13 = (RemoveCharactersMethod) rule13.getMethod();
        method13.setSigns("abcdefghijklmnopqrstuvwxyz:/-_?=");

        Rule rule14 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method14 = (TrimMethod) rule14.getMethod();
        method14.setSide(TrimSide.LEFT);
        method14.setNumberOf(2);

        Rule rule15 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodFactory.createTrim());
        TrimMethod method15 = (TrimMethod) rule15.getMethod();
        method15.setSide(TrimSide.RIGHT);
        method15.setNumberOf(1);
        rule15.getTarget().version(Platforms.WINDOWS);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10,
            rule11, rule12, rule13, rule14, rule15);

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

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method2 = (TrimMethod) rule2.getMethod();
        method2.setSide(TrimSide.LEFT);
        method2.setNumberOf(23);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodFactory.createGrabUntil());
        GrabUntilMethod method3 = (GrabUntilMethod) rule3.getMethod();
        method3.setCharStop('\n');
        rule3.getTarget().date(Platforms.ALL_SUPPORTED);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @NotNull @Contract(pure = true) private String getExpectedMacOsXPkgLink() {
        return "https://content-system.gog.com/open_link/download?path=/open/galaxy/client/galaxy_client_1.2.54.27.pkg";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows32bitExeLink() {
        return "https://content-system.gog.com/open_link/download?path=/open/galaxy/client/setup_galaxy_1.2.54.23.exe";
    }

    @NotNull @Contract(pure = true) private String getExpectedMacOsXVersion() {
        return "1.2.54.27";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsVersion() {
        return "1.2.54.23";
    }

    @NotNull @Contract(pure = true) private String getExpectedAllSupportedDate() {
        return "03/17/2019 12:26 PM";
    }

    @Test public void testLinksGrabbing() {
        // given
        // rules 1-7
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(7), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedMacOsXPkgLink(), data.getLinks().getItem(LinksID.MAC_OS_X_PKG));
        Assert.assertEquals(getExpectedWindows32bitExeLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
    }

    @Test public void testVersionsGrabbing() {
        // given
        // rules 8-15
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(15), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedMacOsXVersion(), data.getVersions().getItem(Platforms.MAC_OS_X));
        Assert.assertEquals(getExpectedWindowsVersion(), data.getVersions().getItem(Platforms.WINDOWS));
    }

    @Test public void testDateGrabbing() {
        // given
        // rules 1-3
        RulesExecutor executor = new RulesExecutor(getRulesFromSet2(3), getWebPage2());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAllSupportedDate(), data.getDates().getItem(Platforms.ALL_SUPPORTED));
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedMacOsXPkgLink(), data.getLinks().getItem(LinksID.MAC_OS_X_PKG));
        Assert.assertEquals(getExpectedWindows32bitExeLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedMacOsXVersion(), data.getVersions().getItem(Platforms.MAC_OS_X));
        Assert.assertEquals(getExpectedWindowsVersion(), data.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedAllSupportedDate(), data.getDates().getItem(Platforms.ALL_SUPPORTED));
    }

}
