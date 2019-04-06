package net.vego1mar.xml;

import static net.vego1mar.tests.TestHelper.getWorkingDirectory;

import java.nio.file.Paths;
import net.vego1mar.rules.PotPlayerExecutorTest;
import org.junit.Test;

public class PotPlayerXmlTest extends XmlTest {

    public PotPlayerXmlTest() {
        super(new PotPlayerExecutorTest());
        setXmlRuntime1(Paths.get(getWorkingDirectory(), "/runtime/PotPlayer_settings.xml").toString());
        setXmlPattern1(Paths.get(getWorkingDirectory(), "/src/test/resources/PotPlayer_set__ptrn.xml").toString());
    }

    @Test @Override public void testXmlConstruction() {
        testLoadSettings();
        testSaveSettings();
    }

}
