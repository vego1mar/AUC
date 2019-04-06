package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.HashTabExecutorTest;
import net.vego1mar.xml.HashTabXmlTest;
import net.vego1mar.xml.XmlTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class HashTabCollectorTest extends CollectorTest {

    public HashTabCollectorTest() {
        super(new HashTabExecutorTest());
    }

    @NotNull @Contract(pure = true) private String getURL1() {
        return "http://implbits.com/products/hashtab/";
    }

    @NotNull @Contract(pure = true) private String getURL2() {
        return "https://www.majorgeeks.com/files/details/hashtab.html";
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        XmlTest xmlTest = new HashTabXmlTest();

        return new LinkedHashMap<>() {
            {
                put(xmlTest.getXmlPattern1(), getURL1());
                put(xmlTest.getXmlPattern2(), getURL2());
            }
        };
    }

    @Override protected @NotNull String getAppName() {
        return "Implbits Software HashTab";
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
