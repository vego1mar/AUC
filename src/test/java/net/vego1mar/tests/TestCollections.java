package net.vego1mar.tests;

import java.util.Deque;
import java.util.LinkedList;
import net.vego1mar.auxiliary.method.ExtractWordMethod;
import net.vego1mar.auxiliary.method.FetchHrefsMethod;
import net.vego1mar.auxiliary.method.FirstOfMethod;
import net.vego1mar.auxiliary.method.GrabUntilMethod;
import net.vego1mar.auxiliary.method.PrependMethod;
import net.vego1mar.auxiliary.method.RemoveCharactersMethod;
import net.vego1mar.auxiliary.method.RetrieveTagsMethod;
import net.vego1mar.auxiliary.method.SplitWordsMethod;
import net.vego1mar.auxiliary.method.TrimMethod;
import net.vego1mar.enumerators.methods.RetrieveTagsType;
import net.vego1mar.enumerators.methods.TrimSide;
import net.vego1mar.rules.Rule;
import net.vego1mar.rules.RuleBased;
import net.vego1mar.auxiliary.target.Target;
import net.vego1mar.enumerators.methods.FirstOfType;
import net.vego1mar.enumerators.traits.In;
import net.vego1mar.enumerators.traits.UseAs;

public final class TestCollections {

    private TestCollections() {
        // This should be a utility class.
    }

    public static Deque<RuleBased> getRulesFor7Zip_1() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("h1");

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method2 = (FirstOfMethod) rule2.getMethod();
        method2.setType(FirstOfType.TAG);
        method2.setText("p");

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new SplitWordsMethod());

        RuleBased rule4 = new Rule(new Target(In.COLLECTION, UseAs.LATEST_APP_VERSION), new ExtractWordMethod());
        ExtractWordMethod method4 = (ExtractWordMethod) rule4.getMethod();
        method4.setPosition(3);

        RuleBased rule5 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), new ExtractWordMethod());
        ExtractWordMethod method5 = (ExtractWordMethod) rule5.getMethod();
        method5.setPosition(4);

        RuleBased rule6 = new Rule(new Target(In.CONTENT, UseAs.UPDATE_DATE), new RemoveCharactersMethod());
        RemoveCharactersMethod method6 = (RemoveCharactersMethod) rule6.getMethod();
        method6.setSigns("()");

        RuleBased rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method8 = (FirstOfMethod) rule8.getMethod();
        method8.setType(FirstOfType.TAG);
        method8.setText("table");

        RuleBased rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new RetrieveTagsMethod());
        RetrieveTagsMethod method9 = (RetrieveTagsMethod) rule9.getMethod();
        method9.setType(RetrieveTagsType.ALL);
        method9.setTagname("a");

        RuleBased rule10 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), new FetchHrefsMethod());

        RuleBased rule11 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), new PrependMethod());
        PrependMethod method11 = (PrependMethod) rule11.getMethod();
        method11.setText("https://www.7-zip.org/");

        RuleBased rule12 = new Rule(new Target(In.COLLECTION, UseAs.WINDOWS_X86_PACKAGE_URL), new ExtractWordMethod());
        ExtractWordMethod method12 = (ExtractWordMethod) rule12.getMethod();
        method12.setPosition(1);

        RuleBased rule13 = new Rule(new Target(In.COLLECTION, UseAs.WINDOWS_X64_PACKAGE_URL), new ExtractWordMethod());
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(2);

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

    public static Deque<RuleBased> getRulesForAimp_1() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("card_title");

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method2 = (FirstOfMethod) rule2.getMethod();
        method2.setType(FirstOfType.STRING);
        method2.setText("v");

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new GrabUntilMethod());
        GrabUntilMethod method3 = (GrabUntilMethod) rule3.getMethod();
        method3.setCharStop('<');

        RuleBased rule4 = new Rule(new Target(In.CONTENT, UseAs.LATEST_APP_VERSION), new RemoveCharactersMethod());
        RemoveCharactersMethod method4 = (RemoveCharactersMethod) rule4.getMethod();
        method4.setSigns(",");

        RuleBased rule5 = new Rule(new Target(In.CODE, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method5 = (FirstOfMethod) rule5.getMethod();
        method5.setType(FirstOfType.STRING);
        method5.setText("card_description");

        RuleBased rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method6 = (FirstOfMethod) rule6.getMethod();
        method6.setType(FirstOfType.STRING);
        method6.setText(">");

        RuleBased rule7 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new GrabUntilMethod());
        GrabUntilMethod method7 = (GrabUntilMethod) rule7.getMethod();
        method7.setCharStop('<');

        RuleBased rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new SplitWordsMethod());

        RuleBased rule9 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), new ExtractWordMethod());
        ExtractWordMethod method9 = (ExtractWordMethod) rule9.getMethod();
        method9.setPosition(1);

        RuleBased rule10 = new Rule(new Target(In.CONTENT, UseAs.UPDATE_DATE), new RemoveCharactersMethod());
        RemoveCharactersMethod method10 = (RemoveCharactersMethod) rule10.getMethod();
        method10.setSigns(">");

        RuleBased rule11 = new Rule(new Target(In.COLLECTION, UseAs.WINDOWS_X86_HASH), new ExtractWordMethod());
        ExtractWordMethod method11 = (ExtractWordMethod) rule11.getMethod();
        method11.setPosition(7);

        RuleBased rule12 = new Rule(new Target(In.CODE, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method12 = (FirstOfMethod) rule12.getMethod();
        method12.setType(FirstOfType.STRING);
        method12.setText("Download from:");

        RuleBased rule13 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method13 = (FirstOfMethod) rule13.getMethod();
        method13.setType(FirstOfType.TAG);
        method13.setText("a");

        RuleBased rule14 = new Rule(new Target(In.CONTENT, UseAs.WINDOWS_X86_PACKAGE_URL), new FetchHrefsMethod());

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

    public static Deque<RuleBased> getRulesForSourceTree_1() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("link--secondary");

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new RetrieveTagsMethod());
        RetrieveTagsMethod method2 = (RetrieveTagsMethod) rule2.getMethod();
        method2.setType(RetrieveTagsType.FIRST);
        method2.setTagname("a");

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.WINDOWS_X86_PACKAGE_URL), new FetchHrefsMethod());

        RuleBased rule4 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method4 = (FirstOfMethod) rule4.getMethod();
        method4.setType(FirstOfType.STRING);
        method4.setText("-");

        RuleBased rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new TrimMethod());
        TrimMethod method5 = (TrimMethod) rule5.getMethod();
        method5.setSide(TrimSide.LEFT);
        method5.setNumberOf(1);

        RuleBased rule6 = new Rule(new Target(In.CONTENT, UseAs.LATEST_APP_VERSION), new TrimMethod());
        TrimMethod method6 = (TrimMethod) rule6.getMethod();
        method6.setSide(TrimSide.RIGHT);
        method6.setNumberOf(4);

        RuleBased rule7 = new Rule(new Target(In.CODE, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method7 = (FirstOfMethod) rule7.getMethod();
        method7.setType(FirstOfType.STRING);
        method7.setText("LastRendered:");

        RuleBased rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method8 = (FirstOfMethod) rule8.getMethod();
        method8.setType(FirstOfType.STRING);
        method8.setText(" ");

        RuleBased rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new GrabUntilMethod());
        GrabUntilMethod method9 = (GrabUntilMethod) rule9.getMethod();
        method9.setCharStop(':');

        RuleBased rule10 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new TrimMethod());
        TrimMethod method10 = (TrimMethod) rule10.getMethod();
        method10.setSide(TrimSide.RIGHT);
        method10.setNumberOf(2);

        RuleBased rule11 = new Rule(new Target(In.CONTENT, UseAs.UPDATE_DATE), new TrimMethod());
        TrimMethod method11 = (TrimMethod) rule11.getMethod();
        method11.setSide(TrimSide.LEFT);
        method11.setNumberOf(1);

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

        return rulesSet;
    }

    public static Deque<RuleBased> getRulesForJetClean_1() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), new RetrieveTagsMethod());
        RetrieveTagsMethod method1 = (RetrieveTagsMethod) rule1.getMethod();
        method1.setType(RetrieveTagsType.FIRST);
        method1.setTagname("title");

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new ExtractWordMethod());
        ExtractWordMethod method2 = (ExtractWordMethod) rule2.getMethod();
        method2.setPosition(3);

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.LATEST_APP_VERSION), new TrimMethod());
        TrimMethod method3 = (TrimMethod) rule3.getMethod();
        method3.setSide(TrimSide.RIGHT);
        method3.setNumberOf(8);

        RuleBased rule4 = new Rule(new Target(In.CODE, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method4 = (FirstOfMethod) rule4.getMethod();
        method4.setType(FirstOfType.STRING);
        method4.setText("Debug:");

        RuleBased rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new TrimMethod());
        TrimMethod method5 = (TrimMethod) rule5.getMethod();
        method5.setSide(TrimSide.LEFT);
        method5.setNumberOf(7);

        RuleBased rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new GrabUntilMethod());
        GrabUntilMethod method6 = (GrabUntilMethod) rule6.getMethod();
        method6.setCharStop('>');

        RuleBased rule7 = new Rule(new Target(In.CONTENT, UseAs.WINDOWS_X86_PACKAGE_URL), new TrimMethod());
        TrimMethod method7 = (TrimMethod) rule7.getMethod();
        method7.setSide(TrimSide.RIGHT);
        method7.setNumberOf(3);

        rulesSet.add(rule1);
        rulesSet.add(rule2);
        rulesSet.add(rule3);
        rulesSet.add(rule4);
        rulesSet.add(rule5);
        rulesSet.add(rule6);
        rulesSet.add(rule7);

        return rulesSet;
    }

    public static Deque<RuleBased> getRulesForJetClean_2() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), new FirstOfMethod());
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("<strong>Date:");

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), new SplitWordsMethod());

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.UPDATE_DATE), new ExtractWordMethod());
        ExtractWordMethod method3 = (ExtractWordMethod) rule3.getMethod();
        method3.setPosition(2);

        rulesSet.add(rule1);
        rulesSet.add(rule2);
        rulesSet.add(rule3);

        return rulesSet;
    }

}
