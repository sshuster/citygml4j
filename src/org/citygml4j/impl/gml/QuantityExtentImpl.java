package org.citygml4j.impl.gml;

import java.util.List;

import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.model.gml.DoubleOrNull;
import org.citygml4j.model.gml.GMLClass;
import org.citygml4j.model.gml.QuantityExtent;

public class QuantityExtentImpl extends MeasureOrNullListImpl implements QuantityExtent {

	@Override
	public void addDoubleOrNull(DoubleOrNull doubleOrNull) {
		if (getDoubleOrNull().size() < 2)
			super.addDoubleOrNull(doubleOrNull);
	}

	@Override
	public void setDoubleOrNull(List<DoubleOrNull> doubleOrNull) {
		if (doubleOrNull != null && doubleOrNull.size() >= 2)
			super.setDoubleOrNull(doubleOrNull.subList(0, 2));
	}

	@Override
	public GMLClass getGMLClass() {
		return GMLClass.QUANTITYEXTENT;
	}

	@Override
	public Object copy(CopyBuilder copyBuilder) {
		return copyTo(new QuantityExtentImpl(), copyBuilder);
	}

	@Override
	public Object copyTo(Object target, CopyBuilder copyBuilder) {
		QuantityExtent copy = (target == null) ? new QuantityExtentImpl() : (QuantityExtent)target;
		return super.copyTo(copy, copyBuilder);
	}

}