package net.vego1mar.auxiliary.properties;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class InProperty implements InImpl {

    private String code;
    private String content;
    private List<String> collection;

    public InProperty() {
        code = "";
        content = "";
        collection = new ArrayList<>();
    }

    @Override public String getCode() {
        return code;
    }

    @Override public void setCode(String code) {
        this.code = code;
    }

    @Override public String getContent() {
        return content;
    }

    @Override public void setContent(String content) {
        this.content = content;
    }

    @Override public List<String> getCollection() {
        return collection;
    }

    @Override public void setCollection(@NotNull List<String> collection) {
        this.collection = collection;
    }
}
