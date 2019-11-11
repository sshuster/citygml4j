package org.citygml4j.model.vegetation;

import org.citygml4j.model.core.StandardObjectClassifier;
import org.xmlobjects.gml.model.basictypes.Code;
import org.xmlobjects.gml.model.measures.Length;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class SolitaryVegetationObject extends AbstractVegetationObject implements StandardObjectClassifier {
    private Code classifier;
    private List<Code> functions;
    private List<Code> usages;
    private Code species;
    private Length height;
    private Length trunkDiameter;
    private Length crownDiameter;
    private Length rootBallDiameter;
    private Length maxRootBallDepth;
    private List<ADEPropertyOfSolitaryVegetationObject> adeProperties;

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

    public Code getSpecies() {
        return species;
    }

    public void setSpecies(Code species) {
        this.species = asChild(species);
    }

    public Length getHeight() {
        return height;
    }

    public void setHeight(Length height) {
        this.height = asChild(height);
    }

    public Length getTrunkDiameter() {
        return trunkDiameter;
    }

    public void setTrunkDiameter(Length trunkDiameter) {
        this.trunkDiameter = asChild(trunkDiameter);
    }

    public Length getCrownDiameter() {
        return crownDiameter;
    }

    public void setCrownDiameter(Length crownDiameter) {
        this.crownDiameter = asChild(crownDiameter);
    }

    public Length getRootBallDiameter() {
        return rootBallDiameter;
    }

    public void setRootBallDiameter(Length rootBallDiameter) {
        this.rootBallDiameter = asChild(rootBallDiameter);
    }

    public Length getMaxRootBallDepth() {
        return maxRootBallDepth;
    }

    public void setMaxRootBallDepth(Length maxRootBallDepth) {
        this.maxRootBallDepth = asChild(maxRootBallDepth);
    }

    public List<ADEPropertyOfSolitaryVegetationObject> getADEPropertiesOfSolitaryVegetationObject() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfSolitaryVegetationObject(List<ADEPropertyOfSolitaryVegetationObject> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }
}