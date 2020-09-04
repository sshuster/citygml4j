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

package org.citygml4j.model.construction;

import org.citygml4j.model.common.GeometryInfo;
import org.citygml4j.model.core.AbstractThematicSurface;
import org.citygml4j.model.deprecated.construction.DeprecatedPropertiesOfAbstractFillingSurface;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public abstract class AbstractFillingSurface extends AbstractThematicSurface {
    private List<ADEOfAbstractFillingSurface> adeOfAbstractFillingSurface;

    @Override
    public DeprecatedPropertiesOfAbstractFillingSurface getDeprecatedProperties() {
        return (DeprecatedPropertiesOfAbstractFillingSurface) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedPropertiesOfAbstractFillingSurface createDeprecatedProperties() {
        return new DeprecatedPropertiesOfAbstractFillingSurface();
    }

    public List<ADEOfAbstractFillingSurface> getADEOfAbstractFillingSurface() {
        if (adeOfAbstractFillingSurface == null)
            adeOfAbstractFillingSurface = new ChildList<>(this);

        return adeOfAbstractFillingSurface;
    }

    public void setADEOfAbstractFillingSurface(List<ADEOfAbstractFillingSurface> adeOfAbstractFillingSurface) {
        this.adeOfAbstractFillingSurface = asChild(adeOfAbstractFillingSurface);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAbstractFillingSurface properties = getDeprecatedProperties();

            if (properties.getLod4MultiSurface() != null && properties.getLod4MultiSurface().getObject() != null)
                envelope.include(properties.getLod4MultiSurface().getObject().computeEnvelope());

            if (properties.getLod3ImplicitRepresentation() != null && properties.getLod3ImplicitRepresentation().getObject() != null)
                envelope.include(properties.getLod3ImplicitRepresentation().getObject().computeEnvelope());

            if (properties.getLod4ImplicitRepresentation() != null && properties.getLod4ImplicitRepresentation().getObject() != null)
                envelope.include(properties.getLod4ImplicitRepresentation().getObject().computeEnvelope());
        }

        if (adeOfAbstractFillingSurface != null) {
            for (ADEOfAbstractFillingSurface container : adeOfAbstractFillingSurface)
                updateEnvelope(container, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        if (hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAbstractFillingSurface properties = getDeprecatedProperties();

            geometryInfo.addGeometry(4, properties.getLod4MultiSurface());
            geometryInfo.addImplicitGeometry(3, properties.getLod3ImplicitRepresentation());
            geometryInfo.addImplicitGeometry(4, properties.getLod4ImplicitRepresentation());
        }

        if (adeOfAbstractFillingSurface != null) {
            for (ADEOfAbstractFillingSurface container : adeOfAbstractFillingSurface)
                updateGeometryInfo(container, geometryInfo);
        }
    }
}
