package net.vego1mar.collector;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import net.vego1mar.enumerators.properties.LinksID;
import net.vego1mar.enumerators.properties.Platforms;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

public class AppInfoCollectorTest {

    private static final String OUT_DESTINATION_PATH = System.getProperty("user.dir") + "/runtime";

    @Test public void saveAndLoad_7Zip() {
        // given
        final Map<String, String> execOrder = new LinkedHashMap<>() {
            {
                put(TestVariables.XML_PATTERN_7ZIP, "7-zip.org/download.html");
            }
        };
        final String appName = "7-Zip";
        AppInfoCollector collector1 = new AppInfoCollector(appName, execOrder);
        final int EXPECTED_SERIAL_FILE_SIZE = 903;
        final int EXPECTED_XML_FILE_SIZE = 4192;

        // when
        collector1.save(OUT_DESTINATION_PATH, OUT_DESTINATION_PATH);
        AppInfoCollector collector2 = AppInfoCollector.load(collector1.getSerialFileName());

        // then
        File serialFile = new File(collector1.getSerialFileName());
        File xmlFile = new File(collector1.getXMLFileNames().get(0));
        Assert.assertTrue(EXPECTED_SERIAL_FILE_SIZE >= serialFile.length());
        Assert.assertTrue(EXPECTED_XML_FILE_SIZE >= xmlFile.length());
        Assert.assertEquals(appName, collector1.getAppName());
        Assert.assertEquals(collector1.getAppName(), collector2.getAppName());
        Assert.assertEquals(collector1.getSerialFileName(), collector2.getSerialFileName());
        Assert.assertEquals(collector1.getXMLFileNames().size(), collector2.getXMLFileNames().size());
        Assert.assertEquals(collector1.getXMLFileNames().get(0), collector2.getXMLFileNames().get(0));
        Assert.assertEquals(collector1.getURLNames().get(0), collector2.getURLNames().get(0));
        Assert.assertEquals(collector2.getURLNames().get(0), execOrder.get(TestVariables.XML_PATTERN_7ZIP));
    }

    @Test public void class_7Zip_online() {
        // given
        final Map<String, String> execOrder = new LinkedHashMap<>() {
            {
                put(TestVariables.XML_PATTERN_7ZIP, "http://www.7-zip.org/download.html");
            }
        };
        final String appName = "7-Zip";
        AppInfoCollector collector1 = new AppInfoCollector(appName, execOrder);
        final int EXPECTED_SERIAL_FILE_SIZE = 917;
        final int EXPECTED_XML_FILE_SIZE = 4192;

        // when
        collector1.gatherInformation();
        collector1.save(OUT_DESTINATION_PATH, OUT_DESTINATION_PATH);
        AppInfoCollector collector2 = AppInfoCollector.load(collector1.getSerialFileName());

        // then
        File serialFile = new File(collector1.getSerialFileName());
        File xmlFile = new File(collector1.getXMLFileNames().get(0));
        Assert.assertTrue(EXPECTED_SERIAL_FILE_SIZE >= serialFile.length());
        Assert.assertTrue(EXPECTED_XML_FILE_SIZE >= xmlFile.length());
        Assert.assertEquals(appName, collector1.getAppName());
        Assert.assertEquals(collector1.getAppName(), collector2.getAppName());
        Assert.assertEquals(collector1.getSerialFileName(), collector2.getSerialFileName());
        Assert.assertEquals(collector1.getXMLFileNames().size(), collector2.getXMLFileNames().size());
        Assert.assertEquals(collector1.getXMLFileNames().get(0), collector2.getXMLFileNames().get(0));
        Assert.assertEquals(collector1.getURLNames().get(0), collector2.getURLNames().get(0));
        Assert.assertEquals(collector2.getURLNames().get(0), execOrder.get(TestVariables.XML_PATTERN_7ZIP));
        Assert.assertFalse(collector1.isUpdateAvailable(Platforms.WINDOWS));
        Assert.assertTrue(collector2.isUpdateAvailable(Platforms.WINDOWS));
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getDates().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X64_EXE).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X86_EXE).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.SOURCECODE_ANY_7Z).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.SDK_ANY_7Z).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_ANY_7Z).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X64_MSI).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X86_MSI).isEmpty());
        Assert.assertTrue(collector2.getCollectedData().getVersions().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertTrue(collector2.getCollectedData().getDates().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertTrue(collector2.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X64_EXE).isEmpty());
        Assert.assertTrue(collector2.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X86_EXE).isEmpty());
        Assert.assertTrue(collector2.getCollectedData().getLinks().getItem(LinksID.SOURCECODE_ANY_7Z).isEmpty());
        Assert.assertTrue(collector2.getCollectedData().getLinks().getItem(LinksID.SDK_ANY_7Z).isEmpty());
        Assert.assertTrue(collector2.getCollectedData().getLinks().getItem(LinksID.WINDOWS_ANY_7Z).isEmpty());
        Assert.assertTrue(collector2.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X64_MSI).isEmpty());
        Assert.assertTrue(collector2.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X86_MSI).isEmpty());
    }

}
