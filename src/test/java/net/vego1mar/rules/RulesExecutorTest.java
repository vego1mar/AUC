package net.vego1mar.rules;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import net.vego1mar.auxiliary.properties.UseAsImpl;
import net.vego1mar.auxiliary.properties.UseAsProperty;
import net.vego1mar.enumerators.properties.LinksID;
import net.vego1mar.enumerators.properties.Platforms;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import net.vego1mar.utils.ReflectionHelper;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class RulesExecutorTest {

    private static final Logger log = Logger.getLogger(RulesExecutorTest.class);
    private static final String htmlCodeOf7ZipWebPage = TestVariables.readFile(TestVariables.CODE_7ZIP);
    private static final String htmlCodeOfAimpWebPage1 = TestVariables.readFile(TestVariables.CODE_AIMP_1);
    private static final String htmlCodeOfAimpWebPage2 = TestVariables.readFile(TestVariables.CODE_AIMP_2);
    private static final String htmlCodeOfSourceTreeWebPage = TestVariables.readFile(TestVariables.CODE_SOURCETREE);
    private static final String htmlCodeOfJetCleanWebPage1 = TestVariables.readFile(TestVariables.CODE_JETCLEAN_1);
    private static final String htmlCodeOfJetCleanWebPage2 = TestVariables.readFile(TestVariables.CODE_JETCLEAN_2);
    private static final String htmlCodeOfBorderlessGamingWebPage = TestVariables.readFile(TestVariables.CODE_BORDERLESSGAMING);
    private static final String htmlCodeOfTeraCopyWebPage = TestVariables.readFile(TestVariables.CODE_TERACOPY);
    private static final String htmlCodeOfPotPlayerWebPage = TestVariables.readFile(TestVariables.CODE_POTPLAYER);

    @Test public void execute_7Zip() throws Exception {
        // given
        Deque<RuleImpl> rulesSet = TestCollections.getRulesFor7Zip_1();
        RulesExecutable rulesExecutor = new RulesExecutor(rulesSet, htmlCodeOf7ZipWebPage);

        // when
        rulesExecutor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(rulesExecutor);
        Assert.assertEquals("18.05", useAsProperty.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals("2018-04-30", useAsProperty.getDates().getItem(Platforms.WINDOWS).trim());
        Assert.assertEquals("https://www.7-zip.org/a/7z1805.exe", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals("https://www.7-zip.org/a/7z1805-x64.exe", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X64_EXE));
        Assert.assertEquals("https://www.7-zip.org/a/7z1805-extra.7z", useAsProperty.getLinks().getItem(LinksID.WINDOWS_ANY_7Z));
        Assert.assertEquals("https://www.7-zip.org/a/7z1805-src.7z", useAsProperty.getLinks().getItem(LinksID.SOURCECODE_ANY_7Z));
        Assert.assertEquals("https://www.7-zip.org/a/lzma1805.7z", useAsProperty.getLinks().getItem(LinksID.SDK_ANY_7Z));
        Assert.assertEquals("https://www.7-zip.org/a/7z1805.msi", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_MSI));
        Assert.assertEquals("https://www.7-zip.org/a/7z1805-x64.msi", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X64_MSI));
    }

    @Ignore @Test public void executeMultithreading_7Zip() throws Exception {
        // given
        List<RulesExecutor> rulesExecutors = Arrays.asList(
            new RulesExecutor(TestCollections.getRulesFor7Zip_1(), htmlCodeOf7ZipWebPage),
            new RulesExecutor(TestCollections.getRulesFor7Zip_1(), htmlCodeOf7ZipWebPage)
        );

        CyclicBarrier barrier = new CyclicBarrier(rulesExecutors.size());
        List<Thread> threads = new LinkedList<>();

        for (RulesExecutable executor : rulesExecutors) {
            threads.add(new Thread(() -> {
                try {
                    executor.execute();
                    barrier.await();
                } catch (Exception exp) {
                    log.error(exp);
                }
            }));
        }

        // when
        for (Thread thread : threads) {
            thread.start();
        }

        barrier.await();

        // then
        for (RulesExecutable executor : rulesExecutors) {
            Field ruleExecutor = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
            UseAsImpl useAsProperty = (UseAsProperty) ruleExecutor.get(executor);
            Assert.assertEquals("18.05", useAsProperty.getVersions().getItem(Platforms.WINDOWS));
            Assert.assertEquals("2018-04-30", useAsProperty.getDates().getItem(Platforms.WINDOWS).trim());
            Assert.assertEquals("https://www.7-zip.org/a/7z1805.exe", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
            Assert.assertEquals("https://www.7-zip.org/a/7z1805-x64.exe", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X64_EXE));
            Assert.assertEquals("https://www.7-zip.org/a/7z1805-extra.7z", useAsProperty.getLinks().getItem(LinksID.WINDOWS_ANY_7Z));
            Assert.assertEquals("https://www.7-zip.org/a/7z1805-src.7z", useAsProperty.getLinks().getItem(LinksID.SOURCECODE_ANY_7Z));
            Assert.assertEquals("https://www.7-zip.org/a/lzma1805.7z", useAsProperty.getLinks().getItem(LinksID.SDK_ANY_7Z));
            Assert.assertEquals("https://www.7-zip.org/a/7z1805.msi", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_MSI));
            Assert.assertEquals("https://www.7-zip.org/a/7z1805-x64.msi", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X64_MSI));
        }
    }

    @Test public void execute_AIMP() throws Exception {
        // given
        Deque<RuleImpl> rulesSet1 = TestCollections.getRulesForAimp_1();
        Deque<RuleImpl> rulesSet2 = TestCollections.getRulesForAimp_2();
        RulesExecutable rulesExecutor = new RulesExecutor(rulesSet1, htmlCodeOfAimpWebPage1);

        // when
        rulesExecutor.execute();
        rulesExecutor.renew(rulesSet2, htmlCodeOfAimpWebPage2);
        rulesExecutor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(rulesExecutor);
        Assert.assertEquals("v4.51, build 2084", useAsProperty.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals("01.12.2018", useAsProperty.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals("acc1353719050e5fa6f28e8d296078a4", useAsProperty.getHashes().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals("http://aimp.su/storage/acc1353719050e5fa6f28e8d296078a4/aimp_4.51.2084.exe",
            useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals("v2.80, build 631", useAsProperty.getVersions().getItem(Platforms.ANDROID));
        Assert.assertEquals("22.10.2018", useAsProperty.getDates().getItem(Platforms.ANDROID));
        Assert.assertEquals("1fa56dc3b88e3979875f6f4065261ab5", useAsProperty.getHashes().getItem(LinksID.ANDROID_X86ARM_APK));
        Assert.assertEquals("https://play.google.com/store/apps/details?id=com.aimp.player",
            useAsProperty.getLinks().getItem(LinksID.ANDROID_X86ARM_GOOGLEPLAY));
        Assert.assertEquals("http://www.aimp.ru/files/android/aimp_2.80.631.apk",
            useAsProperty.getLinks().getItem(LinksID.ANDROID_X86ARM_APK));
    }

    @Test public void execute_SourceTree() throws Exception {
        // given
        Deque<RuleImpl> rulesSet = TestCollections.getRulesForSourceTree_1();
        RulesExecutable rulesExecutor = new RulesExecutor(rulesSet, htmlCodeOfSourceTreeWebPage);

        // when
        rulesExecutor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(rulesExecutor);
        Assert.assertEquals("3.0.12", useAsProperty.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals("Jan 7, 2019", useAsProperty.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals("https://product-downloads.atlassian.com/software/sourcetree/windows/ga/SourceTreeSetup-3.0.12.exe",
            useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals("3.0.1_205", useAsProperty.getVersions().getItem(Platforms.MAC_OS_X));
        Assert.assertEquals("Jan 7, 2019", useAsProperty.getDates().getItem(Platforms.MAC_OS_X));
        Assert.assertEquals("https://product-downloads.atlassian.com/software/sourcetree/ga/Sourcetree_3.0.1_205.zip",
            useAsProperty.getLinks().getItem(LinksID.MAC_OS_X_ZIP));
    }

    @Test public void execute_JetClean() throws Exception {
        // given
        Deque<RuleImpl> rulesSet1 = TestCollections.getRulesForJetClean_1();
        Deque<RuleImpl> rulesSet2 = TestCollections.getRulesForJetClean_2();
        RulesExecutable executor = new RulesExecutor(rulesSet1, htmlCodeOfJetCleanWebPage1);

        // when
        executor.execute();
        executor.renew(rulesSet2, htmlCodeOfJetCleanWebPage2);
        executor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(executor);
//        Assert.assertEquals("1.5.0.129", useAsProperty.getLatestAppVersion());
//        Assert.assertEquals("02/26/2016", useAsProperty.getUpdateDate());
//        Assert.assertEquals("http://download.bluesprig.com/dl/jetclean-setup.exe", useAsProperty.getWindowsX86packageURL());
//        Assert.assertEquals("", useAsProperty.getWindowsX64packageURL());
//        Assert.assertEquals("", useAsProperty.getWindowsX86hash());
//        Assert.assertEquals("", useAsProperty.getWindowsX64hash());
    }

    @Test public void execute_BorderlessGaming() throws Exception {
        // given
        Deque<RuleImpl> rulesSet = TestCollections.getRulesForBorderlessGaming_1();
        RulesExecutable executor = new RulesExecutor(rulesSet, htmlCodeOfBorderlessGamingWebPage);

        // when
        executor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(executor);
//        Assert.assertEquals("9.5.4", useAsProperty.getLatestAppVersion());
//        Assert.assertEquals("Mar 31, 2018", useAsProperty.getUpdateDate());

//        Assert.assertEquals(
//            "https://github.com/Codeusa/Borderless-Gaming/releases/download/9.5.4/BorderlessGaming9.5.4_admin_setup.exe",
//            useAsProperty.getWindowsX86packageURL()
//        );
//
//        Assert.assertEquals("", useAsProperty.getWindowsX64packageURL());
//        Assert.assertEquals("", useAsProperty.getWindowsX86hash());
//        Assert.assertEquals("", useAsProperty.getWindowsX64hash());
    }

    @Test public void execute_TeraCopy() throws Exception {
        // given
        Deque<RuleImpl> rulesSet = TestCollections.getRulesForTeraCopy_1();
        RulesExecutable executor = new RulesExecutor(rulesSet, htmlCodeOfTeraCopyWebPage);

        // when
        executor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(executor);
//        Assert.assertEquals("3.26", useAsProperty.getLatestAppVersion());
//        Assert.assertEquals("", useAsProperty.getUpdateDate());
//        Assert.assertEquals("http://www.codesector.com/files/teracopy.exe", useAsProperty.getWindowsX86packageURL());
//        Assert.assertEquals("", useAsProperty.getWindowsX64packageURL());
//        Assert.assertEquals("", useAsProperty.getWindowsX86hash());
//        Assert.assertEquals("", useAsProperty.getWindowsX64hash());
    }

    @Test public void execute_PotPlayer() throws Exception {
        // given
        Deque<RuleImpl> rulesSet = TestCollections.getRulesForPotPlayer_1();
        RulesExecutable executor = new RulesExecutor(rulesSet, htmlCodeOfPotPlayerWebPage);

        // when
        executor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(executor);
//        Assert.assertEquals("1.7.13963", useAsProperty.getLatestAppVersion());
//        Assert.assertEquals("2018/08", useAsProperty.getUpdateDate());

//        Assert.assertEquals(
//            "https://daumpotplayer.com/wp-content/uploads/2018/08/PotPlayerSetup.exe",
//            useAsProperty.getWindowsX86packageURL()
//        );
//
//        Assert.assertEquals(
//            "https://daumpotplayer.com/wp-content/uploads/2018/08/PotPlayerSetup64.exe",
//            useAsProperty.getWindowsX64packageURL()
//        );
//
//        Assert.assertEquals("", useAsProperty.getWindowsX86hash());
//        Assert.assertEquals("", useAsProperty.getWindowsX64hash());
    }

}
