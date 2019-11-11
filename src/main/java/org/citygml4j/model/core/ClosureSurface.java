package org.citygml4j.model.core;

import org.xmlobjects.model.ChildList;

import java.util.List;

public class ClosureSurface extends AbstractThematicSurface {
    private List<ADEPropertyOfClosureSurface> adeProperties;

    public List<ADEPropertyOfClosureSurface> getADEPropertiesOfClosureSurface() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfClosureSurface(List<ADEPropertyOfClosureSurface> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }
}