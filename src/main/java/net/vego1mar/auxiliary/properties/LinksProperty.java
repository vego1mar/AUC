package net.vego1mar.auxiliary.properties;

import net.vego1mar.enumerators.properties.LinksID;

import java.io.Serializable;

public class LinksProperty implements LinksImpl, Serializable {

    private String windowsX86EXE;
    private String windowsX86MSI;
    private String windowsX64EXE;
    private String windowsX64MSI;
    private String windowsAny7Z;
    private String sourceCodeAny7Z;
    private String sdkAny7Z;

    public LinksProperty() {
        windowsX86EXE = "";
        windowsX86MSI = "";
        windowsX64EXE = "";
        windowsX64MSI = "";
        windowsAny7Z = "";
        sourceCodeAny7Z = "";
        sdkAny7Z = "";
    }

    @Override public void setItem(LinksID id, String item) {
        switch (id) {
            case NO_LINK:
                break;
            case WINDOWS_X86_EXE:
                this.windowsX86EXE = item;
                break;
            case WINDOWS_X86_MSI:
                this.windowsX86MSI = item;
                break;
            case WINDOWS_X64_EXE:
                this.windowsX64EXE = item;
                break;
            case WINDOWS_X64_MSI:
                this.windowsX64MSI = item;
                break;
            case WINDOWS_ANY_7Z:
                this.windowsAny7Z = item;
                break;
            case SOURCECODE_ANY_7Z:
                this.sourceCodeAny7Z = item;
                break;
            case SDK_ANY_7Z:
                this.sdkAny7Z = item;
                break;
        }
    }

    @Override public String getItem(LinksID id) {
        switch (id) {
            case NO_LINK:
                break;
            case WINDOWS_X86_EXE:
                return this.windowsX86EXE;
            case WINDOWS_X86_MSI:
                return windowsX86MSI;
            case WINDOWS_X64_EXE:
                return this.windowsX64EXE;
            case WINDOWS_X64_MSI:
                return this.windowsX64MSI;
            case WINDOWS_ANY_7Z:
                return this.windowsAny7Z;
            case SOURCECODE_ANY_7Z:
                return this.sourceCodeAny7Z;
            case SDK_ANY_7Z:
                return this.sdkAny7Z;
        }

        return "";
    }
}
