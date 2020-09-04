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

package org.citygml4j.xml.module.citygml;

import org.citygml4j.model.CityGMLVersion;
import org.citygml4j.util.CityGMLConstants;

public class CityObjectGroupModule extends CityGMLModule {
    public static final CityObjectGroupModule v3_0;
    public static final CityObjectGroupModule v2_0;
    public static final CityObjectGroupModule v1_0;

    private CityObjectGroupModule(String namespaceURI, String namespacePrefix, String schemaLocation, CityGMLVersion version) {
        super(namespaceURI, namespacePrefix, schemaLocation, version);
    }

    static {
        v3_0 = new CityObjectGroupModule(
                CityGMLConstants.CITYGML_3_0_CITYOBJECTGROUP_NAMESPACE,
                "grp",
                "http://schemas.opengis.net/citygml/cityobjectgroup/3.0/cityObjectGroup.xsd",
                CityGMLVersion.v3_0
        );

        v2_0 = new CityObjectGroupModule(
                CityGMLConstants.CITYGML_2_0_CITYOBJECTGROUP_NAMESPACE,
                "grp",
                "http://schemas.opengis.net/citygml/cityobjectgroup/2.0/cityObjectGroup.xsd",
                CityGMLVersion.v2_0
        );

        v1_0 = new CityObjectGroupModule(
                CityGMLConstants.CITYGML_1_0_CITYOBJECTGROUP_NAMESPACE,
                "grp",
                "http://schemas.opengis.net/citygml/cityobjectgroup/1.0/cityObjectGroup.xsd",
                CityGMLVersion.v1_0
        );
    }

    public static CityObjectGroupModule of(CityGMLVersion version) {
        switch (version) {
            case v2_0:
                return v2_0;
            case v1_0:
                return v1_0;
            default:
                return v3_0;
        }
    }
}