package org.citygml4j.model.dynamizer;

import org.citygml4j.visitor.ObjectVisitor;
import org.xmlobjects.gml.model.basictypes.Code;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class StandardFileTimeseries extends AbstractAtomicTimeseries {
    private String fileLocation;
    private Code fileType;
    private Code mimeType;
    private List<ADEOfStandardFileTimeseries> adeOfStandardFileTimeseries;

    public StandardFileTimeseries() {
    }

    public StandardFileTimeseries(String observationProperty, String fileLocation, Code fileType) {
        super(observationProperty);
        this.fileLocation = fileLocation;
        setFileType(fileType);
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Code getFileType() {
        return fileType;
    }

    public void setFileType(Code fileType) {
        this.fileType = asChild(fileType);
    }

    public Code getMimeType() {
        return mimeType;
    }

    public void setMimeType(Code mimeType) {
        this.mimeType = asChild(mimeType);
    }

    public List<ADEOfStandardFileTimeseries> getADEOfStandardFileTimeseries() {
        if (adeOfStandardFileTimeseries == null)
            adeOfStandardFileTimeseries = new ChildList<>(this);

        return adeOfStandardFileTimeseries;
    }

    public void setADEOfStandardFileTimeseries(List<ADEOfStandardFileTimeseries> adeOfStandardFileTimeseries) {
        this.adeOfStandardFileTimeseries = asChild(adeOfStandardFileTimeseries);
    }

    @Override
    public void accept(ObjectVisitor visitor) {
        visitor.visit(this);
    }
}