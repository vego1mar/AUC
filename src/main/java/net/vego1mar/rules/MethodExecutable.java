package net.vego1mar.rules;

import net.vego1mar.rules.auxiliary.target.Targetable;
import net.vego1mar.rules.enumerators.methods.firstof.FirstOfType;
import org.jetbrains.annotations.NotNull;

public interface MethodExecutable {

    void firstOf(@NotNull Targetable target, @NotNull FirstOfType type, @NotNull String text);

    void extractWord(@NotNull Targetable target, int position);

    void splitWords();

    void removeCharacters(@NotNull Targetable target, @NotNull String characters);

    void retrieveAllTags(@NotNull String tag);

    void fetchHrefs(@NotNull Targetable target);

    void prepend(@NotNull Targetable target, @NotNull String text);

}
