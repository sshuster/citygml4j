package org.citygml4j.xml.adapter.relief;

import org.citygml4j.model.ade.generic.GenericADEOfReliefFeature;
import org.citygml4j.model.relief.ADEOfReliefFeature;
import org.citygml4j.model.relief.AbstractReliefComponentProperty;
import org.citygml4j.model.relief.ReliefFeature;
import org.citygml4j.util.CityGMLConstants;
import org.citygml4j.xml.adapter.CityGMLBuilderHelper;
import org.citygml4j.xml.adapter.CityGMLSerializerHelper;
import org.citygml4j.xml.adapter.ade.ADEBuilderHelper;
import org.citygml4j.xml.adapter.ade.ADESerializerHelper;
import org.citygml4j.xml.adapter.core.AbstractSpaceBoundaryAdapter;
import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.annotation.XMLElements;
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

@XMLElements({
        @XMLElement(name = "ReliefFeature", namespaceURI = CityGMLConstants.CITYGML_3_0_RELIEF_NAMESPACE),
        @XMLElement(name = "ReliefFeature", namespaceURI = CityGMLConstants.CITYGML_2_0_RELIEF_NAMESPACE),
        @XMLElement(name = "ReliefFeature", namespaceURI = CityGMLConstants.CITYGML_1_0_RELIEF_NAMESPACE)
})
public class ReliefFeatureAdapter extends AbstractSpaceBoundaryAdapter<ReliefFeature> {
    private final QName[] substitutionGroups = new QName[]{
            new QName(CityGMLConstants.CITYGML_2_0_RELIEF_NAMESPACE, "_GenericApplicationPropertyOfReliefFeature"),
            new QName(CityGMLConstants.CITYGML_1_0_RELIEF_NAMESPACE, "_GenericApplicationPropertyOfReliefFeature")
    };

    @Override
    public ReliefFeature createObject(QName name, Object parent) throws ObjectBuildException {
        return new ReliefFeature();
    }

    @Override
    public void buildChildObject(ReliefFeature object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLBuilderHelper.isReliefNamespace(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "lod":
                    reader.getTextContent().ifInteger(object::setLod);
                    return;
                case "reliefComponent":
                    object.getReliefComponents().add(reader.getObjectUsingBuilder(AbstractReliefComponentPropertyAdapter.class));
                    return;
                case "adeOfReliefFeature":
                    ADEBuilderHelper.addADEContainer(ADEOfReliefFeature.class, object.getADEOfReliefFeature(), GenericADEOfReliefFeature::of, reader);
                    return;
            }
        } else if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI())) {
            buildADEProperty(object, name, reader);
            return;
        }

        super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public void buildADEProperty(ReliefFeature object, QName name, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (!ADEBuilderHelper.addADEContainer(name, ADEOfReliefFeature.class, object.getADEOfReliefFeature(),
                GenericADEOfReliefFeature::of, reader, substitutionGroups))
            super.buildADEProperty(object, name, reader);
    }

    @Override
    public Element createElement(ReliefFeature object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(CityGMLSerializerHelper.getReliefNamespace(namespaces), "ReliefFeature");
    }

    @Override
    public void writeChildElements(ReliefFeature object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);
        String reliefNamespace = CityGMLSerializerHelper.getReliefNamespace(namespaces);
        boolean isCityGML3 = CityGMLConstants.CITYGML_3_0_RELIEF_NAMESPACE.equals(reliefNamespace);

        writer.writeElement(Element.of(reliefNamespace, "lod").addTextContent(TextContent.ofInteger(object.getLod())));

        for (AbstractReliefComponentProperty property : object.getReliefComponents())
            writer.writeElementUsingSerializer(Element.of(reliefNamespace, "reliefComponent"), property, AbstractReliefComponentPropertyAdapter.class, namespaces);

        for (ADEOfReliefFeature container : object.getADEOfReliefFeature())
            ADESerializerHelper.writeADEContainer(isCityGML3 ? Element.of(reliefNamespace, "adeOfReliefFeature") : null, container, namespaces, writer);
    }
}