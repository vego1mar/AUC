package net.vego1mar.tests;

import java.util.Deque;
import java.util.LinkedList;
import net.vego1mar.rules.Rule;
import net.vego1mar.rules.RuleBased;
import net.vego1mar.rules.auxiliary.method.Method;
import net.vego1mar.rules.auxiliary.target.Target;
import net.vego1mar.rules.enumerators.methods.firstof.FirstOfType;
import net.vego1mar.rules.enumerators.traits.InTrait;
import net.vego1mar.rules.enumerators.traits.MethodTrait;
import net.vego1mar.rules.enumerators.traits.UseAsTrait;

public final class TestCollections {

    private TestCollections() {
        // This should be a utility class.
    }

    public static Deque<RuleBased> getRulesSetFor7Zip() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(InTrait.HTML, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule1.getMethod().firstOf(FirstOfType.STRING, "h1");
        RuleBased rule2 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule2.getMethod().firstOf(FirstOfType.TAG, "p");
        RuleBased rule3 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.SPLIT_WORDS));
        RuleBased rule4 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.LATEST_APP_VERSION), new Method(MethodTrait.EXTRACT_WORD));
        rule4.getMethod().extractWord(3);
        RuleBased rule5 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.IGNORE), new Method(MethodTrait.EXTRACT_WORD));
        rule5.getMethod().extractWord(4);
        RuleBased rule6 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.UPDATE_DATE), new Method(MethodTrait.REMOVE_CHARACTERS));
        rule6.getMethod().removeCharacters("()");
        RuleBased rule8 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule8.getMethod().firstOf(FirstOfType.TAG, "table");
        RuleBased rule9 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.RETRIEVE_ALL_TAGS));
        rule9.getMethod().retrieveAllTags("a");
        RuleBased rule10 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.IGNORE), new Method(MethodTrait.FETCH_HREFS));
        RuleBased rule11 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.IGNORE), new Method(MethodTrait.PREPEND));
        rule11.getMethod().prepend("https://www.7-zip.org/");
        RuleBased rule12 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.WINDOWS_X86_PACKAGE_URL), new Method(MethodTrait.EXTRACT_WORD));
        rule12.getMethod().extractWord(1);
        RuleBased rule13 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.WINDOWS_X64_PACKAGE_URL), new Method(MethodTrait.EXTRACT_WORD));
        rule13.getMethod().extractWord(2);

        rulesSet.add(rule1);
        rulesSet.add(rule2);
        rulesSet.add(rule3);
        rulesSet.add(rule4);
        rulesSet.add(rule5);
        rulesSet.add(rule6);
        rulesSet.add(rule1);
        rulesSet.add(rule8);
        rulesSet.add(rule9);
        rulesSet.add(rule10);
        rulesSet.add(rule11);
        rulesSet.add(rule12);
        rulesSet.add(rule13);

        return rulesSet;
    }

    public static Deque<RuleBased> getRulesSetForAimp() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(InTrait.HTML, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule1.getMethod().firstOf(FirstOfType.STRING, "card_title");
        RuleBased rule2 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule2.getMethod().firstOf(FirstOfType.STRING, "v");
        RuleBased rule3 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.GRAB_UNTIL));
        rule3.getMethod().grabUntil('<');
        RuleBased rule4 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.LATEST_APP_VERSION), new Method(MethodTrait.REMOVE_CHARACTERS));
        rule4.getMethod().removeCharacters(",");
        RuleBased rule5 = new Rule(new Target(InTrait.HTML, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule5.getMethod().firstOf(FirstOfType.STRING, "card_description");
        RuleBased rule6 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule6.getMethod().firstOf(FirstOfType.STRING, ">");
        RuleBased rule7 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.GRAB_UNTIL));
        rule7.getMethod().grabUntil('<');
        RuleBased rule8 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.SPLIT_WORDS));
        RuleBased rule9 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.IGNORE), new Method(MethodTrait.EXTRACT_WORD));
        rule9.getMethod().extractWord(1);
        RuleBased rule10 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.UPDATE_DATE), new Method(MethodTrait.REMOVE_CHARACTERS));
        rule10.getMethod().removeCharacters(">");
        RuleBased rule11 = new Rule(new Target(InTrait.COLLECTION, UseAsTrait.WINDOWS_X86_HASH), new Method(MethodTrait.EXTRACT_WORD));
        rule11.getMethod().extractWord(7);
        RuleBased rule12 = new Rule(new Target(InTrait.HTML, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule12.getMethod().firstOf(FirstOfType.STRING, "Download from:");
        RuleBased rule13 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        rule13.getMethod().firstOf(FirstOfType.TAG, "a");
        RuleBased rule14 = new Rule(new Target(InTrait.CONTENT, UseAsTrait.WINDOWS_X86_PACKAGE_URL), new Method(MethodTrait.FETCH_HREFS));

        rulesSet.add(rule1);
        rulesSet.add(rule2);
        rulesSet.add(rule3);
        rulesSet.add(rule4);
        rulesSet.add(rule5);
        rulesSet.add(rule6);
        rulesSet.add(rule7);
        rulesSet.add(rule8);
        rulesSet.add(rule9);
        rulesSet.add(rule10);
        rulesSet.add(rule11);
        rulesSet.add(rule12);
        rulesSet.add(rule13);
        rulesSet.add(rule14);

        return rulesSet;
    }

}
