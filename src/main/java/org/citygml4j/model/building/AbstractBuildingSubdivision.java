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

package org.citygml4j.model.building;

import org.citygml4j.model.common.GeometryInfo;
import org.citygml4j.model.construction.AbstractConstructionSurface;
import org.citygml4j.model.construction.ElevationProperty;
import org.citygml4j.model.core.AbstractLogicalSpace;
import org.citygml4j.model.core.AbstractSpaceBoundary;
import org.citygml4j.model.core.ClosureSurface;
import org.citygml4j.model.core.StandardObjectClassifier;
import org.citygml4j.model.generics.GenericThematicSurface;
import org.xmlobjects.gml.model.base.Reference;
import org.xmlobjects.gml.model.basictypes.Code;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public abstract class AbstractBuildingSubdivision extends AbstractLogicalSpace implements StandardObjectClassifier {
    private Code classifier;
    private List<Code> functions;
    private List<Code> usages;
    private List<ElevationProperty> elevations;
    private Double sortKey;
    private List<Reference> buildingConstructiveElements;
    private List<Reference> buildingFurniture;
    private List<Reference> buildingInstallations;
    private List<Reference> buildingRooms;
    private List<ADEOfAbstractBuildingSubdivision> adeOfAbstractBuildingSubdivision;

    @Override
    public boolean isValidBoundary(AbstractSpaceBoundary boundary) {
        return boundary instanceof AbstractConstructionSurface
                || boundary instanceof ClosureSurface
                || boundary instanceof GenericThematicSurface;
    }

    @Override
    public Code getClassifier() {
        return classifier;
    }

    @Override
    public void setClassifier(Code classifier) {
        this.classifier = asChild(classifier);
    }

    @Override
    public List<Code> getFunctions() {
        if (functions == null)
            functions = new ChildList<>(this);

        return functions;
    }

    @Override
    public void setFunctions(List<Code> functions) {
        this.functions = asChild(functions);
    }

    @Override
    public List<Code> getUsages() {
        if (usages == null)
            usages = new ChildList<>(this);

        return usages;
    }

    @Override
    public void setUsages(List<Code> usages) {
        this.usages = asChild(usages);
    }

    public List<ElevationProperty> getElevations() {
        if (elevations == null)
            elevations = new ChildList<>(this);

        return elevations;
    }

    public void setElevations(List<ElevationProperty> elevations) {
        this.elevations = asChild(elevations);
    }

    public Double getSortKey() {
        return sortKey;
    }

    public void setSortKey(Double sortKey) {
        this.sortKey = sortKey;
    }

    public List<Reference> getBuildingConstructiveElements() {
        if (buildingConstructiveElements == null)
            buildingConstructiveElements = new ChildList<>(this);

        return buildingConstructiveElements;
    }

    public void setBuildingConstructiveElements(List<Reference> buildingConstructiveElements) {
        this.buildingConstructiveElements = asChild(buildingConstructiveElements);
    }

    public List<Reference> getBuildingFurniture() {
        if (buildingFurniture == null)
            buildingFurniture = new ChildList<>(this);

        return buildingFurniture;
    }

    public void setBuildingFurniture(List<Reference> buildingFurniture) {
        this.buildingFurniture = asChild(buildingFurniture);
    }

    public List<Reference> getBuildingInstallations() {
        if (buildingInstallations == null)
            buildingInstallations = new ChildList<>(this);

        return buildingInstallations;
    }

    public void setBuildingInstallations(List<Reference> buildingInstallations) {
        this.buildingInstallations = asChild(buildingInstallations);
    }

    public List<Reference> getBuildingRooms() {
        if (buildingRooms == null)
            buildingRooms = new ChildList<>(this);

        return buildingRooms;
    }

    public void setBuildingRooms(List<Reference> buildingRooms) {
        this.buildingRooms = asChild(buildingRooms);
    }

    public List<ADEOfAbstractBuildingSubdivision> getADEOfAbstractBuildingSubdivision() {
        if (adeOfAbstractBuildingSubdivision == null)
            adeOfAbstractBuildingSubdivision = new ChildList<>(this);

        return adeOfAbstractBuildingSubdivision;
    }

    public void setADEOfAbstractBuildingSubdivision(List<ADEOfAbstractBuildingSubdivision> adeOfAbstractBuildingSubdivision) {
        this.adeOfAbstractBuildingSubdivision = asChild(adeOfAbstractBuildingSubdivision);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (adeOfAbstractBuildingSubdivision != null) {
            for (ADEOfAbstractBuildingSubdivision container : adeOfAbstractBuildingSubdivision)
                updateEnvelope(container, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        if (adeOfAbstractBuildingSubdivision != null) {
            for (ADEOfAbstractBuildingSubdivision container : adeOfAbstractBuildingSubdivision)
                updateGeometryInfo(container, geometryInfo);
        }
    }
}
