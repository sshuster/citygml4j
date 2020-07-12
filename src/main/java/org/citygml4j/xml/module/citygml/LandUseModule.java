package org.citygml4j.xml.module.citygml;

import org.citygml4j.model.CityGMLVersion;
import org.citygml4j.util.CityGMLConstants;

public class LandUseModule extends CityGMLModule {
    public static final LandUseModule v3_0;
    public static final LandUseModule v2_0;
    public static final LandUseModule v1_0;

    private LandUseModule(String namespaceURI, String namespacePrefix, String schemaLocation, CityGMLVersion version) {
        super(namespaceURI, namespacePrefix, schemaLocation, version);
    }

    static {
        v3_0 = new LandUseModule(
                CityGMLConstants.CITYGML_3_0_LANDUSE_NAMESPACE,
                "luse",
                "http://schemas.opengis.net/citygml/landuse/3.0/landUse.xsd",
                CityGMLVersion.v3_0
        );

        v2_0 = new LandUseModule(
                CityGMLConstants.CITYGML_2_0_LANDUSE_NAMESPACE,
                "luse",
                "http://schemas.opengis.net/citygml/landuse/2.0/landUse.xsd",
                CityGMLVersion.v2_0
        );

        v1_0 = new LandUseModule(
                CityGMLConstants.CITYGML_1_0_LANDUSE_NAMESPACE,
                "luse",
                "http://schemas.opengis.net/citygml/landuse/1.0/landUse.xsd",
                CityGMLVersion.v1_0
        );
    }

    public static LandUseModule of(CityGMLVersion version) {
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