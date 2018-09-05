package net.vego1mar.rules;

import java.util.LinkedList;
import net.vego1mar.rules.auxiliary.method.Method;
import net.vego1mar.rules.auxiliary.target.Target;
import net.vego1mar.rules.auxiliary.target.Targetable;
import net.vego1mar.rules.enumerators.methods.firstof.FirstOfType;
import net.vego1mar.rules.enumerators.traits.AfterActionTrait;
import net.vego1mar.rules.enumerators.traits.FromTrait;
import net.vego1mar.rules.enumerators.traits.InTrait;
import net.vego1mar.rules.enumerators.traits.MethodTrait;
import net.vego1mar.rules.enumerators.traits.UseAsTrait;
import net.vego1mar.utils.WebPageDownloader;
import org.junit.Assert;
import org.junit.Test;

public class RulesExecutorTest {

    @Test
    public void executeRule1_online() {
        // given
        LinkedList<RuleBased> rulesSet = new LinkedList<>();
        Targetable target1 = new Target(FromTrait.START, InTrait.HTML, UseAsTrait.IGNORE);
        RuleBased rule1 = new Rule(target1, new Method(MethodTrait.FIRST_OF), AfterActionTrait.STOP);
        rule1.getMethod().firstOf(target1, FirstOfType.TAG, "<h1>");
        rulesSet.add(rule1);

        final String HTML_CODE = WebPageDownloader.getHtml("https://www.7-zip.org/download.html");
        RulesExecutor rulesExecutor = new RulesExecutor(rulesSet, HTML_CODE);

        // when
        rulesExecutor.executeRule(rule1);

        // then
        Assert.assertEquals("<h1>Download</h1>", ((MethodExecutor) rulesExecutor.executor).getInProperty().getContent());
    }

}
