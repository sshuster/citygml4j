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

package org.citygml4j.model.relief;

import org.citygml4j.model.common.GeometryInfo;
import org.citygml4j.model.core.AbstractSpaceBoundary;
import org.citygml4j.visitor.ObjectVisitor;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class ReliefFeature extends AbstractSpaceBoundary {
    private int lod;
    private List<AbstractReliefComponentProperty> reliefComponents;
    private List<ADEOfReliefFeature> adeOfReliefFeature;

    public ReliefFeature() {
    }

    public ReliefFeature(int lod, List<AbstractReliefComponentProperty> reliefComponents) {
        setLod(lod);
        setReliefComponents(reliefComponents);
    }

    public int getLod() {
        return lod;
    }

    public void setLod(int lod) {
        if (lod >= 0)
            this.lod = lod;
    }

    public List<AbstractReliefComponentProperty> getReliefComponents() {
        if (reliefComponents == null)
            reliefComponents = new ChildList<>(this);

        return reliefComponents;
    }

    public void setReliefComponents(List<AbstractReliefComponentProperty> reliefComponents) {
        this.reliefComponents = asChild(reliefComponents);
    }

    public List<ADEOfReliefFeature> getADEOfReliefFeature() {
        if (adeOfReliefFeature == null)
            adeOfReliefFeature = new ChildList<>(this);

        return adeOfReliefFeature;
    }

    public void setADEOfReliefFeature(List<ADEOfReliefFeature> adeOfReliefFeature) {
        this.adeOfReliefFeature = asChild(adeOfReliefFeature);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (reliefComponents != null) {
            for (AbstractReliefComponentProperty property : reliefComponents) {
                if (property.getObject() != null)
                    envelope.include(property.getObject().computeEnvelope(options));
            }
        }

        if (adeOfReliefFeature != null) {
            for (ADEOfReliefFeature container : adeOfReliefFeature)
                updateEnvelope(container, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        if (adeOfReliefFeature != null) {
            for (ADEOfReliefFeature container : adeOfReliefFeature)
                updateGeometryInfo(container, geometryInfo);
        }
    }

    @Override
    public void accept(ObjectVisitor visitor) {
        visitor.visit(this);
    }
}
