package net.vego1mar.rules.auxiliary.method;

import net.vego1mar.rules.enumerators.methods.firstof.FirstOfType;
import net.vego1mar.rules.enumerators.traits.MethodTrait;
import org.jetbrains.annotations.NotNull;

public interface Methodable {

    MethodTrait getSelectedMethod();

    void firstOf(FirstOfType type, @NotNull String text);

    void extractWord(int position);

    void removeCharacters(@NotNull String signs);

}
