package org.citygml4j.xml.adapter.transportation;

import org.citygml4j.model.ade.generic.GenericADEOfTrafficArea;
import org.citygml4j.model.transportation.ADEOfTrafficArea;
import org.citygml4j.model.transportation.TrafficArea;
import org.citygml4j.util.CityGMLConstants;
import org.citygml4j.xml.adapter.CityGMLBuilderHelper;
import org.citygml4j.xml.adapter.CityGMLSerializerHelper;
import org.citygml4j.xml.adapter.ade.ADEBuilderHelper;
import org.citygml4j.xml.adapter.ade.ADESerializerHelper;
import org.citygml4j.xml.adapter.core.AbstractThematicSurfaceAdapter;
import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.annotation.XMLElements;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.gml.adapter.basictypes.CodeAdapter;
import org.xmlobjects.gml.adapter.geometry.aggregates.MultiSurfacePropertyAdapter;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElements({
        @XMLElement(name = "TrafficArea", namespaceURI = CityGMLConstants.CITYGML_3_0_TRANSPORTATION_NAMESPACE),
        @XMLElement(name = "TrafficArea", namespaceURI = CityGMLConstants.CITYGML_2_0_TRANSPORTATION_NAMESPACE),
        @XMLElement(name = "TrafficArea", namespaceURI = CityGMLConstants.CITYGML_1_0_TRANSPORTATION_NAMESPACE)
})
public class TrafficAreaAdapter extends AbstractThematicSurfaceAdapter<TrafficArea> {
    private final QName[] substitutionGroups = new QName[]{
            new QName(CityGMLConstants.CITYGML_2_0_TRANSPORTATION_NAMESPACE, "_GenericApplicationPropertyOfTrafficArea"),
            new QName(CityGMLConstants.CITYGML_1_0_TRANSPORTATION_NAMESPACE, "_GenericApplicationPropertyOfTrafficArea")
    };

    @Override
    public TrafficArea createObject(QName name, Object parent) throws ObjectBuildException {
        return new TrafficArea();
    }

    @Override
    public void buildChildObject(TrafficArea object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLBuilderHelper.isTransportationNamespace(name.getNamespaceURI())) {
            if (CityGMLBuilderHelper.buildStandardObjectClassifier(object, name.getLocalPart(), reader))
                return;

            switch (name.getLocalPart()) {
                case "surfaceMaterial":
                    object.setSurfaceMaterial(reader.getObjectUsingBuilder(CodeAdapter.class));
                    return;
                case "lod2MultiSurface":
                    object.setLod2MultiSurface(reader.getObjectUsingBuilder(MultiSurfacePropertyAdapter.class));
                    return;
                case "lod3MultiSurface":
                    object.setLod3MultiSurface(reader.getObjectUsingBuilder(MultiSurfacePropertyAdapter.class));
                    return;
                case "lod4MultiSurface":
                    object.getDeprecatedProperties().setLod4MultiSurface(reader.getObjectUsingBuilder(MultiSurfacePropertyAdapter.class));
                    return;
                case "adeOfTrafficArea":
                    ADEBuilderHelper.addADEContainer(ADEOfTrafficArea.class, object.getADEOfTrafficArea(), GenericADEOfTrafficArea::of, reader);
                    return;
            }
        } else if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI())) {
            buildADEProperty(object, name, reader);
            return;
        }

        super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public void buildADEProperty(TrafficArea object, QName name, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (!ADEBuilderHelper.addADEContainer(name, ADEOfTrafficArea.class, object.getADEOfTrafficArea(),
                GenericADEOfTrafficArea::of, reader, substitutionGroups))
            super.buildADEProperty(object, name, reader);
    }

    @Override
    public Element createElement(TrafficArea object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(CityGMLSerializerHelper.getTransportationNamespace(namespaces), "TrafficArea");
    }

    @Override
    public void writeChildElements(TrafficArea object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);
        String transportationNamespace = CityGMLSerializerHelper.getTransportationNamespace(namespaces);
        boolean isCityGML3 = CityGMLConstants.CITYGML_3_0_TRANSPORTATION_NAMESPACE.equals(transportationNamespace);

        CityGMLSerializerHelper.writeStandardObjectClassifier(object, transportationNamespace, namespaces, writer);

        if (object.getSurfaceMaterial() != null)
            writer.writeElementUsingSerializer(Element.of(transportationNamespace, "surfaceMaterial"), object.getSurfaceMaterial(), CodeAdapter.class, namespaces);

        if (!isCityGML3) {
            if (object.getLod2MultiSurface() != null)
                writer.writeElementUsingSerializer(Element.of(transportationNamespace, "lod2MultiSurface"), object.getLod2MultiSurface(), MultiSurfacePropertyAdapter.class, namespaces);

            if (object.getLod3MultiSurface() != null)
                writer.writeElementUsingSerializer(Element.of(transportationNamespace, "lod3MultiSurface"), object.getLod3MultiSurface(), MultiSurfacePropertyAdapter.class, namespaces);

            if (object.getDeprecatedProperties().getLod4MultiSurface() != null)
                writer.writeElementUsingSerializer(Element.of(transportationNamespace, "lod4MultiSurface"), object.getDeprecatedProperties().getLod4MultiSurface(), MultiSurfacePropertyAdapter.class, namespaces);
        }

        for (ADEOfTrafficArea container : object.getADEOfTrafficArea())
            ADESerializerHelper.writeADEContainer(isCityGML3 ? Element.of(transportationNamespace, "adeOfTrafficArea") : null, container, namespaces, writer);
    }
}