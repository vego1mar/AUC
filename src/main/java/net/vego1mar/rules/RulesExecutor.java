package net.vego1mar.rules;

import java.util.Deque;
import net.vego1mar.rules.auxiliary.inproperty.InInterface;
import net.vego1mar.utils.ReflectionHelper;
import org.apache.log4j.Logger;
import net.vego1mar.rules.auxiliary.method.Method;
import net.vego1mar.rules.auxiliary.target.Target;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsImpl;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsProperty;
import net.vego1mar.rules.enumerators.traits.InTrait;
import net.vego1mar.rules.enumerators.traits.MethodTrait;
import net.vego1mar.rules.enumerators.traits.UseAsTrait;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class RulesExecutor {

    private static final Logger log = Logger.getLogger(RulesExecutor.class);
    private MethodExecutable executor;
    private Deque<RuleBased> rulesSet;
    private RuleBased currentRule;
    private UseAsImpl useAsProperty;

    public RulesExecutor(@NotNull Deque<RuleBased> rulesSet, @NotNull String htmlCode) {
        executor = new MethodExecutor();
        ((MethodExecutor) executor).getInProperty().setCode(htmlCode);
        this.rulesSet = rulesSet;
        currentRule = new Rule(new Target(InTrait.HTML, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        useAsProperty = new UseAsProperty();
        String identity = '@' + Integer.toHexString(System.identityHashCode(this));
        log.info(getClass().getSimpleName() + identity + "(rulesSet.size()=" + rulesSet.size() + "; htmlCode.length()=" + htmlCode.length() + ')');
    }

    public void execute() {
        String identity = getClass().getSimpleName() + '@' + Integer.toHexString(System.identityHashCode(this));

        while (!rulesSet.isEmpty()) {
            currentRule = rulesSet.removeFirst();
            executeRule(currentRule);
            log.info("Rule at " + identity + currentRule + " executed.");
        }

        log.info(identity + ':' + ReflectionHelper.getCurrentMethodName() + "() completed");
    }

    private void executeRule(@NotNull RuleBased rule) {
        Method currentMethod = (Method) rule.getMethod();

        switch (rule.getMethod().getSelectedMethod()) {
            case FIRST_OF:
                executor.firstOf(rule.getTarget(), currentMethod.getFirstOfType(), currentMethod.getFirstOfText());
                break;
            case EXTRACT_WORD:
                executor.extractWord(rule.getTarget(), currentMethod.getExtractWordPosition());
                break;
            case SPLIT_WORDS:
                executor.splitWords();
                break;
            case REMOVE_CHARACTERS:
                executor.removeCharacters(rule.getTarget(), currentMethod.getRemoveCharactersSigns());
                break;
            case RETRIEVE_ALL_TAGS:
                executor.retrieveAllTags(currentMethod.getRetrieveAllTagsTag());
                break;
            case FETCH_HREFS:
                executor.fetchHrefs(rule.getTarget());
                break;
            case PREPEND:
                executor.prepend(rule.getTarget(), currentMethod.getPrependText());
                break;
        }

        InInterface inProperty = ((MethodExecutor) executor).getInProperty();

        switch (rule.getTarget().useAs()) {
            case IGNORE:
                break;
            case LATEST_APP_VERSION:
                useAsProperty.setLatestAppVersion(inProperty.getContent());
                break;
            case UPDATE_DATE:
                useAsProperty.setUpdateDate(inProperty.getContent());
                break;
            case WINDOWS_X86_PACKAGE_URL:
                useAsProperty.setWindowsX86packageURL(inProperty.getContent());
                break;
            case WINDOWS_X64_PACKAGE_URL:
                useAsProperty.setWindowsX64packageURL(inProperty.getContent());
                break;
        }
    }

    @Contract(pure = true) public UseAsImpl getResults() {
        return useAsProperty;
    }

}
