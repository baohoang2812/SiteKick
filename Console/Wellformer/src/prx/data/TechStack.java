
package prx.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="site">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="technologyGroup" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="technology" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="techName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "site",
    "technologyGroup"
})
@XmlRootElement(name = "techStack")
public class TechStack {

    @XmlElement(required = true)
    protected TechStack.Site site;
    @XmlElement(required = true)
    protected List<TechStack.TechnologyGroup> technologyGroup;

    /**
     * Gets the value of the site property.
     * 
     * @return
     *     possible object is
     *     {@link TechStack.Site }
     *     
     */
    public TechStack.Site getSite() {
        return site;
    }

    /**
     * Sets the value of the site property.
     * 
     * @param value
     *     allowed object is
     *     {@link TechStack.Site }
     *     
     */
    public void setSite(TechStack.Site value) {
        this.site = value;
    }

    /**
     * Gets the value of the technologyGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the technologyGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTechnologyGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TechStack.TechnologyGroup }
     * 
     * 
     */
    public List<TechStack.TechnologyGroup> getTechnologyGroup() {
        if (technologyGroup == null) {
            technologyGroup = new ArrayList<TechStack.TechnologyGroup>();
        }
        return this.technologyGroup;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "name"
    })
    public static class Site {

        @XmlElement(required = true)
        protected String name;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="technology" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="techName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "groupName",
        "technology"
    })
    public static class TechnologyGroup {

        @XmlElement(required = true)
        protected String groupName;
        @XmlElement(required = true)
        protected List<TechStack.TechnologyGroup.Technology> technology;

        /**
         * Gets the value of the groupName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGroupName() {
            return groupName;
        }

        /**
         * Sets the value of the groupName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGroupName(String value) {
            this.groupName = value;
        }

        /**
         * Gets the value of the technology property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the technology property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTechnology().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TechStack.TechnologyGroup.Technology }
         * 
         * 
         */
        public List<TechStack.TechnologyGroup.Technology> getTechnology() {
            if (technology == null) {
                technology = new ArrayList<TechStack.TechnologyGroup.Technology>();
            }
            return this.technology;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="techName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "techName",
            "description"
        })
        public static class Technology {

            @XmlElement(required = true)
            protected String techName;
            @XmlElement(required = true)
            protected String description;

            /**
             * Gets the value of the techName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTechName() {
                return techName;
            }

            /**
             * Sets the value of the techName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTechName(String value) {
                this.techName = value;
            }

            /**
             * Gets the value of the description property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDescription() {
                return description;
            }

            /**
             * Sets the value of the description property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDescription(String value) {
                this.description = value;
            }

        }

    }

}
