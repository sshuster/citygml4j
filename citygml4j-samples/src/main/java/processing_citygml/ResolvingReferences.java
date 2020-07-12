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

package processing_citygml;

import helpers.Logger;
import helpers.Util;
import org.citygml4j.CityGMLContext;
import org.citygml4j.model.appearance.GeometryReference;
import org.citygml4j.model.appearance.X3DMaterial;
import org.citygml4j.model.cityobjectgroup.CityObjectGroup;
import org.citygml4j.model.cityobjectgroup.Role;
import org.citygml4j.model.cityobjectgroup.RoleProperty;
import org.citygml4j.model.core.AbstractCityObject;
import org.citygml4j.model.core.CityModel;
import org.citygml4j.util.CityGMLConstants;
import org.citygml4j.util.reference.ReferenceResolver;
import org.citygml4j.xml.reader.CityGMLInputFactory;
import org.citygml4j.xml.reader.CityGMLReader;
import org.xmlobjects.gml.model.geometry.AbstractGeometry;

import java.nio.file.Path;

public class ResolvingReferences {

    public static void main(String[] args) throws Exception {
        Logger log = Logger.start(ResolvingReferences.class);

        CityGMLContext context = CityGMLContext.newInstance();

        CityGMLInputFactory in = context.createCityGMLInputFactory();

        Path file = Util.SAMPLE_DATA_DIR.resolve("lod3_railway_v3.gml");
        log.print("Reading the file " + file + " into main memory");

        CityModel cityModel;
        try (CityGMLReader reader = in.createCityGMLReader(file)) {
            cityModel = (CityModel) reader.next();
        }

        ReferenceResolver resolver = ReferenceResolver.newInstance();

        log.print("Retrieving the CityObjectGroup feature with gml:id UUID_f488e8ce-b953-4b35-a3fe-a394fb203868");
        CityObjectGroup group = resolver.resolveReference("UUID_f488e8ce-b953-4b35-a3fe-a394fb203868", cityModel, CityObjectGroup.class);

        log.print("Step 1: Iterating through the group members and resolving the references one by one");
        for (RoleProperty property : group.getGroupMembers()) {
            Role role = property.getObject();

            if (role.getGroupMember() != null && role.getGroupMember().getHref() != null) {
                log.print("Resolving the group member given by the XLink " + role.getGroupMember().getHref());

                AbstractCityObject member = resolver.resolveReference(role.getGroupMember().getHref(), cityModel, AbstractCityObject.class);
                log.print("- The referenced group member is a " + member.getClass().getSimpleName() + " with gml:id " + member.getId());
            }
        }

        log.print("Step 2: Resolving all references in the dataset in one run");
        resolver.resolveReferences(cityModel);

        log.print("Retrieving the X3DMaterial feature with gml:id UUID_2e70b948-c8db-4bd2-a99e-1d99a352cabc");
        X3DMaterial material = resolver.resolveReference("UUID_2e70b948-c8db-4bd2-a99e-1d99a352cabc", cityModel, X3DMaterial.class);

        log.print("Iterating through the targets of the X3DMaterial and printing the referenced surface geometries");
        for (GeometryReference target : material.getTargets()) {
            AbstractGeometry geometry = target.getLocalProperties().get(CityGMLConstants.REFERENCED_OBJECT, AbstractGeometry.class);
            log.print("- Found " + geometry.getClass().getSimpleName() + " with gml:id " + geometry.getId());
        }

        log.finish();
    }
}