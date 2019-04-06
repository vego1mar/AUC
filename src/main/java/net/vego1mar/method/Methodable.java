package net.vego1mar.method;

import net.vego1mar.method.enumerators.MethodType;
import net.vego1mar.properties.InProperty;
import net.vego1mar.target.Target;
import org.jetbrains.annotations.NotNull;

public interface Methodable {

    MethodType getMethodType();

    InProperty invoke(@NotNull Target target, @NotNull InProperty inProperty);

}
