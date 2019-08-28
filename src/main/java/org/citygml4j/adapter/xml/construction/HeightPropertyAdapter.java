package org.citygml4j.adapter.xml.construction;

import org.citygml4j.model.construction.HeightProperty;
import org.xmlobjects.gml.adapter.base.AbstractInlinePropertyAdapter;

import javax.xml.namespace.QName;

public class HeightPropertyAdapter extends AbstractInlinePropertyAdapter<HeightProperty> {

    @Override
    public HeightProperty createObject(QName name) {
        return new HeightProperty();
    }
}
