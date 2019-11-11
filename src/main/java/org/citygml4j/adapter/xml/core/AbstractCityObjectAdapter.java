package org.citygml4j.adapter.xml.core;

import org.citygml4j.adapter.xml.CityGMLBuilderHelper;
import org.citygml4j.adapter.xml.CityGMLSerializerHelper;
import org.citygml4j.model.ade.generic.GenericADEPropertyOfAbstractCityObject;
import org.citygml4j.model.core.ADEPropertyOfAbstractCityObject;
import org.citygml4j.model.core.AbstractAppearanceProperty;
import org.citygml4j.model.core.AbstractCityObject;
import org.citygml4j.model.core.AbstractCityObjectProperty;
import org.citygml4j.model.core.AbstractDynamizerProperty;
import org.citygml4j.model.core.AbstractGenericAttribute;
import org.citygml4j.model.core.AbstractGenericAttributeProperty;
import org.citygml4j.model.core.CityObjectRelationProperty;
import org.citygml4j.model.core.ExternalReference;
import org.citygml4j.model.core.ExternalReferenceProperty;
import org.citygml4j.model.core.RelativeToTerrain;
import org.citygml4j.model.core.RelativeToWater;
import org.citygml4j.model.deprecated.DeprecatedProperties;
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

import javax.xml.namespace.QName;

public abstract class AbstractCityObjectAdapter<T extends AbstractCityObject> extends AbstractFeatureWithLifespanAdapter<T> {
    private final QName[] substitutionGroups = new QName[] {
            new QName(CityGMLConstants.CITYGML_3_0_CORE_NAMESPACE, "AbstractGenericApplicationPropertyOfAbstractCityObject"),
            new QName(CityGMLConstants.CITYGML_2_0_CORE_NAMESPACE, "_GenericApplicationPropertyOfCityObject"),
            new QName(CityGMLConstants.CITYGML_1_0_CORE_NAMESPACE, "_GenericApplicationPropertyOfCityObject")
    };

    @Override
    public void buildChildObject(T object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLBuilderHelper.isCoreNamespace(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "externalReference":
                    if (CityGMLConstants.CITYGML_3_0_CORE_NAMESPACE.equals(name.getNamespaceURI()))
                        object.getExternalReferences().add(reader.getObjectUsingBuilder(ExternalReferencePropertyAdapter.class));
                    else {
                        ExternalReference reference = reader.getObjectUsingBuilder(ExternalReferenceAdapter.class);
                        object.getExternalReferences().add(new ExternalReferenceProperty(reference));
                    }
                    return;
                case "generalizesTo":
                    if (CityGMLConstants.CITYGML_3_0_CORE_NAMESPACE.equals(name.getNamespaceURI()))
                        object.getGeneralizesTo().add(reader.getObjectUsingBuilder(ReferenceAdapter.class));
                    else
                        object.getDeprecatedProperties().addFeature(DeprecatedProperties.GENERALIZES_TO_OBJECT, reader.getObjectUsingBuilder(AbstractCityObjectPropertyAdapter.class));
                    return;
                case "relativeToTerrain":
                    reader.getTextContent().ifPresent(v -> object.setRelativeToTerrain(RelativeToTerrain.fromValue(v)));
                    return;
                case "relativeToWater":
                    reader.getTextContent().ifPresent(v -> object.setRelativeToWater(RelativeToWater.fromValue(v)));
                    return;
                case "relatedTo":
                    object.getRelatedTo().add(reader.getObjectUsingBuilder(CityObjectRelationPropertyAdapter.class));
                    return;
                case "appearance":
                    object.getAppearances().add(reader.getObjectUsingBuilder(AbstractAppearancePropertyAdapter.class));
                    return;
                case "genericAttribute":
                    object.getGenericAttributes().add(reader.getObjectUsingBuilder(AbstractGenericAttributePropertyAdapter.class));
                    return;
                case "dynamizer":
                    object.getDynamizers().add(reader.getObjectUsingBuilder(AbstractDynamizerPropertyAdapter.class));
                    return;
            }
        } else if (CityGMLConstants.CITYGML_2_0_APPEARANCE_NAMESPACE.equals(name.getNamespaceURI())
                || CityGMLConstants.CITYGML_1_0_APPEARANCE_NAMESPACE.equals(name.getNamespaceURI())) {
            object.getAppearances().add(reader.getObjectUsingBuilder(AbstractAppearancePropertyAdapter.class));
            return;
        } else if (CityGMLConstants.CITYGML_2_0_GENERICS_NAMESPACE.equals(name.getNamespaceURI())
                || CityGMLConstants.CITYGML_1_0_GENERICS_NAMESPACE.equals(name.getNamespaceURI())) {
            object.getGenericAttributes().add(new AbstractGenericAttributeProperty(reader.getObject(AbstractGenericAttribute.class)));
            return;
        }

        if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI())) {
            ObjectBuilder<ADEPropertyOfAbstractCityObject> builder = reader.getXMLObjects().getBuilder(name, ADEPropertyOfAbstractCityObject.class);
            if (builder != null)
                object.getADEPropertiesOfAbstractCityObject().add(reader.getObjectUsingBuilder(builder));
            else if (CityGMLBuilderHelper.createAsGenericADEProperty(name, reader, substitutionGroups))
                object.getADEPropertiesOfAbstractCityObject().add(GenericADEPropertyOfAbstractCityObject.of(reader.getDOMElement()));
        } else
            super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public void writeChildElements(T object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);
        String coreNamespace = CityGMLSerializerHelper.getCoreNamespace(namespaces);
        boolean isCityGML3 = CityGMLConstants.CITYGML_3_0_CORE_NAMESPACE.equals(coreNamespace);

        for (ExternalReferenceProperty property : object.getExternalReferences()) {
            if (isCityGML3)
                writer.writeElementUsingSerializer(Element.of(coreNamespace, "externalReference"), property, ExternalReferencePropertyAdapter.class, namespaces);
            else
                writer.writeElementUsingSerializer(Element.of(coreNamespace, "externalReference"), property.getObject(), ExternalReferenceAdapter.class, namespaces);
        }

        for (Reference reference : object.getGeneralizesTo())
            writer.writeElementUsingSerializer(Element.of(coreNamespace, "generalizesTo"), reference, ReferenceAdapter.class, namespaces);

        if (object.getDeprecatedProperties().containsFeatures(DeprecatedProperties.GENERALIZES_TO_OBJECT)) {
            for (AbstractCityObjectProperty property : object.getDeprecatedProperties().getFeatures(DeprecatedProperties.GENERALIZES_TO_OBJECT, AbstractCityObjectProperty.class))
                writer.writeElementUsingSerializer(Element.of(coreNamespace, "generalizesTo"), property, AbstractCityObjectPropertyAdapter.class, namespaces);
        }

        if (object.getRelativeToTerrain() != null)
            writer.writeElement(Element.of(coreNamespace, "relativeToTerrain").addTextContent(object.getRelativeToTerrain().toValue()));

        if (object.getRelativeToWater() != null)
            writer.writeElement(Element.of(coreNamespace, "relativeToWater").addTextContent(object.getRelativeToWater().toValue()));

        if (isCityGML3) {
            for (CityObjectRelationProperty property : object.getRelatedTo())
                writer.writeElementUsingSerializer(Element.of(coreNamespace, "relatedTo"), property, CityObjectRelationPropertyAdapter.class, namespaces);
        }

        for (AbstractAppearanceProperty member : object.getAppearances()) {
            if (isCityGML3)
                writer.writeElementUsingSerializer(Element.of(coreNamespace, "appearance"), member, AbstractAppearancePropertyAdapter.class, namespaces);
            else {
                String namespace = namespaces.contains(CityGMLConstants.CITYGML_1_0_APPEARANCE_NAMESPACE) ?
                        CityGMLConstants.CITYGML_1_0_APPEARANCE_NAMESPACE :
                        CityGMLConstants.CITYGML_2_0_APPEARANCE_NAMESPACE;
                writer.writeElementUsingSerializer(Element.of(namespace, "appearance"), member, AbstractAppearancePropertyAdapter.class, namespaces);
            }
        }

        for (AbstractGenericAttributeProperty property : object.getGenericAttributes()) {
            if (isCityGML3)
                writer.writeElementUsingSerializer(Element.of(coreNamespace, "genericAttribute"), property, AbstractGenericAttributePropertyAdapter.class, namespaces);
            else if (property.getObject() != null)
                writer.writeObject(property.getObject(), namespaces);
        }

        if (isCityGML3) {
            for (AbstractDynamizerProperty property : object.getDynamizers())
                writer.writeElementUsingSerializer(Element.of(coreNamespace, "dynamizer"), property, AbstractDynamizerPropertyAdapter.class, namespaces);
        }

        for (ADEPropertyOfAbstractCityObject property : object.getADEPropertiesOfAbstractCityObject())
            CityGMLSerializerHelper.serializeADEProperty(property, namespaces, writer);
    }
}