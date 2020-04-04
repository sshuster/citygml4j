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

package reading_citygml;

import org.citygml4j.CityGMLContext;
import org.citygml4j.model.building.Building;
import org.citygml4j.model.core.CityModel;
import org.citygml4j.xml.reader.CityGMLInputFactory;
import org.citygml4j.xml.reader.CityGMLReadException;
import org.citygml4j.xml.reader.CityGMLReader;
import org.citygml4j.xml.transform.TransformerPipeline;
import util.Logger;
import util.Util;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.nio.file.Path;

public class ApplyXSLTWhileReading {

    public static void main(String[] args) throws Exception {
        Logger log = Logger.start(ApplyXSLTWhileReading.class);

        CityGMLContext context = CityGMLContext.newInstance();

        CityGMLInputFactory in = context.createCityGMLInputFactory();

        Path file = Util.SAMPLE_DATA_DIR.resolve("lod3_building_v2.gml");
        log.print("Reading the building from the file " + file.getFileName());

        Building building = readBuilding(in, file);
        log.print("The building has address information: " + !building.getAddresses().isEmpty());

        log.print("Reading the building once more and removing its address using an XSLT stylesheet");
        File stylesheet = Util.STYLESHEETS_DIR.resolve("AddressRemover.xsl").toFile();
        TransformerPipeline pipeline = TransformerPipeline.newInstance(new StreamSource(stylesheet));

        in.withTransformer(pipeline);

        building = readBuilding(in, file);
        log.print("The building has address information: " + !building.getAddresses().isEmpty());

        log.finish();
    }

    public static Building readBuilding(CityGMLInputFactory in, Path file) throws CityGMLReadException {
        try (CityGMLReader reader = in.createCityGMLReader(file)) {
            CityModel cityModel = (CityModel) reader.next();
            return (Building) cityModel.getCityObjectMembers().get(0).getObject();
        }
    }
}
