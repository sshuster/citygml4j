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

package implementing_ades.walker;

import implementing_ades.model.AbstractBuildingUnit;
import implementing_ades.model.BuildingUnit;
import implementing_ades.model.BuildingUnitPart;
import implementing_ades.model.DHWFacilities;
import implementing_ades.model.Facilities;
import implementing_ades.model.IndustrialBuilding;
import implementing_ades.model.IndustrialBuildingPart;
import implementing_ades.model.IndustrialBuildingRoofSurface;
import implementing_ades.model.LightingFacilities;
import implementing_ades.model.OtherConstruction;
import org.citygml4j.model.building.Building;
import org.citygml4j.model.building.BuildingPart;
import org.citygml4j.model.construction.AbstractConstruction;
import org.citygml4j.model.construction.RoofSurface;
import org.citygml4j.model.core.AbstractCityObject;
import org.citygml4j.model.core.AbstractFeature;
import org.citygml4j.visitor.ADEWalker;

public class TestADEWalker extends ADEWalker {

    public void visit(AbstractBuildingUnit abstractBuildingUnit) {
        walker.visit((AbstractCityObject) abstractBuildingUnit);
    }

    public void visit(BuildingUnit buildingUnit) {
        visit((AbstractBuildingUnit) buildingUnit);
    }

    public void visit(BuildingUnitPart buildingUnitPart) {
        visit((AbstractBuildingUnit) buildingUnitPart);
    }

    public void visit(DHWFacilities dhwFacilities) {
        visit((Facilities) dhwFacilities);
    }

    public void visit(Facilities facilities) {
        walker.visit((AbstractFeature) facilities);
    }

    public void visit(IndustrialBuilding building) {
        walker.visit((Building) building);
    }

    public void visit(IndustrialBuildingPart buildingPart) {
        walker.visit((BuildingPart) buildingPart);
    }

    public void visit(IndustrialBuildingRoofSurface roofSurface) {
        walker.visit((RoofSurface) roofSurface);
    }

    public void visit(LightingFacilities lightingFacilities) {
        visit((Facilities) lightingFacilities);
    }

    public void visit(OtherConstruction otherConstruction) {
        walker.visit((AbstractConstruction) otherConstruction);
    }
}
