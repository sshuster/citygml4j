package org.citygml4j.adapter.xml.building;

import org.citygml4j.model.building.BuildingRoomProperty;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.gml.adapter.feature.AbstractFeaturePropertyAdapter;

import javax.xml.namespace.QName;

public class BuildingRoomPropertyAdapter extends AbstractFeaturePropertyAdapter<BuildingRoomProperty> {

    @Override
    public BuildingRoomProperty createObject(QName name) throws ObjectBuildException {
        return new BuildingRoomProperty();
    }
}