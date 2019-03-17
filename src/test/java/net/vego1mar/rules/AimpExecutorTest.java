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

public class AimpExecutorTest extends ExecutorTest {

    public AimpExecutorTest() {
        final String relationalPath1 = "/src/test/resources/aimp_wp_windows.txt";
        final String codePath1 = Paths.get(getWorkingDirectory(), relationalPath1).toString();
        setFilePath1(codePath1);
        setWebPage1(readFile(getFilePath1()));
        final String relationalPath2 = "/src/test/resources/aimp_wp_android.txt";
        final String codePath2 = Paths.get(getWorkingDirectory(), relationalPath2).toString();
        setFilePath2(codePath2);
        setWebPage2(readFile(getFilePath2()));
    }

    @Override public Deque<Rule> getRules(int setNo) {
        if (setNo == 1) {
            return getRulesFromSet1(12);
        }

        return getRulesFromSet2(18);
    }

    private Deque<Rule> getRulesFromSet1(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setText("AIMP v");
        method1.setType(FirstOfType.STRING);

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method2 = (GrabUntilMethod) rule2.getMethod();
        method2.setCharStop('<');

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method3 = (TrimMethod) rule3.getMethod();
        method3.setSide(TrimSide.LEFT);
        method3.setNumberOf(5);
        rule3.getTarget().version(Platforms.WINDOWS);

        Rule rule4 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method4 = (RetrieveTagsMethod) rule4.getMethod();
        method4.setType(RetrieveTagsType.FIRST);
        method4.setTagname("nobr");

        Rule rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method5 = (TrimMethod) rule5.getMethod();
        method5.setSide(TrimSide.LEFT);
        method5.setNumberOf(22);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.HASHES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method6 = (TrimMethod) rule6.getMethod();
        method6.setSide(TrimSide.RIGHT);
        method6.setNumberOf(18);
        rule6.getTarget().hashID(LinksID.WINDOWS_X86_EXE);

        Rule rule7 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method7 = (FirstOfMethod) rule7.getMethod();
        method7.setText("<h2>");
        method7.setType(FirstOfType.STRING);

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method8 = (GrabUntilMethod) rule8.getMethod();
        method8.setCharStop(' ');

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method9 = (TrimMethod) rule9.getMethod();
        method9.setSide(TrimSide.LEFT);
        method9.setNumberOf(4);
        rule9.getTarget().date(Platforms.WINDOWS);

        Rule rule10 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method10 = (FirstOfMethod) rule10.getMethod();
        method10.setType(FirstOfType.STRING);
        method10.setText("Скачать с:");

        Rule rule11 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method11 = (RetrieveTagsMethod) rule11.getMethod();
        method11.setTagname("a");
        method11.setType(RetrieveTagsType.FIRST);

        Rule rule12 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        rule12.getTarget().linkID(LinksID.WINDOWS_X86_EXE);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10, rule11, rule12);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    private Deque<Rule> getRulesFromSet2(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method1 = (RetrieveTagsMethod) rule1.getMethod();
        method1.setType(RetrieveTagsType.FIRST);
        method1.setTagname("h1");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method2 = (TrimMethod) rule2.getMethod();
        method2.setSide(TrimSide.LEFT);
        method2.setNumberOf(21);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method3 = (TrimMethod) rule3.getMethod();
        method3.setSide(TrimSide.RIGHT);
        method3.setNumberOf(5);
        rule3.getTarget().version(Platforms.ANDROID);

        Rule rule4 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method4 = (FirstOfMethod) rule4.getMethod();
        method4.setType(FirstOfType.STRING);
        method4.setText("h2");

        Rule rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method5 = (GrabUntilMethod) rule5.getMethod();
        method5.setCharStop(' ');

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method6 = (TrimMethod) rule6.getMethod();
        method6.setSide(TrimSide.LEFT);
        method6.setNumberOf(3);
        rule6.getTarget().date(Platforms.ANDROID);

        Rule rule7 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method7 = (RetrieveTagsMethod) rule7.getMethod();
        method7.setType(RetrieveTagsType.FIRST);
        method7.setTagname("nobr");

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method8 = (TrimMethod) rule8.getMethod();
        method8.setSide(TrimSide.LEFT);
        method8.setNumberOf(22);

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.HASHES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method9 = (TrimMethod) rule9.getMethod();
        method9.setSide(TrimSide.RIGHT);
        method9.setNumberOf(18);
        rule9.getTarget().hashID(LinksID.ANDROID_X86ARM_APK);

        Rule rule10 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method10 = (FirstOfMethod) rule10.getMethod();
        method10.setType(FirstOfType.STRING);
        method10.setText("Скачать с:");

        Rule rule11 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method11 = (GrabUntilMethod) rule11.getMethod();
        method11.setCharStop('|');

        Rule rule12 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method12 = (RetrieveTagsMethod) rule12.getMethod();
        method12.setType(RetrieveTagsType.ALL);
        method12.setTagname("a");

        Rule rule13 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(1);

        Rule rule14 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method14 = (TrimMethod) rule14.getMethod();
        method14.setSide(TrimSide.LEFT);
        method14.setNumberOf(9);

        Rule rule15 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method15 = (TrimMethod) rule15.getMethod();
        method15.setSide(TrimSide.RIGHT);
        method15.setNumberOf(17);
        rule15.getTarget().linkID(LinksID.ANDROID_X86ARM_GOOGLEPLAY);

        Rule rule16 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method16 = (ExtractWordMethod) rule16.getMethod();
        method16.setPosition(2);

        Rule rule17 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method17 = (TrimMethod) rule17.getMethod();
        method17.setSide(TrimSide.LEFT);
        method17.setNumberOf(9);

        Rule rule18 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method18 = (TrimMethod) rule18.getMethod();
        method18.setSide(TrimSide.RIGHT);
        method18.setNumberOf(13);
        rule18.getTarget().linkID(LinksID.ANDROID_X86ARM_APK);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10, rule11, rule12,
            rule13, rule14, rule15, rule16, rule17, rule18);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsVersion() {
        return "v4.51, build 2084";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindowsDate() {
        return "01.12.2018";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows32bitHash() {
        return "acc1353719050e5fa6f28e8d296078a4";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows32bitLink() {
        return "http://aimp.su/storage/acc1353719050e5fa6f28e8d296078a4/aimp_4.51.2084.exe";
    }

    @NotNull @Contract(pure = true) private String getExpectedAndroidVersion() {
        return "v2.85, build 722";
    }

    @NotNull @Contract(pure = true) private String getExpectedAndroidDate() {
        return "04.03.2019";
    }

    @NotNull @Contract(pure = true) private String getExpectedAndroidArmApkHash() {
        return "bbdbee7149b1c83f9a884225a164f70d";
    }

    @NotNull @Contract(pure = true) private String getExpectedGooglePlayArmApkLink() {
        return "https://play.google.com/store/apps/details?id=com.aimp.player";
    }

    @NotNull @Contract(pure = true) private String getExpectedAndroidArmApkLink() {
        return "http://www.aimp.ru/files/android/aimp_2.85.722.apk";
    }

    @Test public void testWindowsVersionGrabbing() {
        // given
        // rules 1-3
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(3), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsVersion(), data.getVersions().getItem(Platforms.WINDOWS));
    }

    @Test public void testWindowsHashGrabbing() {
        // given
        // rules 4-6
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(6), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindows32bitHash(), data.getHashes().getItem(LinksID.WINDOWS_X86_EXE));
    }

    @Test public void testWindowsDateGrabbing() {
        // given
        // rules 7-9
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(9), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindowsDate(), data.getDates().getItem(Platforms.WINDOWS));
    }

    @Test public void testWindowsLinkGrabbing() {
        // given
        // rules 10-12
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(12), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindows32bitLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
    }

    @Test public void testAndroidVersionGrabbing() {
        // given
        // rules 1-3
        RulesExecutor executor = new RulesExecutor(getRulesFromSet2(3), getWebPage2());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAndroidVersion(), data.getVersions().getItem(Platforms.ANDROID));
    }

    @Test public void testAndroidDateGrabbing() {
        // given
        // rules 4-6
        RulesExecutor executor = new RulesExecutor(getRulesFromSet2(6), getWebPage2());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAndroidDate(), data.getDates().getItem(Platforms.ANDROID));
    }

    @Test public void testAndroidHashGrabbing() {
        // given
        // rules 7-9
        RulesExecutor executor = new RulesExecutor(getRulesFromSet2(9), getWebPage2());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAndroidArmApkHash(), data.getHashes().getItem(LinksID.ANDROID_X86ARM_APK));
    }

    @Test public void testAndroidLinksGrabbing() {
        // given
        // rules 10-18
        RulesExecutor executor = new RulesExecutor(getRulesFromSet2(18), getWebPage2());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAndroidArmApkLink(), data.getLinks().getItem(LinksID.ANDROID_X86ARM_APK));
        Assert.assertEquals(getExpectedGooglePlayArmApkLink(), data.getLinks().getItem(LinksID.ANDROID_X86ARM_GOOGLEPLAY));
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedWindowsVersion(), data.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedWindows32bitHash(), data.getHashes().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedWindowsDate(), data.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals(getExpectedWindows32bitLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedAndroidVersion(), data.getVersions().getItem(Platforms.ANDROID));
        Assert.assertEquals(getExpectedAndroidDate(), data.getDates().getItem(Platforms.ANDROID));
        Assert.assertEquals(getExpectedAndroidArmApkHash(), data.getHashes().getItem(LinksID.ANDROID_X86ARM_APK));
        Assert.assertEquals(getExpectedAndroidArmApkLink(), data.getLinks().getItem(LinksID.ANDROID_X86ARM_APK));
        Assert.assertEquals(getExpectedGooglePlayArmApkLink(), data.getLinks().getItem(LinksID.ANDROID_X86ARM_GOOGLEPLAY));
    }

}
