package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.AimpExecutorTest;
import net.vego1mar.xml.AimpXmlTest;
import net.vego1mar.xml.XmlTest;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class AimpCollectorTest extends CollectorTest {

    public AimpCollectorTest() {
        super(new AimpExecutorTest());
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        XmlTest xmlTest = new AimpXmlTest();

        return new LinkedHashMap<>() {
            {
                put(xmlTest.getXmlPattern1(), "http://www.aimp.ru/?do=download&os=windows");
                put(xmlTest.getXmlPattern2(), "http://www.aimp.ru/?do=download&os=android");
            }
        };
    }

    @Override protected @NotNull String getAppName() {
        return "AIMP";
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
