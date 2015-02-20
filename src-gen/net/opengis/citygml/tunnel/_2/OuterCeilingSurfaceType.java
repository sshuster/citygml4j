//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2015.02.20 um 10:38:51 PM CET 
//


package net.opengis.citygml.tunnel._2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Mainly horizontal construction that separates the interior part of the Tunnel from the ambient medium
 * 				(rock, earth, ..) from above.
 * 
 * <p>Java-Klasse für OuterCeilingSurfaceType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="OuterCeilingSurfaceType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/citygml/tunnel/2.0}AbstractBoundarySurfaceType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/citygml/tunnel/2.0}_GenericApplicationPropertyOfOuterCeilingSurface" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OuterCeilingSurfaceType", propOrder = {
    "_GenericApplicationPropertyOfOuterCeilingSurface"
})
public class OuterCeilingSurfaceType
    extends AbstractBoundarySurfaceType
{

    @XmlElementRef(name = "_GenericApplicationPropertyOfOuterCeilingSurface", namespace = "http://www.opengis.net/citygml/tunnel/2.0", type = JAXBElement.class, required = false)
    protected List<JAXBElement<Object>> _GenericApplicationPropertyOfOuterCeilingSurface;

    /**
     * Gets the value of the genericApplicationPropertyOfOuterCeilingSurface property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the genericApplicationPropertyOfOuterCeilingSurface property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    get_GenericApplicationPropertyOfOuterCeilingSurface().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * 
     */
    public List<JAXBElement<Object>> get_GenericApplicationPropertyOfOuterCeilingSurface() {
        if (_GenericApplicationPropertyOfOuterCeilingSurface == null) {
            _GenericApplicationPropertyOfOuterCeilingSurface = new ArrayList<JAXBElement<Object>>();
        }
        return this._GenericApplicationPropertyOfOuterCeilingSurface;
    }

    public boolean isSet_GenericApplicationPropertyOfOuterCeilingSurface() {
        return ((this._GenericApplicationPropertyOfOuterCeilingSurface!= null)&&(!this._GenericApplicationPropertyOfOuterCeilingSurface.isEmpty()));
    }

    public void unset_GenericApplicationPropertyOfOuterCeilingSurface() {
        this._GenericApplicationPropertyOfOuterCeilingSurface = null;
    }

    public void set_GenericApplicationPropertyOfOuterCeilingSurface(List<JAXBElement<Object>> value) {
        this._GenericApplicationPropertyOfOuterCeilingSurface = value;
    }

}
