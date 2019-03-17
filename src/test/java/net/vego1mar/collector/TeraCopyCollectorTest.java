package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.TeraCopyExecutorTest;
import net.vego1mar.xml.TeraCopyXmlTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class TeraCopyCollectorTest extends CollectorTest {

    public TeraCopyCollectorTest() {
        super(new TeraCopyExecutorTest());
    }

    @NotNull @Contract(pure = true) private String getUrl1() {
        return "http://www.codesector.com/downloads";
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        return new LinkedHashMap<>() {
            {
                put(new TeraCopyXmlTest().getXmlPattern1(), getUrl1());
            }
        };
    }

    @Override protected @NotNull String getAppName() {
        return "Code Sector TeraCopy";
    }

    @Test @Ignore public void testCollectingOnline() {
        // given
        // when
        getCollector().gatherInformation();

        // then
        getExecutorAsserter().assertCollectedDataForExpectedOutput(getCollector().getCollectedData());
        getCollectorAsserter().assertCollectorSaveAndLoad(getCollector());
    }

}
