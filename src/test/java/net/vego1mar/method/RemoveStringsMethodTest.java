package net.vego1mar.method;

import net.vego1mar.properties.InProperty;
import net.vego1mar.target.Target;
import net.vego1mar.target.enumerators.In;
import net.vego1mar.target.enumerators.UseAs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RemoveStringsMethodTest {

    private RemoveStringsMethod method;

    @Before public void before() {
        // given
        method = new RemoveStringsMethod();
    }

    @Test public void testRemoveStringForContent1() {
        // given
        final Target target = new Target(In.CONTENT, UseAs.IGNORE);
        method.setString("a");
        final InProperty inProperty1 = new InProperty();
        inProperty1.setContent("abcdefghijklmnopqrstuvwxyz");

        // when
        InProperty inProperty1out = method.invoke(target, inProperty1);

        // then
        Assert.assertEquals("bcdefghijklmnopqrstuvwxyz", inProperty1.getContent());
        Assert.assertEquals(inProperty1out.getContent(), inProperty1.getContent());
    }

    @Test public void testRemoveStringForContent2() {
        // given
        final Target target = new Target(In.CONTENT, UseAs.IGNORE);
        method.setString("klmnopq");
        final InProperty inProperty2 = new InProperty();
        inProperty2.setContent("abcdefghijklmnopqrstuvwxyz");

        // when
        InProperty inProperty2out = method.invoke(target, inProperty2);

        // then
        Assert.assertEquals("abcdefghijrstuvwxyz", inProperty2.getContent());
        Assert.assertEquals(inProperty2out.getContent(), inProperty2.getContent());
    }

    @Test public void testRemoveStringForContent3() {
        // given
        final Target target = new Target(In.CONTENT, UseAs.IGNORE);
        method.setString("yz");
        final InProperty inProperty3 = new InProperty();
        inProperty3.setContent("abcdefghijklmnopqrstuvwxyz");

        // when
        InProperty inProperty3out = method.invoke(target, inProperty3);

        // then
        Assert.assertEquals("abcdefghijklmnopqrstuvwx", inProperty3.getContent());
        Assert.assertEquals(inProperty3out.getContent(), inProperty3.getContent());
    }

}
