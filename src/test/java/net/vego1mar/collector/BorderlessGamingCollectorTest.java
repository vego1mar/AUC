package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.BorderlessGamingExecutorTest;
import net.vego1mar.xml.BorderlessGamingXmlTest;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class BorderlessGamingCollectorTest extends CollectorTest {

    public BorderlessGamingCollectorTest() {
        super(new BorderlessGamingExecutorTest());
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        return new LinkedHashMap<>() {
            {
                put(new BorderlessGamingXmlTest().getXmlPattern1(), "https://github.com/Codeusa/Borderless-Gaming/releases/latest");
            }
        };
    }

    @Override protected @NotNull String getAppName() {
        return "Borderless Gaming";
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
