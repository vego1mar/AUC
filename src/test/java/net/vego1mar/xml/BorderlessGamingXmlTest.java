package net.vego1mar.xml;

import static net.vego1mar.tests.TestVariables.getWorkingDirectory;

import java.nio.file.Paths;
import net.vego1mar.rules.BorderlessGamingExecutorTest;
import org.junit.Test;

public class BorderlessGamingXmlTest extends XmlTest {

    public BorderlessGamingXmlTest() {
        super(new BorderlessGamingExecutorTest());
        setXmlRuntime1(Paths.get(getWorkingDirectory(), "/runtime/BorderlessGaming_settings.xml").toString());
        setXmlPattern1(Paths.get(getWorkingDirectory(), "/src/test/resources/BorderlessGaming__ptrn.xml").toString());
    }

    @Override @Test public void testXmlConstruction() {
        testLoadSettings();
        testSaveSettings();
    }

}
