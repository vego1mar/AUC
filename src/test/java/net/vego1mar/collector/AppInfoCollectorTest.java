package net.vego1mar.collector;

import static net.vego1mar.tests.TestHelper.getWorkingDirectory;

import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.Map;
import net.vego1mar.properties.PlatformsProperty;
import net.vego1mar.properties.enumerators.Platforms;
import net.vego1mar.utils.ReflectionHelper;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

public class AppInfoCollectorTest {

    private static final Logger log = Logger.getLogger(AppInfoCollectorTest.class);

    @Test private void assertCommonCollectorFields(@NotNull AppInfoCollector collector1, @NotNull AppInfoCollector collector2) {
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

    @Test private void assertAppVersionsThroughReflection(@NotNull AppInfoCollector ver1, @NotNull AppInfoCollector ver2) {
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

    @Test protected void assertCollectorSaveAndLoad(@NotNull AppInfoCollector collector1) {
        // given
        final String outDestinationPath = Paths.get(getWorkingDirectory(), "/runtime").toString();

        // when
        collector1.save(outDestinationPath, outDestinationPath);
        AppInfoCollector collector2 = AppInfoCollector.load(collector1.getSerialFileName());

        // then
        assertCommonCollectorFields(collector1, collector2);
    }

}
