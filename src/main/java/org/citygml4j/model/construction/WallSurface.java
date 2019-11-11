package org.citygml4j.model.construction;

import org.xmlobjects.model.ChildList;

import java.util.List;

public class WallSurface extends AbstractConstructionSurface {
    private List<ADEPropertyOfWallSurface> adeProperties;

    public List<ADEPropertyOfWallSurface> getADEPropertiesOfWallSurface() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfWallSurface(List<ADEPropertyOfWallSurface> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }
}