package org.citygml4j.model.transportation;

import org.citygml4j.model.common.GeometryInfo;
import org.citygml4j.model.core.StandardObjectClassifier;
import org.citygml4j.visitor.ObjectVisitor;
import org.xmlobjects.gml.model.basictypes.Code;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class Track extends AbstractTransportationSpace implements StandardObjectClassifier {
    private Code classifier;
    private List<Code> functions;
    private List<Code> usages;
    private List<SectionProperty> sections;
    private List<IntersectionProperty> intersections;
    private List<ADEOfTrack> adeOfTrack;

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

    public List<SectionProperty> getSections() {
        if (sections == null)
            sections = new ChildList<>(this);

        return sections;
    }

    public void setSections(List<SectionProperty> sections) {
        this.sections = asChild(sections);
    }

    public List<IntersectionProperty> getIntersections() {
        if (intersections == null)
            intersections = new ChildList<>(this);

        return intersections;
    }

    public void setIntersections(List<IntersectionProperty> intersections) {
        this.intersections = asChild(intersections);
    }

    public List<ADEOfTrack> getADEOfTrack() {
        if (adeOfTrack == null)
            adeOfTrack = new ChildList<>(this);

        return adeOfTrack;
    }

    public void setADEOfTrack(List<ADEOfTrack> adeOfTrack) {
        this.adeOfTrack = asChild(adeOfTrack);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (sections != null) {
            for (SectionProperty property : sections) {
                if (property.getObject() != null)
                    envelope.include(property.getObject().computeEnvelope(options));
            }
        }

        if (intersections != null) {
            for (IntersectionProperty property : intersections) {
                if (property.getObject() != null)
                    envelope.include(property.getObject().computeEnvelope(options));
            }
        }

        if (adeOfTrack != null) {
            for (ADEOfTrack container : adeOfTrack)
                updateEnvelope(container, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        if (adeProperties != null) {
            for (ADEOfTrack<?> property : adeProperties)
                updateGeometryInfo(property, geometryInfo);
        }
    }

    @Override
    public void accept(ObjectVisitor visitor) {
        visitor.visit(this);
    }
}
