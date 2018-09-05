package net.vego1mar.rules;

import net.vego1mar.rules.auxiliary.target.Targetable;
import net.vego1mar.rules.enumerators.methods.firstof.FirstOfType;
import org.jetbrains.annotations.NotNull;

public interface MethodExecutable {

    void firstOf(@NotNull Targetable target, @NotNull FirstOfType type, @NotNull String text);

}
