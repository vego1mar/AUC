package net.vego1mar.utils;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public final class ReflectionHelper {

    private static final Logger log = Logger.getLogger(ReflectionHelper.class);

    private ReflectionHelper() {
        // This should be a utility class.
    }

    @Nullable public static String getCurrentMethodName() {
        try {
            return Thread.currentThread().getStackTrace()[2].getMethodName();
        } catch (SecurityException | ArrayIndexOutOfBoundsException ex) {
            log.error(ex.getMessage());
        }

        return null;
    }
}
