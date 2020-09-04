/*
 * citygml4j - The Open Source Java API for CityGML
 * https://github.com/citygml4j
 *
 * Copyright 2013-2020 Claus Nagel <claus.nagel@gmail.com>
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

package org.citygml4j.model.relief;

import org.citygml4j.model.common.GeometryInfo;
import org.citygml4j.visitor.ObjectVisitor;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class RasterRelief extends AbstractReliefComponent {
    private GridProperty grid;
    private List<ADEOfRasterRelief> adeOfRasterRelief;

    public RasterRelief() {
    }

    public RasterRelief(int lod, GridProperty grid) {
        super(lod);
        setGrid(grid);
    }

    public GridProperty getGrid() {
        return grid;
    }

    public void setGrid(GridProperty grid) {
        this.grid = asChild(grid);
    }

    public List<ADEOfRasterRelief> getADEOfRasterRelief() {
        if (adeOfRasterRelief == null)
            adeOfRasterRelief = new ChildList<>(this);

        return adeOfRasterRelief;
    }

    public void setADEOfRasterRelief(List<ADEOfRasterRelief> adeOfRasterRelief) {
        this.adeOfRasterRelief = asChild(adeOfRasterRelief);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (grid != null && grid.getObject() != null)
            envelope.include(grid.getObject().computeEnvelope(options));

        if (adeOfRasterRelief != null) {
            for (ADEOfRasterRelief container : adeOfRasterRelief)
                updateEnvelope(container, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        if (adeOfRasterRelief != null) {
            for (ADEOfRasterRelief container : adeOfRasterRelief)
                updateGeometryInfo(container, geometryInfo);
        }
    }

    @Override
    public void accept(ObjectVisitor visitor) {
        visitor.visit(this);
    }
}
