package org.citygml4j.model.gml;

public interface AbstractDiscreteCoverage extends AbstractCoverage {
	public CoverageFunction getCoverageFunction();
	public boolean isSetCoverageFunction();

	public void setCoverageFunction(CoverageFunction coverageFunction);
	public void unsetCoverageFunction();
}