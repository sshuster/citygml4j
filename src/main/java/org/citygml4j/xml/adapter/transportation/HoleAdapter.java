package org.citygml4j.xml.adapter.transportation;

import org.citygml4j.model.ade.generic.GenericADEOfHole;
import org.citygml4j.model.transportation.ADEOfHole;
import org.citygml4j.model.transportation.Hole;
import org.citygml4j.util.CityGMLConstants;
import org.citygml4j.xml.adapter.ade.ADEBuilderHelper;
import org.citygml4j.xml.adapter.ade.ADESerializerHelper;
import org.citygml4j.xml.adapter.core.AbstractUnoccupiedSpaceAdapter;
import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.gml.adapter.basictypes.CodeAdapter;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "Hole", namespaceURI = CityGMLConstants.CITYGML_3_0_TRANSPORTATION_NAMESPACE)
public class HoleAdapter extends AbstractUnoccupiedSpaceAdapter<Hole> {

    @Override
    public Hole createObject(QName name) throws ObjectBuildException {
        return new Hole();
    }

    @Override
    public void buildChildObject(Hole object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLConstants.CITYGML_3_0_TRANSPORTATION_NAMESPACE.equals(name.getNamespaceURI())) {
            switch (name.getLocalPart()) {
                case "class":
                    object.setClassifier(reader.getObjectUsingBuilder(CodeAdapter.class));
                    return;
                case "adeOfHole":
                    ADEBuilderHelper.addADEContainer(ADEOfHole.class, object.getADEOfHole(), GenericADEOfHole::of, reader);
                    return;
            }
        }

        super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public Element createElement(Hole object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(CityGMLConstants.CITYGML_3_0_TRANSPORTATION_NAMESPACE, "Hole");
    }

    @Override
    public void writeChildElements(Hole object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);

        if (object.getClassifier() != null)
            writer.writeElementUsingSerializer(Element.of(CityGMLConstants.CITYGML_3_0_TRANSPORTATION_NAMESPACE, "class"), object.getClassifier(), CodeAdapter.class, namespaces);

        for (ADEOfHole container : object.getADEOfHole())
            ADESerializerHelper.writeADEContainer(Element.of(CityGMLConstants.CITYGML_3_0_TRANSPORTATION_NAMESPACE, "adeOfHole"), container, namespaces, writer);
    }
}
