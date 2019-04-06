package net.vego1mar.xml;

import static net.vego1mar.tests.TestHelper.getWorkingDirectory;

import java.nio.file.Paths;
import net.vego1mar.rules.GoogleChromeExecutorTest;
import org.junit.Test;

public class GoogleChromeXmlTest extends XmlTest {

    public GoogleChromeXmlTest() {
        super(new GoogleChromeExecutorTest());
        setXmlRuntime1(Paths.get(getWorkingDirectory(), "/runtime/GoogleChrome.xml").toString());
        setXmlPattern1(Paths.get(getWorkingDirectory(), "/src/test/resources/GoogleChrome_ptrn.xml").toString());
    }

    @Test @Override public void testXmlConstruction() {
        testLoadSettings();
        testSaveSettings();
    }

}
