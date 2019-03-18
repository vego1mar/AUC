package net.vego1mar.rules;

import static net.vego1mar.tests.TestHelper.getWorkingDirectory;
import static net.vego1mar.tests.TestHelper.readFile;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import net.vego1mar.method.ExtractWordMethod;
import net.vego1mar.method.FirstOfMethod;
import net.vego1mar.method.GrabUntilMethod;
import net.vego1mar.method.PrependMethod;
import net.vego1mar.method.RetrieveTagsMethod;
import net.vego1mar.method.TrimMethod;
import net.vego1mar.method.enumerators.FirstOfType;
import net.vego1mar.method.enumerators.RetrieveTagsType;
import net.vego1mar.method.enumerators.TrimSide;
import net.vego1mar.properties.UseAsProperty;
import net.vego1mar.properties.enumerators.LinksID;
import net.vego1mar.properties.enumerators.Platforms;
import net.vego1mar.target.Target;
import net.vego1mar.target.enumerators.In;
import net.vego1mar.target.enumerators.UseAs;
import net.vego1mar.utils.MethodFactory;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class VirtualBoxExecutorTest extends ExecutorTest {

    private String webPage3;
    private String webPageA;
    private String webPageB;
    private String filePath3;
    private String filePathA;
    private String filePathB;

    public VirtualBoxExecutorTest() {
        final String relationalPath1 = "/src/test/resources/OracleVBox_wp1.txt";
        final String codePath1 = Paths.get(getWorkingDirectory(), relationalPath1).toString();
        setFilePath1(codePath1);
        setWebPage1(readFile(getFilePath1()));
        final String relationalPath2 = "/src/test/resources/OracleVBox_wp2.txt";
        final String codePath2 = Paths.get(getWorkingDirectory(), relationalPath2).toString();
        setFilePath2(codePath2);
        setWebPage2(readFile(getFilePath2()));
        final String relationalPath3 = "/src/test/resources/OracleVBox_wp3.txt";
        final String codePath3 = Paths.get(getWorkingDirectory(), relationalPath3).toString();
        setFilePath3(codePath3);
        setWebPage3(readFile(getFilePath3()));
        final String relationalPathA = "/src/test/resources/OracleVBox_wp1.txt";
        final String codePathA = Paths.get(getWorkingDirectory(), relationalPathA).toString();
        setFilePathA(codePathA);
        setWebPageA(readFile(getFilePathA()));
        final String relationalPathB = "/src/test/resources/OracleVBox_wpAB.txt";
        final String codePathB = Paths.get(getWorkingDirectory(), relationalPathB).toString();
        setFilePathB(codePathB);
        setWebPageB(readFile(getFilePathB()));
    }

    @Override public Deque<Rule> getRules(int setNo) {
        switch (setNo) {
            case 1:
                return getRulesFromSet1(22);
            case 2:
                return getRulesFromSet2(4);
            case 3:
                return getRulesFromSet3(21);
            case 'A':
                return getRulesFromSetA(4);
            case 'B':
                return getRulesFromSetB(24);
        }

        return getRulesFromSet1(22);
    }

    private Deque<Rule> getRulesFromSet1(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("h3");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method2 = (TrimMethod) rule2.getMethod();
        method2.setSide(TrimSide.LEFT);
        method2.setNumberOf(2);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method3 = (FirstOfMethod) rule3.getMethod();
        method3.setType(FirstOfType.STRING);
        method3.setText("h3");

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createRetrieveTags());
        RetrieveTagsMethod method4 = (RetrieveTagsMethod) rule4.getMethod();
        method4.setType(RetrieveTagsType.ALL);
        method4.setTagname("a");

        Rule rule5 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createExtractWord());
        ExtractWordMethod method5 = (ExtractWordMethod) rule5.getMethod();
        method5.setPosition(2);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method6 = (FirstOfMethod) rule6.getMethod();
        method6.setType(FirstOfType.STRING);
        method6.setText("//");

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method7 = (FirstOfMethod) rule7.getMethod();
        method7.setType(FirstOfType.STRING);
        method7.setText("-");

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method8 = (TrimMethod) rule8.getMethod();
        method8.setSide(TrimSide.LEFT);
        method8.setNumberOf(1);

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createGrabUntil());
        GrabUntilMethod method9 = (GrabUntilMethod) rule9.getMethod();
        method9.setCharStop('W');

        Rule rule10 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodFactory.createTrim());
        TrimMethod method10 = (TrimMethod) rule10.getMethod();
        method10.setSide(TrimSide.RIGHT);
        method10.setNumberOf(1);
        rule10.getTarget().version(Platforms.ALL_SUPPORTED);

        Rule rule11 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createExtractWord());
        ExtractWordMethod method11 = (ExtractWordMethod) rule11.getMethod();
        method11.setPosition(2);

        Rule rule12 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createFetchHrefs());
        rule12.getTarget().linkID(LinksID.WINDOWS_X86_EXE);

        Rule rule13 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createExtractWord());
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(3);

        Rule rule14 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createFetchHrefs());
        rule14.getTarget().linkID(LinksID.MAC_OS_X_DMG);

        Rule rule15 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createExtractWord());
        ExtractWordMethod method15 = (ExtractWordMethod) rule15.getMethod();
        method15.setPosition(5);

        Rule rule16 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createFetchHrefs());
        rule16.getTarget().linkID(LinksID.SOLARIS_TAR_GZ);

        Rule rule17 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createExtractWord());
        ExtractWordMethod method17 = (ExtractWordMethod) rule17.getMethod();
        method17.setPosition(10);

        Rule rule18 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createFetchHrefs());
        rule18.getTarget().linkID(LinksID.AUX_EXTPACK);

        Rule rule19 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createExtractWord());
        ExtractWordMethod method19 = (ExtractWordMethod) rule19.getMethod();
        method19.setPosition(14);

        Rule rule20 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createFetchHrefs());
        rule20.getTarget().linkID(LinksID.SDK_ZIP);

        Rule rule21 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createExtractWord());
        ExtractWordMethod method21 = (ExtractWordMethod) rule21.getMethod();
        method21.setPosition(21);

        Rule rule22 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createFetchHrefs());
        rule22.getTarget().linkID(LinksID.SOURCECODE_TAR_BZ2);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10,
            rule11, rule12, rule13, rule14, rule15, rule16, rule17, rule18, rule19, rule20, rule21, rule22);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    private Deque<Rule> getRulesFromSet2(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("<strong>Date:");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method2 = (TrimMethod) rule2.getMethod();
        method2.setSide(TrimSide.LEFT);
        method2.setNumberOf(13);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createGrabUntil());
        GrabUntilMethod method3 = (GrabUntilMethod) rule3.getMethod();
        method3.setCharStop('\n');

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodFactory.createTrim());
        TrimMethod method4 = (TrimMethod) rule4.getMethod();
        method4.setSide(TrimSide.LEFT);
        method4.setNumberOf(10);
        rule4.getTarget().date(Platforms.ALL_SUPPORTED);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    private Deque<Rule> getRulesFromSet3(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("h3");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createTrim());
        TrimMethod method2 = (TrimMethod) rule2.getMethod();
        method2.setSide(TrimSide.LEFT);
        method2.setNumberOf(2);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method3 = (FirstOfMethod) rule3.getMethod();
        method3.setType(FirstOfType.STRING);
        method3.setText("h3");

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createRetrieveTags());
        RetrieveTagsMethod method4 = (RetrieveTagsMethod) rule4.getMethod();
        method4.setType(RetrieveTagsType.ALL);
        method4.setTagname("a");

        Rule rule5 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodFactory.createFetchHrefs());

        Rule rule6 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method6 = (ExtractWordMethod) rule6.getMethod();
        method6.setPosition(2);
        rule6.getTarget().linkID(LinksID.UBUNTU_BIONIC_DEB);

        Rule rule7 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method7 = (ExtractWordMethod) rule7.getMethod();
        method7.setPosition(3);
        rule7.getTarget().linkID(LinksID.UBUNTU_XENIAL_DEB);

        Rule rule8 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method8 = (ExtractWordMethod) rule8.getMethod();
        method8.setPosition(4);
        rule8.getTarget().linkID(LinksID.UBUNTU_TRUSTY_DEB);

        Rule rule9 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method9 = (ExtractWordMethod) rule9.getMethod();
        method9.setPosition(5);
        rule9.getTarget().linkID(LinksID.DEBIAN_STRETCH_DEB);

        Rule rule10 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method10 = (ExtractWordMethod) rule10.getMethod();
        method10.setPosition(6);
        rule10.getTarget().linkID(LinksID.DEBIAN_JESSIE_DEB);

        Rule rule11 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method11 = (ExtractWordMethod) rule11.getMethod();
        method11.setPosition(7);
        rule11.getTarget().linkID(LinksID.OPENSUSE_RPM);

        Rule rule12 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method12 = (ExtractWordMethod) rule12.getMethod();
        method12.setPosition(8);
        rule12.getTarget().linkID(LinksID.LEAP_RPM);

        Rule rule13 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(9);
        rule13.getTarget().linkID(LinksID.FEDORA_RPM);

        Rule rule14 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method14 = (ExtractWordMethod) rule14.getMethod();
        method14.setPosition(10);
        rule14.getTarget().linkID(LinksID.FEDORA_OLD_RPM);

        Rule rule15 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method15 = (ExtractWordMethod) rule15.getMethod();
        method15.setPosition(11);
        rule15.getTarget().linkID(LinksID.ORACLE_LINUX_RPM);

        Rule rule16 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method16 = (ExtractWordMethod) rule16.getMethod();
        method16.setPosition(11);
        rule16.getTarget().linkID(LinksID.RED_HAT_ENTERPRISE_RPM);

        Rule rule17 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method17 = (ExtractWordMethod) rule17.getMethod();
        method17.setPosition(11);
        rule17.getTarget().linkID(LinksID.CENTOS_RPM);

        Rule rule18 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method18 = (ExtractWordMethod) rule18.getMethod();
        method18.setPosition(12);
        rule18.getTarget().linkID(LinksID.ORACLE_LINUX_OLD_RPM);

        Rule rule19 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method19 = (ExtractWordMethod) rule19.getMethod();
        method19.setPosition(12);
        rule19.getTarget().linkID(LinksID.RED_HAT_ENTERPRISE_OLD_RPM);

        Rule rule20 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method20 = (ExtractWordMethod) rule20.getMethod();
        method20.setPosition(12);
        rule20.getTarget().linkID(LinksID.CENTOS_OLD_RPM);

        Rule rule21 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodFactory.createExtractWord());
        ExtractWordMethod method21 = (ExtractWordMethod) rule21.getMethod();
        method21.setPosition(13);
        rule21.getTarget().linkID(LinksID.LINUX_RUN);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10,
            rule11, rule12, rule13, rule14, rule15, rule16, rule17, rule18, rule19, rule20, rule21);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    private Deque<Rule> getRulesFromSetA(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("SHA256");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createRetrieveTags());
        RetrieveTagsMethod method2 = (RetrieveTagsMethod) rule2.getMethod();
        method2.setType(RetrieveTagsType.FIRST);
        method2.setTagname("a");

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createFetchHrefs());

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodFactory.createPrepend());
        PrependMethod method4 = (PrependMethod) rule4.getMethod();
        method4.setText("http://www.virtualbox.org");
        rule4.getTarget().linkID(LinksID.GENERIC);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    private Deque<Rule> getRulesFromSetB(int itemsNo) {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodFactory.createFirstOf());
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("*");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodFactory.createSplitWords());

        Rule rule3 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method3 = (ExtractWordMethod) rule3.getMethod();
        method3.setPosition(2);
        rule3.getTarget().hashID(LinksID.AUX_EXTPACK);

        Rule rule4 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method4 = (ExtractWordMethod) rule4.getMethod();
        method4.setPosition(10);
        rule4.getTarget().hashID(LinksID.ORACLE_LINUX_OLD_RPM);

        Rule rule5 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method5 = (ExtractWordMethod) rule5.getMethod();
        method5.setPosition(10);
        rule5.getTarget().hashID(LinksID.RED_HAT_ENTERPRISE_OLD_RPM);

        Rule rule6 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method6 = (ExtractWordMethod) rule6.getMethod();
        method6.setPosition(10);
        rule6.getTarget().hashID(LinksID.CENTOS_OLD_RPM);

        Rule rule7 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method7 = (ExtractWordMethod) rule7.getMethod();
        method7.setPosition(12);
        rule7.getTarget().hashID(LinksID.ORACLE_LINUX_RPM);

        Rule rule8 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method8 = (ExtractWordMethod) rule8.getMethod();
        method8.setPosition(12);
        rule8.getTarget().hashID(LinksID.RED_HAT_ENTERPRISE_RPM);

        Rule rule9 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method9 = (ExtractWordMethod) rule9.getMethod();
        method9.setPosition(12);
        rule9.getTarget().hashID(LinksID.CENTOS_RPM);

        Rule rule10 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method10 = (ExtractWordMethod) rule10.getMethod();
        method10.setPosition(14);
        rule10.getTarget().hashID(LinksID.FEDORA_OLD_RPM);

        Rule rule11 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method11 = (ExtractWordMethod) rule11.getMethod();
        method11.setPosition(16);
        rule11.getTarget().hashID(LinksID.FEDORA_RPM);

        Rule rule12 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method12 = (ExtractWordMethod) rule12.getMethod();
        method12.setPosition(18);
        rule12.getTarget().hashID(LinksID.LEAP_RPM);

        Rule rule13 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(20);
        rule13.getTarget().hashID(LinksID.OPENSUSE_RPM);

        Rule rule14 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method14 = (ExtractWordMethod) rule14.getMethod();
        method14.setPosition(22);
        rule14.getTarget().hashID(LinksID.LINUX_RUN);

        Rule rule15 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method15 = (ExtractWordMethod) rule15.getMethod();
        method15.setPosition(24);
        rule15.getTarget().hashID(LinksID.MAC_OS_X_DMG);

        Rule rule16 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method16 = (ExtractWordMethod) rule16.getMethod();
        method16.setPosition(26);
        rule16.getTarget().hashID(LinksID.SOLARIS_TAR_GZ);

        Rule rule17 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method17 = (ExtractWordMethod) rule17.getMethod();
        method17.setPosition(28);
        rule17.getTarget().hashID(LinksID.WINDOWS_X86_EXE);

        Rule rule18 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method18 = (ExtractWordMethod) rule18.getMethod();
        method18.setPosition(30);
        rule18.getTarget().hashID(LinksID.SOURCECODE_TAR_BZ2);

        Rule rule19 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method19 = (ExtractWordMethod) rule19.getMethod();
        method19.setPosition(32);
        rule19.getTarget().hashID(LinksID.SDK_ZIP);

        Rule rule20 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method20 = (ExtractWordMethod) rule20.getMethod();
        method20.setPosition(34);
        rule20.getTarget().hashID(LinksID.DEBIAN_JESSIE_DEB);

        Rule rule21 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method21 = (ExtractWordMethod) rule21.getMethod();
        method21.setPosition(36);
        rule21.getTarget().hashID(LinksID.DEBIAN_STRETCH_DEB);

        Rule rule22 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method22 = (ExtractWordMethod) rule22.getMethod();
        method22.setPosition(38);
        rule22.getTarget().hashID(LinksID.UBUNTU_BIONIC_DEB);

        Rule rule23 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method23 = (ExtractWordMethod) rule23.getMethod();
        method23.setPosition(40);
        rule23.getTarget().hashID(LinksID.UBUNTU_TRUSTY_DEB);

        Rule rule24 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodFactory.createExtractWord());
        ExtractWordMethod method24 = (ExtractWordMethod) rule24.getMethod();
        method24.setPosition(42);
        rule24.getTarget().hashID(LinksID.UBUNTU_XENIAL_DEB);

        List<Rule> rulesList = Arrays.asList(rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10,
            rule11, rule12, rule13, rule14, rule15, rule16, rule17, rule18, rule19, rule20, rule21, rule22, rule23,
            rule24);

        for (int i = 0; i < itemsNo; i++) {
            rulesSet.add(rulesList.get(i));
        }

        return rulesSet;
    }

    @NotNull @Contract(pure = true) private String getExpectedAllSupportedVersion() {
        return "6.0.4-128413";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows32bitExeLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0.4-128413-Win.exe";
    }

    @NotNull @Contract(pure = true) private String getExpectedMacOsXDmgPackageLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0.4-128413-OSX.dmg";
    }

    @NotNull @Contract(pure = true) private String getExpectedSolarisTarGzPackageLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0.4-128413-SunOS.tar.gz";
    }

    @NotNull @Contract(pure = true) private String getExpectedAuxExtpackLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/Oracle_VM_VirtualBox_Extension_Pack-6.0.4.vbox-extpack";
    }

    @NotNull @Contract(pure = true) private String getExpectedSdkZipLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBoxSDK-6.0.4-128413.zip";
    }

    @NotNull @Contract(pure = true) private String getExpectedSourceCodeTarBz2PackageLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0.4.tar.bz2";
    }

    @NotNull @Contract(pure = true) private String getExpectedAllSupportedDate() {
        return "01/28/2019 12:57 PM";
    }

    @NotNull @Contract(pure = true) private String getExpectedUbuntuBionicDebLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/virtualbox-6.0_6.0.4-128413~Ubuntu~bionic_amd64.deb";
    }

    @NotNull @Contract(pure = true) private String getExpectedUbuntuXenialDebLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/virtualbox-6.0_6.0.4-128413~Ubuntu~xenial_amd64.deb";
    }

    @NotNull @Contract(pure = true) private String getExpectedUbuntuTrustyDebLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/virtualbox-6.0_6.0.4-128413~Ubuntu~trusty_amd64.deb";
    }

    @NotNull @Contract(pure = true) private String getExpectedDebianStretchDebLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/virtualbox-6.0_6.0.4-128413~Debian~stretch_amd64.deb";
    }

    @NotNull @Contract(pure = true) private String getExpectedDebianJessieDebLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/virtualbox-6.0_6.0.4-128413~Debian~jessie_amd64.deb";
    }

    @NotNull @Contract(pure = true) private String getExpectedOpenSuseRpmLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_openSUSE150-1.x86_64.rpm";
    }

    @NotNull @Contract(pure = true) private String getExpectedLeapRpmLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_openSUSE132-1.x86_64.rpm";
    }

    @NotNull @Contract(pure = true) private String getExpectedFedoraRpmLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_fedora29-1.x86_64.rpm";
    }

    @NotNull @Contract(pure = true) private String getExpectedFedoraOldRpmLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_fedora26-1.x86_64.rpm";
    }

    @NotNull @Contract(pure = true) private String getExpectedOracleLinuxRpmLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_el7-1.x86_64.rpm";
    }

    @NotNull @Contract(pure = true) private String getExpectedRedHatEnterpriseRpmLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_el7-1.x86_64.rpm";
    }

    @NotNull @Contract(pure = true) private String getExpectedCentOsRpmLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_el7-1.x86_64.rpm";
    }

    @NotNull @Contract(pure = true) private String getExpectedOracleLinuxOldRpmLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_el6-1.x86_64.rpm";
    }

    @NotNull @Contract(pure = true) private String getExpectedRedHatEnterpriseOldRpmLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_el6-1.x86_64.rpm";
    }

    @NotNull @Contract(pure = true) private String getExpectedCentOsOldRpmLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0-6.0.4_128413_el6-1.x86_64.rpm";
    }

    @NotNull @Contract(pure = true) private String getExpectedLinuxRunLink() {
        return "https://download.virtualbox.org/virtualbox/6.0.4/VirtualBox-6.0.4-128413-Linux_amd64.run";
    }

    @NotNull @Contract(pure = true) private String getExpectedGenericLink() {
        return "http://www.virtualbox.org/download/hashes/6.0.4/SHA256SUMS";
    }

    @NotNull @Contract(pure = true) private String getExpectedAuxExtpackHash() {
        return "8887d5dd9dd26bd376926b38857715e28f2d678b6d3a034144ddc3fde4a387d9";
    }

    @NotNull @Contract(pure = true) private String getExpectedOracleLinuxOldRpmHash() {
        return "a4190a7f8b290ce846f9afb0f225f1b44b5fe2484f1136469f6f2d690a21f0ae";
    }

    @NotNull @Contract(pure = true) private String getExpectedRedHatEnterpriseOldRpmHash() {
        return "a4190a7f8b290ce846f9afb0f225f1b44b5fe2484f1136469f6f2d690a21f0ae";
    }

    @NotNull @Contract(pure = true) private String getExpectedCentOsOldRpmHash() {
        return "a4190a7f8b290ce846f9afb0f225f1b44b5fe2484f1136469f6f2d690a21f0ae";
    }

    @NotNull @Contract(pure = true) private String getExpectedOracleLinuxRpmHash() {
        return "64216d151610decd42471158380f334afd8ec45855e134d8d998a34a28676dec";
    }

    @NotNull @Contract(pure = true) private String getExpectedRedHatEnterpriseRpmHash() {
        return "64216d151610decd42471158380f334afd8ec45855e134d8d998a34a28676dec";
    }

    @NotNull @Contract(pure = true) private String getExpectedCentOsRpmHash() {
        return "64216d151610decd42471158380f334afd8ec45855e134d8d998a34a28676dec";
    }

    @NotNull @Contract(pure = true) private String getExpectedFedoraOldRpmHash() {
        return "239c34d7cedbb3e4908823e81768b98b0090f10a552f176c6b750ff34eca951a";
    }

    @NotNull @Contract(pure = true) private String getExpectedFedoraRpmHash() {
        return "10dd3c55c685d7bc635a720bd7d62b9ee8aa8b1eee1d7ad617b3d452ab4efe74";
    }

    @NotNull @Contract(pure = true) private String getExpectedLeapRpmHash() {
        return "15a3fa443ce3ae87d73b062943e9f601614ab917bcf0c3e27c94624e1f7aabb7";
    }

    @NotNull @Contract(pure = true) private String getExpectedOpenSuseRpmHash() {
        return "f0d388de5adf36e5bf5732fbb419b5589ab941db03fff868d88100b94fb5e168";
    }

    @NotNull @Contract(pure = true) private String getExpectedLinuxRunHash() {
        return "a04454161150e2f387177b4a3b688292ea4a516c915d859782742e8137393ba5";
    }

    @NotNull @Contract(pure = true) private String getExpectedMaxOsXDmgHash() {
        return "06d989b5717942ed5c48a3e26277885e69a3f341e459236208d01807d25dcb76";
    }

    @NotNull @Contract(pure = true) private String getExpectedSolarisTarGzHash() {
        return "28763874449c6bec0f39ad03dbe367d7b3d1e27d7d7efaa33db84a2466ec40e9";
    }

    @NotNull @Contract(pure = true) private String getExpectedWindows32bitExeHash() {
        return "a7b340eaa8ad9de72373162bcbba3fc0eeed9696fa404a0e5b99c0983151a3fc";
    }

    @NotNull @Contract(pure = true) private String getExpectedSourceCodeTarBz2Hash() {
        return "f80b0c68182c946fb74ada8034960c38159ad91085b153da1277e4f191af6e1f";
    }

    @NotNull @Contract(pure = true) private String getExpectedSdkZipHash() {
        return "618ee3fd3eb64b4dd6f11bd80f1116cad7a5f9308a65536ce257cd2dbbb68dd7";
    }

    @NotNull @Contract(pure = true) private String getExpectedDebianJessieDebHash() {
        return "db7d79865c60562b8dc77804a6954387bc732468e6b4338a23147fb10e04fe8b";
    }

    @NotNull @Contract(pure = true) private String getExpectedDebianStretchDebHash() {
        return "e959a3e083e612adadf6055c028bd9cb83c537f6cc49f37e0296cdd70f266a6b";
    }

    @NotNull @Contract(pure = true) private String getExpectedUbuntuBionicDebHash() {
        return "b7d2cec180dd7c8ef05053ecb7d2fe5939bc52fca7f31fbfd65ae7a02bce402f";
    }

    @NotNull @Contract(pure = true) private String getExpectedUbuntuTrustyDebHash() {
        return "706107936b2d268f0cfb6322602181b9730128420257ba81b5849145425402b1";
    }

    @NotNull @Contract(pure = true) private String getExpectedUbuntuXenialDebHash() {
        return "9bd7e40d54da8454fffa1bf8e838678cb55e0818df7e04bf88df23b1f4874cc2";
    }

    @Test public void testVersionGrabbing() {
        // given
        // rules 1-10
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(10), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAllSupportedVersion(), data.getVersions().getItem(Platforms.ALL_SUPPORTED));
    }

    @Test public void testLinks1Grabbing() {
        // given
        // rules 11-22
        RulesExecutor executor = new RulesExecutor(getRulesFromSet1(22), getWebPage1());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedWindows32bitExeLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedMacOsXDmgPackageLink(), data.getLinks().getItem(LinksID.MAC_OS_X_DMG));
        Assert.assertEquals(getExpectedSolarisTarGzPackageLink(), data.getLinks().getItem(LinksID.SOLARIS_TAR_GZ));
        Assert.assertEquals(getExpectedAuxExtpackLink(), data.getLinks().getItem(LinksID.AUX_EXTPACK));
        Assert.assertEquals(getExpectedSdkZipLink(), data.getLinks().getItem(LinksID.SDK_ZIP));
        Assert.assertEquals(getExpectedSourceCodeTarBz2PackageLink(), data.getLinks().getItem(LinksID.SOURCECODE_TAR_BZ2));
    }

    @Test public void testDateGrabbing() {
        // given
        // rules 1-4
        RulesExecutor executor = new RulesExecutor(getRulesFromSet2(4), getWebPage2());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAllSupportedDate(), data.getDates().getItem(Platforms.ALL_SUPPORTED));
    }

    @Test public void testLinks3Grabbing() {
        // given
        // rules 1-21
        RulesExecutor executor = new RulesExecutor(getRulesFromSet3(21), getWebPage3());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedUbuntuBionicDebLink(), data.getLinks().getItem(LinksID.UBUNTU_BIONIC_DEB));
        Assert.assertEquals(getExpectedUbuntuXenialDebLink(), data.getLinks().getItem(LinksID.UBUNTU_XENIAL_DEB));
        Assert.assertEquals(getExpectedUbuntuTrustyDebLink(), data.getLinks().getItem(LinksID.UBUNTU_TRUSTY_DEB));
        Assert.assertEquals(getExpectedDebianJessieDebLink(), data.getLinks().getItem(LinksID.DEBIAN_JESSIE_DEB));
        Assert.assertEquals(getExpectedDebianStretchDebLink(), data.getLinks().getItem(LinksID.DEBIAN_STRETCH_DEB));
        Assert.assertEquals(getExpectedOpenSuseRpmLink(), data.getLinks().getItem(LinksID.OPENSUSE_RPM));
        Assert.assertEquals(getExpectedLeapRpmLink(), data.getLinks().getItem(LinksID.LEAP_RPM));
        Assert.assertEquals(getExpectedFedoraRpmLink(), data.getLinks().getItem(LinksID.FEDORA_RPM));
        Assert.assertEquals(getExpectedFedoraOldRpmLink(), data.getLinks().getItem(LinksID.FEDORA_OLD_RPM));
        Assert.assertEquals(getExpectedOracleLinuxRpmLink(), data.getLinks().getItem(LinksID.ORACLE_LINUX_RPM));
        Assert.assertEquals(getExpectedRedHatEnterpriseRpmLink(), data.getLinks().getItem(LinksID.RED_HAT_ENTERPRISE_RPM));
        Assert.assertEquals(getExpectedCentOsRpmLink(), data.getLinks().getItem(LinksID.CENTOS_RPM));
        Assert.assertEquals(getExpectedOracleLinuxOldRpmLink(), data.getLinks().getItem(LinksID.ORACLE_LINUX_OLD_RPM));
        Assert.assertEquals(getExpectedRedHatEnterpriseOldRpmLink(), data.getLinks().getItem(LinksID.RED_HAT_ENTERPRISE_OLD_RPM));
        Assert.assertEquals(getExpectedCentOsOldRpmLink(), data.getLinks().getItem(LinksID.CENTOS_OLD_RPM));
        Assert.assertEquals(getExpectedLinuxRunLink(), data.getLinks().getItem(LinksID.LINUX_RUN));
    }

    @Test public void testGenericLinkAGrabbing() {
        // given
        // rules 1-4
        RulesExecutor executor = new RulesExecutor(getRulesFromSetA(4), getWebPageA());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedGenericLink(), data.getLinks().getItem(LinksID.GENERIC));
    }

    @Test public void testHashesBGrabbing() {
        // given
        // rules 1-24
        RulesExecutor executor = new RulesExecutor(getRulesFromSetB(24), getWebPageB());

        // when
        executor.execute();

        // then
        UseAsProperty data = executor.getResults();
        Assert.assertEquals(getExpectedAuxExtpackHash(), data.getHashes().getItem(LinksID.AUX_EXTPACK));
        Assert.assertEquals(getExpectedOracleLinuxOldRpmHash(), data.getHashes().getItem(LinksID.ORACLE_LINUX_OLD_RPM));
        Assert.assertEquals(getExpectedRedHatEnterpriseOldRpmHash(), data.getHashes().getItem(LinksID.RED_HAT_ENTERPRISE_OLD_RPM));
        Assert.assertEquals(getExpectedCentOsOldRpmHash(), data.getHashes().getItem(LinksID.CENTOS_OLD_RPM));
        Assert.assertEquals(getExpectedOracleLinuxRpmHash(), data.getHashes().getItem(LinksID.ORACLE_LINUX_RPM));
        Assert.assertEquals(getExpectedRedHatEnterpriseRpmHash(), data.getHashes().getItem(LinksID.RED_HAT_ENTERPRISE_RPM));
        Assert.assertEquals(getExpectedCentOsRpmHash(), data.getHashes().getItem(LinksID.CENTOS_RPM));
        Assert.assertEquals(getExpectedFedoraOldRpmHash(), data.getHashes().getItem(LinksID.FEDORA_OLD_RPM));
        Assert.assertEquals(getExpectedFedoraRpmHash(), data.getHashes().getItem(LinksID.FEDORA_RPM));
        Assert.assertEquals(getExpectedLeapRpmHash(), data.getHashes().getItem(LinksID.LEAP_RPM));
        Assert.assertEquals(getExpectedOpenSuseRpmHash(), data.getHashes().getItem(LinksID.OPENSUSE_RPM));
        Assert.assertEquals(getExpectedLinuxRunHash(), data.getHashes().getItem(LinksID.LINUX_RUN));
        Assert.assertEquals(getExpectedMaxOsXDmgHash(), data.getHashes().getItem(LinksID.MAC_OS_X_DMG));
        Assert.assertEquals(getExpectedSolarisTarGzHash(), data.getHashes().getItem(LinksID.SOLARIS_TAR_GZ));
        Assert.assertEquals(getExpectedWindows32bitExeHash(), data.getHashes().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedSourceCodeTarBz2Hash(), data.getHashes().getItem(LinksID.SOURCECODE_TAR_BZ2));
        Assert.assertEquals(getExpectedSdkZipHash(), data.getHashes().getItem(LinksID.SDK_ZIP));
        Assert.assertEquals(getExpectedDebianJessieDebHash(), data.getHashes().getItem(LinksID.DEBIAN_JESSIE_DEB));
        Assert.assertEquals(getExpectedDebianStretchDebHash(), data.getHashes().getItem(LinksID.DEBIAN_STRETCH_DEB));
        Assert.assertEquals(getExpectedUbuntuBionicDebHash(), data.getHashes().getItem(LinksID.UBUNTU_BIONIC_DEB));
        Assert.assertEquals(getExpectedUbuntuTrustyDebHash(), data.getHashes().getItem(LinksID.UBUNTU_TRUSTY_DEB));
        Assert.assertEquals(getExpectedUbuntuXenialDebHash(), data.getHashes().getItem(LinksID.UBUNTU_XENIAL_DEB));
    }

    @Override public void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data) {
        Assert.assertEquals(getExpectedAllSupportedVersion(), data.getVersions().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals(getExpectedWindows32bitExeLink(), data.getLinks().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedMacOsXDmgPackageLink(), data.getLinks().getItem(LinksID.MAC_OS_X_DMG));
        Assert.assertEquals(getExpectedSolarisTarGzPackageLink(), data.getLinks().getItem(LinksID.SOLARIS_TAR_GZ));
        Assert.assertEquals(getExpectedAuxExtpackLink(), data.getLinks().getItem(LinksID.AUX_EXTPACK));
        Assert.assertEquals(getExpectedSdkZipLink(), data.getLinks().getItem(LinksID.SDK_ZIP));
        Assert.assertEquals(getExpectedSourceCodeTarBz2PackageLink(), data.getLinks().getItem(LinksID.SOURCECODE_TAR_BZ2));
        Assert.assertEquals(getExpectedAllSupportedDate(), data.getDates().getItem(Platforms.ALL_SUPPORTED));
        Assert.assertEquals(getExpectedUbuntuBionicDebLink(), data.getLinks().getItem(LinksID.UBUNTU_BIONIC_DEB));
        Assert.assertEquals(getExpectedUbuntuXenialDebLink(), data.getLinks().getItem(LinksID.UBUNTU_XENIAL_DEB));
        Assert.assertEquals(getExpectedUbuntuTrustyDebLink(), data.getLinks().getItem(LinksID.UBUNTU_TRUSTY_DEB));
        Assert.assertEquals(getExpectedDebianJessieDebLink(), data.getLinks().getItem(LinksID.DEBIAN_JESSIE_DEB));
        Assert.assertEquals(getExpectedDebianStretchDebLink(), data.getLinks().getItem(LinksID.DEBIAN_STRETCH_DEB));
        Assert.assertEquals(getExpectedOpenSuseRpmLink(), data.getLinks().getItem(LinksID.OPENSUSE_RPM));
        Assert.assertEquals(getExpectedLeapRpmLink(), data.getLinks().getItem(LinksID.LEAP_RPM));
        Assert.assertEquals(getExpectedFedoraRpmLink(), data.getLinks().getItem(LinksID.FEDORA_RPM));
        Assert.assertEquals(getExpectedFedoraOldRpmLink(), data.getLinks().getItem(LinksID.FEDORA_OLD_RPM));
        Assert.assertEquals(getExpectedOracleLinuxRpmLink(), data.getLinks().getItem(LinksID.ORACLE_LINUX_RPM));
        Assert.assertEquals(getExpectedRedHatEnterpriseRpmLink(), data.getLinks().getItem(LinksID.RED_HAT_ENTERPRISE_RPM));
        Assert.assertEquals(getExpectedCentOsRpmLink(), data.getLinks().getItem(LinksID.CENTOS_RPM));
        Assert.assertEquals(getExpectedOracleLinuxOldRpmLink(), data.getLinks().getItem(LinksID.ORACLE_LINUX_OLD_RPM));
        Assert.assertEquals(getExpectedRedHatEnterpriseOldRpmLink(), data.getLinks().getItem(LinksID.RED_HAT_ENTERPRISE_OLD_RPM));
        Assert.assertEquals(getExpectedCentOsOldRpmLink(), data.getLinks().getItem(LinksID.CENTOS_OLD_RPM));
        Assert.assertEquals(getExpectedLinuxRunLink(), data.getLinks().getItem(LinksID.LINUX_RUN));
        Assert.assertEquals(getExpectedAuxExtpackHash(), data.getHashes().getItem(LinksID.AUX_EXTPACK));
        Assert.assertEquals(getExpectedOracleLinuxOldRpmHash(), data.getHashes().getItem(LinksID.ORACLE_LINUX_OLD_RPM));
        Assert.assertEquals(getExpectedRedHatEnterpriseOldRpmHash(), data.getHashes().getItem(LinksID.RED_HAT_ENTERPRISE_OLD_RPM));
        Assert.assertEquals(getExpectedCentOsOldRpmHash(), data.getHashes().getItem(LinksID.CENTOS_OLD_RPM));
        Assert.assertEquals(getExpectedOracleLinuxRpmHash(), data.getHashes().getItem(LinksID.ORACLE_LINUX_RPM));
        Assert.assertEquals(getExpectedRedHatEnterpriseRpmHash(), data.getHashes().getItem(LinksID.RED_HAT_ENTERPRISE_RPM));
        Assert.assertEquals(getExpectedCentOsRpmHash(), data.getHashes().getItem(LinksID.CENTOS_RPM));
        Assert.assertEquals(getExpectedFedoraOldRpmHash(), data.getHashes().getItem(LinksID.FEDORA_OLD_RPM));
        Assert.assertEquals(getExpectedFedoraRpmHash(), data.getHashes().getItem(LinksID.FEDORA_RPM));
        Assert.assertEquals(getExpectedLeapRpmHash(), data.getHashes().getItem(LinksID.LEAP_RPM));
        Assert.assertEquals(getExpectedOpenSuseRpmHash(), data.getHashes().getItem(LinksID.OPENSUSE_RPM));
        Assert.assertEquals(getExpectedLinuxRunHash(), data.getHashes().getItem(LinksID.LINUX_RUN));
        Assert.assertEquals(getExpectedMaxOsXDmgHash(), data.getHashes().getItem(LinksID.MAC_OS_X_DMG));
        Assert.assertEquals(getExpectedSolarisTarGzHash(), data.getHashes().getItem(LinksID.SOLARIS_TAR_GZ));
        Assert.assertEquals(getExpectedWindows32bitExeHash(), data.getHashes().getItem(LinksID.WINDOWS_X86_EXE));
        Assert.assertEquals(getExpectedSourceCodeTarBz2Hash(), data.getHashes().getItem(LinksID.SOURCECODE_TAR_BZ2));
        Assert.assertEquals(getExpectedSdkZipHash(), data.getHashes().getItem(LinksID.SDK_ZIP));
        Assert.assertEquals(getExpectedDebianJessieDebHash(), data.getHashes().getItem(LinksID.DEBIAN_JESSIE_DEB));
        Assert.assertEquals(getExpectedDebianStretchDebHash(), data.getHashes().getItem(LinksID.DEBIAN_STRETCH_DEB));
        Assert.assertEquals(getExpectedUbuntuBionicDebHash(), data.getHashes().getItem(LinksID.UBUNTU_BIONIC_DEB));
        Assert.assertEquals(getExpectedUbuntuTrustyDebHash(), data.getHashes().getItem(LinksID.UBUNTU_TRUSTY_DEB));
        Assert.assertEquals(getExpectedUbuntuXenialDebHash(), data.getHashes().getItem(LinksID.UBUNTU_XENIAL_DEB));
    }

    public String getWebPage3() {
        return webPage3;
    }

    protected void setWebPage3(@NotNull String webPage3) {
        this.webPage3 = webPage3;
    }

    public String getFilePath3() {
        return filePath3;
    }

    protected void setFilePath3(@NotNull String filePath3) {
        this.filePath3 = filePath3;
    }

    public String getWebPageA() {
        return webPageA;
    }

    protected void setWebPageA(@NotNull String webPageA) {
        this.webPageA = webPageA;
    }

    public String getFilePathA() {
        return filePathA;
    }

    protected void setFilePathA(@NotNull String filePathA) {
        this.filePathA = filePathA;
    }

    public String getWebPageB() {
        return webPageB;
    }

    protected void setWebPageB(@NotNull String webPageB) {
        this.webPageB = webPageB;
    }

    public String getFilePathB() {
        return filePathB;
    }

    protected void setFilePathB(@NotNull String filePathB) {
        this.filePathB = filePathB;
    }

}
