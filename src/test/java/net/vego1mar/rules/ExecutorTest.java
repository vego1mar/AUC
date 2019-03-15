package net.vego1mar.rules;

import java.util.Deque;
import net.vego1mar.properties.UseAsProperty;
import org.jetbrains.annotations.NotNull;

public abstract class ExecutorTest {

    private String webPage1;
    private String webPage2;
    private String filePath1;
    private String filePath2;

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

    public String getFilePath1() {
        return filePath1;
    }

    protected void setFilePath1(@NotNull String filePath1) {
        this.filePath1 = filePath1;
    }

    public String getFilePath2() {
        return filePath2;
    }

    protected void setFilePath2(@NotNull String filePath2) {
        this.filePath2 = filePath2;
    }

    public abstract Deque<Rule> getRules(int setNo);

    public abstract void assertCollectedDataForExpectedOutput(@NotNull UseAsProperty data);

}
