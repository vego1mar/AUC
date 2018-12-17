package net.vego1mar.rules;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import net.vego1mar.auxiliary.properties.InImpl;
import net.vego1mar.auxiliary.properties.InProperty;
import net.vego1mar.auxiliary.target.Target;
import net.vego1mar.utils.ReflectionHelper;
import org.apache.log4j.Logger;
import net.vego1mar.auxiliary.properties.UseAsImpl;
import net.vego1mar.auxiliary.properties.UseAsProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class RulesExecutor implements RulesExecutable, Serializable {

    private static final transient Logger log = Logger.getLogger(RulesExecutor.class);
    private transient Deque<RuleBased> rulesSet;
    private transient InImpl inProperty;
    private UseAsImpl useAsProperty;

    public RulesExecutor(@NotNull Deque<RuleBased> rulesSet, @NotNull String htmlCode) {
        this.rulesSet = new LinkedList<>(rulesSet);
        inProperty = new InProperty();
        inProperty.setCode(htmlCode);
        useAsProperty = new UseAsProperty();
        String identity = '@' + Integer.toHexString(System.identityHashCode(this));
        log.info(
            getClass().getSimpleName() + identity + "(RULES=" + rulesSet.size() + "; CODE_CHARS="
                + htmlCode.length() + ')');
    }

    @Override public void renew(@NotNull Deque<RuleBased> rulesSet, @NotNull String htmlCode) {
        this.rulesSet = new LinkedList<>(rulesSet);
        inProperty.setCode(htmlCode);
        String identity = '@' + Integer.toHexString(System.identityHashCode(this));
        log.info(ReflectionHelper.getCurrentMethodName() + identity + "(RULES=" + rulesSet.size() + "; CODE_CHARS=" + htmlCode.length() + ')');
    }

    @Override public void execute() {
        String identity = getClass().getSimpleName() + '@' + Integer.toHexString(System.identityHashCode(this));
        Deque<RuleBased> rules = new LinkedList<>(this.rulesSet);

        while (!rules.isEmpty()) {
            RuleBased currentRule = rules.removeFirst();
            executeRule(currentRule);
            log.info("Rule executed -> " + identity + currentRule);
        }

        log.info(identity + ':' + ReflectionHelper.getCurrentMethodName() + "() completed");
    }

    private void executeRule(@NotNull RuleBased rule) {
        inProperty = rule.getMethod().invoke(rule.getTarget(), inProperty);
        Target target = (Target) rule.getTarget();

        switch (target.useAs()) {
            case IGNORE:
                break;
            case VERSIONS:
                useAsProperty.getVersions().setItem(target.getVersion(), inProperty.getContent());
                break;
            case DATES:
                useAsProperty.getDates().setItem(target.getDate(), inProperty.getContent());
                break;
            case LINKS:
                useAsProperty.getLinks().setItem(target.getLinkID(), inProperty.getContent());
                break;
            case HASHES:
                useAsProperty.getHashes().setItem(target.getHashID(), inProperty.getContent());
                break;
        }
    }

    @Contract(pure = true) @Override public UseAsImpl getResults() {
        return useAsProperty;
    }

    @Contract(pure = true) public Deque<RuleBased> getRulesSet() {
        return rulesSet;
    }

}
