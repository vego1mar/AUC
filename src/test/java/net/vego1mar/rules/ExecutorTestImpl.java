package net.vego1mar.rules;

import java.util.Deque;
import net.vego1mar.properties.UseAsProperty;
import org.jetbrains.annotations.NotNull;

public interface ExecutorTestImpl {

    Deque<Rule> getRules(int setNo);

    void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data);

}
