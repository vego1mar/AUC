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
import net.vego1mar.method.PrependMethod;
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

public class BorderlessGamingExecutorTest extends ExecutorTest {

    public BorderlessGamingExecutorTest() {
        final String relationalPath = "/src/test/resources/BorderlessGaming_wp.txt";
        final String codePath = Paths.get(getWorkingDirectory(), relationalPath).toString();
        setWebPage1(readFile(codePath));
    }

    @Override public Deque<Rule> getRules(int setNo) {
        return getRulesFromSet1(19);
    }

    private Deque<Rule> getRulesFromSet1(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.TAG);
        method1.setText("relative-time");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method2 = (FirstOfMethod) rule2.getMethod();
        method2.setType(FirstOfType.STRING);
        method2.setText(">");

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method3 = (TrimMethod) rule3.getMethod();
        method3.setSide(TrimSide.LEFT);
        method3.setNumberOf(1);

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method4 = (GrabUntilMethod) rule4.getMethod();
        method4.setCharStop('<');

        Rule rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method5 = (TrimMethod) rule5.getMethod();
        method5.setSide(TrimSide.LEFT);
        method5.setNumberOf(14);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method6 = (TrimMethod) rule6.getMethod();
        method6.setSide(TrimSide.RIGHT);
        method6.setNumberOf(13);
        rule6.getTarget().date(Platforms.WINDOWS);

        Rule rule7 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method7 = (FirstOfMethod) rule7.getMethod();
        method7.setType(FirstOfType.STRING);
        method7.setText("/Codeusa/Borderless-Gaming/releases/download");

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method8 = (GrabUntilMethod) rule8.getMethod();
        method8.setCharStop('"');

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.PREPEND));
        PrependMethod method9 = (PrependMethod) rule9.getMethod();
        method9.setText("https://github.com");
        rule9.getTarget().linkID(LinksID.WINDOWS_X86_EXE);

        Rule rule10 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method10 = (FirstOfMethod) rule10.getMethod();
        method10.setType(FirstOfType.STRING);
        method10.setText("download/");

        Rule rule11 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method11 = (TrimMethod) rule11.getMethod();
        method11.setSide(TrimSide.LEFT);
        method11.setNumberOf(9);

        Rule rule12 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method12 = (GrabUntilMethod) rule12.getMethod();
        method12.setCharStop('/');
        rule12.getTarget().version(Platforms.WINDOWS);

        Rule rule13 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method13 = (FirstOfMethod) rule13.getMethod();
        method13.setType(FirstOfType.STRING);
        method13.setText("d-block py-1 py-md-2");

        Rule rule14 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method14 = (RetrieveTagsMethod) rule14.getMethod();
        method14.setType(RetrieveTagsType.ALL);
        method14.setTagname("a");

        Rule rule15 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FETCH_HREFS));

        Rule rule16 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method16 = (ExtractWordMethod) rule16.getMethod();
        method16.setPosition(2);

        Rule rule17 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.PREPEND));
        PrependMethod method17 = (PrependMethod) rule17.getMethod();
        method17.setText("https://github.com");
        rule17.getTarget().linkID(LinksID.SOURCECODE_TAR_GZ);

        Rule rule18 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method18 = (ExtractWordMethod) rule18.getMethod();
        method18.setPosition(1);

        Rule rule19 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.PREPEND));
        PrependMethod method19 = (PrependMethod) rule19.getMethod();
        method19.setText("https://github.com");
        rule19.getTarget().linkID(LinksID.WINDOWS_ZIP);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10, rule11, rule12,
            rule13, rule14, rule15, rule16, rule17, rule18, rule19);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsDateFromSet1() {
        return "Oct 11, 2018";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsLinkFromSet1() {
        return "https://github.com/Codeusa/Borderless-Gaming/releases/download/9.5.5/BorderlessGaming9.5.5_admin_setup.exe";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsVersionFromSet1() {
        return "9.5.5";
    }

    @NotNull @Contract(pure = true) private String getExpectedSourceCodeLinkFromSet1() {
        return "https://github.com/Codeusa/Borderless-Gaming/archive/9.5.5.tar.gz";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsZipLinkFromSet1() {
        return "https://github.com/Codeusa/Borderless-Gaming/archive/9.5.5.zip";
    }

    @Test public void testDatesGrabbingFromSet1() {
        // given
        // rules 1-6
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(6), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsDateFromSet1(), data.getDates().getItem(Platforms.WINDOWS));
    }

    @Test public void testLinksGrabbingFromSet1() {
        // given
        // rules 7-9
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(9), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsLinkFromSet1(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
    }

    @Test public void testVersionsGrabbingFromSet1() {
        // given
        // rules 10-12
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(12), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsVersionFromSet1(), data.getVersions().getItem(Platforms.WINDOWS));
    }

    @Test public void testLinks2GrabbingFromSet1() {
        // given
        // rules 13-19
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(19), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedSourceCodeLinkFromSet1(), data.getLinks().getItem(LinksID.SOURCECODE_TAR_GZ));
        Assert.assertEquals(getExpectedWindowsZipLinkFromSet1(), data.getLinks().getItem(LinksID.WINDOWS_ZIP));
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedWindowsDateFromSet1(), data.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedWindowsLinkFromSet1(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedWindowsVersionFromSet1(), data.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedSourceCodeLinkFromSet1(), data.getLinks().getItem(LinksID.SOURCECODE_TAR_GZ));
        Assert.assertEquals(getExpectedWindowsZipLinkFromSet1(), data.getLinks().getItem(LinksID.WINDOWS_ZIP));
    }

}
