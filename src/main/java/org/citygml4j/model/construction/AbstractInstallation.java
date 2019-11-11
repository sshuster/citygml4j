package org.citygml4j.model.construction;

import org.citygml4j.model.core.AbstractOccupiedSpace;
import org.citygml4j.model.core.AbstractThematicSurface;
import org.citygml4j.model.core.ClosureSurface;
import org.citygml4j.model.generics.GenericThematicSurface;
import org.xmlobjects.model.ChildList;

import java.util.List;

public abstract class AbstractInstallation extends AbstractOccupiedSpace {
    private RelationToConstruction relationToConstruction;
    private List<ADEPropertyOfAbstractInstallation> adeProperties;

    @Override
    public boolean isValidBoundarySurface(AbstractThematicSurface boundarySurface) {
        return boundarySurface instanceof AbstractConstructionSurface
                || boundarySurface instanceof ClosureSurface
                || boundarySurface instanceof GenericThematicSurface;
    }

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