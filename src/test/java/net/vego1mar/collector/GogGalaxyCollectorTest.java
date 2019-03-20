package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.GogGalaxyExecutorTest;
import net.vego1mar.xml.GogGalaxyXmlTest;
import net.vego1mar.xml.XmlTest;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class GogGalaxyCollectorTest extends CollectorTest {

    public GogGalaxyCollectorTest() {
        super(new GogGalaxyExecutorTest());
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        XmlTest xmlTest = new GogGalaxyXmlTest();

        return new LinkedHashMap<>() {
            {
                put(xmlTest.getXmlPattern1(), "https://www.gog.com/galaxy");
                put(xmlTest.getXmlPattern2(), "https://www.majorgeeks.com/files/details/gog_galaxy.html");
            }
        };
    }

    @Override protected @NotNull String getAppName() {
        return "GOG Galaxy";
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
