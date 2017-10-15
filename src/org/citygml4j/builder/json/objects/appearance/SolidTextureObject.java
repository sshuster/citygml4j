package org.citygml4j.builder.json.objects.appearance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolidTextureObject extends AbstractTextureObject {
	public static final List<List<List<Integer>>> NULL_VALUE = Collections.singletonList(SurfaceCollectionTextureObject.NULL_VALUE);
	private List<List<List<List<Integer>>>> values;
	
	public SolidTextureObject() {
	}
	
	public SolidTextureObject(String theme) {
		super(theme);
	}

	public boolean isSetValues() {
		return values != null;
	}

	public void addValue(List<List<List<Integer>>> value) {
		if (values == null)
			values = new ArrayList<>();

		values.add(value);
	}
	
	@Override
	public void addNullValue() {
		addValue(NULL_VALUE);
	}

	public List<List<List<List<Integer>>>> getValues() {
		return values;
	}

	public void setValues(List<List<List<List<Integer>>>> values) {
		this.values = values;
	}
	
	@Override
	public int getNumValues() {
		return values != null ? values.size() : 0;
	}

}
