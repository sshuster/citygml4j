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

package org.citygml4j.model.bridge;

import org.citygml4j.model.common.GeometryInfo;
import org.citygml4j.visitor.ObjectVisitor;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class BridgePart extends AbstractBridge {
    private List<ADEOfBridgePart> adeOfBridgePart;

    public List<ADEOfBridgePart> getADEOfBridgePart() {
        if (adeOfBridgePart == null)
            adeOfBridgePart = new ChildList<>(this);

        return adeOfBridgePart;
    }

    public void setADEOfBridgePart(List<ADEOfBridgePart> adeOfBridgePart) {
        this.adeOfBridgePart = asChild(adeOfBridgePart);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (adeOfBridgePart != null) {
            for (ADEOfBridgePart container : adeOfBridgePart)
                updateEnvelope(container, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        if (adeOfBridgePart != null) {
            for (ADEOfBridgePart container : adeOfBridgePart)
                updateGeometryInfo(container, geometryInfo);
        }
    }

    @Override
    public void accept(ObjectVisitor visitor) {
        visitor.visit(this);
    }
}
