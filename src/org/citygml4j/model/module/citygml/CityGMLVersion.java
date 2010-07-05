package org.citygml4j.model.module.citygml;

import java.util.ArrayList;
import java.util.List;

import org.citygml4j.model.module.AbstractModuleConfiguration;
import org.citygml4j.model.module.Module;
import org.citygml4j.model.module.gml.GMLCoreModule;
import org.citygml4j.model.module.gml.GMLModule;
import org.citygml4j.model.module.gml.XLinkModule;
import org.citygml4j.model.module.xal.AbstractXALModule;
import org.citygml4j.model.module.xal.XALCoreModule;

public class CityGMLVersion extends AbstractModuleConfiguration {
	private static final List<CityGMLVersion> instances = new ArrayList<CityGMLVersion>();

	public static final CityGMLVersion DEFAULT;
	public static final CityGMLVersion v1_0_0;
	public static final CityGMLVersion v0_4_0;

	private CityGMLVersion(Module... modules) {
		super(modules);
		instances.add(this);
	}

	static {		
		v1_0_0 = new CityGMLVersion(
				AppearanceModule.v1_0_0,
				BuildingModule.v1_0_0,
				CityFurnitureModule.v1_0_0,
				CityObjectGroupModule.v1_0_0,
				CoreModule.v1_0_0,
				GenericsModule.v1_0_0,
				LandUseModule.v1_0_0,
				ReliefModule.v1_0_0,
				TexturedSurfaceModule.v1_0_0,
				TransportationModule.v1_0_0,
				VegetationModule.v1_0_0,
				WaterBodyModule.v1_0_0,
				GMLCoreModule.v3_1_1,
				XLinkModule.v3_1_1,
				XALCoreModule.v2_0				
		);
		
		v0_4_0 = new CityGMLVersion(
				AppearanceModule.v0_4_0,
				BuildingModule.v0_4_0,
				CityFurnitureModule.v0_4_0,
				CityObjectGroupModule.v0_4_0,
				CoreModule.v0_4_0,
				GenericsModule.v0_4_0,
				LandUseModule.v0_4_0,
				ReliefModule.v0_4_0,
				TexturedSurfaceModule.v0_4_0,
				TransportationModule.v0_4_0,
				VegetationModule.v0_4_0,
				WaterBodyModule.v0_4_0,
				GMLCoreModule.v3_1_1,
				XLinkModule.v3_1_1,
				XALCoreModule.v2_0	
		);
		
		DEFAULT = v1_0_0;
	}

	public List<CityGMLModule> getCityGMLModules() {
		List<CityGMLModule> citygml = new ArrayList<CityGMLModule>();
		for (Module module : modules)
			if (module instanceof CityGMLModule)
				citygml.add((CityGMLModule)module);

		return citygml;
	}

	public List<GMLModule> getGMLModules() {
		List<GMLModule> gml = new ArrayList<GMLModule>();
		for (Module module : modules)
			if (module instanceof GMLModule)
				gml.add((GMLModule)module);

		return gml;
	}

	public List<AbstractXALModule> getXALModules() {
		List<AbstractXALModule> xal = new ArrayList<AbstractXALModule>();
		for (Module module : modules)
			if (module instanceof AbstractXALModule)
				xal.add((AbstractXALModule)module);

		return xal;
	}

	public static List<CityGMLVersion> getInstances() {
		return instances;
	}

}