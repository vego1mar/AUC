package net.vego1mar.rules;

import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import net.vego1mar.properties.UseAsProperty;
import net.vego1mar.properties.enumerators.LinksID;
import net.vego1mar.properties.enumerators.Platforms;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import net.vego1mar.utils.ReflectionHelper;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class RulesExecutorTest {

    private static final Logger log = Logger.getLogger(RulesExecutorTest.class);
    private static final String htmlCodeOf7ZipWebPage = TestVariables.readFile(TestVariables.CODE_7ZIP);
    private static final String htmlCodeOfAimpWebPage1 = TestVariables.readFile(TestVariables.CODE_AIMP_1);
    private static final String htmlCodeOfAimpWebPage2 = TestVariables.readFile(TestVariables.CODE_AIMP_2);
    private static final String htmlCodeOfSourceTreeWebPage = TestVariables.readFile(TestVariables.CODE_SOURCETREE);
    private static final String htmlCodeOfJetCleanWebPage1 = TestVariables.readFile(TestVariables.CODE_JETCLEAN_1);
    private static final String htmlCodeOfJetCleanWebPage2 = TestVariables.readFile(TestVariables.CODE_JETCLEAN_2);
    private static final String htmlCodeOfBorderlessGamingWebPage = TestVariables.readFile(TestVariables.CODE_BORDERLESSGAMING);
    private static final String htmlCodeOfTeraCopyWebPage = TestVariables.readFile(TestVariables.CODE_TERACOPY);
    private static final String htmlCodeOfPotPlayerWebPage = TestVariables.readFile(TestVariables.CODE_POTPLAYER);
    private static final String htmlCodeOfBlizzardBattleNetWebPage1 = TestVariables.readFile(TestVariables.CODE_BLIZZARDBATTLENET_1);
    private static final String htmlCodeOfBlizzardBattleNetWebPage2 = TestVariables.readFile(TestVariables.CODE_BLIZZARDBATTLENET_2);

    @Test public void execute_7Zip() throws Exception {
        // given
        Deque<Rule> rulesSet = TestCollections.getRulesFor7Zip_1();
        RulesExecutor rulesExecutor = new RulesExecutor(rulesSet, htmlCodeOf7ZipWebPage);

        // when
        rulesExecutor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsProperty useAsProperty = (UseAsProperty) executor2.get(rulesExecutor);
        Assert.assertEquals("18.05", useAsProperty.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals("2018-04-30", useAsProperty.getDates().getItem(Platforms.WINDOWS).trim());
        Assert.assertEquals("https://www.7-zip.org/a/7z1805.exe", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals("https://www.7-zip.org/a/7z1805-x64.exe", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X64_EXE));
        Assert.assertEquals("https://www.7-zip.org/a/7z1805-extra.7z", useAsProperty.getLinks().getItem(LinksID.WINDOWS_ANY_7Z));
        Assert.assertEquals("https://www.7-zip.org/a/7z1805-src.7z", useAsProperty.getLinks().getItem(LinksID.SOURCECODE_ANY_7Z));
        Assert.assertEquals("https://www.7-zip.org/a/lzma1805.7z", useAsProperty.getLinks().getItem(LinksID.SDK_ANY_7Z));
        Assert.assertEquals("https://www.7-zip.org/a/7z1805.msi", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_MSI));
        Assert.assertEquals("https://www.7-zip.org/a/7z1805-x64.msi", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X64_MSI));
    }

    @Ignore @Test public void executeMultithreading_7Zip() throws Exception {
        // given
        List<RulesExecutor> rulesExecutors = Arrays.asList(
            new RulesExecutor(TestCollections.getRulesFor7Zip_1(), htmlCodeOf7ZipWebPage),
            new RulesExecutor(TestCollections.getRulesFor7Zip_1(), htmlCodeOf7ZipWebPage)
        );

        CyclicBarrier barrier = new CyclicBarrier(rulesExecutors.size());
        List<Thread> threads = new LinkedList<>();

        for (RulesExecutor executor : rulesExecutors) {
            threads.add(new Thread(() -> {
                try {
                    executor.execute();
                    barrier.await();
                } catch (Exception exp) {
                    log.error(exp);
                }
            }));
        }

        // when
        for (Thread thread : threads) {
            thread.start();
        }

        barrier.await();

        // then
        for (RulesExecutor executor : rulesExecutors) {
            Field ruleExecutor = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
            UseAsProperty useAsProperty = (UseAsProperty) ruleExecutor.get(executor);
            Assert.assertEquals("18.05", useAsProperty.getVersions().getItem(Platforms.WINDOWS));
            Assert.assertEquals("2018-04-30", useAsProperty.getDates().getItem(Platforms.WINDOWS).trim());
            Assert.assertEquals("https://www.7-zip.org/a/7z1805.exe", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
            Assert.assertEquals("https://www.7-zip.org/a/7z1805-x64.exe", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X64_EXE));
            Assert.assertEquals("https://www.7-zip.org/a/7z1805-extra.7z", useAsProperty.getLinks().getItem(LinksID.WINDOWS_ANY_7Z));
            Assert.assertEquals("https://www.7-zip.org/a/7z1805-src.7z", useAsProperty.getLinks().getItem(LinksID.SOURCECODE_ANY_7Z));
            Assert.assertEquals("https://www.7-zip.org/a/lzma1805.7z", useAsProperty.getLinks().getItem(LinksID.SDK_ANY_7Z));
            Assert.assertEquals("https://www.7-zip.org/a/7z1805.msi", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_MSI));
            Assert.assertEquals("https://www.7-zip.org/a/7z1805-x64.msi", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X64_MSI));
        }
    }

    @Test public void execute_AIMP() throws Exception {
        // given
        Deque<Rule> rulesSet1 = TestCollections.getRulesForAimp_1();
        Deque<Rule> rulesSet2 = TestCollections.getRulesForAimp_2();
        RulesExecutor rulesExecutor = new RulesExecutor(rulesSet1, htmlCodeOfAimpWebPage1);

        // when
        rulesExecutor.execute();
        rulesExecutor.renew(rulesSet2, htmlCodeOfAimpWebPage2);
        rulesExecutor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsProperty useAsProperty = (UseAsProperty) executor2.get(rulesExecutor);
        Assert.assertEquals("v4.51, build 2084", useAsProperty.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals("01.12.2018", useAsProperty.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals("acc1353719050e5fa6f28e8d296078a4", useAsProperty.getHashes().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals("http://aimp.su/storage/acc1353719050e5fa6f28e8d296078a4/aimp_4.51.2084.exe",
            useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals("v2.80, build 631", useAsProperty.getVersions().getItem(Platforms.ANDROID));
        Assert.assertEquals("22.10.2018", useAsProperty.getDates().getItem(Platforms.ANDROID));
        Assert.assertEquals("1fa56dc3b88e3979875f6f4065261ab5", useAsProperty.getHashes().getItem(LinksID.ANDROID_X86ARM_APK));
        Assert.assertEquals("https://play.google.com/store/apps/details?id=com.aimp.player",
            useAsProperty.getLinks().getItem(LinksID.ANDROID_X86ARM_GOOGLEPLAY));
        Assert.assertEquals("http://www.aimp.ru/files/android/aimp_2.80.631.apk",
            useAsProperty.getLinks().getItem(LinksID.ANDROID_X86ARM_APK));
    }

    @Test public void execute_SourceTree() throws Exception {
        // given
        Deque<Rule> rulesSet = TestCollections.getRulesForSourceTree_1();
        RulesExecutor rulesExecutor = new RulesExecutor(rulesSet, htmlCodeOfSourceTreeWebPage);

        // when
        rulesExecutor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsProperty useAsProperty = (UseAsProperty) executor2.get(rulesExecutor);
        Assert.assertEquals("3.0.12", useAsProperty.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals("Jan 7, 2019", useAsProperty.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals("https://product-downloads.atlassian.com/software/sourcetree/windows/ga/SourceTreeSetup-3.0.12.exe",
            useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals("3.0.1_205", useAsProperty.getVersions().getItem(Platforms.MAC_OS_X));
        Assert.assertEquals("Jan 7, 2019", useAsProperty.getDates().getItem(Platforms.MAC_OS_X));
        Assert.assertEquals("https://product-downloads.atlassian.com/software/sourcetree/ga/Sourcetree_3.0.1_205.zip",
            useAsProperty.getLinks().getItem(LinksID.MAC_OS_X_ZIP));
    }

    @Test public void execute_JetClean() throws Exception {
        // given
        Deque<Rule> rulesSet1 = TestCollections.getRulesForJetClean_1();
        Deque<Rule> rulesSet2 = TestCollections.getRulesForJetClean_2();
        RulesExecutor executor = new RulesExecutor(rulesSet1, htmlCodeOfJetCleanWebPage1);

        // when
        executor.execute();
        executor.renew(rulesSet2, htmlCodeOfJetCleanWebPage2);
        executor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsProperty useAsProperty = (UseAsProperty) executor2.get(executor);
        Assert.assertEquals("1.5.0.129", useAsProperty.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals("02/26/2016", useAsProperty.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals("http://download.bluesprig.com/dl/jetclean-setup.exe",
            useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
    }

    @Test public void execute_BorderlessGaming() {
        // given
        Deque<Rule> rulesSet = TestCollections.getRulesForBorderlessGaming_1();
        RulesExecutor executor = new RulesExecutor(rulesSet, htmlCodeOfBorderlessGamingWebPage);

        // when
        executor.execute();

        // then
        UseAsProperty useAsProperty = executor.getResults();
        Assert.assertEquals("9.5.5", useAsProperty.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals("Oct 11, 2018", useAsProperty.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals("https://github.com/Codeusa/Borderless-Gaming/releases/download/9.5.5/BorderlessGaming9.5.5_admin_setup.exe",
            useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals("https://github.com/Codeusa/Borderless-Gaming/archive/9.5.5.tar.gz",
            useAsProperty.getLinks().getItem(LinksID.SOURCECODE_TAR_GZ));
        Assert.assertEquals("https://github.com/Codeusa/Borderless-Gaming/archive/9.5.5.zip",
            useAsProperty.getLinks().getItem(LinksID.WINDOWS_ZIP));
    }

    @Test public void execute_TeraCopy() {
        // given
        Deque<Rule> rulesSet = TestCollections.getRulesForTeraCopy_1();
        RulesExecutor executor = new RulesExecutor(rulesSet, htmlCodeOfTeraCopyWebPage);

        // when
        executor.execute();

        // then
        UseAsProperty useAsProperty = executor.getResults();
        Assert.assertEquals("3.26", useAsProperty.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals("http://www.codesector.com/files/teracopy.exe",
            useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
    }

    @Test public void execute_PotPlayer() {
        // given
        Deque<Rule> rulesSet = TestCollections.getRulesForPotPlayer_1();
        RulesExecutor executor = new RulesExecutor(rulesSet, htmlCodeOfPotPlayerWebPage);

        // when
        executor.execute();

        // then
        UseAsProperty useAsProperty = executor.getResults();
        Assert.assertEquals("1.7.16291", useAsProperty.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals("2018/12", useAsProperty.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals("https://daumpotplayer.com/wp-content/uploads/2018/12/PotPlayerSetup.exe",
            useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals("https://daumpotplayer.com/wp-content/uploads/2018/12/PotPlayerSetup64.exe",
            useAsProperty.getLinks().getItem(LinksID.WINDOWS_X64_EXE));
    }

    @Test public void execute_BlizzardBattleNet() {
        // given
        Deque<Rule> rulesSet1 = TestCollections.getRulesForBlizzardBattleNet_1();
        Deque<Rule> rulesSet2 = TestCollections.getRulesForBlizzardBattleNet_2();
        RulesExecutor executor = new RulesExecutor(rulesSet1, htmlCodeOfBlizzardBattleNetWebPage1);

        // when
        executor.execute();
        executor.renew(rulesSet2, htmlCodeOfBlizzardBattleNetWebPage2);
        executor.execute();

        // then
        UseAsProperty useAsProperty = executor.getResults();
        Assert.assertEquals("1.12.7.10904", useAsProperty.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals("17.01.2019", useAsProperty.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals("https://www.battle.net/download/getInstallerForGame?os=mac&amp;locale=plPL&amp;version=LIVE&amp;"
            + "gameProgram=BATTLENET_APP", useAsProperty.getLinks().getItem(LinksID.MAC_OS_X_ZIP));
        Assert.assertEquals("https://www.battle.net/download/getInstallerForGame?os=win&amp;locale=plPL&amp;version=LIVE&amp;"
            + "gameProgram=BATTLENET_APP", useAsProperty.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
    }

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

    @Test public void execute_EAOrigin() {
        // given
        final String CODE_EA_ORIGIN = Paths.get(System.getProperty("user.dir"), "/src/test/resources/Origin_wp1.txt").toString();
        final String htmlCode1 = TestVariables.readFile(CODE_EA_ORIGIN);
        final Deque<Rule> rulesSet1 = TestCollections.getRulesForEAOrigin_1();
        final RulesExecutor executor1 = new RulesExecutor(rulesSet1, htmlCode1);

        // when
        executor1.execute();

        // then
        UseAsProperty useAsProperty1 = executor1.getResults();
        String version = "10.5.35";
        Assert.assertEquals(version, useAsProperty1.getVersions().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals(version, useAsProperty1.getVersions().getItem(Platforms.WINDOWS));
        Assert.assertEquals(version, useAsProperty1.getVersions().getItem(Platforms.MAC_OS_X));
        String date = "02/26/2019";
        Assert.assertEquals(date.concat(" 08:04 AM"), useAsProperty1.getDates().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals(date, useAsProperty1.getDates().getItem(Platforms.WINDOWS));
        Assert.assertEquals(date, useAsProperty1.getDates().getItem(Platforms.MAC_OS_X));
        Assert.assertEquals("https://www.dm.origin.com/download/legacy",
            useAsProperty1.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals("https://www.dm.origin.com/mac/download/legacy",
            useAsProperty1.getLinks().getItem(LinksID.MAC_OS_X_DMG));
    }

}
