package net.vego1mar.rules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import net.vego1mar.rules.auxiliary.method.Method;
import net.vego1mar.rules.auxiliary.target.Target;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsInterface;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsProperty;
import net.vego1mar.rules.enumerators.methods.firstof.FirstOfType;
import net.vego1mar.rules.enumerators.traits.InTrait;
import net.vego1mar.rules.enumerators.traits.MethodTrait;
import net.vego1mar.rules.enumerators.traits.UseAsTrait;
import net.vego1mar.utils.ReflectionHelper;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RulesExecutorTest {

    private static final Logger log = Logger.getLogger(RulesExecutorTest.class);
    private String htmlCodeOf7ZipWebPage;

    @Before public void init() {
        log.debug("Working directory: " + System.getProperty("user.dir"));
        String result = "";

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader("runtime/7zip_wp.txt"))) {
                StringBuilder builder = new StringBuilder();
                String line = reader.readLine();

                while (line != null) {
                    builder.append(line);
                    builder.append(System.lineSeparator());
                    line = reader.readLine();
                }

                result = builder.toString();
            }
        } catch (IOException exp) {
            log.error(exp);
        }

        htmlCodeOf7ZipWebPage = result;
    }

    private Deque<RuleBased> getRulesSetFor7Zip() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(InTrait.HTML, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule1.getMethod().firstOf(FirstOfType.STRING, "h1");
        RuleBased rule2 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule2.getMethod().firstOf(FirstOfType.TAG, "p");
        RuleBased rule3 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.SPLIT_WORDS));
        RuleBased rule4 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.LATEST_APP_VERSION), new Method(MethodTrait.EXTRACT_WORD));
        rule4.getMethod().extractWord(3);
        RuleBased rule5 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.IGNORE), new Method(MethodTrait.EXTRACT_WORD));
        rule5.getMethod().extractWord(4);
        RuleBased rule6 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.UPDATE_DATE), new Method(MethodTrait.REMOVE_CHARACTERS));
        rule6.getMethod().removeCharacters("()");
        RuleBased rule8 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule8.getMethod().firstOf(FirstOfType.TAG, "table");
        RuleBased rule9 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.RETRIEVE_ALL_TAGS));
        rule9.getMethod().retrieveAllTags("a");
        RuleBased rule10 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.IGNORE), new Method(MethodTrait.FETCH_HREFS));
        RuleBased rule11 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.IGNORE), new Method(MethodTrait.PREPEND));
        rule11.getMethod().prepend("https://www.7-zip.org/");
        RuleBased rule12 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.WINDOWS_X86_PACKAGE_URL), new Method(MethodTrait.EXTRACT_WORD));
        rule12.getMethod().extractWord(1);
        RuleBased rule13 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.WINDOWS_X64_PACKAGE_URL), new Method(MethodTrait.EXTRACT_WORD));
        rule13.getMethod().extractWord(2);

        rulesSet.add(rule1);
        rulesSet.add(rule2);
        rulesSet.add(rule3);
        rulesSet.add(rule4);
        rulesSet.add(rule5);
        rulesSet.add(rule6);
        rulesSet.add(rule1);
        rulesSet.add(rule8);
        rulesSet.add(rule9);
        rulesSet.add(rule10);
        rulesSet.add(rule11);
        rulesSet.add(rule12);
        rulesSet.add(rule13);

        return rulesSet;
    }

    @Test public void executeRuleFor7Zip() throws Exception {
        // given
        Deque<RuleBased> rulesSet = getRulesSetFor7Zip();
        RulesExecutor rulesExecutor = new RulesExecutor(rulesSet, htmlCodeOf7ZipWebPage);

        // when
        rulesExecutor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsInterface useAsProperty = (UseAsProperty) executor2.get(rulesExecutor);
        Assert.assertEquals("18.05", useAsProperty.getLatestAppVersion());
        Assert.assertEquals("2018-04-30", useAsProperty.getUpdateDate().trim());
        Assert.assertEquals("https://www.7-zip.org/a/7z1805.exe", useAsProperty.getWindowsX86packageURL());
        Assert.assertEquals("https://www.7-zip.org/a/7z1805-x64.exe", useAsProperty.getWindowsX64packageURL());
    }

    @Test public void executeRuleFor7Zip_multithreading() throws Exception {
        // given
        List<RulesExecutor> rulesExecutors = Arrays.asList(
            new RulesExecutor(getRulesSetFor7Zip(), htmlCodeOf7ZipWebPage),
            new RulesExecutor(getRulesSetFor7Zip(), htmlCodeOf7ZipWebPage)
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
            UseAsInterface useAsProperty = (UseAsProperty) ruleExecutor.get(executor);
            Assert.assertEquals("18.05", useAsProperty.getLatestAppVersion());
            Assert.assertEquals("2018-04-30", useAsProperty.getUpdateDate().trim());
            Assert.assertEquals("https://www.7-zip.org/a/7z1805.exe", useAsProperty.getWindowsX86packageURL());
            Assert.assertEquals("https://www.7-zip.org/a/7z1805-x64.exe", useAsProperty.getWindowsX64packageURL());
        }
    }

}
