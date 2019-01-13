package net.vego1mar.collector;

import java.util.Deque;
import net.vego1mar.rules.RuleImpl;
import org.jetbrains.annotations.NotNull;

public interface AppInfoCollectible {

    void gatherInformation(@NotNull Deque<RuleImpl> rulesSet);

    boolean isUpdateAvailable();

    void save(@NotNull String objDestination, @NotNull String xmlDestination);

}
