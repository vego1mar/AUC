package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.SevenZipExecutorTest;
import net.vego1mar.xml.SevenZipXmlTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class SevenZipCollectorTest extends CollectorTest {

    public SevenZipCollectorTest() {
        super(new SevenZipExecutorTest());
    }

    @NotNull @Contract(pure = true) private String getUrl1() {
        return "http://www.7-zip.org/download.html";
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        return new LinkedHashMap<>() {
            {
                put(new SevenZipXmlTest().getXmlPattern1(), getUrl1());
            }
        };
    }

    @Override protected @NotNull String getAppName() {
        return "7-Zip";
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
