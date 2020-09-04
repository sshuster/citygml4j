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

package org.citygml4j.model.dynamizer;

import org.xmlobjects.model.ChildList;

import java.util.List;

public abstract class AbstractAtomicTimeseries extends AbstractTimeseries {
    private String observationProperty;
    private String uom;
    private List<ADEOfAbstractAtomicTimeseries> adeOfAbstractAtomicTimeseries;

    public AbstractAtomicTimeseries() {
    }

    public AbstractAtomicTimeseries(String observationProperty) {
        this.observationProperty = observationProperty;
    }

    public String getObservationProperty() {
        return observationProperty;
    }

    public void setObservationProperty(String observationProperty) {
        this.observationProperty = observationProperty;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public List<ADEOfAbstractAtomicTimeseries> getADEOfAbstractAtomicTimeseries() {
        if (adeOfAbstractAtomicTimeseries == null)
            adeOfAbstractAtomicTimeseries = new ChildList<>(this);

        return adeOfAbstractAtomicTimeseries;
    }

    public void setADEOfAbstractAtomicTimeseries(List<ADEOfAbstractAtomicTimeseries> adeOfAbstractAtomicTimeseries) {
        this.adeOfAbstractAtomicTimeseries = asChild(adeOfAbstractAtomicTimeseries);
    }
}
