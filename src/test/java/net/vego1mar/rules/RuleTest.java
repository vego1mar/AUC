package net.vego1mar.rules;

import net.vego1mar.rules.properties.*;
import org.apache.log4j.Logger;
import org.junit.Test;

public class RuleTest {

    private static final Logger log = Logger.getLogger(RuleTest.class);

    @Test
    public void ctor1() {
        // given
        Rule rule1 = new Rule();
        rule1.from(From.START);
        rule1.in(In.HTML);
        rule1.searchFor(SearchFor.FIRST_OF);
        rule1.searchType(SearchType.TAG);
        rule1.searchText("<h1>");
        rule1.useAs(UseAs.IGNORE);
        rule1.afterAction(AfterAction.NEXT_RULE);

        Rule rule2 = new Rule();
        rule2.from(From.CURSOR);
        rule2.in(In.HTML);
        rule2.searchFor(SearchFor.FIRST_OF);
        rule2.searchType(SearchType.TAG);
        rule2.searchText("<p>");
        rule2.useAs(UseAs.IGNORE);
        rule2.afterAction(AfterAction.STOP);

        RulesSet rulesSet = new RulesSet();
        rulesSet.push(new Rule(rule1));
        rulesSet.push(rule2);

        RulesExecutor executor = new RulesExecutor(rulesSet, "https://www.7-zip.org/download.html");

        // when
        executor.prepare();
        executor.next();
        executor.next();

        // then
    }

}
