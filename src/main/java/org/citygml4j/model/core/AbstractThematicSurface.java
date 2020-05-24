package org.citygml4j.model.core;

import org.citygml4j.model.common.GeometryInfo;
import org.citygml4j.model.deprecated.core.DeprecatedPropertiesOfAbstractThematicSurface;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.model.geometry.aggregates.MultiCurveProperty;
import org.xmlobjects.gml.model.geometry.aggregates.MultiSurfaceProperty;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public abstract class AbstractThematicSurface extends AbstractSpaceBoundary {
    private List<QualifiedAreaProperty> areas;
    private MultiCurveProperty lod0MultiCurve;
    private MultiSurfaceProperty lod0MultiSurface;
    private MultiSurfaceProperty lod1MultiSurface;
    private MultiSurfaceProperty lod2MultiSurface;
    private MultiSurfaceProperty lod3MultiSurface;
    private AbstractPointCloudProperty pointCloud;
    private List<ADEOfAbstractThematicSurface> adeOfAbstractThematicSurface;

    public List<QualifiedAreaProperty> getAreas() {
        if (areas == null)
            areas = new ChildList<>(this);

        return areas;
    }

    public void setAreas(List<QualifiedAreaProperty> areas) {
        this.areas = asChild(areas);
    }

    public MultiCurveProperty getLod0MultiCurve() {
        return lod0MultiCurve;
    }

    public void setLod0MultiCurve(MultiCurveProperty lod0MultiCurve) {
        this.lod0MultiCurve = asChild(lod0MultiCurve);
    }

    public MultiSurfaceProperty getLod0MultiSurface() {
        return lod0MultiSurface;
    }

    public void setLod0MultiSurface(MultiSurfaceProperty lod0MultiSurface) {
        this.lod0MultiSurface = asChild(lod0MultiSurface);
    }

    public MultiSurfaceProperty getLod1MultiSurface() {
        return lod1MultiSurface;
    }

    public void setLod1MultiSurface(MultiSurfaceProperty lod1MultiSurface) {
        this.lod1MultiSurface = asChild(lod1MultiSurface);
    }

    public MultiSurfaceProperty getLod2MultiSurface() {
        return lod2MultiSurface;
    }

    public void setLod2MultiSurface(MultiSurfaceProperty lod2MultiSurface) {
        this.lod2MultiSurface = asChild(lod2MultiSurface);
    }

    public MultiSurfaceProperty getLod3MultiSurface() {
        return lod3MultiSurface;
    }

    public void setLod3MultiSurface(MultiSurfaceProperty lod3MultiSurface) {
        this.lod3MultiSurface = asChild(lod3MultiSurface);
    }

    public AbstractPointCloudProperty getPointCloud() {
        return pointCloud;
    }

    public void setPointCloud(AbstractPointCloudProperty pointCloud) {
        this.pointCloud = asChild(pointCloud);
    }

    @Override
    public DeprecatedPropertiesOfAbstractThematicSurface getDeprecatedProperties() {
        return (DeprecatedPropertiesOfAbstractThematicSurface) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedPropertiesOfAbstractThematicSurface createDeprecatedProperties() {
        return new DeprecatedPropertiesOfAbstractThematicSurface();
    }

    public List<ADEOfAbstractThematicSurface> getADEOfAbstractThematicSurface() {
        if (adeOfAbstractThematicSurface == null)
            adeOfAbstractThematicSurface = new ChildList<>(this);

        return adeOfAbstractThematicSurface;
    }

    public void setADEOfAbstractThematicSurface(List<ADEOfAbstractThematicSurface> adeOfAbstractThematicSurface) {
        this.adeOfAbstractThematicSurface = asChild(adeOfAbstractThematicSurface);
    }

    public MultiSurfaceProperty getMultiSurface(int lod) {
        switch (lod) {
            case 0:
                return getLod0MultiSurface();
            case 1:
                return getLod1MultiSurface();
            case 2:
                return getLod2MultiSurface();
            case 3:
                return getLod3MultiSurface();
            default:
                return null;
        }
    }

    public boolean setMultiSurface(int lod, MultiSurfaceProperty property) {
        switch (lod) {
            case 0:
                setLod0MultiSurface(property);
                return true;
            case 1:
                setLod1MultiSurface(property);
                return true;
            case 2:
                setLod2MultiSurface(property);
                return true;
            case 3:
                setLod3MultiSurface(property);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (lod0MultiCurve != null && lod0MultiCurve.getObject() != null)
            envelope.include(lod0MultiCurve.getObject().computeEnvelope());

        for (int lod = 0; lod < 4; lod++) {
            MultiSurfaceProperty property = getMultiSurface(lod);
            if (property != null && property.getObject() != null)
                envelope.include(property.getObject().computeEnvelope());
        }

        if (pointCloud != null && pointCloud.getObject() != null)
            envelope.include(pointCloud.getObject().computeEnvelope(options));

        if (hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAbstractThematicSurface properties = getDeprecatedProperties();

            if (properties.getLod4MultiSurface() != null && properties.getLod4MultiSurface().getObject() != null)
                envelope.include(properties.getLod4MultiSurface().getObject().computeEnvelope());
        }

        if (adeProperties != null) {
            for (ADEOfAbstractThematicSurface<?> property : adeProperties)
                updateEnvelope(property, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        geometryInfo.addGeometry(0, lod0MultiCurve);

        for (int lod = 0; lod < 4; lod++)
            geometryInfo.addGeometry(lod, getMultiSurface(lod));

        if (hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAbstractThematicSurface properties = getDeprecatedProperties();

            geometryInfo.addGeometry(4, properties.getLod4MultiSurface());
        }

        if (adeProperties != null) {
            for (ADEOfAbstractThematicSurface<?> property : adeProperties)
                updateGeometryInfo(property, geometryInfo);
        }
    }
}
