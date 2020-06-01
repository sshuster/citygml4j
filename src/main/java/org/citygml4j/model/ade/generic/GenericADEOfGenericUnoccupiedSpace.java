package org.citygml4j.model.ade.generic;

import org.citygml4j.model.generics.ADEOfGenericUnoccupiedSpace;
import org.w3c.dom.Element;

public class GenericADEOfGenericUnoccupiedSpace extends ADEOfGenericUnoccupiedSpace implements ADEGenericPropertyContainer {
    private Element value;

    private GenericADEOfGenericUnoccupiedSpace(Element value) {
        this.value = value;
    }

    public static GenericADEOfGenericUnoccupiedSpace of(Element value) {
        return new GenericADEOfGenericUnoccupiedSpace(value);
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
