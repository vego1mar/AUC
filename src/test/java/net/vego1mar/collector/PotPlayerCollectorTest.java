package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.PotPlayerExecutorTest;
import net.vego1mar.xml.PotPlayerXmlTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class PotPlayerCollectorTest extends CollectorTest {

    public PotPlayerCollectorTest() {
        super(new PotPlayerExecutorTest());
    }

    @NotNull @Contract(pure = true) private String getUrl1() {
        return "https://daumpotplayer.com/download/";
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        return new LinkedHashMap<>() {
            {
                put(new PotPlayerXmlTest().getXmlPattern1(), getUrl1());
            }
        };
    }

    @Override protected @NotNull String getAppName() {
        return "DAUM Kakao PotPlayer";
    }

    @Test @Ignore public void testCollectingOnline() {
        // given
        // when
        getCollector().gatherInformation();

        // then
        getExecutorAsserter().assertCollectedDataForExpectedOutput(getCollector().getCollectedData());
        assertCollectorSaveAndLoad(getCollector());
    }

}
