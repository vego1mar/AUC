package net.vego1mar.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import net.vego1mar.utils.enumerators.HashType;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class HashGenerator {

    private static final Logger log = Logger.getLogger(HashGenerator.class);

    private HashGenerator() {
        // This should be a utility class.
    }

    @NotNull public static String calculate(@NotNull String filename, HashType type) {
        final int BUFFER_SIZE = 1024;
        File file = new File(filename);

        try (FileInputStream fileInput = new FileInputStream(file)) {
            MessageDigest digest = MessageDigest.getInstance(getAlgorithmType(type));
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            while ((bytesRead = fileInput.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }

            byte[] hashedBytes = digest.digest();
            return getBytesAsHexString(hashedBytes);
        } catch (IOException | NoSuchAlgorithmException exp) {
            log.error("Hash generation from file has failed.", exp);
        }

        return "";
    }

    @NotNull @Contract(pure = true) private static String getAlgorithmType(@NotNull HashType type) {
        switch (type) {
            case MD5:
                return "MD5";
            case SHA_1:
                return "SHA-1";
            case SHA_256:
                return "SHA-256";
        }

        return "";
    }

    @NotNull private static String getBytesAsHexString(@NotNull byte[] hashedBytes) {
        StringBuilder builder = new StringBuilder();

        for (byte block : hashedBytes) {
            builder.append(Integer.toString((block & 0xFF) + 0x100, 16).substring(1));
        }

        return builder.toString();
    }

}
