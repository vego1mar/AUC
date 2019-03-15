package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.JetCleanExecutorTest;
import net.vego1mar.xml.JetCleanXmlTest;
import net.vego1mar.xml.XmlTest;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class JetCleanCollectorTest extends CollectorTest {

    public JetCleanCollectorTest() {
        super(new JetCleanExecutorTest());
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        XmlTest xmlTest = new JetCleanXmlTest();

        return new LinkedHashMap<>() {
            {
                put(xmlTest.getXmlPattern1(), "https://www.majorgeeks.com/mg/get/jetclean,1.html");
                put(xmlTest.getXmlPattern2(), "https://www.majorgeeks.com/files/details/jetclean.html");
            }
        };
    }

    @Override protected @NotNull String getAppName() {
        return "Bluesprig JetClean";
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
