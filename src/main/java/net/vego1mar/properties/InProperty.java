package net.vego1mar.properties;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class InProperty {

    private String code;
    private String content;
    private List<String> collection;

    public InProperty() {
        code = "";
        content = "";
        collection = new ArrayList<>();
    }

    @Contract(pure = true) public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Contract(pure = true) public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Contract(pure = true) public List<String> getCollection() {
        return collection;
    }

    public void setCollection(@NotNull List<String> collection) {
        this.collection = collection;
    }
}
