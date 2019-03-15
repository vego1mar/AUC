package net.vego1mar.tests;

import java.util.Deque;
import java.util.LinkedList;
import net.vego1mar.method.ExtractWordMethod;
import net.vego1mar.method.FirstOfMethod;
import net.vego1mar.method.GrabUntilMethod;
import net.vego1mar.method.PrependMethod;
import net.vego1mar.method.RemoveCharactersMethod;
import net.vego1mar.method.RetrieveTagsMethod;
import net.vego1mar.method.TrimMethod;
import net.vego1mar.method.enumerators.RetrieveTagsType;
import net.vego1mar.method.enumerators.TrimSide;
import net.vego1mar.properties.enumerators.LinksID;
import net.vego1mar.properties.enumerators.Platforms;
import net.vego1mar.method.enumerators.MethodType;
import net.vego1mar.rules.Rule;
import net.vego1mar.target.Target;
import net.vego1mar.method.enumerators.FirstOfType;
import net.vego1mar.target.enumerators.In;
import net.vego1mar.target.enumerators.UseAs;
import net.vego1mar.utils.MethodCreator;

@Deprecated public final class TestCollections {

    private TestCollections() {
        // This should be a utility class.
    }

    public static Deque<Rule> getRulesFor7Zip_1() {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("h1");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method2 = (FirstOfMethod) rule2.getMethod();
        method2.setType(FirstOfType.TAG);
        method2.setText("p");

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.SPLIT_WORDS));

        Rule rule4 = new Rule(new Target(In.COLLECTION, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target4 = (Target) rule4.getTarget();
        target4.version(Platforms.WINDOWS);
        ExtractWordMethod method4 = (ExtractWordMethod) rule4.getMethod();
        method4.setPosition(3);

        Rule rule5 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method5 = (ExtractWordMethod) rule5.getMethod();
        method5.setPosition(4);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.REMOVE_CHARACTERS));
        Target target6 = (Target) rule6.getTarget();
        target6.date(Platforms.WINDOWS);
        RemoveCharactersMethod method6 = (RemoveCharactersMethod) rule6.getMethod();
        method6.setSigns("()");

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method8 = (FirstOfMethod) rule8.getMethod();
        method8.setType(FirstOfType.TAG);
        method8.setText("table");

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method9 = (RetrieveTagsMethod) rule9.getMethod();
        method9.setType(RetrieveTagsType.ALL);
        method9.setTagname("a");

        Rule rule10 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FETCH_HREFS));

        Rule rule11 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.PREPEND));
        PrependMethod method11 = (PrependMethod) rule11.getMethod();
        method11.setText("https://www.7-zip.org/");

        Rule rule12 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target12 = (Target) rule12.getTarget();
        target12.linkID(LinksID.WINDOWS_X86_EXE);
        ExtractWordMethod method12 = (ExtractWordMethod) rule12.getMethod();
        method12.setPosition(1);

        Rule rule13 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target13 = (Target) rule13.getTarget();
        target13.linkID(LinksID.WINDOWS_X64_EXE);
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(2);

        Rule rule14 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target14 = (Target) rule14.getTarget();
        target14.linkID(LinksID.WINDOWS_ANY_7Z);
        ExtractWordMethod method14 = (ExtractWordMethod) rule14.getMethod();
        method14.setPosition(3);

        Rule rule15 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target15 = (Target) rule15.getTarget();
        target15.linkID(LinksID.SOURCECODE_ANY_7Z);
        ExtractWordMethod method15 = (ExtractWordMethod) rule15.getMethod();
        method15.setPosition(4);

        Rule rule16 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target16 = (Target) rule16.getTarget();
        target16.linkID(LinksID.SDK_ANY_7Z);
        ExtractWordMethod method16 = (ExtractWordMethod) rule16.getMethod();
        method16.setPosition(5);

        Rule rule17 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target17 = (Target) rule17.getTarget();
        target17.linkID(LinksID.WINDOWS_X86_MSI);
        ExtractWordMethod method17 = (ExtractWordMethod) rule17.getMethod();
        method17.setPosition(6);

        Rule rule18 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        Target target18 = (Target) rule18.getTarget();
        target18.linkID(LinksID.WINDOWS_X64_MSI);
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

    public static Deque<Rule> getRulesForAimp_1() {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setText("AIMP v");
        method1.setType(FirstOfType.STRING);

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method2 = (GrabUntilMethod) rule2.getMethod();
        method2.setCharStop('<');

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method3 = (TrimMethod) rule3.getMethod();
        method3.setSide(TrimSide.LEFT);
        method3.setNumberOf(5);
        Target target3 = (Target) rule3.getTarget();
        target3.version(Platforms.WINDOWS);

        Rule rule4 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method4 = (RetrieveTagsMethod) rule4.getMethod();
        method4.setType(RetrieveTagsType.FIRST);
        method4.setTagname("nobr");

        Rule rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method5 = (TrimMethod) rule5.getMethod();
        method5.setSide(TrimSide.LEFT);
        method5.setNumberOf(10);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.HASHES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method6 = (TrimMethod) rule6.getMethod();
        method6.setSide(TrimSide.RIGHT);
        method6.setNumberOf(7);
        Target target6 = (Target) rule6.getTarget();
        target6.hashID(LinksID.WINDOWS_X86_EXE);

        Rule rule7 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method7 = (FirstOfMethod) rule7.getMethod();
        method7.setText("</h1><h2>");
        method7.setType(FirstOfType.STRING);

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method8 = (GrabUntilMethod) rule8.getMethod();
        method8.setCharStop(' ');

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method9 = (TrimMethod) rule9.getMethod();
        method9.setSide(TrimSide.LEFT);
        method9.setNumberOf(9);
        Target target9 = (Target) rule9.getTarget();
        target9.date(Platforms.WINDOWS);

        Rule rule10 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method10 = (FirstOfMethod) rule10.getMethod();
        method10.setType(FirstOfType.STRING);
        method10.setText("Скачать с:");

        Rule rule11 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method11 = (RetrieveTagsMethod) rule11.getMethod();
        method11.setTagname("a");
        method11.setType(RetrieveTagsType.FIRST);

        Rule rule12 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        Target target12 = (Target) rule12.getTarget();
        target12.linkID(LinksID.WINDOWS_X86_EXE);

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

    public static Deque<Rule> getRulesForAimp_2() {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method1 = (RetrieveTagsMethod) rule1.getMethod();
        method1.setType(RetrieveTagsType.FIRST);
        method1.setTagname("h1");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method2 = (TrimMethod) rule2.getMethod();
        method2.setSide(TrimSide.LEFT);
        method2.setNumberOf(21);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method3 = (TrimMethod) rule3.getMethod();
        method3.setSide(TrimSide.RIGHT);
        method3.setNumberOf(5);
        Target target3 = (Target) rule3.getTarget();
        target3.version(Platforms.ANDROID);

        Rule rule4 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method4 = (FirstOfMethod) rule4.getMethod();
        method4.setType(FirstOfType.STRING);
        method4.setText("h2");

        Rule rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method5 = (GrabUntilMethod) rule5.getMethod();
        method5.setCharStop(' ');

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method6 = (TrimMethod) rule6.getMethod();
        method6.setSide(TrimSide.LEFT);
        method6.setNumberOf(3);
        Target target6 = (Target) rule6.getTarget();
        target6.date(Platforms.ANDROID);

        Rule rule7 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method7 = (RetrieveTagsMethod) rule7.getMethod();
        method7.setType(RetrieveTagsType.FIRST);
        method7.setTagname("nobr");

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method8 = (TrimMethod) rule8.getMethod();
        method8.setSide(TrimSide.LEFT);
        method8.setNumberOf(10);

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.HASHES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method9 = (TrimMethod) rule9.getMethod();
        method9.setSide(TrimSide.RIGHT);
        method9.setNumberOf(7);
        Target target9 = (Target) rule9.getTarget();
        target9.hashID(LinksID.ANDROID_X86ARM_APK);

        Rule rule10 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method10 = (FirstOfMethod) rule10.getMethod();
        method10.setType(FirstOfType.STRING);
        method10.setText("Скачать с:");

        Rule rule11 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method11 = (GrabUntilMethod) rule11.getMethod();
        method11.setCharStop('|');

        Rule rule12 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method12 = (RetrieveTagsMethod) rule12.getMethod();
        method12.setType(RetrieveTagsType.ALL);
        method12.setTagname("a");

        Rule rule13 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(1);

        Rule rule14 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method14 = (TrimMethod) rule14.getMethod();
        method14.setSide(TrimSide.LEFT);
        method14.setNumberOf(9);

        Rule rule15 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method15 = (TrimMethod) rule15.getMethod();
        method15.setSide(TrimSide.RIGHT);
        method15.setNumberOf(17);
        Target target15 = (Target) rule15.getTarget();
        target15.linkID(LinksID.ANDROID_X86ARM_GOOGLEPLAY);

        Rule rule16 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method16 = (ExtractWordMethod) rule16.getMethod();
        method16.setPosition(2);

        Rule rule17 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method17 = (TrimMethod) rule17.getMethod();
        method17.setSide(TrimSide.LEFT);
        method17.setNumberOf(9);

        Rule rule18 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method18 = (TrimMethod) rule18.getMethod();
        method18.setSide(TrimSide.RIGHT);
        method18.setNumberOf(13);
        Target target18 = (Target) rule18.getTarget();
        target18.linkID(LinksID.ANDROID_X86ARM_APK);

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

    public static Deque<Rule> getRulesForJetClean_1() {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method1 = (RetrieveTagsMethod) rule1.getMethod();
        method1.setType(RetrieveTagsType.FIRST);
        method1.setTagname("title");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method2 = (ExtractWordMethod) rule2.getMethod();
        method2.setPosition(3);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method3 = (TrimMethod) rule3.getMethod();
        method3.setSide(TrimSide.RIGHT);
        method3.setNumberOf(8);
        Target target3 = (Target) rule3.getTarget();
        target3.version(Platforms.WINDOWS);

        Rule rule4 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method4 = (FirstOfMethod) rule4.getMethod();
        method4.setType(FirstOfType.STRING);
        method4.setText("Debug:");

        Rule rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method5 = (TrimMethod) rule5.getMethod();
        method5.setSide(TrimSide.LEFT);
        method5.setNumberOf(7);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method6 = (GrabUntilMethod) rule6.getMethod();
        method6.setCharStop('>');

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method7 = (TrimMethod) rule7.getMethod();
        method7.setSide(TrimSide.RIGHT);
        method7.setNumberOf(3);
        Target target7 = (Target) rule7.getTarget();
        target7.linkID(LinksID.WINDOWS_X86_EXE);

        rulesSet.add(rule1);
        rulesSet.add(rule2);
        rulesSet.add(rule3);
        rulesSet.add(rule4);
        rulesSet.add(rule5);
        rulesSet.add(rule6);
        rulesSet.add(rule7);

        return rulesSet;
    }

    public static Deque<Rule> getRulesForJetClean_2() {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("<strong>Date:");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.SPLIT_WORDS));

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method3 = (ExtractWordMethod) rule3.getMethod();
        method3.setPosition(2);
        Target target3 = (Target) rule3.getTarget();
        target3.date(Platforms.WINDOWS);

        rulesSet.add(rule1);
        rulesSet.add(rule2);
        rulesSet.add(rule3);

        return rulesSet;
    }

    public static Deque<Rule> getRulesForTeraCopy_1() {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.TAG);
        method1.setText("tbody");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method2 = (FirstOfMethod) rule2.getMethod();
        method2.setType(FirstOfType.TAG);
        method2.setText("tr");

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method3 = (RetrieveTagsMethod) rule3.getMethod();
        method3.setType(RetrieveTagsType.ALL);
        method3.setTagname("td");

        Rule rule4 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method4 = (ExtractWordMethod) rule4.getMethod();
        method4.setPosition(1);

        Rule rule5 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method5 = (FirstOfMethod) rule5.getMethod();
        method5.setType(FirstOfType.STRING);
        method5.setText(" ");

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method6 = (GrabUntilMethod) rule6.getMethod();
        method6.setCharStop('<');

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method7 = (TrimMethod) rule7.getMethod();
        method7.setSide(TrimSide.LEFT);
        method7.setNumberOf(1);
        Target target7 = (Target) rule7.getTarget();
        target7.version(Platforms.WINDOWS);

        Rule rule8 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method8 = (ExtractWordMethod) rule8.getMethod();
        method8.setPosition(3);

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FETCH_HREFS));

        Rule rule10 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.PREPEND));
        PrependMethod method10 = (PrependMethod) rule10.getMethod();
        method10.setText("http://www.codesector.com");
        Target target10 = (Target) rule10.getTarget();
        target10.linkID(LinksID.WINDOWS_X86_EXE);

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

    public static Deque<Rule> getRulesForXmlRulesSetWriter() {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EMPTY));

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method2 = (FirstOfMethod) rule2.getMethod();
        method2.setType(FirstOfType.STRING);
        method2.setText(" \\xBA ");
        Target target2 = (Target) rule2.getTarget();
        target2.version(Platforms.WINDOWS);

        Rule rule3 = new Rule(new Target(In.COLLECTION, UseAs.DATES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method3 = (ExtractWordMethod) rule3.getMethod();
        method3.setPosition(1);
        Target target3 = (Target) rule3.getTarget();
        target3.date(Platforms.ANDROID);

        Rule rule4 = new Rule(new Target(In.CODE, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        Target target4 = (Target) rule4.getTarget();
        target4.linkID(LinksID.SOURCECODE_ANY_7Z);

        Rule rule5 = new Rule(new Target(In.CONTENT, UseAs.HASHES), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method5 = (GrabUntilMethod) rule5.getMethod();
        method5.setCharStop(' ');
        Target target5 = (Target) rule5.getTarget();
        target5.hashID(LinksID.ANDROID_X86ARM_APK);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.PREPEND));
        PrependMethod method6 = (PrependMethod) rule6.getMethod();
        method6.setText(" aąbcćdeęfghijklłmnńoópqrsśtuwxyzżź0123456789 `~!@#$%^&*()_+-=[]\\;':\",./<>? ");

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.REMOVE_CHARACTERS));
        RemoveCharactersMethod method7 = (RemoveCharactersMethod) rule7.getMethod();
        method7.setSigns(" Mężny bądź, chroń pułk swój i sześć flag.");

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method8 = (RetrieveTagsMethod) rule8.getMethod();
        method8.setType(RetrieveTagsType.ALL);
        method8.setTagname(" Ж ");

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.SPLIT_WORDS));

        Rule rule10 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));

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

    public static Deque<Rule> getRulesForOracleVirtualBox_1() {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("h3");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method2 = (TrimMethod) rule2.getMethod();
        method2.setSide(TrimSide.LEFT);
        method2.setNumberOf(2);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method3 = (FirstOfMethod) rule3.getMethod();
        method3.setType(FirstOfType.STRING);
        method3.setText("h3");

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method4 = (RetrieveTagsMethod) rule4.getMethod();
        method4.setType(RetrieveTagsType.ALL);
        method4.setTagname("a");

        Rule rule5 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method5 = (ExtractWordMethod) rule5.getMethod();
        method5.setPosition(2);

        Rule rule6 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method6 = (FirstOfMethod) rule6.getMethod();
        method6.setType(FirstOfType.STRING);
        method6.setText("//");

        Rule rule7 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method7 = (FirstOfMethod) rule7.getMethod();
        method7.setType(FirstOfType.STRING);
        method7.setText("-");

        Rule rule8 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method8 = (TrimMethod) rule8.getMethod();
        method8.setSide(TrimSide.LEFT);
        method8.setNumberOf(1);

        Rule rule9 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method9 = (GrabUntilMethod) rule9.getMethod();
        method9.setCharStop('W');

        Rule rule10 = new Rule(new Target(In.CONTENT, UseAs.VERSIONS), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method10 = (TrimMethod) rule10.getMethod();
        method10.setSide(TrimSide.RIGHT);
        method10.setNumberOf(1);
        Target target10 = (Target) rule10.getTarget();
        target10.version(Platforms.ALL_SUPPORTED);

        Rule rule11 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method11 = (ExtractWordMethod) rule11.getMethod();
        method11.setPosition(2);

        Rule rule12 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        Target target12 = (Target) rule12.getTarget();
        target12.linkID(LinksID.WINDOWS_X86_EXE);

        Rule rule13 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(3);

        Rule rule14 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        Target target14 = (Target) rule14.getTarget();
        target14.linkID(LinksID.MAC_OS_X_DMG);

        Rule rule15 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method15 = (ExtractWordMethod) rule15.getMethod();
        method15.setPosition(5);

        Rule rule16 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        Target target16 = (Target) rule16.getTarget();
        target16.linkID(LinksID.SOLARIS_TAR_GZ);

        Rule rule17 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method17 = (ExtractWordMethod) rule17.getMethod();
        method17.setPosition(10);

        Rule rule18 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        Target target18 = (Target) rule18.getTarget();
        target18.linkID(LinksID.AUX_EXTPACK);

        Rule rule19 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method19 = (ExtractWordMethod) rule19.getMethod();
        method19.setPosition(14);

        Rule rule20 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        Target target20 = (Target) rule20.getTarget();
        target20.linkID(LinksID.SDK_ZIP);

        Rule rule21 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method21 = (ExtractWordMethod) rule21.getMethod();
        method21.setPosition(21);

        Rule rule22 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.FETCH_HREFS));
        Target target22 = (Target) rule22.getTarget();
        target22.linkID(LinksID.SOURCECODE_TAR_BZ2);

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
        rulesSet.add(rule19);
        rulesSet.add(rule20);
        rulesSet.add(rule21);
        rulesSet.add(rule22);

        return rulesSet;
    }

    public static Deque<Rule> getRulesForOracleVirtualBox_2() {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("<strong>Date:");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method2 = (TrimMethod) rule2.getMethod();
        method2.setSide(TrimSide.LEFT);
        method2.setNumberOf(13);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.GRAB_UNTIL));
        GrabUntilMethod method3 = (GrabUntilMethod) rule3.getMethod();
        method3.setCharStop('\n');

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.DATES), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method4 = (TrimMethod) rule4.getMethod();
        method4.setSide(TrimSide.LEFT);
        method4.setNumberOf(10);
        Target target4 = (Target) rule4.getTarget();
        target4.date(Platforms.ALL_SUPPORTED);

        rulesSet.add(rule1);
        rulesSet.add(rule2);
        rulesSet.add(rule3);
        rulesSet.add(rule4);

        return rulesSet;
    }

    public static Deque<Rule> getRulesForOracleVirtualBox_3() {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("h3");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.TRIM));
        TrimMethod method2 = (TrimMethod) rule2.getMethod();
        method2.setSide(TrimSide.LEFT);
        method2.setNumberOf(2);

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method3 = (FirstOfMethod) rule3.getMethod();
        method3.setType(FirstOfType.STRING);
        method3.setText("h3");

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method4 = (RetrieveTagsMethod) rule4.getMethod();
        method4.setType(RetrieveTagsType.ALL);
        method4.setTagname("a");

        Rule rule5 = new Rule(new Target(In.COLLECTION, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FETCH_HREFS));

        Rule rule6 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method6 = (ExtractWordMethod) rule6.getMethod();
        method6.setPosition(2);
        Target target6 = (Target) rule6.getTarget();
        target6.linkID(LinksID.UBUNTU_BIONIC_DEB);

        Rule rule7 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method7 = (ExtractWordMethod) rule7.getMethod();
        method7.setPosition(3);
        Target target7 = (Target) rule7.getTarget();
        target7.linkID(LinksID.UBUNTU_XENIAL_DEB);

        Rule rule8 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method8 = (ExtractWordMethod) rule8.getMethod();
        method8.setPosition(4);
        Target target8 = (Target) rule8.getTarget();
        target8.linkID(LinksID.UBUNTU_TRUSTY_DEB);

        Rule rule9 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method9 = (ExtractWordMethod) rule9.getMethod();
        method9.setPosition(5);
        Target target9 = (Target) rule9.getTarget();
        target9.linkID(LinksID.DEBIAN_STRETCH_DEB);

        Rule rule10 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method10 = (ExtractWordMethod) rule10.getMethod();
        method10.setPosition(6);
        Target target10 = (Target) rule10.getTarget();
        target10.linkID(LinksID.DEBIAN_JESSIE_DEB);

        Rule rule11 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method11 = (ExtractWordMethod) rule11.getMethod();
        method11.setPosition(7);
        Target target11 = (Target) rule11.getTarget();
        target11.linkID(LinksID.OPENSUSE_RPM);

        Rule rule12 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method12 = (ExtractWordMethod) rule12.getMethod();
        method12.setPosition(8);
        Target target12 = (Target) rule12.getTarget();
        target12.linkID(LinksID.LEAP_RPM);

        Rule rule13 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(9);
        Target target13 = (Target) rule13.getTarget();
        target13.linkID(LinksID.FEDORA_RPM);

        Rule rule14 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method14 = (ExtractWordMethod) rule14.getMethod();
        method14.setPosition(10);
        Target target14 = (Target) rule14.getTarget();
        target14.linkID(LinksID.FEDORA_OLD_RPM);

        Rule rule15 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method15 = (ExtractWordMethod) rule15.getMethod();
        method15.setPosition(11);
        Target target15 = (Target) rule15.getTarget();
        target15.linkID(LinksID.ORACLE_LINUX_RPM);

        Rule rule16 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method16 = (ExtractWordMethod) rule16.getMethod();
        method16.setPosition(11);
        Target target16 = (Target) rule16.getTarget();
        target16.linkID(LinksID.RED_HAT_ENTERPRISE_RPM);

        Rule rule17 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method17 = (ExtractWordMethod) rule17.getMethod();
        method17.setPosition(11);
        Target target17 = (Target) rule17.getTarget();
        target17.linkID(LinksID.CENTOS_RPM);

        Rule rule18 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method18 = (ExtractWordMethod) rule18.getMethod();
        method18.setPosition(12);
        Target target18 = (Target) rule18.getTarget();
        target18.linkID(LinksID.ORACLE_LINUX_OLD_RPM);

        Rule rule19 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method19 = (ExtractWordMethod) rule19.getMethod();
        method19.setPosition(12);
        Target target19 = (Target) rule19.getTarget();
        target19.linkID(LinksID.RED_HAT_ENTERPRISE_OLD_RPM);

        Rule rule20 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method20 = (ExtractWordMethod) rule20.getMethod();
        method20.setPosition(12);
        Target target20 = (Target) rule20.getTarget();
        target20.linkID(LinksID.CENTOS_OLD_RPM);

        Rule rule21 = new Rule(new Target(In.COLLECTION, UseAs.LINKS), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method21 = (ExtractWordMethod) rule21.getMethod();
        method21.setPosition(13);
        Target target21 = (Target) rule21.getTarget();
        target21.linkID(LinksID.LINUX_RUN);

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
        rulesSet.add(rule19);
        rulesSet.add(rule20);
        rulesSet.add(rule21);

        return rulesSet;
    }

    public static Deque<Rule> getRulesForOracleVirtualBox_A() {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("SHA256");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.RETRIEVE_TAGS));
        RetrieveTagsMethod method2 = (RetrieveTagsMethod) rule2.getMethod();
        method2.setType(RetrieveTagsType.FIRST);
        method2.setTagname("a");

        Rule rule3 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FETCH_HREFS));

        Rule rule4 = new Rule(new Target(In.CONTENT, UseAs.LINKS), MethodCreator.getMethod(MethodType.PREPEND));
        PrependMethod method4 = (PrependMethod) rule4.getMethod();
        method4.setText("http://www.virtualbox.org");
        Target target4 = (Target) rule4.getTarget();
        target4.linkID(LinksID.GENERIC);

        rulesSet.add(rule1);
        rulesSet.add(rule2);
        rulesSet.add(rule3);
        rulesSet.add(rule4);

        return rulesSet;
    }

    public static Deque<Rule> getRulesForOracleVirtualBox_B() {
        Deque<Rule> rulesSet = new LinkedList<>();

        Rule rule1 = new Rule(new Target(In.CODE, UseAs.IGNORE), MethodCreator.getMethod(MethodType.FIRST_OF));
        FirstOfMethod method1 = (FirstOfMethod) rule1.getMethod();
        method1.setType(FirstOfType.STRING);
        method1.setText("*");

        Rule rule2 = new Rule(new Target(In.CONTENT, UseAs.IGNORE), MethodCreator.getMethod(MethodType.SPLIT_WORDS));

        Rule rule3 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method3 = (ExtractWordMethod) rule3.getMethod();
        method3.setPosition(2);
        Target target3 = (Target) rule3.getTarget();
        target3.hashID(LinksID.AUX_EXTPACK);

        Rule rule4 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method4 = (ExtractWordMethod) rule4.getMethod();
        method4.setPosition(10);
        Target target4 = (Target) rule4.getTarget();
        target4.hashID(LinksID.ORACLE_LINUX_OLD_RPM);

        Rule rule5 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method5 = (ExtractWordMethod) rule5.getMethod();
        method5.setPosition(10);
        Target target5 = (Target) rule5.getTarget();
        target5.hashID(LinksID.RED_HAT_ENTERPRISE_OLD_RPM);

        Rule rule6 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method6 = (ExtractWordMethod) rule6.getMethod();
        method6.setPosition(10);
        Target target6 = (Target) rule6.getTarget();
        target6.hashID(LinksID.CENTOS_OLD_RPM);

        Rule rule7 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method7 = (ExtractWordMethod) rule7.getMethod();
        method7.setPosition(12);
        Target target7 = (Target) rule7.getTarget();
        target7.hashID(LinksID.ORACLE_LINUX_RPM);

        Rule rule8 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method8 = (ExtractWordMethod) rule8.getMethod();
        method8.setPosition(12);
        Target target8 = (Target) rule8.getTarget();
        target8.hashID(LinksID.RED_HAT_ENTERPRISE_RPM);

        Rule rule9 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method9 = (ExtractWordMethod) rule9.getMethod();
        method9.setPosition(12);
        Target target9 = (Target) rule9.getTarget();
        target9.hashID(LinksID.CENTOS_RPM);

        Rule rule10 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method10 = (ExtractWordMethod) rule10.getMethod();
        method10.setPosition(14);
        Target target10 = (Target) rule10.getTarget();
        target10.hashID(LinksID.FEDORA_OLD_RPM);

        Rule rule11 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method11 = (ExtractWordMethod) rule11.getMethod();
        method11.setPosition(16);
        Target target11 = (Target) rule11.getTarget();
        target11.hashID(LinksID.FEDORA_RPM);

        Rule rule12 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method12 = (ExtractWordMethod) rule12.getMethod();
        method12.setPosition(18);
        Target target12 = (Target) rule12.getTarget();
        target12.hashID(LinksID.LEAP_RPM);

        Rule rule13 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method13 = (ExtractWordMethod) rule13.getMethod();
        method13.setPosition(20);
        Target target13 = (Target) rule13.getTarget();
        target13.hashID(LinksID.OPENSUSE_RPM);

        Rule rule14 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method14 = (ExtractWordMethod) rule14.getMethod();
        method14.setPosition(22);
        Target target14 = (Target) rule14.getTarget();
        target14.hashID(LinksID.LINUX_RUN);

        Rule rule15 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method15 = (ExtractWordMethod) rule15.getMethod();
        method15.setPosition(24);
        Target target15 = (Target) rule15.getTarget();
        target15.hashID(LinksID.MAC_OS_X_DMG);

        Rule rule16 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method16 = (ExtractWordMethod) rule16.getMethod();
        method16.setPosition(26);
        Target target16 = (Target) rule16.getTarget();
        target16.hashID(LinksID.SOLARIS_TAR_GZ);

        Rule rule17 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method17 = (ExtractWordMethod) rule17.getMethod();
        method17.setPosition(28);
        Target target17 = (Target) rule17.getTarget();
        target17.hashID(LinksID.WINDOWS_X86_EXE);

        Rule rule18 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method18 = (ExtractWordMethod) rule18.getMethod();
        method18.setPosition(30);
        Target target18 = (Target) rule18.getTarget();
        target18.hashID(LinksID.SOURCECODE_TAR_BZ2);

        Rule rule19 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method19 = (ExtractWordMethod) rule19.getMethod();
        method19.setPosition(32);
        Target target19 = (Target) rule19.getTarget();
        target19.hashID(LinksID.SDK_ZIP);

        Rule rule20 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method20 = (ExtractWordMethod) rule20.getMethod();
        method20.setPosition(34);
        Target target20 = (Target) rule20.getTarget();
        target20.hashID(LinksID.DEBIAN_JESSIE_DEB);

        Rule rule21 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method21 = (ExtractWordMethod) rule21.getMethod();
        method21.setPosition(36);
        Target target21 = (Target) rule21.getTarget();
        target21.hashID(LinksID.DEBIAN_STRETCH_DEB);

        Rule rule22 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method22 = (ExtractWordMethod) rule22.getMethod();
        method22.setPosition(38);
        Target target22 = (Target) rule22.getTarget();
        target22.hashID(LinksID.UBUNTU_BIONIC_DEB);

        Rule rule23 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method23 = (ExtractWordMethod) rule23.getMethod();
        method23.setPosition(40);
        Target target23 = (Target) rule23.getTarget();
        target23.hashID(LinksID.UBUNTU_TRUSTY_DEB);

        Rule rule24 = new Rule(new Target(In.COLLECTION, UseAs.HASHES), MethodCreator.getMethod(MethodType.EXTRACT_WORD));
        ExtractWordMethod method24 = (ExtractWordMethod) rule24.getMethod();
        method24.setPosition(42);
        Target target24 = (Target) rule24.getTarget();
        target24.hashID(LinksID.UBUNTU_XENIAL_DEB);

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
        rulesSet.add(rule19);
        rulesSet.add(rule20);
        rulesSet.add(rule21);
        rulesSet.add(rule22);
        rulesSet.add(rule23);
        rulesSet.add(rule24);

        return rulesSet;
    }

}
