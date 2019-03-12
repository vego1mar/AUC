package net.vego1mar.rules;

import java.util.Deque;
import net.vego1mar.properties.UseAsProperty;
import org.jetbrains.annotations.NotNull;

public abstract class ExecutorTest {

    private String webPage1;
    private String webPage2;

    protected ExecutorTest() {
        setWebPage1("");
        setWebPage2("");
    }

    protected String getWebPage1() {
        return webPage1;
    }

    protected void setWebPage1(@NotNull String webPage1) {
        this.webPage1 = webPage1;
    }

    protected String getWebPage2() {
        return webPage2;
    }

    protected void setWebPage2(@NotNull String webPage2) {
        this.webPage2 = webPage2;
    }

    public abstract Deque<Rule> getRules(int setNo);

    public abstract void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data);

}
