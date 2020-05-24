package org.citygml4j.model.transportation;

import org.citygml4j.model.common.GeometryInfo;
import org.citygml4j.model.core.AbstractThematicSurface;
import org.citygml4j.model.core.StandardObjectClassifier;
import org.citygml4j.visitor.ObjectVisitor;
import org.xmlobjects.gml.model.basictypes.Code;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class TrafficArea extends AbstractThematicSurface implements StandardObjectClassifier {
    private Code classifier;
    private List<Code> functions;
    private List<Code> usages;
    private Code surfaceMaterial;
    private List<ADEOfTrafficArea> adeOfTrafficArea;

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

    public Code getSurfaceMaterial() {
        return surfaceMaterial;
    }

    public void setSurfaceMaterial(Code surfaceMaterial) {
        this.surfaceMaterial = asChild(surfaceMaterial);
    }

    public List<ADEOfTrafficArea> getADEOfTrafficArea() {
        if (adeOfTrafficArea == null)
            adeOfTrafficArea = new ChildList<>(this);

        return adeOfTrafficArea;
    }

    public void setADEOfTrafficArea(List<ADEOfTrafficArea> adeOfTrafficArea) {
        this.adeOfTrafficArea = asChild(adeOfTrafficArea);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (adeProperties != null) {
            for (ADEOfTrafficArea<?> property : adeProperties)
                updateEnvelope(property, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        if (adeProperties != null) {
            for (ADEOfTrafficArea<?> property : adeProperties)
                updateGeometryInfo(property, geometryInfo);
        }
    }

    @Override
    public void accept(ObjectVisitor visitor) {
        visitor.visit(this);
    }
}
