package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.SourceTreeExecutorTest;
import net.vego1mar.xml.SourceTreeXmlTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class SourceTreeCollectorTest extends CollectorTest {

    public SourceTreeCollectorTest() {
        super(new SourceTreeExecutorTest());
    }

    @NotNull @Contract(pure = true) private String getUrl1() {
        return "https://www.sourcetreeapp.com/";
    }

    @Override @NotNull @Contract(" -> new") public LinkedHashMap<String, String> getExecutionOrder() {
        return new LinkedHashMap<>() {
            {
                put(new SourceTreeXmlTest().getXmlPattern1(), getUrl1());
            }
        };
    }

    @Override @NotNull @Contract(pure = true) public String getAppName() {
        return "Atlassian SourceTree";
    }

    @Test @Ignore public void testCollectingFromSet1Online() {
        // given
        // when
        getCollector().gatherInformation();

        // then
        getExecutorAsserter().assertCollectedDataForExpectedOutput(getCollector().getCollectedData());
        getCollectorAsserter().assertCollectorSaveAndLoad(getCollector());
    }
}
