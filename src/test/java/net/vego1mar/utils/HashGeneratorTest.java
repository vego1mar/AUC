package net.vego1mar.utils;

import net.vego1mar.enumerators.utils.HashType;
import net.vego1mar.tests.TestVariables;
import org.junit.Assert;
import org.junit.Test;

public class HashGeneratorTest {

    @Test public void calculate_PotPlayer() {
        // when
        String hash1 = HashGenerator.calculate(TestVariables.CODE_POTPLAYER, HashType.MD5);
        String hash2 = HashGenerator.calculate(TestVariables.CODE_POTPLAYER, HashType.SHA_1);
        String hash3 = HashGenerator.calculate(TestVariables.CODE_POTPLAYER, HashType.SHA_256);

        // then
        Assert.assertEquals("27575A921C4EAB3A89BED241C5097D88", hash1.toUpperCase());
        Assert.assertEquals("F2AA3884A4E5DD5C2457BDEBDF5F49E9D712171B", hash2.toUpperCase());
        Assert.assertEquals("11EEEC7C7F3575F0F8B348EDE8A38565C3DC13B2A5DEF0607AF8C8F7FB9A6029", hash3.toUpperCase());
    }

}
