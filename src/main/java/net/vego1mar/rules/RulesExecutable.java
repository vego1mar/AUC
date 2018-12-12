package net.vego1mar.rules;

import java.util.Deque;
import net.vego1mar.auxiliary.properties.UseAsImpl;
import org.jetbrains.annotations.NotNull;

public interface RulesExecutable {

    void execute();

    UseAsImpl getResults();

    void renew(@NotNull Deque<RuleBased> rulesSet, @NotNull String htmlCode);

}
