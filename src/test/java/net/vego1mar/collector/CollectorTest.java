package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.ExecutorTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;

public abstract class CollectorTest {

    private AppInfoCollector collector;
    private ExecutorTest asserter1;
    private AppInfoCollectorTest asserter2;

    protected CollectorTest(@NotNull ExecutorTest asserter1) {
        this.asserter1 = asserter1;
    }

    protected AppInfoCollector getCollector() {
        return collector;
    }

    protected ExecutorTest getExecutorAsserter() {
        return asserter1;
    }

    protected AppInfoCollectorTest getCollectorAsserter() {
        return asserter2;
    }

    @NotNull @Contract(" -> new") protected abstract LinkedHashMap<String, String> getExecutionOrder();

    @NotNull @Contract(pure = true) protected abstract String getAppName();

    @Before public void before() {
        // given
        collector = new AppInfoCollector(getAppName(), getExecutionOrder());
        asserter2 = new AppInfoCollectorTest();
    }
}
