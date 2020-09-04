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

package org.citygml4j.model.versioning;

import org.citygml4j.model.CityGMLObject;
import org.citygml4j.model.core.AbstractFeatureWithLifespanProperty;
import org.xmlobjects.gml.model.GMLObject;

public class Transaction extends GMLObject implements CityGMLObject {
    private TransactionValue type;
    private AbstractFeatureWithLifespanProperty oldFeature;
    private AbstractFeatureWithLifespanProperty newFeature;

    public Transaction() {
    }

    public Transaction(TransactionValue type) {
        this.type = type;
    }

    public Transaction(TransactionValue type, AbstractFeatureWithLifespanProperty oldFeature, AbstractFeatureWithLifespanProperty newFeature) {
        this.type = type;
        setOldFeature(oldFeature);
        setNewFeature(newFeature);
    }

    public TransactionValue getType() {
        return type;
    }

    public void setType(TransactionValue type) {
        this.type = type;
    }

    public AbstractFeatureWithLifespanProperty getOldFeature() {
        return oldFeature;
    }

    public void setOldFeature(AbstractFeatureWithLifespanProperty oldFeature) {
        this.oldFeature = asChild(oldFeature);
    }

    public AbstractFeatureWithLifespanProperty getNewFeature() {
        return newFeature;
    }

    public void setNewFeature(AbstractFeatureWithLifespanProperty newFeature) {
        this.newFeature = asChild(newFeature);
    }
}