package org.citygml4j.adapter.xml.building;

import org.citygml4j.adapter.xml.CityGMLBuilderHelper;
import org.citygml4j.adapter.xml.CityGMLSerializerHelper;
import org.citygml4j.adapter.xml.construction.ElevationPropertyAdapter;
import org.citygml4j.adapter.xml.core.AbstractLogicalSpaceAdapter;
import org.citygml4j.model.ade.generic.GenericADEPropertyOfAbstractBuildingSubdivision;
import org.citygml4j.model.building.ADEPropertyOfAbstractBuildingSubdivision;
import org.citygml4j.model.building.AbstractBuildingSubdivision;
import org.citygml4j.model.construction.ElevationProperty;
import org.citygml4j.util.CityGMLConstants;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.gml.adapter.base.ReferenceAdapter;
import org.xmlobjects.gml.model.base.Reference;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;
import org.xmlobjects.xml.TextContent;

import javax.xml.namespace.QName;

public abstract class AbstractBuildingSubdivisionAdapter<T extends AbstractBuildingSubdivision> extends AbstractLogicalSpaceAdapter<T> {
    private final QName substitutionGroup = new QName(CityGMLConstants.CITYGML_3_0_BUILDING_NAMESPACE, "AbstractGenericApplicationPropertyOfAbstractBuildingSubdivision");

    @Override
    public void buildChildObject(T object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLConstants.CITYGML_3_0_BUILDING_NAMESPACE.equals(name.getNamespaceURI())) {
            if (CityGMLBuilderHelper.buildStandardObjectClassifier(object, name.getLocalPart(), reader))
                return;

            switch (name.getLocalPart()) {
                case "elevation":
                    object.getElevations().add(reader.getObjectUsingBuilder(ElevationPropertyAdapter.class));
                    return;
                case "sortKey":
                    reader.getTextContent().ifDouble(object::setSortKey);
                    return;
                case "buildingConstructiveElement":
                    object.getBuildingConstructiveElements().add(reader.getObjectUsingBuilder(ReferenceAdapter.class));
                    return;
                case "buildingFurniture":
                    object.getBuildingFurniture().add(reader.getObjectUsingBuilder(ReferenceAdapter.class));
                    return;
                case "buildingInstallation":
                    object.getBuildingInstallations().add(reader.getObjectUsingBuilder(ReferenceAdapter.class));
                    return;
                case "buildingRoom":
                    object.getBuildingRooms().add(reader.getObjectUsingBuilder(ReferenceAdapter.class));
                    return;
            }
        }

        if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI())) {
            ObjectBuilder<ADEPropertyOfAbstractBuildingSubdivision> builder = reader.getXMLObjects().getBuilder(name, ADEPropertyOfAbstractBuildingSubdivision.class);
            if (builder != null)
                object.getADEPropertiesOfAbstractBuildingSubdivision().add(reader.getObjectUsingBuilder(builder));
            else if (CityGMLBuilderHelper.createAsGenericADEProperty(name, reader, substitutionGroup))
                object.getADEPropertiesOfAbstractBuildingSubdivision().add(GenericADEPropertyOfAbstractBuildingSubdivision.of(reader.getDOMElement()));
        } else
            super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public void writeChildElements(T object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);

        CityGMLSerializerHelper.serializeStandardObjectClassifier(object, CityGMLConstants.CITYGML_3_0_BUILDING_NAMESPACE, namespaces, writer);

        for (ElevationProperty property : object.getElevations())
            writer.writeElementUsingSerializer(Element.of(CityGMLConstants.CITYGML_3_0_BUILDING_NAMESPACE, "elevation"), property, ElevationPropertyAdapter.class, namespaces);

        if (object.getSortKey() != null)
            writer.writeElement(Element.of(CityGMLConstants.CITYGML_3_0_BUILDING_NAMESPACE, "sortKey").addTextContent(TextContent.ofDouble(object.getSortKey())));

        for (Reference reference : object.getBuildingConstructiveElements())
            writer.writeElementUsingSerializer(Element.of(CityGMLConstants.CITYGML_3_0_BUILDING_NAMESPACE, "buildingConstructiveElement"), reference, ReferenceAdapter.class, namespaces);

        for (Reference reference : object.getBuildingFurniture())
            writer.writeElementUsingSerializer(Element.of(CityGMLConstants.CITYGML_3_0_BUILDING_NAMESPACE, "buildingFurniture"), reference, ReferenceAdapter.class, namespaces);

        for (Reference reference : object.getBuildingInstallations())
            writer.writeElementUsingSerializer(Element.of(CityGMLConstants.CITYGML_3_0_BUILDING_NAMESPACE, "buildingInstallation"), reference, ReferenceAdapter.class, namespaces);

        for (Reference reference : object.getBuildingRooms())
            writer.writeElementUsingSerializer(Element.of(CityGMLConstants.CITYGML_3_0_BUILDING_NAMESPACE, "buildingRoom"), reference, ReferenceAdapter.class, namespaces);

        for (ADEPropertyOfAbstractBuildingSubdivision property : object.getADEPropertiesOfAbstractBuildingSubdivision())
            CityGMLSerializerHelper.serializeADEProperty(property, namespaces, writer);
    }
}