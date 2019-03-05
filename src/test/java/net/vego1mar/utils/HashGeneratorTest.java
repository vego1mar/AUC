package net.vego1mar.utils;

import net.vego1mar.tests.TestVariables;
import net.vego1mar.utils.enumerators.HashType;
import org.junit.Assert;
import org.junit.Test;

public class HashGeneratorTest {

    @Test public void calculate_PotPlayer() {
        // when
        String hash1 = HashGenerator.calculate(TestVariables.CODE_POTPLAYER, HashType.MD5);
        String hash2 = HashGenerator.calculate(TestVariables.CODE_POTPLAYER, HashType.SHA_1);
        String hash3 = HashGenerator.calculate(TestVariables.CODE_POTPLAYER, HashType.SHA_256);

        // then
        Assert.assertEquals("A703485C7184E7807C5D1C3ECEDBFB67", hash1.toUpperCase());
        Assert.assertEquals("2FDC8C70C356CA2BB6BEC33A9E6BBCB9B79239A3", hash2.toUpperCase());
        Assert.assertEquals("DDEEB125C198A1D4DC5F1191FF71264B4DB5D739F36F8E2DFE4BCD01BD828AD4", hash3.toUpperCase());
    }

}
