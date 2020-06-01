package org.citygml4j.model.ade.generic;

import org.citygml4j.model.construction.ADEOfAbstractInstallation;
import org.w3c.dom.Element;

public class GenericADEOfAbstractInstallation extends ADEOfAbstractInstallation implements ADEGenericPropertyContainer {
    private Element value;

    public GenericADEOfAbstractInstallation() {
    }

    public GenericADEOfAbstractInstallation(Element value) {
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