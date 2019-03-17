package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.properties.enumerators.LinksID;
import net.vego1mar.rules.VirtualBoxExecutorTest;
import net.vego1mar.xml.VirtualBoxXmlTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class VirtualBoxCollectorTest extends CollectorTest {

    private final VirtualBoxXmlTest xmlTest;

    public VirtualBoxCollectorTest() {
        super(new VirtualBoxExecutorTest());
        xmlTest = new VirtualBoxXmlTest();
    }

    @NotNull @Contract(pure = true) private String getUrl1() {
        return "https://www.virtualbox.org/wiki/Downloads";
    }

    @NotNull @Contract(pure = true) private String getUrl2() {
        return "https://www.majorgeeks.com/files/details/virtualbox.html";
    }

    @NotNull @Contract(pure = true) private String getUrl3() {
        return "https://www.virtualbox.org/wiki/Linux_Downloads";
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        return new LinkedHashMap<>() {
            {
                put(xmlTest.getXmlPattern1(), getUrl1());
                put(xmlTest.getXmlPattern2(), getUrl2());
                put(xmlTest.getXmlPattern3(), getUrl3());
            }
        };
    }

    @Override protected @NotNull String getAppName() {
        return "Oracle VirtualBox";
    }

    @Test @Ignore public void testCollectingOnline() {
        // given
        // when
        getCollector().addMutableEntry(getUrl1(), xmlTest.getXmlPatternA(), LinksID.GENERIC, xmlTest.getXmlPatternB());
        getCollector().gatherInformation();

        // then
        getExecutorAsserter().assertCollectedDataForExpectedOutput(getCollector().getCollectedData());
        getCollectorAsserter().assertCollectorSaveAndLoad(getCollector());
    }

}
