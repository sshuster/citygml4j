package org.citygml4j.model.gml.basicTypes;

import org.citygml4j.model.common.child.Child;
import org.citygml4j.model.common.copy.Copyable;
import org.citygml4j.model.gml.GML;


public interface IntegerOrNull extends GML, Child, Copyable {
	public Integer getInteger();
	public Null getNull();
	public boolean isSetInteger();
	public boolean isSetNull();
	
	public void setInteger(Integer integer);
	public void setNull(Null _null);
	public void unsetInteger();
	public void unsetNull();
}