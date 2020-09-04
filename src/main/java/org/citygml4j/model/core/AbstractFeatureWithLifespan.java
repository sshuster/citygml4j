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

package org.citygml4j.model.core;

import org.citygml4j.model.common.GeometryInfo;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.time.OffsetDateTime;
import java.util.List;

public abstract class AbstractFeatureWithLifespan extends AbstractFeature {
    private OffsetDateTime creationDate;
    private OffsetDateTime terminationDate;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;
    private List<ADEOfAbstractFeatureWithLifespan> adeOfAbstractFeatureWithLifespan;

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public OffsetDateTime getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(OffsetDateTime terminationDate) {
        this.terminationDate = terminationDate;
    }

    public OffsetDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(OffsetDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public OffsetDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(OffsetDateTime validTo) {
        this.validTo = validTo;
    }

    public List<ADEOfAbstractFeatureWithLifespan> getADEOfAbstractFeatureWithLifespan() {
        if (adeOfAbstractFeatureWithLifespan == null)
            adeOfAbstractFeatureWithLifespan = new ChildList<>(this);

        return adeOfAbstractFeatureWithLifespan;
    }

    public void setADEOfAbstractFeatureWithLifespan(List<ADEOfAbstractFeatureWithLifespan> adeOfAbstractFeatureWithLifespan) {
        this.adeOfAbstractFeatureWithLifespan = asChild(adeOfAbstractFeatureWithLifespan);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (adeOfAbstractFeatureWithLifespan != null) {
            for (ADEOfAbstractFeatureWithLifespan container : adeOfAbstractFeatureWithLifespan)
                updateEnvelope(container, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        if (adeOfAbstractFeatureWithLifespan != null) {
            for (ADEOfAbstractFeatureWithLifespan container : adeOfAbstractFeatureWithLifespan)
                updateGeometryInfo(container, geometryInfo);
        }
    }
}
