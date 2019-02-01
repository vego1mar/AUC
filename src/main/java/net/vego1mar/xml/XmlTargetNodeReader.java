package net.vego1mar.xml;

import net.vego1mar.auxiliary.target.Target;
import net.vego1mar.enumerators.properties.LinksID;
import net.vego1mar.enumerators.properties.Platforms;
import net.vego1mar.enumerators.traits.In;
import net.vego1mar.enumerators.traits.UseAs;
import org.dom4j.Element;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class XmlTargetNodeReader extends XmlRulesSetTags {

    private Element xmlTarget;
    private Target objTarget;

    public XmlTargetNodeReader(@NotNull Element targetNode) {
        xmlTarget = targetNode;
        objTarget = new Target(In.CODE, UseAs.IGNORE);
    }

    public void readXmlTarget() {
        // Index 0 will be ignored by the switchFetching() method.
        Element[] elements = new Element[TARGETNODE_HASHID + 1];
        boolean[] isCorrect = new boolean[elements.length];

        for (int i = 0; i < elements.length; i++) {
            elements[i] = switchFetching(i);
        }

        for (int i = 0; i < elements.length; i++) {
            isCorrect[i] = checkElement(elements[i]);
        }

        for (int i = 0; i < isCorrect.length; i++) {
            if (isCorrect[i]) {
                switchReading(i, elements[i]);
            }
        }
    }

    @Nullable private Element switchFetching(int elementNo) {
        switch (elementNo) {
            case TARGETNODE_IN:
                return xmlTarget.element(TAG_IN);
            case TARGETNODE_USEAS:
                return xmlTarget.element(TAG_USEAS);
            case TARGETNODE_VERSION:
                return xmlTarget.element(TAG_VERSION);
            case TARGETNODE_DATE:
                return xmlTarget.element(TAG_DATE);
            case TARGETNODE_LINKID:
                return xmlTarget.element(TAG_LINKID);
            case TARGETNODE_HASHID:
                return xmlTarget.element(TAG_HASHID);
            default:
                break;
        }

        return null;
    }

    @Contract(value = "null -> false; !null -> true", pure = true) private boolean checkElement(Element element) {
        return element != null;
    }

    private void switchReading(int elementNo, Element element) {
        switch (elementNo) {
            case TARGETNODE_IN:
                objTarget.in(In.valueOf(element.getText()));
                break;
            case TARGETNODE_USEAS:
                objTarget.useAs(UseAs.valueOf(element.getText()));
                break;
            case TARGETNODE_VERSION:
                objTarget.version(Platforms.valueOf(element.getText()));
                break;
            case TARGETNODE_DATE:
                objTarget.date(Platforms.valueOf(element.getText()));
                break;
            case TARGETNODE_LINKID:
                objTarget.linkID(LinksID.valueOf(element.getText()));
                break;
            case TARGETNODE_HASHID:
                objTarget.hashID(LinksID.valueOf(element.getText()));
                break;
            default:
                break;
        }
    }

    public Target getObjTarget() {
        return objTarget;
    }
}
