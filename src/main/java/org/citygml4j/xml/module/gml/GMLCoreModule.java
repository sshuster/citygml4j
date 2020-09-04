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

package org.citygml4j.xml.module.gml;

import org.xmlobjects.gml.util.GMLConstants;

public class GMLCoreModule extends GMLModule {
    public static final GMLCoreModule v3_2;
    public static final GMLCoreModule v3_1;

    static {
        v3_2 = new GMLCoreModule(
                GMLConstants.GML_3_2_NAMESPACE,
                "gml",
                "http://schemas.opengis.net/gml/3.2.1/gml.xsd"
        );

        v3_1 = new GMLCoreModule(
                GMLConstants.GML_3_1_NAMESPACE,
                "gml",
                "http://schemas.opengis.net/gml/3.1.1/base/gml.xsd"
        );
    }

    private GMLCoreModule(String namespaceURI, String namespacePrefix, String schemaLocation) {
        super(namespaceURI, namespacePrefix, schemaLocation);
    }
}
