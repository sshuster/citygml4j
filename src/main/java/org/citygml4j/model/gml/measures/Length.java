/*
 * citygml4j - The Open Source Java API for CityGML
 * https://github.com/citygml4j
 *
 * Copyright 2013-2019 Claus Nagel <claus.nagel@gmail.com>
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
package org.citygml4j.model.gml.measures;

import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.model.gml.GMLClass;
import org.citygml4j.model.gml.basicTypes.Measure;

public class Length extends Measure {
	
	public Length() {
	}
	
	public Length(double value) {
		super(value);
	}
	
	@Override
	public GMLClass getGMLClass() {
		return GMLClass.LENGTH;
	}

	@Override
	public Object copy(CopyBuilder copyBuilder) {
		return copyTo(new Length(), copyBuilder);
	}

	@Override
	public Object copyTo(Object target, CopyBuilder copyBuilder) {
		Length copy = (target == null) ? new Length() : (Length)target;
		return super.copyTo(copy, copyBuilder);
	}
	
}
