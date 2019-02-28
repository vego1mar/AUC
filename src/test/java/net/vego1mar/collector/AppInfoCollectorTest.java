package net.vego1mar.collector;

import java.io.File;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import net.vego1mar.enumerators.properties.LinksID;
import net.vego1mar.enumerators.properties.Platforms;
import net.vego1mar.tests.TestVariables;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class AppInfoCollectorTest {

    private static final String OUT_DESTINATION_PATH = Paths.get(System.getProperty("user.dir"), "/runtime").toString();

    @Test public void saveAndLoad_7Zip() {
        // given
        final Map<String, String> execOrder = new LinkedHashMap<>() {
            {
                put(TestVariables.XML_PATTERN_7ZIP, "7-zip.org/download.html");
            }
        };
        final String appName = "7-Zip";
        AppInfoCollector collector1 = new AppInfoCollector(appName, execOrder);
        final int EXPECTED_SERIAL_FILE_SIZE = 910;
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
        final int EXPECTED_SERIAL_FILE_SIZE = 924;
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

    @Test public void class_AIMP_online() {
        // given
        final Map<String, String> execOrder = new LinkedHashMap<>() {
            {
                put(TestVariables.XML_PATTERN_AIMP_1, "http://www.aimp.ru/?do=download&os=windows");
                put(TestVariables.XML_PATTERN_AIMP_2, "http://www.aimp.ru/?do=download&os=android");
            }
        };
        final String appName = "AIMP";
        AppInfoCollector collector1 = new AppInfoCollector(appName, execOrder);

        // when
        collector1.gatherInformation();
        collector1.save(OUT_DESTINATION_PATH, OUT_DESTINATION_PATH);
        AppInfoCollector collector2 = AppInfoCollector.load(collector1.getSerialFileName());

        // then
        assertCommonCollectorFields(collector1, collector2);
        Assert.assertFalse(collector1.isUpdateAvailable(Platforms.WINDOWS));
        Assert.assertFalse(collector1.isUpdateAvailable(Platforms.ANDROID));
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.ANDROID).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getDates().getItem(Platforms.ANDROID).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.ANDROID_X86ARM_APK).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X86_EXE).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.ANDROID_X86ARM_GOOGLEPLAY).isEmpty());
    }

    private void assertCommonCollectorFields(@NotNull AppInfoCollector collector1, @NotNull AppInfoCollector collector2) {
        Assert.assertEquals(collector1.getAppName(), collector2.getAppName());
        Assert.assertEquals(collector1.getSerialFileName(), collector2.getSerialFileName());
        Assert.assertEquals(collector1.getXMLFileNames().size(), collector2.getXMLFileNames().size());
        Assert.assertEquals(collector1.getURLNames().size(), collector2.getURLNames().size());

        for (int i = 0; i < collector1.getXMLFileNames().size(); i++) {
            Assert.assertEquals(collector1.getXMLFileNames().get(i), collector2.getXMLFileNames().get(i));
        }

        for (int j = 0; j < collector1.getURLNames().size(); j++) {
            Assert.assertEquals(collector1.getURLNames().get(j), collector2.getURLNames().get(j));
        }
    }

    @Test public void class_SourceTree_online() {
        // given
        final Map<String, String> execOrder = new LinkedHashMap<>() {
            {
                put(TestVariables.XML_PATTERN_SOURCETREE, "https://www.sourcetreeapp.com/");
            }
        };
        final String appName = "Atlassian SourceTree";
        AppInfoCollector collector1 = new AppInfoCollector(appName, execOrder);

        // when
        collector1.gatherInformation();
        collector1.save(OUT_DESTINATION_PATH, OUT_DESTINATION_PATH);
        AppInfoCollector collector2 = AppInfoCollector.load(collector1.getSerialFileName());

        // then
        assertCommonCollectorFields(collector1, collector2);
        Assert.assertFalse(collector1.isUpdateAvailable(Platforms.WINDOWS));
        Assert.assertFalse(collector1.isUpdateAvailable(Platforms.MAC_OS_X));
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.MAC_OS_X).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getDates().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getDates().getItem(Platforms.MAC_OS_X).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.MAC_OS_X_ZIP).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X86_EXE).isEmpty());
    }

    @Test public void class_JetClean_online() {
        // given
        final Map<String, String> execOrder = new LinkedHashMap<>() {
            {
                put(TestVariables.XML_PATTERN_JETCLEAN_1, "https://www.majorgeeks.com/mg/get/jetclean,1.html");
                put(TestVariables.XML_PATTERN_JETCLEAN_2, "https://www.majorgeeks.com/files/details/jetclean.html");
            }
        };
        final String appName = "Bluesprig JetClean";
        AppInfoCollector collector1 = new AppInfoCollector(appName, execOrder);

        // when
        collector1.gatherInformation();
        collector1.save(OUT_DESTINATION_PATH, OUT_DESTINATION_PATH);
        AppInfoCollector collector2 = AppInfoCollector.load(collector1.getSerialFileName());

        assertCommonCollectorFields(collector1, collector2);
        Assert.assertFalse(collector1.isUpdateAvailable(Platforms.WINDOWS));
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getDates().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X86_EXE).isEmpty());
    }

    @Test public void class_BorderlessGaming_online() {
        // given
        final Map<String, String> execOrder = new LinkedHashMap<>() {
            {
                put(TestVariables.XML_PATTERN_BORDERLESSGAMING, "https://github.com/Codeusa/Borderless-Gaming/releases/latest");
            }
        };
        final String appName = "Borderless Gaming";
        AppInfoCollector collector1 = new AppInfoCollector(appName, execOrder);

        // when
        collector1.gatherInformation();
        collector1.save(OUT_DESTINATION_PATH, OUT_DESTINATION_PATH);
        AppInfoCollector collector2 = AppInfoCollector.load(collector1.getSerialFileName());

        // then
        assertCommonCollectorFields(collector1, collector2);
        Assert.assertFalse(collector1.isUpdateAvailable(Platforms.WINDOWS));
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getDates().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.SOURCECODE_TAR_GZ).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_ZIP).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X86_EXE).isEmpty());
    }

    @Test public void class_TeraCopy_online() {
        // given
        final Map<String, String> execOrder = new LinkedHashMap<>() {
            {
                put(TestVariables.XML_PATTERN_TERACOPY, "http://www.codesector.com/downloads");
            }
        };
        final String appName = "Code Sector TeraCopy";
        AppInfoCollector collector1 = new AppInfoCollector(appName, execOrder);

        // when
        collector1.gatherInformation();
        collector1.save(OUT_DESTINATION_PATH, OUT_DESTINATION_PATH);
        AppInfoCollector collector2 = AppInfoCollector.load(collector1.getSerialFileName());

        // then
        assertCommonCollectorFields(collector1, collector2);
        Assert.assertFalse(collector1.isUpdateAvailable(Platforms.WINDOWS));
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X86_EXE).isEmpty());
    }

    @Test public void class_PotPlayer_online() {
        // given
        final Map<String, String> execOrder = new LinkedHashMap<>() {
            {
                put(TestVariables.XML_PATTERN_POTPLAYER, "https://daumpotplayer.com/download/");
            }
        };
        final String appName = "DAUM Kakao PotPlayer";
        AppInfoCollector collector1 = new AppInfoCollector(appName, execOrder);

        // when
        collector1.gatherInformation();
        collector1.save(OUT_DESTINATION_PATH, OUT_DESTINATION_PATH);
        AppInfoCollector collector2 = AppInfoCollector.load(collector1.getSerialFileName());

        // then
        assertCommonCollectorFields(collector1, collector2);
        Assert.assertFalse(collector1.isUpdateAvailable(Platforms.WINDOWS));
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X86_EXE).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X64_EXE).isEmpty());
    }

    @Test public void class_BlizzardBattleNet_online() {
        // given
        final String url1 = "https://eu.battle.net/account/download/#bnetapp";
        final String url2 = "https://www.instalki.pl/programy/download/Windows/akcesoria/Blizzard.html";
        final Map<String, String> execOrder = new LinkedHashMap<>() {
            {
                put(TestVariables.XML_PATTERN_BLIZZARDBATTLENET_1, url1);
                put(TestVariables.XML_PATTERN_BLIZZARDBATTLENET_2, url2);
            }
        };
        final String appName = "Activision Blizzard Battle.net";
        AppInfoCollector collector1 = new AppInfoCollector(appName, execOrder);

        // when
        collector1.gatherInformation();
        collector1.save(OUT_DESTINATION_PATH, OUT_DESTINATION_PATH);
        AppInfoCollector collector2 = AppInfoCollector.load(collector1.getSerialFileName());

        assertCommonCollectorFields(collector1, collector2);
        Assert.assertFalse(collector1.isUpdateAvailable(Platforms.WINDOWS));
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getDates().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X86_EXE).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.MAC_OS_X_ZIP).isEmpty());
    }

    @Test public void class_OracleVirtualBox_online() {
        // given
        final String url1 = "https://www.virtualbox.org/wiki/Downloads";
        final String url2 = "https://www.majorgeeks.com/files/details/virtualbox.html";
        final String url3 = "https://www.virtualbox.org/wiki/Linux_Downloads";
        final Map<String, String> execOrder = new LinkedHashMap<>() {
            {
                put(TestVariables.XML_PATTERN_VIRTUALBOX_1, url1);
                put(TestVariables.XML_PATTERN_VIRTUALBOX_2, url2);
                put(TestVariables.XML_PATTERN_VIRTUALBOX_3, url3);
            }
        };
        final String appName = "Oracle VirtualBox";
        AppInfoCollector collector1 = new AppInfoCollector(appName, execOrder);

        // when
        collector1.addMutableEntry(url1, TestVariables.XML_PATTERN_VIRTUALBOX_A, LinksID.GENERIC, TestVariables.XML_PATTERN_VIRTUALBOX_B);
        collector1.gatherInformation();
        collector1.save(OUT_DESTINATION_PATH, OUT_DESTINATION_PATH);
        AppInfoCollector collector2 = AppInfoCollector.load(collector1.getSerialFileName());

        // then
        assertCommonCollectorFields(collector1, collector2);
        Assert.assertFalse(collector1.isUpdateAvailable(Platforms.ALL_SUPPORTED));
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.ALL_SUPPORTED).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getDates().getItem(Platforms.ALL_SUPPORTED).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.ORACLE_LINUX_OLD_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.MAC_OS_X_DMG).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.UBUNTU_TRUSTY_DEB).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.SOLARIS_TAR_GZ).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.SOURCECODE_TAR_BZ2).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.CENTOS_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.UBUNTU_XENIAL_DEB).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.DEBIAN_STRETCH_DEB).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.CENTOS_OLD_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.ORACLE_LINUX_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.DEBIAN_JESSIE_DEB).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.OPENSUSE_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.SDK_ZIP).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.RED_HAT_ENTERPRISE_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.FEDORA_OLD_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.LEAP_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.UBUNTU_BIONIC_DEB).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.RED_HAT_ENTERPRISE_OLD_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X86_EXE).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.FEDORA_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.LINUX_RUN).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.ORACLE_LINUX_OLD_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.MAC_OS_X_DMG).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.UBUNTU_TRUSTY_DEB).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.SOLARIS_TAR_GZ).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.SOURCECODE_TAR_BZ2).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.CENTOS_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.UBUNTU_XENIAL_DEB).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.DEBIAN_STRETCH_DEB).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.CENTOS_OLD_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.ORACLE_LINUX_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.DEBIAN_JESSIE_DEB).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.OPENSUSE_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.SDK_ZIP).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.RED_HAT_ENTERPRISE_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.FEDORA_OLD_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.LEAP_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.UBUNTU_BIONIC_DEB).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.RED_HAT_ENTERPRISE_OLD_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.WINDOWS_X86_EXE).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.FEDORA_RPM).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getHashes().getItem(LinksID.LINUX_RUN).isEmpty());
    }

    @Test public void class_EAOrigin_online() {
        // given
        final String url1 = "https://www.majorgeeks.com/files/details/origin_for_pc.html";
        final Map<String, String> execOrder = new LinkedHashMap<>() {
            {
                put(TestVariables.XML_PATTERN_ORIGIN_1, url1);
            }
        };
        final String appName = "EA Origin";
        AppInfoCollector collector1 = new AppInfoCollector(appName, execOrder);

        // when
        collector1.gatherInformation();
        collector1.save(OUT_DESTINATION_PATH, OUT_DESTINATION_PATH);
        AppInfoCollector collector2 = AppInfoCollector.load(collector1.getSerialFileName());

        // then
        assertCommonCollectorFields(collector1, collector2);
        Assert.assertFalse(collector1.isUpdateAvailable(Platforms.ALL_SUPPORTED));
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.ALL_SUPPORTED).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getVersions().getItem(Platforms.MAC_OS_X).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getDates().getItem(Platforms.ALL_SUPPORTED).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getDates().getItem(Platforms.WINDOWS).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getDates().getItem(Platforms.MAC_OS_X).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.WINDOWS_X86_EXE).isEmpty());
        Assert.assertFalse(collector1.getCollectedData().getLinks().getItem(LinksID.MAC_OS_X_DMG).isEmpty());
    }

}
