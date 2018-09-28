package net.vego1mar.auxiliary.method;

import java.util.LinkedList;
import java.util.List;
import net.vego1mar.auxiliary.properties.InImpl;
import net.vego1mar.auxiliary.target.Targetable;
import net.vego1mar.enumerators.methods.RetrieveTagsType;
import net.vego1mar.enumerators.traits.MethodType;
import org.jetbrains.annotations.NotNull;

public class RetrieveTagsMethod extends Method {

    private RetrieveTagsType type;
    private String tagname;

    public RetrieveTagsMethod() {
        super(MethodType.RETRIEVE_TAGS);
        this.type = RetrieveTagsType.FIRST;
        tagname = "";
    }

    @Override public String toString() {
        return "{ " + MethodType.RETRIEVE_TAGS.name() + "; tagname='" + tagname + "' }";
    }

    public RetrieveTagsType getType() {
        return type;
    }

    public void setType(RetrieveTagsType type) {
        this.type = type;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(@NotNull String tagname) {
        this.tagname = tagname;
    }

    @Override public InImpl invoke(@NotNull Targetable target, @NotNull InImpl inProperty) {
        switch (target.in()) {
            case CODE:
                retrieve(inProperty, inProperty.getCode());
                break;
            case CONTENT:
                retrieve(inProperty, inProperty.getContent());
                break;
            case COLLECTION:
                throw new UnsupportedOperationException(METHOD_TARGET_NOT_SUPPORTED);
        }

        return inProperty;
    }

    private void retrieve(@NotNull InImpl inProperty, @NotNull String source) {
        switch (type) {
            case ALL:
                inProperty.setCollection(retrieveAllTags(source));
                break;
            case FIRST:
                inProperty.setContent(retrieveFirstTag(source));
                break;
        }
    }

    private List<String> retrieveAllTags(@NotNull String source) {
        String content = source;
        String openingTag = '<' + tagname;
        String closingTag = "</" + tagname + '>';
        List<String> tags = new LinkedList<>();
        int startIndex = 0;
        int endIndex = 0;

        while (!content.isEmpty()) {
            startIndex = content.indexOf(openingTag);
            endIndex = content.indexOf(closingTag) + tagname.length() + 3;

            if (startIndex < 0 || endIndex < 0) {
                break;
            }

            tags.add(content.substring(startIndex, endIndex));
            content = content.substring(endIndex);
        }

        return tags;
    }

    @NotNull private String retrieveFirstTag(@NotNull String source) {
        String openingTag = '<' + tagname;
        String closingTag = "</" + tagname + '>';
        int startIndex = source.indexOf(openingTag);
        int endIndex = source.indexOf(closingTag);

        if (startIndex < 0 || endIndex < 0) {
            return "";
        }

        endIndex += tagname.length() + 3;
        return source.substring(startIndex, endIndex);
    }

}
