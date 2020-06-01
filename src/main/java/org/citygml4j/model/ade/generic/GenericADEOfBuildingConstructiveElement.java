package org.citygml4j.model.ade.generic;

import org.citygml4j.model.building.ADEOfBuildingConstructiveElement;
import org.w3c.dom.Element;

public class GenericADEOfBuildingConstructiveElement extends ADEOfBuildingConstructiveElement implements ADEGenericPropertyContainer {
    private Element value;

    public GenericADEOfBuildingConstructiveElement() {
    }

    public GenericADEOfBuildingConstructiveElement(Element value) {
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