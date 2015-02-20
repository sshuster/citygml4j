//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2015.02.20 um 10:38:51 PM CET 
//


package net.opengis.citygml.building._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 *  Type for windows in walls. Used in LOD3 and LOD4 only . As subclass of _CityObject, a window inherits all
 * 				attributes and relations, in particular an id, names, external references, and generalization relations.
 * 			
 * 
 * <p>Java-Klasse für WindowType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="WindowType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/citygml/building/1.0}AbstractOpeningType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/citygml/building/1.0}_GenericApplicationPropertyOfWindow" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WindowType", propOrder = {
    "_GenericApplicationPropertyOfWindow"
})
public class WindowType
    extends AbstractOpeningType
{

    @XmlElementRef(name = "_GenericApplicationPropertyOfWindow", namespace = "http://www.opengis.net/citygml/building/1.0", type = JAXBElement.class, required = false)
    protected List<JAXBElement<Object>> _GenericApplicationPropertyOfWindow;

    /**
     * Gets the value of the genericApplicationPropertyOfWindow property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the genericApplicationPropertyOfWindow property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    get_GenericApplicationPropertyOfWindow().add(newItem);
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
    public List<JAXBElement<Object>> get_GenericApplicationPropertyOfWindow() {
        if (_GenericApplicationPropertyOfWindow == null) {
            _GenericApplicationPropertyOfWindow = new ArrayList<JAXBElement<Object>>();
        }
        return this._GenericApplicationPropertyOfWindow;
    }

    public boolean isSet_GenericApplicationPropertyOfWindow() {
        return ((this._GenericApplicationPropertyOfWindow!= null)&&(!this._GenericApplicationPropertyOfWindow.isEmpty()));
    }

    public void unset_GenericApplicationPropertyOfWindow() {
        this._GenericApplicationPropertyOfWindow = null;
    }

    public void set_GenericApplicationPropertyOfWindow(List<JAXBElement<Object>> value) {
        this._GenericApplicationPropertyOfWindow = value;
    }

}
