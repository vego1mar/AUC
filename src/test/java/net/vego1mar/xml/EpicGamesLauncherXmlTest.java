package net.vego1mar.xml;

import static net.vego1mar.tests.TestHelper.getWorkingDirectory;

import java.nio.file.Paths;
import net.vego1mar.rules.EpicGamesLauncherExecutorTest;
import org.junit.Test;

public class EpicGamesLauncherXmlTest extends XmlTest {

    public EpicGamesLauncherXmlTest() {
        super(new EpicGamesLauncherExecutorTest());
        setXmlRuntime1(Paths.get(getWorkingDirectory(), "/runtime/EpicGamesLauncher_rulesSet.xml").toString());
        setXmlPattern1(Paths.get(getWorkingDirectory(), "/src/test/resources/EpicGamesLauncher_rulesSet_p.xml").toString());
    }

    @Test @Override public void testXmlConstruction() {
        testLoadSettings();
        testSaveSettings();
    }

}
