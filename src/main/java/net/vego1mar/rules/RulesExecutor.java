package net.vego1mar.rules;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import net.vego1mar.properties.InProperty;
import net.vego1mar.target.Target;
import net.vego1mar.utils.ReflectionHelper;
import org.apache.log4j.Logger;
import net.vego1mar.properties.UseAsProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class RulesExecutor implements Serializable {

    private static final transient Logger log = Logger.getLogger(RulesExecutor.class);
    private transient Deque<Rule> rulesSet;
    private transient InProperty inProperty;
    private UseAsProperty useAsProperty;

    public RulesExecutor(@NotNull Deque<Rule> rulesSet, @NotNull String htmlCode) {
        this.rulesSet = new LinkedList<>(rulesSet);
        inProperty = new InProperty();
        inProperty.setCode(htmlCode);
        useAsProperty = new UseAsProperty();
        String identity = '@' + Integer.toHexString(System.identityHashCode(this));
        log.info(getClass().getSimpleName() + identity + "(RULES=" + rulesSet.size() + "; CODE_CHARS=" + htmlCode.length() + ')');
    }

    public void renew(@NotNull Deque<Rule> rulesSet, @NotNull String htmlCode) {
        this.rulesSet = new LinkedList<>(rulesSet);
        inProperty.setCode(htmlCode);
        String identity = '@' + Integer.toHexString(System.identityHashCode(this));
        log.info(ReflectionHelper.getCurrentMethodName() + identity + "(RULES=" + rulesSet.size() + "; CODE_CHARS=" +
            htmlCode.length() + ')');
    }

    public void execute() {
        String identity = getClass().getSimpleName() + '@' + Integer.toHexString(System.identityHashCode(this));
        Deque<Rule> rules = new LinkedList<>(this.rulesSet);

        while (!rules.isEmpty()) {
            executeRule(rules.removeFirst());
        }

        log.info(identity + ':' + ReflectionHelper.getCurrentMethodName() + "() OK");
    }

    private void executeRule(@NotNull Rule rule) {
        inProperty = rule.getMethod().invoke(rule.getTarget(), inProperty);
        Target target = (Target) rule.getTarget();

        switch (target.useAs()) {
            case IGNORE:
                break;
            case VERSIONS:
                useAsProperty.getVersions().setItem(target.version(), inProperty.getContent());
                break;
            case DATES:
                useAsProperty.getDates().setItem(target.date(), inProperty.getContent());
                break;
            case LINKS:
                useAsProperty.getLinks().setItem(target.linkID(), inProperty.getContent());
                break;
            case HASHES:
                useAsProperty.getHashes().setItem(target.hashID(), inProperty.getContent());
                break;
        }
    }

    @Contract(pure = true) public UseAsProperty getResults() {
        return useAsProperty;
    }

    @Contract(pure = true) public Deque<Rule> getRulesSet() {
        return rulesSet;
    }

}
