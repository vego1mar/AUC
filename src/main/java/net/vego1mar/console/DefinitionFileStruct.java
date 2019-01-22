package net.vego1mar.console;

import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class DefinitionFileStruct {

    private String entryName;
    private Map<String,String> execLines;

    public DefinitionFileStruct() {
        entryName = "";
        execLines = new LinkedHashMap<>();
    }

    public void setEntryName(@NotNull String name) {
        entryName = name;
    }

    public String getEntryName() {
        return entryName;
    }

    public void addFileName(@NotNull String xmlFileName, @NotNull String url) {
        execLines.put(xmlFileName, url);
    }

    public Map<String,String> getExecLines() {
        return execLines;
    }
}
