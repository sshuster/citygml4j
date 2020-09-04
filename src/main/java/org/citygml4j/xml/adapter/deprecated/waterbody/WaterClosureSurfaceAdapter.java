/*
 * citygml4j - The Open Source Java API for CityGML
 * https://github.com/citygml4j
 *
 * Copyright 2013-2020 Claus Nagel <claus.nagel@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.citygml4j.xml.adapter.deprecated.waterbody;

import org.citygml4j.model.ade.generic.GenericADEOfClosureSurface;
import org.citygml4j.model.core.ADEOfClosureSurface;
import org.citygml4j.model.core.ClosureSurface;
import org.citygml4j.util.CityGMLConstants;
import org.citygml4j.xml.adapter.CityGMLBuilderHelper;
import org.citygml4j.xml.adapter.CityGMLSerializerHelper;
import org.citygml4j.xml.adapter.ade.ADEBuilderHelper;
import org.citygml4j.xml.adapter.ade.ADESerializerHelper;
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

import javax.xml.namespace.QName;

@XMLElements({
        @XMLElement(name = "WaterClosureSurface", namespaceURI = CityGMLConstants.CITYGML_2_0_WATERBODY_NAMESPACE),
        @XMLElement(name = "WaterClosureSurface", namespaceURI = CityGMLConstants.CITYGML_1_0_WATERBODY_NAMESPACE)
})
public class WaterClosureSurfaceAdapter extends AbstractWaterBoundarySurfaceAdapter<ClosureSurface> {
    private final QName[] substitutionGroups = new QName[]{
            new QName(CityGMLConstants.CITYGML_2_0_WATERBODY_NAMESPACE, "_GenericApplicationPropertyOfWaterClosureSurface"),
            new QName(CityGMLConstants.CITYGML_1_0_WATERBODY_NAMESPACE, "_GenericApplicationPropertyOfWaterClosureSurface")
    };

    @Override
    public ClosureSurface createObject(QName name, Object parent) throws ObjectBuildException {
        return new ClosureSurface();
    }

    @Override
    public void buildChildObject(ClosureSurface object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI()))
            buildADEProperty(object, name, reader);
        else
            super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public void buildADEProperty(ClosureSurface object, QName name, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (!ADEBuilderHelper.addADEContainer(name, ADEOfClosureSurface.class, object.getADEOfClosureSurface(),
                GenericADEOfClosureSurface::of, reader, substitutionGroups))
            super.buildADEProperty(object, name, reader);
    }

    @Override
    public Element createElement(ClosureSurface object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(CityGMLSerializerHelper.getWaterBodyNamespace(namespaces), "WaterClosureSurface");
    }

    @Override
    public void writeChildElements(ClosureSurface object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);

        for (ADEOfClosureSurface container : object.getADEOfClosureSurface())
            ADESerializerHelper.writeADEProperty(container, namespaces, writer);
    }
}
