package net.vego1mar.rules;

import java.util.Deque;
import net.vego1mar.utils.ReflectionHelper;
import org.apache.log4j.Logger;
import net.vego1mar.rules.auxiliary.method.Method;
import net.vego1mar.rules.auxiliary.target.Target;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsInterface;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsProperty;
import net.vego1mar.rules.enumerators.traits.InTrait;
import net.vego1mar.rules.enumerators.traits.MethodTrait;
import net.vego1mar.rules.enumerators.traits.UseAsTrait;
import org.jetbrains.annotations.NotNull;

public final class RulesExecutor {

    private static final Logger log = Logger.getLogger(RulesExecutor.class);
    private MethodExecutable executor;
    private Deque<RuleBased> rulesSet;
    private RuleBased currentRule;
    private UseAsInterface useAsProperty;

    public RulesExecutor(@NotNull Deque<RuleBased> rulesSet, @NotNull String htmlCode) {
        executor = new MethodExecutor();
        ((MethodExecutor) executor).getInProperty().setCode(htmlCode);
        this.rulesSet = rulesSet;
        currentRule = new Rule(new Target(InTrait.HTML, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF));
        useAsProperty = new UseAsProperty();
        log.info(ReflectionHelper.getCurrentMethodName() + "(rulesSet=" + rulesSet + "; htmlCode.length()=" + htmlCode.length() + ')');
    }

    public void execute() {
        while (!rulesSet.isEmpty()) {
            currentRule = rulesSet.removeFirst();
            executeRule(currentRule);
            log.info("Rule " + currentRule + " executed.");
        }

        log.info(ReflectionHelper.getCurrentMethodName() + "() completed");
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
        }

        switch (rule.getTarget().useAs()) {
            case LATEST_APP_VERSION:
                useAsProperty.setLatestAppVersion(((MethodExecutor) executor).getInProperty().getContent());
                break;
            case UPDATE_DATE:
                useAsProperty.setUpdateDate(((MethodExecutor) executor).getInProperty().getContent());
                break;
            case WINDOWS_X86_PACKAGE_URL:
                useAsProperty.setWindowsX86packageURL(((MethodExecutor) executor).getInProperty().getContent());
                break;
            case WINDOWS_X64_PACKAGE_URL:
                useAsProperty.setWindowsX64packageURL(((MethodExecutor) executor).getInProperty().getContent());
                break;
        }

        log.info(ReflectionHelper.getCurrentMethodName() + "(rule=" + rule + ')');
    }
}
