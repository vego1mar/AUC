package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.ExecutorTestImpl;
import net.vego1mar.rules.SourceTreeExecutorTest;
import net.vego1mar.xml.SourceTreeXmlTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

public class SourceTreeCollectorTest {

    private AppInfoCollector collector;
    private ExecutorTestImpl asserter;

    @NotNull @Contract(" -> new") private LinkedHashMap<String, String> getExecutionOrder() {
        return new LinkedHashMap<>() {
            {
                put(SourceTreeXmlTest.XML_PATTERN, "https://www.sourcetreeapp.com/");
            }
        };
    }

    @NotNull @Contract(pure = true) private String getAppName() {
        return "Atlassian SourceTree";
    }

    @Before public void before() {
        // given
        collector = new AppInfoCollector(getAppName(), getExecutionOrder());
        asserter = new SourceTreeExecutorTest();
    }

    @Test public void testCollectingFromSet1Online() {
        // when
        collector.gatherInformation();

        // then
        asserter.assertCollectedDataForExpectedOutput(collector.getCollectedData());
    }

}
