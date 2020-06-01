package org.citygml4j.xml.adapter.appearance;

import org.citygml4j.model.ade.generic.GenericADEOfGeoreferencedTexture;
import org.citygml4j.model.appearance.ADEOfGeoreferencedTexture;
import org.citygml4j.model.appearance.GeometryReference;
import org.citygml4j.model.appearance.GeoreferencedTexture;
import org.citygml4j.model.core.TransformationMatrix2x2;
import org.citygml4j.util.CityGMLConstants;
import org.citygml4j.xml.adapter.CityGMLBuilderHelper;
import org.citygml4j.xml.adapter.CityGMLSerializerHelper;
import org.citygml4j.xml.adapter.ade.ADEBuilderHelper;
import org.citygml4j.xml.adapter.ade.ADESerializerHelper;
import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.annotation.XMLElements;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.gml.adapter.geometry.primitives.PointPropertyAdapter;
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
        @XMLElement(name = "GeoreferencedTexture", namespaceURI = CityGMLConstants.CITYGML_3_0_APPEARANCE_NAMESPACE),
        @XMLElement(name = "GeoreferencedTexture", namespaceURI = CityGMLConstants.CITYGML_2_0_APPEARANCE_NAMESPACE),
        @XMLElement(name = "GeoreferencedTexture", namespaceURI = CityGMLConstants.CITYGML_1_0_APPEARANCE_NAMESPACE)
})
public class GeoreferencedTextureAdapter extends AbstractTextureAdapter<GeoreferencedTexture> {
    private final QName[] substitutionGroups = new QName[]{
            new QName(CityGMLConstants.CITYGML_2_0_APPEARANCE_NAMESPACE, "_GenericApplicationPropertyOfGeoreferencedTexture"),
            new QName(CityGMLConstants.CITYGML_1_0_APPEARANCE_NAMESPACE, "_GenericApplicationPropertyOfGeoreferencedTexture")
    };

    @Override
    public GeoreferencedTexture createObject(QName name) throws ObjectBuildException {
        return new GeoreferencedTexture();
    }

    @Override
    public void buildChildObject(GeoreferencedTexture object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLBuilderHelper.isAppearanceNamespace(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "preferWorldFile":
                    reader.getTextContent().ifBoolean(object::setPreferWorldFile);
                    return;
                case "referencePoint":
                    object.setReferencePoint(reader.getObjectUsingBuilder(PointPropertyAdapter.class));
                    return;
                case "orientation":
                    reader.getTextContent().ifDoubleList(v -> object.setOrientation(TransformationMatrix2x2.ofRowMajorList(v)));
                    return;
                case "target":
                    reader.getTextContent().ifPresent(v -> object.getTargets().add(new GeometryReference(v)));
                    return;
                case "adeOfGeoreferencedTexture":
                    ADEBuilderHelper.addADEContainer(ADEOfGeoreferencedTexture.class, object.getADEOfGeoreferencedTexture(), GenericADEOfGeoreferencedTexture::of, reader);
                    return;
            }
        } else if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI())) {
            buildADEProperty(object, name, reader);
            return;
        }

        super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public void buildADEProperty(GeoreferencedTexture object, QName name, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (!ADEBuilderHelper.addADEContainer(name, ADEOfGeoreferencedTexture.class, object.getADEOfGeoreferencedTexture(),
                GenericADEOfGeoreferencedTexture::of, reader, substitutionGroups))
            super.buildADEProperty(object, name, reader);
    }

    @Override
    public Element createElement(GeoreferencedTexture object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(CityGMLSerializerHelper.getAppearanceNamespace(namespaces), "GeoreferencedTexture");
    }

    @Override
    public void writeChildElements(GeoreferencedTexture object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);
        String appearanceNamespace = CityGMLSerializerHelper.getAppearanceNamespace(namespaces);
        boolean isCityGML3 = CityGMLConstants.CITYGML_3_0_APPEARANCE_NAMESPACE.equalsIgnoreCase(appearanceNamespace);

        if (object.isSetPreferWorldFile())
            writer.writeElement(Element.of(appearanceNamespace, "preferWorldFile").addTextContent(TextContent.ofBoolean(object.getPreferWorldFile())));

        if (object.getReferencePoint() != null)
            writer.writeElementUsingSerializer(Element.of(appearanceNamespace, "referencePoint"), object.getReferencePoint(), PointPropertyAdapter.class, namespaces);

        if (object.getOrientation() != null)
            writer.writeElement(Element.of(appearanceNamespace, "orientation").addTextContent(TextContent.ofDoubleList(object.getOrientation().toRowMajorList())));

        for (GeometryReference target : object.getTargets()) {
            if (target != null)
                writer.writeElement(Element.of(appearanceNamespace, "target").addTextContent(target.getURI()));
        }

        for (ADEOfGeoreferencedTexture container : object.getADEOfGeoreferencedTexture())
            ADESerializerHelper.writeADEContainer(isCityGML3 ? Element.of(appearanceNamespace, "adeOfGeoreferencedTexture") : null, container, namespaces, writer);
    }
}
