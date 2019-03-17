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

public class TeraCopyExecutorTest extends ExecutorTest {

    public TeraCopyExecutorTest() {
        final String relationalPath = "/src/test/resources/TeraCopy_wp.txt";
        final String codePath = Paths.get(getWorkingDirectory(), relationalPath).toString();
        setFilePath1(codePath);
        setWebPage1(readFile(getFilePath1()));
    }

    @Override public Deque<Rule> getRules(int setNo) {
        return getRulesFromSet1(10);
    }

    private Deque<Rule> getRulesFromSet1(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.TAG);
        method1.setText("tbody");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method2 = (FirstOfMethod) rule2.getMethod();
        method2.setType(FirstOfType.TAG);
        method2.setText("tr");

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method3 = (RetrieveTagsMethod) rule3.getMethod();
        method3.setType(RetrieveTagsType.ALL);
        method3.setTagname("td");

        Rule rule4 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method4 = (ExtractWordMethod) rule4.getMethod();
        method4.setPosition(1);

        Rule rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method5 = (FirstOfMethod) rule5.getMethod();
        method5.setType(FirstOfType.STRING);
        method5.setText(" ");

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method6 = (GrabUntilMethod) rule6.getMethod();
        method6.setCharStop('<');

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method7 = (TrimMethod) rule7.getMethod();
        method7.setSide(TrimSide.LEFT);
        method7.setNumberOf(1);
        rule7.getTarget().version(Platforms.WINDOWS);

        Rule rule8 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method8 = (ExtractWordMethod) rule8.getMethod();
        method8.setPosition(3);

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FETCH_HREFS));

        Rule rule10 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.PREPEND));
        PrependMethod method10 = (PrependMethod) rule10.getMethod();
        method10.setText("http://www.codesector.com");
        rule10.getTarget().linkID(LinksID.WINDOWS_X86_EXE);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsVersion() {
        return "3.26";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows32bitLink() {
        return "http://www.codesector.com/files/teracopy.exe";
    }

    @Test public void testVersionGrabbing() {
        // given
        // rules 1-7
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(7), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsVersion(), data.getVersions().getItem(Platforms.WINDOWS));
    }

    @Test public void testLinkGrabbing() {
        // given
        // rules 8-10
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(10), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindows32bitLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedWindowsVersion(), data.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedWindows32bitLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
    }

}
