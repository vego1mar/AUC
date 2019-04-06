package net.vego1mar.rules;

import static net.vego1mar.tests.TestHelper.getWorkingDirectory;
import static net.vego1mar.tests.TestHelper.readFile;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import net.vego1mar.method.AppendMethod;
import net.vego1mar.method.FirstOfMethod;
import net.vego1mar.method.GrabUntilMethod;
import net.vego1mar.method.TrimMethod;
import net.vego1mar.method.enumerators.FirstOfType;
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

public class GoogleChromeExecutorTest extends ExecutorTest {

    public GoogleChromeExecutorTest() {
        final String relationalPath = "/src/test/resources/GoogleChrome_webPage.txt";
        final String codePath = Paths.get(getWorkingDirectory(), relationalPath).toString();
        setFilePath1(codePath);
        setWebPage1(readFile(getFilePath1()));
    }

    @Override public Deque<Rule> getRules(int setNo) {
        return getRulesFromSet1(26);
    }

    private Deque<Rule> getRulesFromSet1(int itemsNo) {
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
        rule5.getTarget().date(Platforms.ALL_SUPPORTED);

        Rule rule6 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method6 = (FirstOfMethod) rule6.getMethod();
        method6.setType(FirstOfType.STRING);
        method6.setText("<meta itemprop=\"softwareVersion\"");

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method7 = (TrimMethod) rule7.getMethod();
        method7.setSide(TrimSide.LEFT);
        method7.setNumberOf(32);

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method8 = (FirstOfMethod) rule8.getMethod();
        method8.setType(FirstOfType.STRING);
        method8.setText("\"");

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method9 = (TrimMethod) rule9.getMethod();
        method9.setSide(TrimSide.LEFT);
        method9.setNumberOf(1);

        Rule rule10 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodFactory.createGrabUntil());
        GrabUntilMethod method10 = (GrabUntilMethod) rule10.getMethod();
        method10.setCharStop('\"');
        rule10.getTarget().version(Platforms.ALL_SUPPORTED);

        Rule rule11 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createClearContent());

        Rule rule12 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createAppend());
        AppendMethod method12 = (AppendMethod) rule12.getMethod();
        method12.setText("https://www.google.com/chrome/");
        rule12.getTarget().linkID(LinksID.GENERIC);

        Rule rule13 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createClearContent());

        Rule rule14 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createAppend());
        AppendMethod method14 = (AppendMethod) rule14.getMethod();
        method14.setText(getExpectedDebianUbuntuLink());
        rule14.getTarget().linkID(LinksID.DEBIAN_UBUNTU);

        Rule rule15 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createClearContent());

        Rule rule16 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createAppend());
        AppendMethod method16 = (AppendMethod) rule16.getMethod();
        method16.setText(getExpectedFedoraOpenSUSELink());
        rule16.getTarget().linkID(LinksID.FEDORA_OPENSUSE);

        Rule rule17 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createClearContent());

        Rule rule18 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createAppend());
        AppendMethod method18 = (AppendMethod) rule18.getMethod();
        method18.setText(getExpectedAndroidGooglePlayLink());
        rule18.getTarget().linkID(LinksID.ANDROID_X86ARM_GOOGLEPLAY);

        Rule rule19 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createClearContent());

        Rule rule20 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createAppend());
        AppendMethod method20 = (AppendMethod) rule20.getMethod();
        method20.setText(getExpectedAppleiOSLink());
        rule20.getTarget().linkID(LinksID.APPLE_IOS);

        Rule rule21 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createClearContent());

        Rule rule22 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createAppend());
        AppendMethod method22 = (AppendMethod) rule22.getMethod();
        method22.setText(getExpected64bitWindowsLink());
        rule22.getTarget().linkID(LinksID.WINDOWS_X64_EXE);

        Rule rule23 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createClearContent());

        Rule rule24 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createAppend());
        AppendMethod method24 = (AppendMethod) rule24.getMethod();
        method24.setText(getExpected32bitWindowsLink());
        rule24.getTarget().linkID(LinksID.WINDOWS_X86_EXE);

        Rule rule25 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createClearContent());

        Rule rule26 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createAppend());
        AppendMethod method26 = (AppendMethod) rule26.getMethod();
        method26.setText(getExpectedMacOSXLink());
        rule26.getTarget().linkID(LinksID.MAC_OS_X_DMG);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10,
            rule11, rule12, rule13, rule14, rule15, rule16, rule17, rule18, rule19, rule20, rule21, rule22, rule23,
            rule24, rule25, rule26);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @NotNull @Contract(pure = true) private String getExpectedAllSupportedDate() {
        return "03/20/2019 06:04 PM";
    }

    @NotNull @Contract(pure = true) private String getExpectedAllSupportedVersion() {
        return "73.0.3683.86";
    }

    @NotNull @Contract(pure = true) private String getExpectedGenericLink() {
        return "https://www.google.com/chrome/";
    }

    @NotNull @Contract(pure = true) private String getExpectedDebianUbuntuLink() {
        return "https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb";
    }

    @NotNull @Contract(pure = true) private String getExpectedFedoraOpenSUSELink() {
        return "https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm";
    }

    @NotNull @Contract(pure = true) private String getExpectedAndroidGooglePlayLink() {
        return "https://play.google.com/store/apps/details?id=com.android.chrome";
    }

    @NotNull @Contract(pure = true) private String getExpectedAppleiOSLink() {
        return "https://itunes.apple.com/us/app/chrome/id535886823";
    }

    @NotNull @Contract(pure = true) private String getExpected64bitWindowsLink() {
        return "https://dl.google.com/tag/s/appguid%3D%7B8A69D345-D564-463C-AFF1-A69D9E530F96%7D%26iid%3D%7BDA6FE065-E494-F5D4-0842-"
            + "C9E40B2B2C71%7D%26lang%3Den%26browser%3D4%26usagestats%3D1%26appname%3DGoogle%2520Chrome%26needsadmin%3Dprefers%26ap%"
            + "3Dx64-stable-statsdef_1%26installdataindex%3Ddefaultbrowser/update2/installers/ChromeSetup.exe";
    }

    @NotNull @Contract(pure = true) private String getExpected32bitWindowsLink() {
        return "https://dl.google.com/tag/s/appguid%3D%7B8A69D345-D564-463C-AFF1-A69D9E530F96%7D%26iid%3D%7BDA6FE065-E494-F5D4-0842-"
            + "C9E40B2B2C71%7D%26lang%3Den%26browser%3D4%26usagestats%3D1%26appname%3DGoogle%2520Chrome%26needsadmin%3Dprefers%26ap%"
            + "3Dstable-arch_x86-statsdef_1%26installdataindex%3Ddefaultbrowser/update2/installers/ChromeSetup.exe";
    }

    @NotNull @Contract(pure = true) private String getExpectedMacOSXLink() {
        return "https://dl.google.com/chrome/mac/stable/GGRO/googlechrome.dmg";
    }

    @Test public void testDateGrabbing() {
        // given
        // rules 1-5
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(5), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAllSupportedDate(), data.getDates().getItem(Platforms.ALL_SUPPORTED));
    }

    @Test public void testVersionGrabbing() {
        // given
        // rules 6-10
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(10), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAllSupportedVersion(), data.getVersions().getItem(Platforms.ALL_SUPPORTED));
    }

    @Test public void testLinksGrabbing() {
        // given
        // rules 11-26
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(26), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedGenericLink(), data.getLinks().getItem(LinksID.GENERIC));
        Assert.assertEquals(getExpectedDebianUbuntuLink(), data.getLinks().getItem(LinksID.DEBIAN_UBUNTU));
        Assert.assertEquals(getExpectedFedoraOpenSUSELink(), data.getLinks().getItem(LinksID.FEDORA_OPENSUSE));
        Assert.assertEquals(getExpectedAndroidGooglePlayLink(), data.getLinks().getItem(LinksID.ANDROID_X86ARM_GOOGLEPLAY));
        Assert.assertEquals(getExpectedAppleiOSLink(), data.getLinks().getItem(LinksID.APPLE_IOS));
        Assert.assertEquals(getExpected64bitWindowsLink(), data.getLinks().getItem(LinksID.WINDOWS_X64_EXE));
        Assert.assertEquals(getExpected32bitWindowsLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedMacOSXLink(), data.getLinks().getItem(LinksID.MAC_OS_X_DMG));
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedAllSupportedDate(), data.getDates().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals(getExpectedAllSupportedVersion(), data.getVersions().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals(getExpectedGenericLink(), data.getLinks().getItem(LinksID.GENERIC));
        Assert.assertEquals(getExpectedDebianUbuntuLink(), data.getLinks().getItem(LinksID.DEBIAN_UBUNTU));
        Assert.assertEquals(getExpectedFedoraOpenSUSELink(), data.getLinks().getItem(LinksID.FEDORA_OPENSUSE));
        Assert.assertEquals(getExpectedAndroidGooglePlayLink(), data.getLinks().getItem(LinksID.ANDROID_X86ARM_GOOGLEPLAY));
        Assert.assertEquals(getExpectedAppleiOSLink(), data.getLinks().getItem(LinksID.APPLE_IOS));
        Assert.assertEquals(getExpected64bitWindowsLink(), data.getLinks().getItem(LinksID.WINDOWS_X64_EXE));
        Assert.assertEquals(getExpected32bitWindowsLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedMacOSXLink(), data.getLinks().getItem(LinksID.MAC_OS_X_DMG));
    }

}
