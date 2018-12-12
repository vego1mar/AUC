package net.vego1mar.auxiliary.properties;

import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface InImpl {

    String getCode();

    void setCode(String code);

    String getContent();

    void setContent(String content);

    List<String> getCollection();

    void setCollection(@NotNull List<String> collection);

}
