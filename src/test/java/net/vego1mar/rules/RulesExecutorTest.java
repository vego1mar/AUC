package net.vego1mar.rules;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import net.vego1mar.auxiliary.properties.UseAsImpl;
import net.vego1mar.auxiliary.properties.UseAsProperty;
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
    private static final String htmlCodeOfAimpWebPage = TestVariables.readFile(TestVariables.CODE_AIMP);
    private static final String htmlCodeOfSourceTreeWebPage = TestVariables.readFile(TestVariables.CODE_SOURCETREE);
    private static final String htmlCodeOfJetCleanWebPage1 = TestVariables.readFile(TestVariables.CODE_JETCLEAN_1);
    private static final String htmlCodeOfJetCleanWebPage2 = TestVariables.readFile(TestVariables.CODE_JETCLEAN_2);
    private static final String htmlCodeOfBorderlessGamingWebPage = TestVariables.readFile(TestVariables.CODE_BORDERLESSGAMING);
    private static final String htmlCodeOfTeraCopyWebPage = TestVariables.readFile(TestVariables.CODE_TERACOPY);
    private static final String htmlCodeOfPotPlayerWebPage = TestVariables.readFile(TestVariables.CODE_POTPLAYER);

    @Test public void execute_7Zip() throws Exception {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesFor7Zip_1();
        RulesExecutable rulesExecutor = new RulesExecutor(rulesSet, htmlCodeOf7ZipWebPage);

        // when
        rulesExecutor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(rulesExecutor);
        Assert.assertEquals("18.05", useAsProperty.getLatestAppVersion());
        Assert.assertEquals("2018-04-30", useAsProperty.getUpdateDate().trim());
        Assert.assertEquals("https://www.7-zip.org/a/7z1805.exe", useAsProperty.getWindowsX86packageURL());
        Assert.assertEquals("https://www.7-zip.org/a/7z1805-x64.exe", useAsProperty.getWindowsX64packageURL());
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
            Assert.assertEquals("18.05", useAsProperty.getLatestAppVersion());
            Assert.assertEquals("2018-04-30", useAsProperty.getUpdateDate().trim());
            Assert.assertEquals("https://www.7-zip.org/a/7z1805.exe", useAsProperty.getWindowsX86packageURL());
            Assert.assertEquals("https://www.7-zip.org/a/7z1805-x64.exe", useAsProperty.getWindowsX64packageURL());
        }
    }

    @Test public void execute_AIMP() throws Exception {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesForAimp_1();
        RulesExecutable rulesExecutor = new RulesExecutor(rulesSet, htmlCodeOfAimpWebPage);

        // when
        rulesExecutor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(rulesExecutor);
        Assert.assertEquals("v4.51 build 2080", useAsProperty.getLatestAppVersion());
        Assert.assertEquals("07.07.2018", useAsProperty.getUpdateDate());
        Assert.assertEquals("5a11962272e8dc7777525fd878e95e5d", useAsProperty.getWindowsX86hash());
        Assert.assertEquals("http://aimp.su/storage/5a11962272e8dc7777525fd878e95e5d/aimp_4.51.2080.exe", useAsProperty.getWindowsX86packageURL());
        Assert.assertEquals("", useAsProperty.getWindowsX64hash());
        Assert.assertEquals("", useAsProperty.getWindowsX64packageURL());
    }

    @Test public void execute_SourceTree() throws Exception {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesForSourceTree_1();
        RulesExecutable rulesExecutor = new RulesExecutor(rulesSet, htmlCodeOfSourceTreeWebPage);

        // when
        rulesExecutor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(rulesExecutor);
        Assert.assertEquals("2.6.10", useAsProperty.getLatestAppVersion());
        Assert.assertEquals("Sep 23, 2018", useAsProperty.getUpdateDate());

        Assert.assertEquals(
            "https://downloads.atlassian.com/software/sourcetree/windows/ga/SourceTreeSetup-2.6.10.exe",
            useAsProperty.getWindowsX86packageURL()
        );

        Assert.assertEquals("", useAsProperty.getWindowsX64packageURL());
        Assert.assertEquals("", useAsProperty.getWindowsX86hash());
        Assert.assertEquals("", useAsProperty.getWindowsX64hash());
    }

    @Test public void execute_JetClean() throws Exception {
        // given
        Deque<RuleBased> rulesSet1 = TestCollections.getRulesForJetClean_1();
        Deque<RuleBased> rulesSet2 = TestCollections.getRulesForJetClean_2();
        RulesExecutable executor = new RulesExecutor(rulesSet1, htmlCodeOfJetCleanWebPage1);

        // when
        executor.execute();
        executor.renew(rulesSet2, htmlCodeOfJetCleanWebPage2);
        executor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(executor);
        Assert.assertEquals("1.5.0.129", useAsProperty.getLatestAppVersion());
        Assert.assertEquals("02/26/2016", useAsProperty.getUpdateDate());
        Assert.assertEquals("http://download.bluesprig.com/dl/jetclean-setup.exe", useAsProperty.getWindowsX86packageURL());
        Assert.assertEquals("", useAsProperty.getWindowsX64packageURL());
        Assert.assertEquals("", useAsProperty.getWindowsX86hash());
        Assert.assertEquals("", useAsProperty.getWindowsX64hash());
    }

    @Test public void execute_BorderlessGaming() throws Exception {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesForBorderlessGaming_1();
        RulesExecutable executor = new RulesExecutor(rulesSet, htmlCodeOfBorderlessGamingWebPage);

        // when
        executor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(executor);
        Assert.assertEquals("9.5.4", useAsProperty.getLatestAppVersion());
        Assert.assertEquals("Mar 31, 2018", useAsProperty.getUpdateDate());

        Assert.assertEquals(
            "https://github.com/Codeusa/Borderless-Gaming/releases/download/9.5.4/BorderlessGaming9.5.4_admin_setup.exe",
            useAsProperty.getWindowsX86packageURL()
        );

        Assert.assertEquals("", useAsProperty.getWindowsX64packageURL());
        Assert.assertEquals("", useAsProperty.getWindowsX86hash());
        Assert.assertEquals("", useAsProperty.getWindowsX64hash());
    }

    @Test public void execute_TeraCopy() throws Exception {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesForTeraCopy_1();
        RulesExecutable executor = new RulesExecutor(rulesSet, htmlCodeOfTeraCopyWebPage);

        // when
        executor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(executor);
        Assert.assertEquals("3.26", useAsProperty.getLatestAppVersion());
        Assert.assertEquals("", useAsProperty.getUpdateDate());
        Assert.assertEquals("http://www.codesector.com/files/teracopy.exe", useAsProperty.getWindowsX86packageURL());
        Assert.assertEquals("", useAsProperty.getWindowsX64packageURL());
        Assert.assertEquals("", useAsProperty.getWindowsX86hash());
        Assert.assertEquals("", useAsProperty.getWindowsX64hash());
    }

    @Test public void execute_PotPlayer() throws Exception {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesForPotPlayer_1();
        RulesExecutable executor = new RulesExecutor(rulesSet, htmlCodeOfPotPlayerWebPage);

        // when
        executor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsImpl useAsProperty = (UseAsProperty) executor2.get(executor);
        Assert.assertEquals("1.7.13963", useAsProperty.getLatestAppVersion());
        Assert.assertEquals("2018/08", useAsProperty.getUpdateDate());

        Assert.assertEquals(
            "https://daumpotplayer.com/wp-content/uploads/2018/08/PotPlayerSetup.exe",
            useAsProperty.getWindowsX86packageURL()
        );

        Assert.assertEquals(
            "https://daumpotplayer.com/wp-content/uploads/2018/08/PotPlayerSetup64.exe",
            useAsProperty.getWindowsX64packageURL()
        );

        Assert.assertEquals("", useAsProperty.getWindowsX86hash());
        Assert.assertEquals("", useAsProperty.getWindowsX64hash());
    }

}
