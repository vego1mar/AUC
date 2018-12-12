package net.vego1mar.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ReflectionHelper {

    private static final Logger log = Logger.getLogger(ReflectionHelper.class);

    private ReflectionHelper() {
        // This should be a utility class.
    }

    @Nullable
    public static String getCurrentMethodName() {
        try {
            return Thread.currentThread().getStackTrace()[2].getMethodName();
        } catch (SecurityException | ArrayIndexOutOfBoundsException ex) {
            log.error(ex.getMessage());
        }

        return null;
    }

    @Nullable
    public static Field getField(Class clazz, String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            makeAccessible(field);
            return field;
        } catch (NoSuchFieldException ex) {
            if (clazz.getSuperclass() != null) {
                Field field = getField(clazz.getSuperclass(), name);
                makeAccessible(field);
                return field;
            }

            log.error(ex.getMessage());
        } catch (SecurityException | NullPointerException ex) {
            log.error(ex.getMessage());
        }

        return null;
    }

    private static void makeAccessible(@NotNull Field field) {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }

}
