package org.citygml4j.adapter.xml.core;

import org.citygml4j.model.core.AbstractLogicalSpaceProperty;
import org.xmlobjects.gml.adapter.feature.AbstractFeaturePropertyAdapter;

import javax.xml.namespace.QName;

public class AbstractLogicalSpacePropertyAdapter extends AbstractFeaturePropertyAdapter<AbstractLogicalSpaceProperty> {

    @Override
    public AbstractLogicalSpaceProperty createObject(QName name) {
        return new AbstractLogicalSpaceProperty();
    }
}