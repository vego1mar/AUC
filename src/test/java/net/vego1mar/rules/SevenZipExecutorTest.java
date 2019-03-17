package net.vego1mar.rules;

import static net.vego1mar.tests.TestHelper.getWorkingDirectory;
import static net.vego1mar.tests.TestHelper.readFile;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import net.vego1mar.method.ExtractWordMethod;
import net.vego1mar.method.FirstOfMethod;
import net.vego1mar.method.PrependMethod;
import net.vego1mar.method.RemoveCharactersMethod;
import net.vego1mar.method.RetrieveTagsMethod;
import net.vego1mar.method.enumerators.FirstOfType;
import net.vego1mar.method.enumerators.MethodType;
import net.vego1mar.method.enumerators.RetrieveTagsType;
import net.vego1mar.properties.UseAsProperty;
import net.vego1mar.properties.enumerators.LinksID;
import net.vego1mar.properties.enumerators.Platforms;
import net.vego1mar.target.Target;
import net.vego1mar.target.enumerators.In;
import net.vego1mar.target.enumerators.UseAs;
import net.vego1mar.utils.MethodCreator;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class SevenZipExecutorTest extends ExecutorTest {

    private static final Logger log = Logger.getLogger(SevenZipExecutorTest.class);

    public SevenZipExecutorTest() {
        final String relationalPath = "/src/test/resources/7zip_wp.txt";
        final String codePath = Paths.get(getWorkingDirectory(), relationalPath).toString();
        setFilePath1(codePath);
        setWebPage1(readFile(getFilePath1()));
    }

    @Override public Deque<Rule> getRules(int setNo) {
        return getRulesFromSet1(18);
    }

    private Deque<Rule> getRulesFromSet1(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("h1");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method2 = (FirstOfMethod) rule2.getMethod();
        method2.setType(FirstOfType.TAG);
        method2.setText("p");

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.SPLIT_WORDS));

        Rule rule4 = new Rule(new Target(In.COLLECTION, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method4 = (ExtractWordMethod) rule4.getMethod();
        method4.setPosition(3);
        rule4.getTarget().version(Platforms.ALL_SUPPORTED);

        Rule rule5 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method5 = (ExtractWordMethod) rule5.getMethod();
        method5.setPosition(4);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.REMOVE_CHARACTERS));
        RemoveCharactersMethod method6 = (RemoveCharactersMethod) rule6.getMethod();
        method6.setSigns("()");
        rule6.getTarget().date(Platforms.ALL_SUPPORTED);

        Rule rule7 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method7 = (FirstOfMethod) rule7.getMethod();
        method7.setType(FirstOfType.STRING);
        method7.setText("h1");

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method8 = (FirstOfMethod) rule8.getMethod();
        method8.setType(FirstOfType.TAG);
        method8.setText("table");

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method9 = (RetrieveTagsMethod) rule9.getMethod();
        method9.setType(RetrieveTagsType.ALL);
        method9.setTagname("a");

        Rule rule10 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FETCH_HREFS));

        Rule rule11 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.PREPEND));
        PrependMethod method11 = (PrependMethod) rule11.getMethod();
        method11.setText("https://www.7-zip.org/");

        Rule rule12 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method12 = (ExtractWordMethod) rule12.getMethod();
        method12.setPosition(1);
        rule12.getTarget().linkID(LinksID.WINDOWS_X86_EXE);

        Rule rule13 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(2);
        rule13.getTarget().linkID(LinksID.WINDOWS_X64_EXE);

        Rule rule14 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method14 = (ExtractWordMethod) rule14.getMethod();
        method14.setPosition(3);
        rule14.getTarget().linkID(LinksID.WINDOWS_ANY_7Z);

        Rule rule15 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method15 = (ExtractWordMethod) rule15.getMethod();
        method15.setPosition(4);
        rule15.getTarget().linkID(LinksID.SOURCECODE_ANY_7Z);

        Rule rule16 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method16 = (ExtractWordMethod) rule16.getMethod();
        method16.setPosition(5);
        rule16.getTarget().linkID(LinksID.SDK_ANY_7Z);

        Rule rule17 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method17 = (ExtractWordMethod) rule17.getMethod();
        method17.setPosition(6);
        rule17.getTarget().linkID(LinksID.WINDOWS_X86_MSI);

        Rule rule18 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method18 = (ExtractWordMethod) rule18.getMethod();
        method18.setPosition(7);
        rule18.getTarget().linkID(LinksID.WINDOWS_X64_MSI);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10, rule11,
            rule12, rule13, rule14, rule15, rule16, rule17, rule18);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @NotNull @Contract(pure = true) private String getExpectedAllSupportedVersion() {
        return "19.00";
    }

    @NotNull @Contract(pure = true) private String getExpectedAllSupportedDate() {
        return "2019-02-21";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows32bitExeLink() {
        return "https://www.7-zip.org/a/7z1900.exe";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows64bitExeLink() {
        return "https://www.7-zip.org/a/7z1900-x64.exe";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows7zPackageLink() {
        return "https://www.7-zip.org/a/7z1900-extra.7z";
    }

    @NotNull @Contract(pure = true) private String getExpectedSourceCodePackageLink() {
        return "https://www.7-zip.org/a/7z1900-src.7z";
    }

    @NotNull @Contract(pure = true) private String getExpectedSdk7zPackageLink() {
        return "https://www.7-zip.org/a/lzma1900.7z";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows32bitMsiLink() {
        return "https://www.7-zip.org/a/7z1900.msi";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows64bitMsiLink() {
        return "https://www.7-zip.org/a/7z1900-x64.msi";
    }

    @Test public void testVersionGrabbing() {
        // given
        // rules 1-4
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(4), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAllSupportedVersion(), data.getVersions().getItem(Platforms.ALL_SUPPORTED));
    }

    @Test public void testDateGrabbing() {
        // given
        // rules 5-6
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(6), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAllSupportedDate(), data.getDates().getItem(Platforms.ALL_SUPPORTED));
    }

    @Test public void testLinksGrabbing() {
        // given
        // rules 7-18
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(18), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindows32bitExeLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedWindows64bitExeLink(), data.getLinks().getItem(LinksID.WINDOWS_X64_EXE));
        Assert.assertEquals(getExpectedWindows7zPackageLink(), data.getLinks().getItem(LinksID.WINDOWS_ANY_7Z));
        Assert.assertEquals(getExpectedSourceCodePackageLink(), data.getLinks().getItem(LinksID.SOURCECODE_ANY_7Z));
        Assert.assertEquals(getExpectedSdk7zPackageLink(), data.getLinks().getItem(LinksID.SDK_ANY_7Z));
        Assert.assertEquals(getExpectedWindows32bitMsiLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_MSI));
        Assert.assertEquals(getExpectedWindows64bitMsiLink(), data.getLinks().getItem(LinksID.WINDOWS_X64_MSI));
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedAllSupportedVersion(), data.getVersions().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals(getExpectedAllSupportedDate(), data.getDates().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals(getExpectedWindows32bitExeLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedWindows64bitExeLink(), data.getLinks().getItem(LinksID.WINDOWS_X64_EXE));
        Assert.assertEquals(getExpectedWindows7zPackageLink(), data.getLinks().getItem(LinksID.WINDOWS_ANY_7Z));
        Assert.assertEquals(getExpectedSourceCodePackageLink(), data.getLinks().getItem(LinksID.SOURCECODE_ANY_7Z));
        Assert.assertEquals(getExpectedSdk7zPackageLink(), data.getLinks().getItem(LinksID.SDK_ANY_7Z));
        Assert.assertEquals(getExpectedWindows32bitMsiLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_MSI));
        Assert.assertEquals(getExpectedWindows64bitMsiLink(), data.getLinks().getItem(LinksID.WINDOWS_X64_MSI));
    }

    @Test public void testAllGrabbingOnThreads() throws InterruptedException, BrokenBarrierException {
        // given
        final int THREADS_NO = 3;
        final List<RulesExecutor> executors = Collections.synchronizedList(getExecutorsForThreads(THREADS_NO));
        final CyclicBarrier barrier = new CyclicBarrier(executors.size());
        final List<Runnable> threads = Collections.synchronizedList(new LinkedList<>());

        for (RulesExecutor executor : executors) {
            Runnable runnable = new SevenZipThread(executor, barrier);
            threads.add(new Thread(runnable));
        }

        // when
        for (Object runnable : threads) {
            ((Thread) runnable).start();
        }

        barrier.await();

        // then
        for (RulesExecutor executor : executors) {
            assertCollectedDataForExpectedOutput(executor.getResults());
        }
    }

    private List<RulesExecutor> getExecutorsForThreads(int threadsNo) {
        List<RulesExecutor> list = new LinkedList<>();

        for (int i = 0; i < threadsNo; i++) {
            list.add(new RulesExecutor(getRules(1), getWebPage1()));
        }

        return list;
    }

    private class SevenZipThread implements Runnable {

        private final RulesExecutor executor;
        private final CyclicBarrier barrier;

        protected SevenZipThread(@NotNull RulesExecutor executor, @NotNull CyclicBarrier barrier) {
            this.executor = executor;
            this.barrier = barrier;
        }

        @Override public void run() {
            try {
                executor.execute();
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException exp) {
                log.error(exp.toString());
            }
        }
    }

}
