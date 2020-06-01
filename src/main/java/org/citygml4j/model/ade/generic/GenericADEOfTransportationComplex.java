package org.citygml4j.model.ade.generic;

import org.citygml4j.model.deprecated.transportation.ADEOfTransportationComplex;
import org.w3c.dom.Element;

public class GenericADEOfTransportationComplex extends ADEOfTransportationComplex implements ADEGenericPropertyContainer {
    private Element value;

    public GenericADEOfTransportationComplex() {
    }

    public GenericADEOfTransportationComplex(Element value) {
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