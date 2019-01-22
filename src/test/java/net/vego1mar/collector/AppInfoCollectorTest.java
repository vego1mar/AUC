package net.vego1mar.collector;

import java.util.Deque;
import net.vego1mar.auxiliary.properties.InImpl;
import net.vego1mar.auxiliary.properties.InProperty;
import net.vego1mar.enumerators.properties.Platforms;
import net.vego1mar.enumerators.utils.HashType;
import net.vego1mar.rules.RuleImpl;
import net.vego1mar.rules.RulesExecutor;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import net.vego1mar.utils.HashGenerator;
import net.vego1mar.utils.ReflectionHelper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class AppInfoCollectorTest {

    @Ignore @Test public void save_PotPlayer() {
//        // given
//        AppInfoCollectible collector = TestVariables.getCollector(TestVariables.CODE_POTPLAYER);
//        collector.gatherInformation(TestCollections.getRulesForPotPlayer_1());
//
//        // when
//        collector.save(TestVariables.OBJECT_RUNTIME_POTPLAYER, TestVariables.XML_RUNTIME_POTPLAYER);
//
//        // then
//        final String objectRuntimeHash = HashGenerator.calculate(TestVariables.OBJECT_RUNTIME_POTPLAYER, HashType.SHA_1);
//        final String xmlRuntimeHash = HashGenerator.calculate(TestVariables.XML_RUNTIME_POTPLAYER, HashType.SHA_1);
//        Assert.assertEquals("1FE1835372258BD4477D14BB529AD5B172FA3A80", objectRuntimeHash.toUpperCase());
//        Assert.assertEquals("D5E2C9EA30FE2571E7E962852116B418AC2A0B41", xmlRuntimeHash.toUpperCase());
    }

    @Ignore @Test public void load_PotPlayer() throws IllegalAccessException {
//        // when
//        AppInfoCollectible collectible = AppInfoCollector.load(TestVariables.OBJECT_RUNTIME_POTPLAYER, TestVariables.XML_RUNTIME_POTPLAYER);
//
//        // given
//        final AppInfoCollector collector = (AppInfoCollector) collectible;
//        final String htmlCodeString = (String) ReflectionHelper.getField(AppInfoCollector.class, "htmlCode").get(collector);
//        final String htmlCodeFile = TestVariables.readFile(TestVariables.CODE_POTPLAYER);
//        final String appName = collector.getAppName();
//        final String sourceURL = (String) ReflectionHelper.getField(AppInfoCollector.class, "sourceURL").get(collector);
//        final String currentAppVersion = (String) ReflectionHelper.getField(AppInfoCollector.class, "currentAppVersion").get(collector);
//        final InImpl inProperty = (InProperty) ReflectionHelper.getField(RulesExecutor.class, "inProperty").get(collector.getExecutor());
//        final String inPropertyCode = (String) ReflectionHelper.getField(InProperty.class, "code").get(inProperty);
//        final Deque<RuleImpl> rulesSet = (Deque<RuleImpl>) ReflectionHelper.getField(RulesExecutor.class, "rulesSet").get(collector.getExecutor());
//        final Deque<RuleImpl> rulesSetPattern = TestCollections.getRulesForPotPlayer_1();
//        final String x86URL = "https://daumpotplayer.com/wp-content/uploads/2018/08/PotPlayerSetup.exe";
//        final String x64URL = "https://daumpotplayer.com/wp-content/uploads/2018/08/PotPlayerSetup64.exe";
//
//        // then
//        Assert.assertEquals(htmlCodeFile, htmlCodeString);
//        Assert.assertTrue(appName.contains(TestVariables.CODE_POTPLAYER));
//        Assert.assertEquals("", sourceURL);
//        Assert.assertEquals("", currentAppVersion);
//        Assert.assertNotNull(inProperty);
//        Assert.assertEquals(htmlCodeString, inPropertyCode);
//        Assert.assertEquals(rulesSetPattern.toString(), rulesSet.toString());
//        Assert.assertEquals("1.7.13963", collector.getExecutor().getResults().getVersions().getItem(Platforms.WINDOWS));
//        Assert.assertEquals("2018/08", collector.getExecutor().getResults().getDates().getItem(Platforms.WINDOWS));
    }

    @Test public void isUpdateAvailable_PotPlayer() {
//        // given
//        AppInfoCollectible collectible = TestVariables.getCollector(TestVariables.CODE_POTPLAYER);
//        AppInfoCollector collector = (AppInfoCollector) collectible;
//        collectible.gatherInformation(TestCollections.getRulesForPotPlayer_1());
//
//        // when
//        collector.setCurrentAppVersion("1.7.13964");
//        final boolean update1 = collectible.isUpdateAvailable();
//        collector.setCurrentAppVersion("1.7.13963");
//        final boolean update2 = collectible.isUpdateAvailable();
//        collector.setCurrentAppVersion("1.7.13962");
//        final boolean update3 = collectible.isUpdateAvailable();
//
//        // then
//        Assert.assertFalse(update1);
//        Assert.assertFalse(update2);
//        Assert.assertTrue(update3);
    }

}
