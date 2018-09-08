package net.vego1mar.rules;

import java.lang.reflect.Field;
import java.util.Deque;
import java.util.LinkedList;
import net.vego1mar.rules.auxiliary.method.Method;
import net.vego1mar.rules.auxiliary.target.Target;
import net.vego1mar.rules.auxiliary.target.Targetable;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsInterface;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsProperty;
import net.vego1mar.rules.enumerators.methods.firstof.FirstOfType;
import net.vego1mar.rules.enumerators.traits.InTrait;
import net.vego1mar.rules.enumerators.traits.MethodTrait;
import net.vego1mar.rules.enumerators.traits.UseAsTrait;
import net.vego1mar.utils.ReflectionHelper;
import net.vego1mar.utils.WebPageDownloader;
import org.junit.Assert;
import org.junit.Test;

public class RulesExecutorTest {

    @Test
    public void executeRule1_online() throws Exception {
        // given
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(InTrait.HTML, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule1.getMethod().firstOf(FirstOfType.STRING, "<h1>");
        RuleBased rule2 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule2.getMethod().firstOf(FirstOfType.TAG, "<p>");
        RuleBased rule3 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.SPLIT_WORDS));
        RuleBased rule4 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.LATEST_APP_VERSION), new Method(MethodTrait.EXTRACT_WORD));
        rule4.getMethod().extractWord(3);
        RuleBased rule5 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.IGNORE), new Method(MethodTrait.EXTRACT_WORD));
        rule5.getMethod().extractWord(4);
        RuleBased rule6 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.UPDATE_DATE), new Method(MethodTrait.REMOVE_CHARACTERS));
        rule6.getMethod().removeCharacters("()");

        rulesSet.add(rule1);
        rulesSet.add(rule2);
        rulesSet.add(rule3);
        rulesSet.add(rule4);
        rulesSet.add(rule5);
        rulesSet.add(rule6);

        final String HTML_CODE = WebPageDownloader.getHtml("https://www.7-zip.org/download.html");
        RulesExecutor rulesExecutor = new RulesExecutor(rulesSet, HTML_CODE);

        // when
        rulesExecutor.execute();

        // then
        Field executor2 = ReflectionHelper.getField(RulesExecutor.class, "useAsProperty");
        UseAsInterface useAsProperty = (UseAsProperty) executor2.get(rulesExecutor);
        Assert.assertEquals("18.05", useAsProperty.getLatestAppVersion());
        Assert.assertEquals("2018-04-30", useAsProperty.getUpdateDate().trim());
    }

}
