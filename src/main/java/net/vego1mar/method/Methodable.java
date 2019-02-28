package net.vego1mar.method;

import net.vego1mar.properties.InImpl;
import net.vego1mar.target.Targetable;
import net.vego1mar.enumerators.traits.MethodType;
import org.jetbrains.annotations.NotNull;

public interface Methodable {

    MethodType getMethodType();

    InImpl invoke(@NotNull Targetable target, @NotNull InImpl inProperty);

}
