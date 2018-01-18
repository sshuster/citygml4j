/*
 * citygml4j - The Open Source Java API for CityGML
 * https://github.com/citygml4j
 * 
 * Copyright 2013-2017 Claus Nagel <claus.nagel@gmail.com>
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
package org.citygml4j.binding.cityjson.geometry;

import java.util.ArrayList;
import java.util.List;

public class MultiPointType extends AbstractGeometryType {
	private final GeometryTypeName type = GeometryTypeName.MULTI_POINT;
	private List<Integer> boundaries = new ArrayList<>();
	
	@Override
	public GeometryTypeName getType() {
		return type;
	}
	
	public void addPoint(int point) {
		boundaries.add(point);
	}
	
	public void addPoints(List<Integer> points) {
		boundaries.addAll(points);
	}

	public List<Integer> getPoints() {
		return boundaries;
	}

	public void setPoints(List<Integer> points) {
		if (points != null)
			boundaries = points;
	}
	
	public void unsetPoints() {
		boundaries.clear();
	}
	
}