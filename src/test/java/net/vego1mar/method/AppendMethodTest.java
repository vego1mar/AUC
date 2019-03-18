package net.vego1mar.method;

import java.util.ArrayList;
import java.util.List;
import net.vego1mar.properties.InProperty;
import net.vego1mar.target.Target;
import net.vego1mar.target.enumerators.In;
import net.vego1mar.target.enumerators.UseAs;
import net.vego1mar.tests.TestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppendMethodTest {

    private AppendMethod method;

    @Before public void before() {
        // given
        method = new AppendMethod();
    }

    @Test public void testAppendForContent1() {
        // given
        final Target target = new Target(In.CONTENT, UseAs.IGNORE);
        final String appendText = TestHelper.getRandomUUID();
        final String textToAppend = TestHelper.getRandomUUID();
        final InProperty inProperty1 = new InProperty();
        method.setText(appendText);
        inProperty1.setContent(textToAppend);

        // when
        InProperty inProperty1out = method.invoke(target, inProperty1);

        // then
        Assert.assertEquals(textToAppend.concat(appendText), inProperty1.getContent());
        Assert.assertEquals(inProperty1out.getContent(), inProperty1.getContent());
    }

    @Test public void testAppendForCollection1() {
        // given
        final Target target = new Target(In.COLLECTION, UseAs.IGNORE);
        final int SAMPLES_NO = 23;
        final String appendText = TestHelper.getRandomUUID();
        final List<String> texts = new ArrayList<>();
        final List<String> appendedTexts = new ArrayList<>();
        final InProperty inProperty = new InProperty();

        for (int i = 0; i < SAMPLES_NO; i++) {
            String randomText = TestHelper.getRandomUUID();
            texts.add(randomText);
            appendedTexts.add(randomText.concat(appendText));
        }

        method.setText(appendText);
        inProperty.setCollection(texts);

        // when
        InProperty inPropertyOut = method.invoke(target, inProperty);

        // then
        Assert.assertEquals(appendedTexts, inProperty.getCollection());
        Assert.assertEquals(inPropertyOut.getCollection(), inProperty.getCollection());
    }

}
