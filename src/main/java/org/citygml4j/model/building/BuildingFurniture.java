package org.citygml4j.model.building;

import org.citygml4j.model.construction.AbstractFurniture;
import org.citygml4j.model.core.StandardObjectClassifier;
import org.citygml4j.model.deprecated.building.DeprecatedPropertiesOfBuildingFurniture;
import org.citygml4j.model.deprecated.core.DeprecatedPropertiesOfAbstractCityObject;
import org.citygml4j.visitor.FeatureVisitor;
import org.citygml4j.visitor.ObjectVisitor;
import org.xmlobjects.gml.model.basictypes.Code;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class BuildingFurniture extends AbstractFurniture implements StandardObjectClassifier {
    private Code classifier;
    private List<Code> functions;
    private List<Code> usages;
    private List<ADEPropertyOfBuildingFurniture<?>> adeProperties;

    @Override
    public Code getClassifier() {
        return classifier;
    }

    @Override
    public void setClassifier(Code classifier) {
        this.classifier = asChild(classifier);
    }

    @Override
    public List<Code> getFunctions() {
        if (functions == null)
            functions = new ChildList<>(this);

        return functions;
    }

    @Override
    public void setFunctions(List<Code> functions) {
        this.functions = asChild(functions);
    }

    @Override
    public List<Code> getUsages() {
        if (usages == null)
            usages = new ChildList<>(this);

        return usages;
    }

    @Override
    public void setUsages(List<Code> usages) {
        this.usages = asChild(usages);
    }

    @Override
    public DeprecatedPropertiesOfBuildingFurniture getDeprecatedProperties() {
        return (DeprecatedPropertiesOfBuildingFurniture) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedPropertiesOfAbstractCityObject createDeprecatedProperties() {
        return new DeprecatedPropertiesOfBuildingFurniture();
    }

    public List<ADEPropertyOfBuildingFurniture<?>> getADEPropertiesOfBuildingFurniture() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfBuildingFurniture(List<ADEPropertyOfBuildingFurniture<?>> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }

    @Override
    public void accept(ObjectVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(FeatureVisitor visitor) {
        visitor.visit(this);
    }
}
