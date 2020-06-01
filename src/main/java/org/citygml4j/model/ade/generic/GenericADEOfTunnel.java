package org.citygml4j.model.ade.generic;

import org.citygml4j.model.tunnel.ADEOfTunnel;
import org.w3c.dom.Element;

public class GenericADEOfTunnel extends ADEOfTunnel implements ADEGenericPropertyContainer {
    private Element value;

    public GenericADEOfTunnel() {
    }

    public GenericADEOfTunnel(Element value) {
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