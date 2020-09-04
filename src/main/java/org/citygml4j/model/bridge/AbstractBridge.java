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

package org.citygml4j.model.bridge;

import org.citygml4j.model.common.GeometryInfo;
import org.citygml4j.model.construction.AbstractConstruction;
import org.citygml4j.model.construction.RelationToConstruction;
import org.citygml4j.model.core.AddressProperty;
import org.citygml4j.model.core.StandardObjectClassifier;
import org.citygml4j.model.deprecated.bridge.DeprecatedPropertiesOfAbstractBridge;
import org.xmlobjects.gml.model.basictypes.Code;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public abstract class AbstractBridge extends AbstractConstruction implements StandardObjectClassifier {
    private Code classifier;
    private List<Code> functions;
    private List<Code> usages;
    private Boolean isMovable;
    private List<BridgeConstructiveElementMember> bridgeConstructiveElements;
    private List<BridgeInstallationMember> bridgeInstallations;
    private List<BridgeRoomMember> bridgeRooms;
    private List<BridgeFurnitureMember> bridgeFurniture;
    private List<AddressProperty> addresses;
    private List<ADEOfAbstractBridge> adeOfAbstractBridge;

    @Override
    public Code getClassifier() {
        return classifier;
    }

    @Override
    public void setClassifier(Code classifier) {
        this.classifier = asChild(classifier);
    }

    @Override
    public List<Code> getFunctions() {
        if (functions == null)
            functions = new ChildList<>(this);

        return functions;
    }

    @Override
    public void setFunctions(List<Code> functions) {
        this.functions = asChild(functions);
    }

    @Override
    public List<Code> getUsages() {
        if (usages == null)
            usages = new ChildList<>(this);

        return usages;
    }

    @Override
    public void setUsages(List<Code> usages) {
        this.usages = asChild(usages);
    }

    public Boolean getIsMovable() {
        return isMovable != null ? isMovable : false;
    }

    public boolean isSetIsMovable() {
        return isMovable != null;
    }

    public void setIsMovable(Boolean isMovable) {
        this.isMovable = isMovable;
    }

    public List<BridgeConstructiveElementMember> getBridgeConstructiveElements() {
        if (bridgeConstructiveElements == null)
            bridgeConstructiveElements = new ChildList<>(this);

        return bridgeConstructiveElements;
    }

    public void setBridgeConstructiveElements(List<BridgeConstructiveElementMember> bridgeConstructiveElements) {
        this.bridgeConstructiveElements = asChild(bridgeConstructiveElements);
    }

    public List<BridgeInstallationMember> getBridgeInstallations() {
        if (bridgeInstallations == null)
            bridgeInstallations = new ChildList<>(this);

        return bridgeInstallations;
    }

    public void setBridgeInstallations(List<BridgeInstallationMember> bridgeInstallations) {
        this.bridgeInstallations = asChild(bridgeInstallations);
    }

    public List<BridgeRoomMember> getBridgeRooms() {
        if (bridgeRooms == null)
            bridgeRooms = new ChildList<>(this);

        return bridgeRooms;
    }

    public void setBridgeRooms(List<BridgeRoomMember> bridgeRooms) {
        this.bridgeRooms = asChild(bridgeRooms);
    }

    public List<BridgeFurnitureMember> getBridgeFurniture() {
        if (bridgeFurniture == null)
            bridgeFurniture = new ChildList<>(this);

        return bridgeFurniture;
    }

    public void setBridgeFurniture(List<BridgeFurnitureMember> bridgeFurniture) {
        this.bridgeFurniture = asChild(bridgeFurniture);
    }

    public List<AddressProperty> getAddresses() {
        if (addresses == null)
            addresses = new ChildList<>(this);

        return addresses;
    }

    public void setAddresses(List<AddressProperty> addresses) {
        this.addresses = asChild(addresses);
    }

    @Override
    public DeprecatedPropertiesOfAbstractBridge getDeprecatedProperties() {
        return (DeprecatedPropertiesOfAbstractBridge) super.getDeprecatedProperties();
    }

    @Override
    protected DeprecatedPropertiesOfAbstractBridge createDeprecatedProperties() {
        return new DeprecatedPropertiesOfAbstractBridge();
    }

    public List<ADEOfAbstractBridge> getADEOfAbstractBridge() {
        if (adeOfAbstractBridge == null)
            adeOfAbstractBridge = new ChildList<>(this);

        return adeOfAbstractBridge;
    }

    public void setADEOfAbstractBridge(List<ADEOfAbstractBridge> adeOfAbstractBridge) {
        this.adeOfAbstractBridge = asChild(adeOfAbstractBridge);
    }

    @Override
    protected void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
        super.updateEnvelope(envelope, options);

        if (bridgeConstructiveElements != null) {
            for (BridgeConstructiveElementMember member : bridgeConstructiveElements) {
                if (member.getObject() != null)
                    envelope.include(member.getObject().computeEnvelope(options));
            }
        }

        if (bridgeInstallations != null) {
            for (BridgeInstallationMember member : bridgeInstallations) {
                if (member.getObject() != null && member.getObject().getRelationToConstruction() != RelationToConstruction.INSIDE)
                    envelope.include(member.getObject().computeEnvelope(options));
            }
        }

        if (hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAbstractBridge properties = getDeprecatedProperties();

            for (BridgePartProperty property : properties.getConsistsOfBridgeParts()) {
                if (property.getObject() != null)
                    envelope.include(property.getObject().computeEnvelope(options));
            }

            if (properties.getLod1MultiSurface() != null && properties.getLod1MultiSurface().getObject() != null)
                envelope.include(properties.getLod1MultiSurface().getObject().computeEnvelope());

            if (properties.getLod4MultiCurve() != null && properties.getLod4MultiCurve().getObject() != null)
                envelope.include(properties.getLod4MultiCurve().getObject().computeEnvelope());

            if (properties.getLod4MultiSurface() != null && properties.getLod4MultiSurface().getObject() != null)
                envelope.include(properties.getLod4MultiSurface().getObject().computeEnvelope());

            if (properties.getLod4Solid() != null && properties.getLod4Solid().getObject() != null)
                envelope.include(properties.getLod4Solid().getObject().computeEnvelope());
        }

        if (adeOfAbstractBridge != null) {
            for (ADEOfAbstractBridge container : adeOfAbstractBridge)
                updateEnvelope(container, envelope, options);
        }
    }

    @Override
    protected void updateGeometryInfo(GeometryInfo geometryInfo) {
        super.updateGeometryInfo(geometryInfo);

        if (hasDeprecatedProperties()) {
            DeprecatedPropertiesOfAbstractBridge properties = getDeprecatedProperties();

            geometryInfo.addGeometry(1, properties.getLod1MultiSurface());
            geometryInfo.addGeometry(4, properties.getLod4MultiCurve());
            geometryInfo.addGeometry(4, properties.getLod4MultiSurface());
            geometryInfo.addGeometry(4, properties.getLod4Solid());
        }

        if (adeOfAbstractBridge != null) {
            for (ADEOfAbstractBridge container : adeOfAbstractBridge)
                updateGeometryInfo(container, geometryInfo);
        }
    }
}
