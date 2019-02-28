package net.vego1mar.method;

import net.vego1mar.enumerators.traits.MethodType;

public abstract class Method implements Methodable {

    protected static final String METHOD_TARGET_NOT_SUPPORTED = "Method @Target is not supported.";
    private MethodType methodType;

    public Method(MethodType methodType) {
        this.methodType = methodType;
    }

    @Override public MethodType getMethodType() {
        return methodType;
    }

    public abstract String toString();

}
