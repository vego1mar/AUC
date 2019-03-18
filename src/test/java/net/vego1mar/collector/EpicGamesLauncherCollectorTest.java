package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.EpicGamesLauncherExecutorTest;
import net.vego1mar.xml.EpicGamesLauncherXmlTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class EpicGamesLauncherCollectorTest extends CollectorTest {

    public EpicGamesLauncherCollectorTest() {
        super(new EpicGamesLauncherExecutorTest());
    }

    @NotNull @Contract(pure = true) private String getUrl1() {
        return "https://www.epicgames.com/store/pl/";
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        return new LinkedHashMap<>() {
            {
                put(new EpicGamesLauncherXmlTest().getXmlPattern1(), getUrl1());
            }
        };
    }

    @Override protected @NotNull String getAppName() {
        return "Epic Games Launcher";
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
