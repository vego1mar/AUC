package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.OriginExecutorTest;
import net.vego1mar.xml.OriginXmlTest;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class OriginCollectorTest extends CollectorTest {

    public OriginCollectorTest() {
        super(new OriginExecutorTest());
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        return new LinkedHashMap<>() {
            {
                put(new OriginXmlTest().getXmlPattern(), "https://www.majorgeeks.com/files/details/origin_for_pc.html");
            }
        };
    }

    @Override protected @NotNull String getAppName() {
        return "EA Origin";
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
