package org.citygml4j.adapter.xml.transportation;

import org.citygml4j.model.transportation.AuxiliaryTrafficSpaceProperty;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.gml.adapter.feature.AbstractFeaturePropertyAdapter;

import javax.xml.namespace.QName;

public class AuxiliaryTrafficSpacePropertyAdapter extends AbstractFeaturePropertyAdapter<AuxiliaryTrafficSpaceProperty> {

    @Override
    public AuxiliaryTrafficSpaceProperty createObject(QName name) throws ObjectBuildException {
        return new AuxiliaryTrafficSpaceProperty();
    }
}
