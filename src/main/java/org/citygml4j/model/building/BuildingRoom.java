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
import org.citygml4j.model.core.AbstractSpaceBoundary;
import org.citygml4j.model.core.AbstractUnoccupiedSpace;
import org.citygml4j.model.core.ClosureSurface;
import org.citygml4j.model.core.StandardObjectClassifier;
import org.citygml4j.model.deprecated.building.DeprecatedPropertiesOfBuildingRoom;
import org.citygml4j.model.generics.GenericThematicSurface;
import org.citygml4j.visitor.ObjectVisitor;
import org.xmlobjects.gml.model.basictypes.Code;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class BuildingRoom extends AbstractUnoccupiedSpace implements StandardObjectClassifier {
    private Code classifier;
    private List<Code> functions;
    private List<Code> usages;
    private List<RoomHeightProperty> roomHeights;
    private List<BuildingFurnitureProperty> buildingFurniture;
    private List<BuildingInstallationProperty> buildingInstallations;
    private List<ADEOfBuildingRoom> adeOfBuildingRoom;

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

    public List<RoomHeightProperty> getRoomHeights() {
        if (roomHeights == null)
            roomHeights = new ChildList<>(this);

        return roomHeights;
    }

    public void setRoomHeights(List<RoomHeightProperty> roomHeights) {
        this.roomHeights = asChild(roomHeights);
    }

    public List<BuildingFurnitureProperty> getBuildingFurniture() {
        if (buildingFurniture == null)
            buildingFurniture = new ChildList<>(this);

        return buildingFurniture;
    }

    public void setBuildingFurniture(List<BuildingFurnitureProperty> buildingFurniture) {
        this.buildingFurniture = asChild(buildingFurniture);
    }

    public List<BuildingInstallationProperty> getBuildingInstallations() {
        if (buildingInstallations == null)
            buildingInstallations = new ChildList<>(this);

        return buildingInstallations;
    }

    public void setBuildingInstallations(List<BuildingInstallationProperty> buildingInstallations) {
        this.buildingInstallations = asChild(buildingInstallations);
    }

    @Override
    public DeprecatedPropertiesOfBuildingRoom getDeprecatedProperties() {
        return (DeprecatedPropertiesOfBuildingRoom) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedPropertiesOfBuildingRoom createDeprecatedProperties() {
        return new DeprecatedPropertiesOfBuildingRoom();
    }

    public List<ADEOfBuildingRoom> getADEOfBuildingRoom() {
        if (adeOfBuildingRoom == null)
            adeOfBuildingRoom = new ChildList<>(this);

        return adeOfBuildingRoom;
    }

    public void setADEOfBuildingRoom(List<ADEOfBuildingRoom> adeOfBuildingRoom) {
        this.adeOfBuildingRoom = asChild(adeOfBuildingRoom);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (hasDeprecatedProperties()) {
            DeprecatedPropertiesOfBuildingRoom properties = getDeprecatedProperties();

            if (properties.getLod4Solid() != null && properties.getLod4Solid().getObject() != null)
                envelope.include(properties.getLod4Solid().getObject().computeEnvelope());

            if (properties.getLod4MultiSurface() != null && properties.getLod4MultiSurface().getObject() != null)
                envelope.include(properties.getLod4MultiSurface().getObject().computeEnvelope());
        }

        if (adeOfBuildingRoom != null) {
            for (ADEOfBuildingRoom container : adeOfBuildingRoom)
                updateEnvelope(container, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        if (hasDeprecatedProperties()) {
            DeprecatedPropertiesOfBuildingRoom properties = getDeprecatedProperties();

            geometryInfo.addGeometry(4, properties.getLod4Solid());
            geometryInfo.addGeometry(4, properties.getLod4MultiSurface());
        }

        if (adeOfBuildingRoom != null) {
            for (ADEOfBuildingRoom container : adeOfBuildingRoom)
                updateGeometryInfo(container, geometryInfo);
        }
    }

    @Override
    public void accept(ObjectVisitor visitor) {
        visitor.visit(this);
    }
}
