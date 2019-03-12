package net.vego1mar.xml;

import static net.vego1mar.tests.TestVariables.getWorkingDirectory;

import java.nio.file.Paths;
import net.vego1mar.rules.SourceTreeExecutorTest;
import org.junit.Test;

public class SourceTreeXmlTest extends XmlTest {

    public SourceTreeXmlTest() {
        super(new SourceTreeExecutorTest());
        setXmlRuntime(Paths.get(getWorkingDirectory(), "/runtime/SourceTree_settings.xml").toString());
        setXmlPattern(Paths.get(getWorkingDirectory(), "/src/test/resources/SourceTree_stgs__ptrn.xml").toString());
    }

    @Test @Override public void testXmlConstruction() {
        testLoadSettings();
        testSaveSettings();
    }

}
