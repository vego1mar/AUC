package net.vego1mar.xml;

import static net.vego1mar.tests.TestVariables.getWorkingDirectory;

import java.nio.file.Paths;
import net.vego1mar.rules.OriginExecutorTest;
import org.junit.Test;

public class OriginXmlTest extends XmlTest {

    public OriginXmlTest() {
        super(new OriginExecutorTest());
        setXmlRuntime1(Paths.get(getWorkingDirectory(), "/runtime/Origin1.xml").toString());
        setXmlPattern1(Paths.get(getWorkingDirectory(), "/src/test/resources/Origin1_ptrn.xml").toString());
    }

    @Test @Override public void testXmlConstruction() {
        testLoadSettings();
        testSaveSettings();
    }

}
