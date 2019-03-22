package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.GoogleChromeExecutorTest;
import net.vego1mar.xml.GoogleChromeXmlTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class GoogleChromeCollectorTest extends CollectorTest {

    public GoogleChromeCollectorTest() {
        super(new GoogleChromeExecutorTest());
    }

    @NotNull @Contract(pure = true) private String getURL1() {
        return "https://www.majorgeeks.com/files/details/google_chrome_26_1410_64_stable.html";
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        return new LinkedHashMap<>() {
            {
                put(new GoogleChromeXmlTest().getXmlPattern1(), getURL1());
            }
        };
    }

    @Override protected @NotNull String getAppName() {
        return "Google Chrome";
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
