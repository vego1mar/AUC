package net.vego1mar.utils;

import net.vego1mar.rules.PotPlayerExecutorTest;
import net.vego1mar.utils.enumerators.HashType;
import org.junit.Assert;
import org.junit.Test;

public class HashGeneratorTest {

    @Test public void calculate_PotPlayer() {
        // when
        final String fileName = new PotPlayerExecutorTest().getFilePath1();
        final String hash1 = HashGenerator.calculate(fileName, HashType.MD5);
        final String hash2 = HashGenerator.calculate(fileName, HashType.SHA_1);
        final String hash3 = HashGenerator.calculate(fileName, HashType.SHA_256);

        // then
        Assert.assertEquals("DFAB947E6D16721A98E5EF6B22C228AF", hash1.toUpperCase());
        Assert.assertEquals("B56EC9E24A5B251CAD51C2330EFCC00857D89519", hash2.toUpperCase());
        Assert.assertEquals("F8875E75BE97EEE133651F59950649F2FD0506CD920D6F2A474A3F03677A49B1", hash3.toUpperCase());
    }

}
