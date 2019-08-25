package org.citygml4j.model.construction;

import org.citygml4j.model.core.AbstractOccupiedSpace;
import org.citygml4j.model.core.BoundarySurfaceProperty;
import org.xmlobjects.gml.model.common.ChildList;

import java.util.List;

public abstract class AbstractConstructiveElement<T extends BoundarySurfaceProperty> extends AbstractOccupiedSpace<T> {
    private Boolean isStructuralElement;
    private List<AbstractFillingElementProperty> fillings;
    private List<ADEPropertyOfAbstractConstructiveElement> adeProperties;

    public Boolean getIsStructuralElement() {
        return isStructuralElement;
    }

    public boolean isSetIsStructuralElement() {
        return isStructuralElement != null;
    }

    public void setIsStructuralElement(Boolean isStructuralElement) {
        this.isStructuralElement = isStructuralElement;
    }

    public List<AbstractFillingElementProperty> getFillings() {
        if (fillings == null)
            fillings = new ChildList<>(this);

        return fillings;
    }

    public void setFillings(List<AbstractFillingElementProperty> fillings) {
        this.fillings = asChild(fillings);
    }

    public List<ADEPropertyOfAbstractConstructiveElement> getADEPropertiesOfAbstractConstructiveElement() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfAbstractConstructiveElement(List<ADEPropertyOfAbstractConstructiveElement> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }
}
