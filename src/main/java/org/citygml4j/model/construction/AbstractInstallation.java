package org.citygml4j.model.construction;

import org.citygml4j.model.core.AbstractOccupiedSpace;
import org.citygml4j.model.core.BoundarySurfaceProperty;
import org.xmlobjects.gml.model.common.ChildList;

import java.util.List;

public abstract class AbstractInstallation<T extends BoundarySurfaceProperty> extends AbstractOccupiedSpace<T> {
    private RelationToConstruction relationToConstruction;
    private List<ADEPropertyOfAbstractInstallation> adeProperties;

    public RelationToConstruction getRelationToConstruction() {
        return relationToConstruction;
    }

    public void setRelationToConstruction(RelationToConstruction relationToConstruction) {
        this.relationToConstruction = relationToConstruction;
    }

    public List<ADEPropertyOfAbstractInstallation> getADEPropertiesOfAbstractInstallation() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfAbstractInstallation(List<ADEPropertyOfAbstractInstallation> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }
}
