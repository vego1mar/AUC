package net.vego1mar.tests;

import java.util.Deque;
import java.util.LinkedList;
import net.vego1mar.auxiliary.method.ExtractWordMethod;
import net.vego1mar.auxiliary.method.FirstOfMethod;
import net.vego1mar.auxiliary.method.GrabUntilMethod;
import net.vego1mar.auxiliary.method.PrependMethod;
import net.vego1mar.auxiliary.method.RemoveCharactersMethod;
import net.vego1mar.auxiliary.method.RetrieveTagsMethod;
import net.vego1mar.auxiliary.method.TrimMethod;
import net.vego1mar.enumerators.methods.RetrieveTagsType;
import net.vego1mar.enumerators.methods.TrimSide;
import net.vego1mar.enumerators.properties.LinksID;
import net.vego1mar.enumerators.properties.Platforms;
import net.vego1mar.enumerators.traits.MethodType;
import net.vego1mar.rules.Rule;
import net.vego1mar.rules.RuleBased;
import net.vego1mar.auxiliary.target.Target;
import net.vego1mar.enumerators.methods.FirstOfType;
import net.vego1mar.enumerators.traits.In;
import net.vego1mar.enumerators.traits.UseAs;
import net.vego1mar.utils.MethodCreator;

public final class TestCollections {

    private TestCollections() {
        // This should be a utility class.
    }

    public static Deque<RuleBased> getRulesFor7Zip_1() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("h1");

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method2 = (FirstOfMethod) rule2.getMethod();
        method2.setType(FirstOfType.TAG);
        method2.setText("p");

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.SPLIT_WORDS));

        RuleBased rule4 = new Rule(new Target(In.COLLECTION, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target4 = (Target) rule4.getTarget();
        target4.setVersion(Platforms.WINDOWS);
        ExtractWordMethod method4 = (ExtractWordMethod) rule4.getMethod();
        method4.setPosition(3);

        RuleBased rule5 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method5 = (ExtractWordMethod) rule5.getMethod();
        method5.setPosition(4);

        RuleBased rule6 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.REMOVE_CHARACTERS));
        Target target6 = (Target) rule6.getTarget();
        target6.setDate(Platforms.WINDOWS);
        RemoveCharactersMethod method6 = (RemoveCharactersMethod) rule6.getMethod();
        method6.setSigns("()");

        RuleBased rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method8 = (FirstOfMethod) rule8.getMethod();
        method8.setType(FirstOfType.TAG);
        method8.setText("table");

        RuleBased rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method9 = (RetrieveTagsMethod) rule9.getMethod();
        method9.setType(RetrieveTagsType.ALL);
        method9.setTagname("a");

        RuleBased rule10 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FETCH_HREFS));

        RuleBased rule11 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.PREPEND));
        PrependMethod method11 = (PrependMethod) rule11.getMethod();
        method11.setText("https://www.7-zip.org/");

        RuleBased rule12 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target12 = (Target) rule12.getTarget();
        target12.setLinkID(LinksID.WINDOWS_X86_EXE);
        ExtractWordMethod method12 = (ExtractWordMethod) rule12.getMethod();
        method12.setPosition(1);

        RuleBased rule13 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target13 = (Target) rule13.getTarget();
        target13.setLinkID(LinksID.WINDOWS_X64_EXE);
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(2);

        RuleBased rule14 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target14 = (Target) rule14.getTarget();
        target14.setLinkID(LinksID.WINDOWS_ANY_7Z);
        ExtractWordMethod method14 = (ExtractWordMethod) rule14.getMethod();
        method14.setPosition(3);

        RuleBased rule15 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target15 = (Target) rule15.getTarget();
        target15.setLinkID(LinksID.SOURCECODE_ANY_7Z);
        ExtractWordMethod method15 = (ExtractWordMethod) rule15.getMethod();
        method15.setPosition(4);

        RuleBased rule16 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target16 = (Target) rule16.getTarget();
        target16.setLinkID(LinksID.SDK_ANY_7Z);
        ExtractWordMethod method16 = (ExtractWordMethod) rule16.getMethod();
        method16.setPosition(5);

        RuleBased rule17 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target17 = (Target) rule17.getTarget();
        target17.setLinkID(LinksID.WINDOWS_X86_MSI);
        ExtractWordMethod method17 = (ExtractWordMethod) rule17.getMethod();
        method17.setPosition(6);

        RuleBased rule18 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target18 = (Target) rule18.getTarget();
        target18.setLinkID(LinksID.WINDOWS_X64_MSI);
        ExtractWordMethod method18 = (ExtractWordMethod) rule18.getMethod();
        method18.setPosition(7);

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
        rulesSet.add(rule14);
        rulesSet.add(rule15);
        rulesSet.add(rule16);
        rulesSet.add(rule17);
        rulesSet.add(rule18);

        return rulesSet;
    }

    public static Deque<RuleBased> getRulesForAimp_1() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setText("AIMP v");
        method1.setType(FirstOfType.STRING);

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method2 = (GrabUntilMethod) rule2.getMethod();
        method2.setCharStop('<');

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method3 = (TrimMethod) rule3.getMethod();
        method3.setSide(TrimSide.LEFT);
        method3.setNumberOf(5);
        Target target3 = (Target) rule3.getTarget();
        target3.setVersion(Platforms.WINDOWS);

        RuleBased rule4 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method4 = (RetrieveTagsMethod) rule4.getMethod();
        method4.setType(RetrieveTagsType.FIRST);
        method4.setTagname("nobr");

        RuleBased rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method5 = (TrimMethod) rule5.getMethod();
        method5.setSide(TrimSide.LEFT);
        method5.setNumberOf(10);

        RuleBased rule6 = new Rule(new Target(In.CONTENT, UseAs.HASHES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method6 = (TrimMethod) rule6.getMethod();
        method6.setSide(TrimSide.RIGHT);
        method6.setNumberOf(7);
        Target target6 = (Target) rule6.getTarget();
        target6.setHashID(LinksID.WINDOWS_X86_EXE);

        RuleBased rule7 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method7 = (FirstOfMethod) rule7.getMethod();
        method7.setText("</h1><h2>");
        method7.setType(FirstOfType.STRING);

        RuleBased rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method8 = (GrabUntilMethod) rule8.getMethod();
        method8.setCharStop(' ');

        RuleBased rule9 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method9 = (TrimMethod) rule9.getMethod();
        method9.setSide(TrimSide.LEFT);
        method9.setNumberOf(9);
        Target target9 = (Target) rule9.getTarget();
        target9.setDate(Platforms.WINDOWS);

        RuleBased rule10 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method10 = (FirstOfMethod) rule10.getMethod();
        method10.setType(FirstOfType.STRING);
        method10.setText("Скачать с:");

        RuleBased rule11 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method11 = (RetrieveTagsMethod) rule11.getMethod();
        method11.setTagname("a");
        method11.setType(RetrieveTagsType.FIRST);

        RuleBased rule12 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        Target target12 = (Target) rule12.getTarget();
        target12.setLinkID(LinksID.WINDOWS_X86_EXE);

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

        return rulesSet;
    }

    public static Deque<RuleBased> getRulesForAimp_2() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method1 = (RetrieveTagsMethod) rule1.getMethod();
        method1.setType(RetrieveTagsType.FIRST);
        method1.setTagname("h1");

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method2 = (TrimMethod) rule2.getMethod();
        method2.setSide(TrimSide.LEFT);
        method2.setNumberOf(21);

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method3 = (TrimMethod) rule3.getMethod();
        method3.setSide(TrimSide.RIGHT);
        method3.setNumberOf(5);
        Target target3 = (Target) rule3.getTarget();
        target3.setVersion(Platforms.ANDROID);

        RuleBased rule4 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method4 = (FirstOfMethod) rule4.getMethod();
        method4.setType(FirstOfType.STRING);
        method4.setText("h2");

        RuleBased rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method5 = (GrabUntilMethod) rule5.getMethod();
        method5.setCharStop(' ');

        RuleBased rule6 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method6 = (TrimMethod) rule6.getMethod();
        method6.setSide(TrimSide.LEFT);
        method6.setNumberOf(3);
        Target target6 = (Target) rule6.getTarget();
        target6.setDate(Platforms.ANDROID);

        RuleBased rule7 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method7 = (RetrieveTagsMethod) rule7.getMethod();
        method7.setType(RetrieveTagsType.FIRST);
        method7.setTagname("nobr");

        RuleBased rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method8 = (TrimMethod) rule8.getMethod();
        method8.setSide(TrimSide.LEFT);
        method8.setNumberOf(10);

        RuleBased rule9 = new Rule(new Target(In.CONTENT, UseAs.HASHES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method9 = (TrimMethod) rule9.getMethod();
        method9.setSide(TrimSide.RIGHT);
        method9.setNumberOf(7);
        Target target9 = (Target) rule9.getTarget();
        target9.setHashID(LinksID.ANDROID_X86ARM_APK);

        RuleBased rule10 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method10 = (FirstOfMethod) rule10.getMethod();
        method10.setType(FirstOfType.STRING);
        method10.setText("Скачать с:");

        RuleBased rule11 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method11 = (GrabUntilMethod) rule11.getMethod();
        method11.setCharStop('|');

        RuleBased rule12 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method12 = (RetrieveTagsMethod) rule12.getMethod();
        method12.setType(RetrieveTagsType.ALL);
        method12.setTagname("a");

        RuleBased rule13 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(1);

        RuleBased rule14 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method14 = (TrimMethod) rule14.getMethod();
        method14.setSide(TrimSide.LEFT);
        method14.setNumberOf(9);

        RuleBased rule15 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method15 = (TrimMethod) rule15.getMethod();
        method15.setSide(TrimSide.RIGHT);
        method15.setNumberOf(17);
        Target target15 = (Target) rule15.getTarget();
        target15.setLinkID(LinksID.ANDROID_X86ARM_GOOGLEPLAY);

        RuleBased rule16 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method16 = (ExtractWordMethod) rule16.getMethod();
        method16.setPosition(2);

        RuleBased rule17 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method17 = (TrimMethod) rule17.getMethod();
        method17.setSide(TrimSide.LEFT);
        method17.setNumberOf(9);

        RuleBased rule18 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method18 = (TrimMethod) rule18.getMethod();
        method18.setSide(TrimSide.RIGHT);
        method18.setNumberOf(13);
        Target target18 = (Target) rule18.getTarget();
        target18.setLinkID(LinksID.ANDROID_X86ARM_APK);

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
        rulesSet.add(rule15);
        rulesSet.add(rule16);
        rulesSet.add(rule17);
        rulesSet.add(rule18);

        return rulesSet;
    }

    public static Deque<RuleBased> getRulesForSourceTree_1() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("link--secondary");

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method2 = (RetrieveTagsMethod) rule2.getMethod();
        method2.setType(RetrieveTagsType.FIRST);
        method2.setTagname("a");

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        Target target3 = (Target) rule3.getTarget();
        target3.setLinkID(LinksID.WINDOWS_X86_EXE);

        RuleBased rule4 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method4 = (FirstOfMethod) rule4.getMethod();
        method4.setType(FirstOfType.STRING);
        method4.setText("-");

        RuleBased rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method5 = (TrimMethod) rule5.getMethod();
        method5.setSide(TrimSide.LEFT);
        method5.setNumberOf(1);

        RuleBased rule6 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method6 = (TrimMethod) rule6.getMethod();
        method6.setSide(TrimSide.RIGHT);
        method6.setNumberOf(4);
        Target target6 = (Target) rule6.getTarget();
        target6.setVersion(Platforms.WINDOWS);

        RuleBased rule7 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method7 = (FirstOfMethod) rule7.getMethod();
        method7.setType(FirstOfType.STRING);
        method7.setText("LastRendered:");

        RuleBased rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method8 = (FirstOfMethod) rule8.getMethod();
        method8.setType(FirstOfType.STRING);
        method8.setText(" ");

        RuleBased rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method9 = (GrabUntilMethod) rule9.getMethod();
        method9.setCharStop(':');

        RuleBased rule10 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method10 = (TrimMethod) rule10.getMethod();
        method10.setSide(TrimSide.RIGHT);
        method10.setNumberOf(2);

        RuleBased rule11 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method11 = (TrimMethod) rule11.getMethod();
        method11.setSide(TrimSide.LEFT);
        method11.setNumberOf(1);
        Target target11 = (Target) rule11.getTarget();
        target11.setDate(Platforms.WINDOWS);

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

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method1 = (RetrieveTagsMethod) rule1.getMethod();
        method1.setType(RetrieveTagsType.FIRST);
        method1.setTagname("title");

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method2 = (ExtractWordMethod) rule2.getMethod();
        method2.setPosition(3);

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method3 = (TrimMethod) rule3.getMethod();
        method3.setSide(TrimSide.RIGHT);
        method3.setNumberOf(8);

        RuleBased rule4 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method4 = (FirstOfMethod) rule4.getMethod();
        method4.setType(FirstOfType.STRING);
        method4.setText("Debug:");

        RuleBased rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method5 = (TrimMethod) rule5.getMethod();
        method5.setSide(TrimSide.LEFT);
        method5.setNumberOf(7);

        RuleBased rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method6 = (GrabUntilMethod) rule6.getMethod();
        method6.setCharStop('>');

        RuleBased rule7 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.TRIM));
        Target target7 = (Target) rule7.getTarget();
        target7.setHashID(LinksID.WINDOWS_X86_EXE);
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

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("<strong>Date:");

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.SPLIT_WORDS));

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method3 = (ExtractWordMethod) rule3.getMethod();
        method3.setPosition(2);

        rulesSet.add(rule1);
        rulesSet.add(rule2);
        rulesSet.add(rule3);

        return rulesSet;
    }

    public static Deque<RuleBased> getRulesForBorderlessGaming_1() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.TAG);
        method1.setText("relative-time");

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method2 = (FirstOfMethod) rule2.getMethod();
        method2.setType(FirstOfType.STRING);
        method2.setText(">");

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method3 = (TrimMethod) rule3.getMethod();
        method3.setSide(TrimSide.LEFT);
        method3.setNumberOf(1);

        RuleBased rule4 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method4 = (GrabUntilMethod) rule4.getMethod();
        method4.setCharStop('<');

        RuleBased rule5 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method5 = (FirstOfMethod) rule5.getMethod();
        method5.setType(FirstOfType.STRING);
        method5.setText("/Codeusa/Borderless-Gaming/releases/download");

        RuleBased rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method6 = (GrabUntilMethod) rule6.getMethod();
        method6.setCharStop('"');

        RuleBased rule7 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.PREPEND));
        Target target7 = (Target) rule7.getTarget();
        target7.setHashID(LinksID.WINDOWS_X86_EXE);
        PrependMethod method7 = (PrependMethod) rule7.getMethod();
        method7.setText("https://github.com");

        RuleBased rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method8 = (FirstOfMethod) rule8.getMethod();
        method8.setType(FirstOfType.STRING);
        method8.setText("download/");

        RuleBased rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method9 = (TrimMethod) rule9.getMethod();
        method9.setSide(TrimSide.LEFT);
        method9.setNumberOf(9);

        RuleBased rule10 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method10 = (GrabUntilMethod) rule10.getMethod();
        method10.setCharStop('/');

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

        return rulesSet;
    }

    public static Deque<RuleBased> getRulesForTeraCopy_1() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.TAG);
        method1.setText("tbody");

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method2 = (FirstOfMethod) rule2.getMethod();
        method2.setType(FirstOfType.TAG);
        method2.setText("tr");

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method3 = (RetrieveTagsMethod) rule3.getMethod();
        method3.setType(RetrieveTagsType.ALL);
        method3.setTagname("td");

        RuleBased rule4 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method4 = (ExtractWordMethod) rule4.getMethod();
        method4.setPosition(1);

        RuleBased rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method5 = (FirstOfMethod) rule5.getMethod();
        method5.setType(FirstOfType.STRING);
        method5.setText(" ");

        RuleBased rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method6 = (GrabUntilMethod) rule6.getMethod();
        method6.setCharStop('<');

        RuleBased rule7 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method7 = (TrimMethod) rule7.getMethod();
        method7.setSide(TrimSide.LEFT);
        method7.setNumberOf(1);

        RuleBased rule8 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method8 = (ExtractWordMethod) rule8.getMethod();
        method8.setPosition(3);

        RuleBased rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FETCH_HREFS));

        RuleBased rule10 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.PREPEND));
        Target target10 = (Target) rule10.getTarget();
        target10.setHashID(LinksID.WINDOWS_X86_EXE);
        PrependMethod method10 = (PrependMethod) rule10.getMethod();
        method10.setText("http://www.codesector.com");

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

        return rulesSet;
    }

    public static Deque<RuleBased> getRulesForPotPlayer_1() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("<strong>");

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method2 = (GrabUntilMethod) rule2.getMethod();
        method2.setCharStop(')');

        RuleBased rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.SPLIT_WORDS));

        RuleBased rule4 = new Rule(new Target(In.COLLECTION, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method4 = (ExtractWordMethod) rule4.getMethod();
        method4.setPosition(3);

        RuleBased rule5 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method5 = (ExtractWordMethod) rule5.getMethod();
        method5.setPosition(9);

        RuleBased rule6 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        Target target6 = (Target) rule6.getTarget();
        target6.setHashID(LinksID.WINDOWS_X86_EXE);

        RuleBased rule7 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.REMOVE_CHARACTERS));
        RemoveCharactersMethod method7 = (RemoveCharactersMethod) rule7.getMethod();
        method7.setSigns("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.:-");

        RuleBased rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method8 = (TrimMethod) rule8.getMethod();
        method8.setSide(TrimSide.LEFT);
        method8.setNumberOf(5);

        RuleBased rule9 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method9 = (TrimMethod) rule9.getMethod();
        method9.setSide(TrimSide.RIGHT);
        method9.setNumberOf(1);

        RuleBased rule10 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method10 = (FirstOfMethod) rule10.getMethod();
        method10.setType(FirstOfType.STRING);
        method10.setText("for 64-bit Windows");

        RuleBased rule11 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method11 = (RetrieveTagsMethod) rule11.getMethod();
        method11.setType(RetrieveTagsType.FIRST);
        method11.setTagname("a");

        RuleBased rule12 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        Target target12 = (Target) rule12.getTarget();
        target12.setHashID(LinksID.WINDOWS_X64_EXE);

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

        return rulesSet;
    }

    public static Deque<RuleBased> getRulesForXmlRulesSetWriter() {
        Deque<RuleBased> rulesSet = new LinkedList<>();

        RuleBased rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EMPTY));

        RuleBased rule2 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method2 = (FirstOfMethod) rule2.getMethod();
        method2.setType(FirstOfType.STRING);
        method2.setText(" \\xBA ");
        Target target2 = (Target) rule2.getTarget();
        target2.setVersion(Platforms.WINDOWS);

        RuleBased rule3 = new Rule(new Target(In.COLLECTION, UseAs.DATES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method3 = (ExtractWordMethod) rule3.getMethod();
        method3.setPosition(1);
        Target target3 = (Target) rule3.getTarget();
        target3.setDate(Platforms.ANDROID);

        RuleBased rule4 = new Rule(new Target(In.CODE, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        Target target4 = (Target) rule4.getTarget();
        target4.setLinkID(LinksID.SOURCECODE_ANY_7Z);

        RuleBased rule5 = new Rule(new Target(In.CONTENT, UseAs.HASHES), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method5 = (GrabUntilMethod) rule5.getMethod();
        method5.setCharStop(' ');
        Target target5 = (Target) rule5.getTarget();
        target5.setHashID(LinksID.ANDROID_X86ARM_APK);

        RuleBased rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.PREPEND));
        PrependMethod method6 = (PrependMethod) rule6.getMethod();
        method6.setText(" aąbcćdeęfghijklłmnńoópqrsśtuwxyzżź0123456789 `~!@#$%^&*()_+-=[]\\;':\",./<>? ");

        RuleBased rule7 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.REMOVE_CHARACTERS));
        RemoveCharactersMethod method7 = (RemoveCharactersMethod) rule7.getMethod();
        method7.setSigns(" Mężny bądź, chroń pułk swój i sześć flag.");

        RuleBased rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method8 = (RetrieveTagsMethod) rule8.getMethod();
        method8.setType(RetrieveTagsType.ALL);
        method8.setTagname(" Ж ");

        RuleBased rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.SPLIT_WORDS));

        RuleBased rule10 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));

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

        return rulesSet;
    }

}
