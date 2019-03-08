package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.ExecutorTestImpl;
import net.vego1mar.rules.SourceTreeExecutorTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;

public abstract class CollectorTest {

    protected AppInfoCollector collector;
    protected ExecutorTestImpl asserter1;
    protected AppInfoCollectorTest asserter2;

    @NotNull @Contract(" -> new") protected abstract LinkedHashMap<String, String> getExecutionOrder();

    @NotNull @Contract(pure = true) protected abstract String getAppName();

    @Before public void before() {
        // given
        collector = new AppInfoCollector(getAppName(), getExecutionOrder());
        asserter1 = new SourceTreeExecutorTest();
        asserter2 = new AppInfoCollectorTest();
    }
}
