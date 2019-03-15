package net.vego1mar.rules;

import java.util.Deque;
import net.vego1mar.properties.UseAsProperty;
import net.vego1mar.properties.enumerators.LinksID;
import net.vego1mar.properties.enumerators.Platforms;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

@Deprecated public class RulesExecutorTest {

    @Test public void execute_OracleVirtualBox() {
        // given
        final String htmlCode1 = TestVariables.readFile(TestVariables.CODE_VIRTUALBOX_1);
        final String htmlCode2 = TestVariables.readFile(TestVariables.CODE_VIRTUALBOX_2);
        final String htmlCode3 = TestVariables.readFile(TestVariables.CODE_VIRTUALBOX_3);
        final Deque<Rule> rulesSet1 = TestCollections.getRulesForOracleVirtualBox_1();
        final Deque<Rule> rulesSet2 = TestCollections.getRulesForOracleVirtualBox_2();
        final Deque<Rule> rulesSet3 = TestCollections.getRulesForOracleVirtualBox_3();
        final RulesExecutor executor = new RulesExecutor(rulesSet1, htmlCode1);

        // when
        executor.execute();
        executor.renew(rulesSet2, htmlCode2);
        executor.execute();
        executor.renew(rulesSet3, htmlCode3);
        executor.execute();

        // then
        UseAsProperty useAsProperty = executor.getResults();
        Assert.assertEquals("6.0.4-128413", useAsProperty.getVersions().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0.4-128413-Win.exe",
            useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0.4-128413-OSX.dmg",
            useAsProperty.getLinks().getItem(LinksID.MAC_OS_X_DMG));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0.4-128413-SunOS.tar.gz",
            useAsProperty.getLinks().getItem(LinksID.SOLARIS_TAR_GZ));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/Oracle_VM_VirtualBox_Extension_Pack-6.0.4.vbox-extpack",
            useAsProperty.getLinks().getItem(LinksID.AUX_EXTPACK));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBoxSDK-6.0.4-128413.zip",
            useAsProperty.getLinks().getItem(LinksID.SDK_ZIP));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0.4.tar.bz2",
            useAsProperty.getLinks().getItem(LinksID.SOURCECODE_TAR_BZ2));
        Assert.assertEquals("01/28/2019 12:57 PM", useAsProperty.getDates().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/virtualbox-6.0_6.0.4-128413~Ubuntu~bionic_amd64.deb",
            useAsProperty.getLinks().getItem(LinksID.UBUNTU_BIONIC_DEB));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/virtualbox-6.0_6.0.4-128413~Ubuntu~xenial_amd64.deb",
            useAsProperty.getLinks().getItem(LinksID.UBUNTU_XENIAL_DEB));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/virtualbox-6.0_6.0.4-128413~Ubuntu~trusty_amd64.deb",
            useAsProperty.getLinks().getItem(LinksID.UBUNTU_TRUSTY_DEB));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/virtualbox-6.0_6.0.4-128413~Debian~stretch_amd64.deb",
            useAsProperty.getLinks().getItem(LinksID.DEBIAN_STRETCH_DEB));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/virtualbox-6.0_6.0.4-128413~Debian~jessie_amd64.deb",
            useAsProperty.getLinks().getItem(LinksID.DEBIAN_JESSIE_DEB));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_openSUSE150-1.x86_64.rpm",
            useAsProperty.getLinks().getItem(LinksID.OPENSUSE_RPM));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_openSUSE132-1.x86_64.rpm",
            useAsProperty.getLinks().getItem(LinksID.LEAP_RPM));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_fedora29-1.x86_64.rpm",
            useAsProperty.getLinks().getItem(LinksID.FEDORA_RPM));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_fedora26-1.x86_64.rpm",
            useAsProperty.getLinks().getItem(LinksID.FEDORA_OLD_RPM));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_el7-1.x86_64.rpm",
            useAsProperty.getLinks().getItem(LinksID.ORACLE_LINUX_RPM));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_el7-1.x86_64.rpm",
            useAsProperty.getLinks().getItem(LinksID.RED_HAT_ENTERPRISE_RPM));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_el7-1.x86_64.rpm",
            useAsProperty.getLinks().getItem(LinksID.CENTOS_RPM));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_el6-1.x86_64.rpm",
            useAsProperty.getLinks().getItem(LinksID.ORACLE_LINUX_OLD_RPM));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_el6-1.x86_64.rpm",
            useAsProperty.getLinks().getItem(LinksID.RED_HAT_ENTERPRISE_OLD_RPM));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_el6-1.x86_64.rpm",
            useAsProperty.getLinks().getItem(LinksID.CENTOS_OLD_RPM));
        Assert.assertEquals("https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0.4-128413-Linux_amd64.run",
            useAsProperty.getLinks().getItem(LinksID.LINUX_RUN));
    }

    @Test public void execute_OracleVirtualBox_AB() {
        // given
        final String htmlCode1 = TestVariables.readFile(TestVariables.CODE_VIRTUALBOX_1);
        final String htmlCode2 = TestVariables.readFile(TestVariables.CODE_VIRTUALBOX_AB);
        final Deque<Rule> rulesSet1 = TestCollections.getRulesForOracleVirtualBox_A();
        final Deque<Rule> rulesSet2 = TestCollections.getRulesForOracleVirtualBox_B();
        final RulesExecutor executor1 = new RulesExecutor(rulesSet1, htmlCode1);
        final RulesExecutor executor2 = new RulesExecutor(rulesSet2, htmlCode2);

        // when
        executor1.execute();
        executor2.execute();

        // then
        UseAsProperty useAsProperty1 = executor1.getResults();
        UseAsProperty useAsProperty2 = executor2.getResults();
        Assert.assertEquals("http://www.virtualbox.org/download/hashes/6.0.4/SHA256SUMS",
            useAsProperty1.getLinks().getItem(LinksID.GENERIC));
        Assert.assertEquals("8887d5dd9dd26bd376926b38857715e28f2d678b6d3a034144ddc3fde4a387d9",
            useAsProperty2.getHashes().getItem(LinksID.AUX_EXTPACK));
        Assert.assertEquals("db7d79865c60562b8dc77804a6954387bc732468e6b4338a23147fb10e04fe8b",
            useAsProperty2.getHashes().getItem(LinksID.DEBIAN_JESSIE_DEB));
        Assert.assertEquals("06d989b5717942ed5c48a3e26277885e69a3f341e459236208d01807d25dcb76",
            useAsProperty2.getHashes().getItem(LinksID.MAC_OS_X_DMG));
        Assert.assertEquals("64216d151610decd42471158380f334afd8ec45855e134d8d998a34a28676dec",
            useAsProperty2.getHashes().getItem(LinksID.ORACLE_LINUX_RPM));
        Assert.assertEquals("239c34d7cedbb3e4908823e81768b98b0090f10a552f176c6b750ff34eca951a",
            useAsProperty2.getHashes().getItem(LinksID.FEDORA_OLD_RPM));
        Assert.assertEquals("b7d2cec180dd7c8ef05053ecb7d2fe5939bc52fca7f31fbfd65ae7a02bce402f",
            useAsProperty2.getHashes().getItem(LinksID.UBUNTU_BIONIC_DEB));
        Assert.assertEquals("9bd7e40d54da8454fffa1bf8e838678cb55e0818df7e04bf88df23b1f4874cc2",
            useAsProperty2.getHashes().getItem(LinksID.UBUNTU_XENIAL_DEB));
        Assert.assertEquals("64216d151610decd42471158380f334afd8ec45855e134d8d998a34a28676dec",
            useAsProperty2.getHashes().getItem(LinksID.CENTOS_RPM));
        Assert.assertEquals("e959a3e083e612adadf6055c028bd9cb83c537f6cc49f37e0296cdd70f266a6b",
            useAsProperty2.getHashes().getItem(LinksID.DEBIAN_STRETCH_DEB));
        Assert.assertEquals("618ee3fd3eb64b4dd6f11bd80f1116cad7a5f9308a65536ce257cd2dbbb68dd7",
            useAsProperty2.getHashes().getItem(LinksID.SDK_ZIP));
        Assert.assertEquals("f0d388de5adf36e5bf5732fbb419b5589ab941db03fff868d88100b94fb5e168",
            useAsProperty2.getHashes().getItem(LinksID.OPENSUSE_RPM));
        Assert.assertEquals("f80b0c68182c946fb74ada8034960c38159ad91085b153da1277e4f191af6e1f",
            useAsProperty2.getHashes().getItem(LinksID.SOURCECODE_TAR_BZ2));
        Assert.assertEquals("64216d151610decd42471158380f334afd8ec45855e134d8d998a34a28676dec",
            useAsProperty2.getHashes().getItem(LinksID.RED_HAT_ENTERPRISE_RPM));
        Assert.assertEquals("a04454161150e2f387177b4a3b688292ea4a516c915d859782742e8137393ba5",
            useAsProperty2.getHashes().getItem(LinksID.LINUX_RUN));
        Assert.assertEquals("a4190a7f8b290ce846f9afb0f225f1b44b5fe2484f1136469f6f2d690a21f0ae",
            useAsProperty2.getHashes().getItem(LinksID.RED_HAT_ENTERPRISE_OLD_RPM));
        Assert.assertEquals("28763874449c6bec0f39ad03dbe367d7b3d1e27d7d7efaa33db84a2466ec40e9",
            useAsProperty2.getHashes().getItem(LinksID.SOLARIS_TAR_GZ));
        Assert.assertEquals("8887d5dd9dd26bd376926b38857715e28f2d678b6d3a034144ddc3fde4a387d9",
            useAsProperty2.getHashes().getItem(LinksID.AUX_EXTPACK));
        Assert.assertEquals("706107936b2d268f0cfb6322602181b9730128420257ba81b5849145425402b1",
            useAsProperty2.getHashes().getItem(LinksID.UBUNTU_TRUSTY_DEB));
        Assert.assertEquals("15a3fa443ce3ae87d73b062943e9f601614ab917bcf0c3e27c94624e1f7aabb7",
            useAsProperty2.getHashes().getItem(LinksID.LEAP_RPM));
        Assert.assertEquals("a7b340eaa8ad9de72373162bcbba3fc0eeed9696fa404a0e5b99c0983151a3fc",
            useAsProperty2.getHashes().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals("a4190a7f8b290ce846f9afb0f225f1b44b5fe2484f1136469f6f2d690a21f0ae",
            useAsProperty2.getHashes().getItem(LinksID.CENTOS_OLD_RPM));
        Assert.assertEquals("a4190a7f8b290ce846f9afb0f225f1b44b5fe2484f1136469f6f2d690a21f0ae",
            useAsProperty2.getHashes().getItem(LinksID.ORACLE_LINUX_OLD_RPM));
        Assert.assertEquals("10dd3c55c685d7bc635a720bd7d62b9ee8aa8b1eee1d7ad617b3d452ab4efe74",
            useAsProperty2.getHashes().getItem(LinksID.FEDORA_RPM));
    }

}
