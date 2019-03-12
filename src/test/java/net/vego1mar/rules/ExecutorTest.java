package net.vego1mar.rules;

import java.util.Deque;
import net.vego1mar.properties.UseAsProperty;
import org.jetbrains.annotations.NotNull;

public abstract class ExecutorTest {

    private String webPage;

    protected ExecutorTest() {
        setWebPage("");
    }

    protected String getWebPage() {
        return webPage;
    }

    protected void setWebPage(@NotNull String webPage) {
        this.webPage = webPage;
    }

    public abstract Deque<Rule> getRules(int setNo);

    public abstract void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data);

}
