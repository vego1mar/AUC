package net.vego1mar.rules;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsImpl;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsProperty;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import net.vego1mar.utils.ReflectionHelper;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RulesExecutorTest {

    private static final Logger log = Logger.getLogger(RulesExecutorTest.class);
    private String htmlCodeOf7ZipWebPage;
    private String htmlCodeOfAimpWebPage;

    @Before public void init() {
        htmlCodeOf7ZipWebPage = TestVariables.readFile(TestVariables.WEBPAGE_OF_7ZIP);
        htmlCodeOfAimpWebPage = TestVariables.readFile(TestVariables.WEBPAGE_OF_AIMP);
    }

    @Test public void executeRuleFor7Zip() throws Exception {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesSetFor7Zip();
        RulesExecutor rulesExecutor = new RulesExecutor(rulesSet, htmlCodeOf7ZipWebPage);

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

    @Test public void executeRuleFor7Zip_multithreading() throws Exception {
        // given
        List<RulesExecutor> rulesExecutors = Arrays.asList(
            new RulesExecutor(TestCollections.getRulesSetFor7Zip(), htmlCodeOf7ZipWebPage),
            new RulesExecutor(TestCollections.getRulesSetFor7Zip(), htmlCodeOf7ZipWebPage)
        );

        CyclicBarrier barrier = new CyclicBarrier(rulesExecutors.size());
        List<Thread> threads = new LinkedList<>();

        for (RulesExecutor executor : rulesExecutors) {
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
        for (RulesExecutor executor : rulesExecutors) {
            Field ruleExecutor = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
            UseAsImpl useAsProperty = (UseAsProperty) ruleExecutor.get(executor);
            Assert.assertEquals("18.05", useAsProperty.getLatestAppVersion());
            Assert.assertEquals("2018-04-30", useAsProperty.getUpdateDate().trim());
            Assert.assertEquals("https://www.7-zip.org/a/7z1805.exe", useAsProperty.getWindowsX86packageURL());
            Assert.assertEquals("https://www.7-zip.org/a/7z1805-x64.exe", useAsProperty.getWindowsX64packageURL());
        }
    }

    @Test public void executeRuleForAimp() throws Exception {
        // given
        Deque<RuleBased> rulesSet = TestCollections.getRulesSetForAimp();
        RulesExecutor rulesExecutor = new RulesExecutor(rulesSet, htmlCodeOfAimpWebPage);

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

}
