package net.vego1mar.xml;

import static net.vego1mar.tests.TestHelper.getWorkingDirectory;

import java.nio.file.Paths;
import net.vego1mar.rules.SevenZipExecutorTest;
import org.junit.Test;

public class SevenZipXmlTest extends XmlTest {

    public SevenZipXmlTest() {
        super(new SevenZipExecutorTest());
        setXmlRuntime1(Paths.get(getWorkingDirectory(), "/runtime/7zip_settings.xml").toString());
        setXmlPattern1(Paths.get(getWorkingDirectory(), "/src/test/resources/7zip_settings__pattern.xml").toString());
    }

    @Test @Override public void testXmlConstruction() {
        testLoadSettings();
        testSaveSettings();
    }

}
