package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.AimpExecutorTest;
import net.vego1mar.xml.AimpXmlTest;
import net.vego1mar.xml.XmlTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class AimpCollectorTest extends CollectorTest {

    public AimpCollectorTest() {
        super(new AimpExecutorTest());
    }

    @NotNull @Contract(pure = true) private String getUrl1() {
        return "http://www.aimp.ru/?do=download&os=windows";
    }

    @NotNull @Contract(pure = true) private String getUrl2() {
        return "http://www.aimp.ru/?do=download&os=android";
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        XmlTest xmlTest = new AimpXmlTest();

        return new LinkedHashMap<>() {
            {
                put(xmlTest.getXmlPattern1(), getUrl1());
                put(xmlTest.getXmlPattern2(), getUrl2());
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
