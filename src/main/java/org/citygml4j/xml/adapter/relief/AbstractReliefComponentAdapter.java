package org.citygml4j.xml.adapter.relief;

import org.citygml4j.model.ade.generic.GenericADEOfAbstractReliefComponent;
import org.citygml4j.model.relief.ADEOfAbstractReliefComponent;
import org.citygml4j.model.relief.AbstractReliefComponent;
import org.citygml4j.util.CityGMLConstants;
import org.citygml4j.xml.adapter.CityGMLBuilderHelper;
import org.citygml4j.xml.adapter.CityGMLSerializerHelper;
import org.citygml4j.xml.adapter.ade.ADEBuilderHelper;
import org.citygml4j.xml.adapter.ade.ADESerializerHelper;
import org.citygml4j.xml.adapter.core.AbstractSpaceBoundaryAdapter;
import org.xmlobjects.builder.ObjectBuildException;
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

public abstract class AbstractReliefComponentAdapter<T extends AbstractReliefComponent> extends AbstractSpaceBoundaryAdapter<T> {
    private final QName[] substitutionGroups = new QName[]{
            new QName(CityGMLConstants.CITYGML_2_0_RELIEF_NAMESPACE, "_GenericApplicationPropertyOfReliefComponent"),
            new QName(CityGMLConstants.CITYGML_1_0_RELIEF_NAMESPACE, "_GenericApplicationPropertyOfReliefComponent")
    };

    @Override
    public void buildChildObject(T object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLBuilderHelper.isReliefNamespace(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "lod":
                    reader.getTextContent().ifInteger(object::setLod);
                    return;
                case "extent":
                    object.setExtent(reader.getObjectUsingBuilder(ExtentPropertyAdapter.class));
                    return;
                case "adeOfAbstractReliefComponent":
                    ADEBuilderHelper.addADEContainer(ADEOfAbstractReliefComponent.class, object.getADEOfAbstractReliefComponent(), GenericADEOfAbstractReliefComponent::of, reader);
                    return;
            }
        } else if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI())) {
            buildADEProperty(object, name, reader);
            return;
        }

        super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public void buildADEProperty(T object, QName name, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (!ADEBuilderHelper.addADEContainer(name, ADEOfAbstractReliefComponent.class, object.getADEOfAbstractReliefComponent(),
                GenericADEOfAbstractReliefComponent::of, reader, substitutionGroups))
            super.buildADEProperty(object, name, reader);
    }

    @Override
    public void writeChildElements(T object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);
        String reliefNamespace = CityGMLSerializerHelper.getReliefNamespace(namespaces);
        boolean isCityGML3 = CityGMLConstants.CITYGML_3_0_RELIEF_NAMESPACE.equals(reliefNamespace);

        writer.writeElement(Element.of(reliefNamespace, "lod").addTextContent(TextContent.ofInteger(object.getLod())));

        if (object.getExtent() != null)
            writer.writeElementUsingSerializer(Element.of(reliefNamespace, "extent"), object.getExtent(), ExtentPropertyAdapter.class, namespaces);

        for (ADEOfAbstractReliefComponent container : object.getADEOfAbstractReliefComponent())
            ADESerializerHelper.writeADEContainer(isCityGML3 ? Element.of(reliefNamespace, "adeOfAbstractReliefComponent") : null, container, namespaces, writer);
    }
}