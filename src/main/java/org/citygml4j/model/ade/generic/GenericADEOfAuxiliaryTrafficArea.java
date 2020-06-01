package org.citygml4j.model.ade.generic;

import org.citygml4j.model.transportation.ADEOfAuxiliaryTrafficArea;
import org.w3c.dom.Element;

public class GenericADEOfAuxiliaryTrafficArea extends ADEOfAuxiliaryTrafficArea implements ADEGenericPropertyContainer {
    private Element value;

    public GenericADEOfAuxiliaryTrafficArea() {
    }

    public GenericADEOfAuxiliaryTrafficArea(Element value) {
        this.value = value;
    }

    @Override
    public Element getValue() {
        return value;
    }

    @Override
    public void setValue(Element value) {
        this.value = value;
    }
}