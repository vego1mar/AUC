package net.vego1mar.collector;

import java.util.LinkedHashMap;
import net.vego1mar.rules.BattleNetExecutorTest;
import net.vego1mar.xml.BattleNetXmlTest;
import net.vego1mar.xml.XmlTest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.junit.Test;

public class BattleNetCollectorTest extends CollectorTest {

    public BattleNetCollectorTest() {
        super(new BattleNetExecutorTest());
    }

    @NotNull @Contract(pure = true) private String getUrl1() {
        return "https://eu.battle.net/account/download/#bnetapp";
    }

    @NotNull @Contract(pure = true) private String getUrl2() {
        return "https://www.instalki.pl/programy/download/Windows/akcesoria/Blizzard.html";
    }

    @Override protected @NotNull LinkedHashMap<String, String> getExecutionOrder() {
        XmlTest xmlTest = new BattleNetXmlTest();

        return new LinkedHashMap<>() {
            {
                put(xmlTest.getXmlPattern1(), getUrl1());
                put(xmlTest.getXmlPattern2(), getUrl2());
            }
        };
    }

    @Override protected @NotNull String getAppName() {
        return "Activision Blizzard Battle.Net";
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
