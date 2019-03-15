package net.vego1mar.collector;

import static net.vego1mar.tests.TestVariables.getWorkingDirectory;

import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import net.vego1mar.properties.PlatformsProperty;
import net.vego1mar.properties.enumerators.LinksID;
import net.vego1mar.properties.enumerators.Platforms;
import net.vego1mar.tests.TestVariables;
import net.vego1mar.utils.ReflectionHelper;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

@Deprecated public class AppInfoCollectorTest {

    private static final Logger log = Logger.getLogger(AppInfoCollectorTest.class);
    private final String OUT_DESTINATION_PATH = Paths.get(getWorkingDirectory(), "/runtime").toString();

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

        assertAppVersionsThroughReflection(collector1, collector2);
    }

    private void assertAppVersionsThroughReflection(@NotNull AppInfoCollector ver1, @NotNull AppInfoCollector ver2) {
        try {
            Field appVersions = ReflectionHelper.getField(AppInfoCollector.class, "appVersions");
            PlatformsProperty versions1 = (PlatformsProperty) appVersions.get(ver1);
            PlatformsProperty versions2 = (PlatformsProperty) appVersions.get(ver2);

            for (Map.Entry<Platforms, String> entry : versions2.getPlatforms().entrySet()) {
                Assert.assertEquals(versions1.getPlatforms().get(entry.getKey()), entry.getValue());
            }
        } catch (IllegalAccessException exp) {
            log.error(ReflectionHelper.getCurrentMethodName() + "() NOT OK", exp);
        }
    }

    protected void assertCollectorSaveAndLoad(@NotNull AppInfoCollector collector1) {
        // when
        collector1.save(OUT_DESTINATION_PATH, OUT_DESTINATION_PATH);
        AppInfoCollector collector2 = AppInfoCollector.load(collector1.getSerialFileName());

        // then
        assertCommonCollectorFields(collector1, collector2);
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
        //assertCommonCollectorFields(collector1, collector2);
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

}
