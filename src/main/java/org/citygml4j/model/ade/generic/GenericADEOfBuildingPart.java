package org.citygml4j.model.ade.generic;

import org.citygml4j.model.building.ADEOfBuildingPart;
import org.w3c.dom.Element;

public class GenericADEOfBuildingPart extends ADEOfBuildingPart implements ADEGenericPropertyContainer {
    private Element value;

    public GenericADEOfBuildingPart() {
    }

    public GenericADEOfBuildingPart(Element value) {
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