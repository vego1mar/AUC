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
import net.vego1mar.method.RemoveStringsMethod;
import net.vego1mar.method.enumerators.FirstOfType;
import net.vego1mar.properties.UseAsProperty;
import net.vego1mar.properties.enumerators.LinksID;
import net.vego1mar.target.Target;
import net.vego1mar.target.enumerators.In;
import net.vego1mar.target.enumerators.UseAs;
import net.vego1mar.utils.MethodFactory;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class EpicGamesLauncherExecutorTest extends ExecutorTest {

    public EpicGamesLauncherExecutorTest() {
        final String relationalPath = "/src/test/resources/EpicGamesLauncher_webPage.txt";
        final String codePath = Paths.get(getWorkingDirectory(), relationalPath).toString();
        setFilePath1(codePath);
        setWebPage1(readFile(getFilePath1()));
    }

    @Override public Deque<Rule> getRules(int setNo) {
        return getRulesFromSet1(9);
    }

    private Deque<Rule> getRulesFromSet1(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("id=\"cta\"");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method2 = (FirstOfMethod) rule2.getMethod();
        method2.setType(FirstOfType.TAG);
        method2.setText("a");

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createFetchHrefs());

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createRemoveStrings());
        RemoveStringsMethod method4 = (RemoveStringsMethod) rule4.getMethod();
        method4.setString("EpicGamesLauncherInstaller.msi");

        Rule rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createRemoveStrings());
        RemoveStringsMethod method5 = (RemoveStringsMethod) rule5.getMethod();
        method5.setString("EpicGamesLauncher.dmg");

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createSplitWords());

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createAppend());
        AppendMethod method7 = (AppendMethod) rule7.getMethod();
        method7.setText("EpicGamesLauncherInstaller.msi");
        rule7.getTarget().linkID(LinksID.WINDOWS_X86_MSI);

        Rule rule8 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createExtractWord());
        ExtractWordMethod method8 = (ExtractWordMethod) rule8.getMethod();
        method8.setPosition(1);

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createAppend());
        AppendMethod method9 = (AppendMethod) rule9.getMethod();
        method9.setText("EpicGamesLauncher.dmg");
        rule9.getTarget().linkID(LinksID.MAC_OS_X_DMG);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows32bitMsiLink() {
        return "https://launcher-public-service-prod06.ol.epicgames.com/launcher/api/installer/download/EpicGamesLauncherInstaller.msi";
    }

    @NotNull @Contract(pure = true) private String getExpectedMacOsXDmgLink() {
        return "https://launcher-public-service-prod06.ol.epicgames.com/launcher/api/installer/download/EpicGamesLauncher.dmg";
    }

    @Test public void testLinkGrabbing() {
        // given
        // rules 1-9
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(9), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindows32bitMsiLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_MSI));
        Assert.assertEquals(getExpectedMacOsXDmgLink(), data.getLinks().getItem(LinksID.MAC_OS_X_DMG));
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedWindows32bitMsiLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_MSI));
        Assert.assertEquals(getExpectedMacOsXDmgLink(), data.getLinks().getItem(LinksID.MAC_OS_X_DMG));
    }

}
