package net.vego1mar.xml;

import static net.vego1mar.tests.TestHelper.getWorkingDirectory;

import java.nio.file.Paths;
import net.vego1mar.rules.TeraCopyExecutorTest;
import org.junit.Test;

public class TeraCopyXmlTest extends XmlTest {

    public TeraCopyXmlTest() {
        super(new TeraCopyExecutorTest());
        setXmlRuntime1(Paths.get(getWorkingDirectory(), "/runtime/TeraCopy_settings.xml").toString());
        setXmlPattern1(Paths.get(getWorkingDirectory(), "/src/test/resources/TeraCopy_set__ptrn.xml").toString());
    }

    @Test @Override public void testXmlConstruction() {
        testLoadSettings();
        testSaveSettings();
    }

}
