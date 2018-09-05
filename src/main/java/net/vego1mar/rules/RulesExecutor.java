package net.vego1mar.rules;

import java.util.LinkedList;
import net.vego1mar.rules.auxiliary.method.Method;
import net.vego1mar.rules.auxiliary.target.Target;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsInterface;
import net.vego1mar.rules.auxiliary.useasproperty.UseAsProperty;
import net.vego1mar.rules.enumerators.traits.AfterActionTrait;
import net.vego1mar.rules.enumerators.traits.FromTrait;
import net.vego1mar.rules.enumerators.traits.InTrait;
import net.vego1mar.rules.enumerators.traits.MethodTrait;
import net.vego1mar.rules.enumerators.traits.UseAsTrait;
import org.jetbrains.annotations.NotNull;

public final class RulesExecutor {

    protected MethodExecutable executor;
    private LinkedList<RuleBased> rulesSet;
    private RuleBased currentRule;
    private UseAsInterface useAsProperty;
    private StringBuilder executionInternalLog;

    // TODO: replace LinkedList with List enforcing LinkedList type
    public RulesExecutor(@NotNull LinkedList<RuleBased> rulesSet, @NotNull String htmlCode) {
        executor = new MethodExecutor();
        ((MethodExecutor) executor).getInProperty().setCode(htmlCode);
        this.rulesSet = rulesSet;

        currentRule = rulesSet.isEmpty()
            ? new Rule(new Target(FromTrait.START, InTrait.HTML, UseAsTrait.IGNORE), new Method(MethodTrait.FIRST_OF), AfterActionTrait.STOP)
            : rulesSet.removeFirst();

        useAsProperty = new UseAsProperty();
        executionInternalLog = new StringBuilder();
        String logInfo = "CTOR: rulesSet={" + rulesSet + "}, htmlCode={" + htmlCode + '}' + System.lineSeparator();
        executionInternalLog.append(logInfo);
    }

    // TODO: change access to private and use :execute() instead
    public void executeRule(@NotNull RuleBased rule) {
        switch (rule.getMethod().getSelectedMethod()) {
            case FIRST_OF:
                Method currentMethod = (Method) rule.getMethod();
                executor.firstOf(rule.getTarget(), currentMethod.getFirstOfType(), currentMethod.getFirstOfText());
            case EXTRACT_WORD:
                break;
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

        // TODO: handle log
        String logInfo = "RULE_EXEC: method={" + rule.getMethod().getSelectedMethod() + '}' + System.lineSeparator();
        executionInternalLog.append(logInfo.intern());
    }
}
