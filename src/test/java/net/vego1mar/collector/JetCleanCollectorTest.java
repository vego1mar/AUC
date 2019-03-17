package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.JetCleanExecutorTest;
import net.vego1mar.xml.JetCleanXmlTest;
import net.vego1mar.xml.XmlTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class JetCleanCollectorTest extends CollectorTest {

    public JetCleanCollectorTest() {
        super(new JetCleanExecutorTest());
    }

    @NotNull @Contract(pure = true) private String getUrl1() {
        return "https://www.majorgeeks.com/mg/get/jetclean,1.html";
    }

    @NotNull @Contract(pure = true) private String getUrl2() {
        return "https://www.majorgeeks.com/files/details/jetclean.html";
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        XmlTest xmlTest = new JetCleanXmlTest();

        return new LinkedHashMap<>() {
            {
                put(xmlTest.getXmlPattern1(), getUrl1());
                put(xmlTest.getXmlPattern2(), getUrl2());
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
