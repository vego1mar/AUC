package net.vego1mar.collector;

import net.vego1mar.auxiliary.properties.UseAsImpl;
import net.vego1mar.auxiliary.properties.UseAsProperty;
import net.vego1mar.tests.TestCollections;
import net.vego1mar.tests.TestVariables;
import net.vego1mar.utils.ReflectionHelper;
import org.junit.Assert;
import org.junit.Test;

public class AppInfoCollectorTest {

    @Test public void isUpdateAvailableFor7Zip_online() {
        // given
        AppInfoCollector collector = new AppInfoCollector("7-Zip", "https://www.7-zip.org/download.html");

        // when
        collector.setRulesSet(TestCollections.getRulesSetFor7Zip());
        collector.setCurrentAppVersion("18.05");
        collector.gatherInformation();
        boolean isUpdateAvailable = collector.isUpdateAvailable();

        // then
        Assert.assertFalse(isUpdateAvailable);
    }

    @Test public void serializationFor7Zip() throws Exception {
        // given
        AppInfoCollector collector = new AppInfoCollector("7-Zip", TestVariables.SOURCE_URL_OF_7ZIP);

        // when
        collector.setRulesSet(TestCollections.getRulesSetFor7Zip());
        collector.setCurrentAppVersion("18.05");
        ReflectionHelper.getField(AppInfoCollector.class, "htmlCode").set(collector, TestVariables.readFile(TestVariables.WEBPAGE_OF_7ZIP));
        collector.gatherInformation();
        collector.writeObject(TestVariables.SERIALIZATION_FILE_NAME_OF_7ZIP);
        AppInfoCollector serializedObject = AppInfoCollector.readObject(TestVariables.SERIALIZATION_FILE_NAME_OF_7ZIP);

        // then
        Assert.assertFalse(serializedObject.isUpdateAvailable());
        Assert.assertEquals("7-Zip", ReflectionHelper.getField(AppInfoCollector.class, "appName").get(serializedObject));
        UseAsImpl collectorUseAs = ((UseAsProperty) (ReflectionHelper.getField(AppInfoCollector.class, "useAsProperty").get(collector)));
        UseAsImpl serializedUseAs = ((UseAsProperty) (ReflectionHelper.getField(AppInfoCollector.class, "useAsProperty").get(serializedObject)));

        Assert.assertTrue(serializedUseAs.getLatestAppVersion().equals(collectorUseAs.getLatestAppVersion()) &&
            collectorUseAs.getUpdateDate().equals(serializedUseAs.getUpdateDate()) &&
            collectorUseAs.getWindowsX86packageURL().equals(serializedUseAs.getWindowsX86packageURL()) &&
            collectorUseAs.getWindowsX64packageURL().equals(serializedUseAs.getWindowsX64packageURL())
        );
    }

}
