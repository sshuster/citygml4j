//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2015.02.20 um 10:38:51 PM CET 
//


package net.opengis.gml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für DataBlockType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="DataBlockType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}rangeParameters"/>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}tupleList"/>
 *           &lt;element ref="{http://www.opengis.net/gml}doubleOrNullTupleList"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataBlockType", propOrder = {
    "rangeParameters",
    "tupleList",
    "doubleOrNullTupleList"
})
public class DataBlockType {

    @XmlElement(required = true)
    protected RangeParametersType rangeParameters;
    protected CoordinatesType tupleList;
    @XmlList
    protected List<String> doubleOrNullTupleList;

    /**
     * Ruft den Wert der rangeParameters-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link RangeParametersType }
     *     
     */
    public RangeParametersType getRangeParameters() {
        return rangeParameters;
    }

    /**
     * Legt den Wert der rangeParameters-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link RangeParametersType }
     *     
     */
    public void setRangeParameters(RangeParametersType value) {
        this.rangeParameters = value;
    }

    public boolean isSetRangeParameters() {
        return (this.rangeParameters!= null);
    }

    /**
     * Ruft den Wert der tupleList-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CoordinatesType }
     *     
     */
    public CoordinatesType getTupleList() {
        return tupleList;
    }

    /**
     * Legt den Wert der tupleList-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinatesType }
     *     
     */
    public void setTupleList(CoordinatesType value) {
        this.tupleList = value;
    }

    public boolean isSetTupleList() {
        return (this.tupleList!= null);
    }

    /**
     * Gets the value of the doubleOrNullTupleList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the doubleOrNullTupleList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDoubleOrNullTupleList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDoubleOrNullTupleList() {
        if (doubleOrNullTupleList == null) {
            doubleOrNullTupleList = new ArrayList<String>();
        }
        return this.doubleOrNullTupleList;
    }

    public boolean isSetDoubleOrNullTupleList() {
        return ((this.doubleOrNullTupleList!= null)&&(!this.doubleOrNullTupleList.isEmpty()));
    }

    public void unsetDoubleOrNullTupleList() {
        this.doubleOrNullTupleList = null;
    }

    public void setDoubleOrNullTupleList(List<String> value) {
        this.doubleOrNullTupleList = value;
    }

}
