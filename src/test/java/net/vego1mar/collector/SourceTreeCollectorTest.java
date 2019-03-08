package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.xml.SourceTreeXmlTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class SourceTreeCollectorTest extends CollectorTest {

    @Override @NotNull @Contract(" -> new") public LinkedHashMap<String, String> getExecutionOrder() {
        return new LinkedHashMap<>() {
            {
                put(SourceTreeXmlTest.XML_PATTERN, "https://www.sourcetreeapp.com/");
            }
        };
    }

    @Override @NotNull @Contract(pure = true) public String getAppName() {
        return "Atlassian SourceTree";
    }

    @Test public void testCollectingFromSet1Online() {
        // given
        // when
        collector.gatherInformation();

        // then
        asserter1.assertCollectedDataForExpectedOutput(collector.getCollectedData());
        asserter2.assertCollectorSaveAndLoad(collector);
    }
}
