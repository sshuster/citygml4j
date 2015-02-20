//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2015.02.20 um 10:38:51 PM CET 
//


package org.w3._1999.xlink;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.w3._1999.xlink package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Resource_QNAME = new QName("http://www.w3.org/1999/xlink", "resource");
    private final static QName _Title_QNAME = new QName("http://www.w3.org/1999/xlink", "title");
    private final static QName _Arc_QNAME = new QName("http://www.w3.org/1999/xlink", "arc");
    private final static QName _Locator_QNAME = new QName("http://www.w3.org/1999/xlink", "locator");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.w3._1999.xlink
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LocatorType }
     * 
     */
    public LocatorType createLocatorType() {
        return new LocatorType();
    }

    /**
     * Create an instance of {@link TitleEltType }
     * 
     */
    public TitleEltType createTitleEltType() {
        return new TitleEltType();
    }

    /**
     * Create an instance of {@link ArcType }
     * 
     */
    public ArcType createArcType() {
        return new ArcType();
    }

    /**
     * Create an instance of {@link ResourceType }
     * 
     */
    public ResourceType createResourceType() {
        return new ResourceType();
    }

    /**
     * Create an instance of {@link Simple }
     * 
     */
    public Simple createSimple() {
        return new Simple();
    }

    /**
     * Create an instance of {@link Extended }
     * 
     */
    public Extended createExtended() {
        return new Extended();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/1999/xlink", name = "resource")
    public JAXBElement<ResourceType> createResource(ResourceType value) {
        return new JAXBElement<ResourceType>(_Resource_QNAME, ResourceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TitleEltType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/1999/xlink", name = "title")
    public JAXBElement<TitleEltType> createTitle(TitleEltType value) {
        return new JAXBElement<TitleEltType>(_Title_QNAME, TitleEltType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArcType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/1999/xlink", name = "arc")
    public JAXBElement<ArcType> createArc(ArcType value) {
        return new JAXBElement<ArcType>(_Arc_QNAME, ArcType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocatorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/1999/xlink", name = "locator")
    public JAXBElement<LocatorType> createLocator(LocatorType value) {
        return new JAXBElement<LocatorType>(_Locator_QNAME, LocatorType.class, null, value);
    }

}
