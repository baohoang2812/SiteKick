
package sitekick.config;

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
 *         &lt;element name="alexa">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="baseUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="xslPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="xsdPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="allCategoryNavigationPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="categoryNavigationPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="xPath">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="detailUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *         &lt;element name="builtWith">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="baseUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="xslPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="xsdPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="navigationPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "alexa",
    "builtWith"
})
@XmlRootElement(name = "config")
public class Config {

    @XmlElement(required = true)
    protected Config.Alexa alexa;
    @XmlElement(required = true)
    protected Config.BuiltWith builtWith;

    /**
     * Gets the value of the alexa property.
     * 
     * @return
     *     possible object is
     *     {@link Config.Alexa }
     *     
     */
    public Config.Alexa getAlexa() {
        return alexa;
    }

    /**
     * Sets the value of the alexa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Config.Alexa }
     *     
     */
    public void setAlexa(Config.Alexa value) {
        this.alexa = value;
    }

    /**
     * Gets the value of the builtWith property.
     * 
     * @return
     *     possible object is
     *     {@link Config.BuiltWith }
     *     
     */
    public Config.BuiltWith getBuiltWith() {
        return builtWith;
    }

    /**
     * Sets the value of the builtWith property.
     * 
     * @param value
     *     allowed object is
     *     {@link Config.BuiltWith }
     *     
     */
    public void setBuiltWith(Config.BuiltWith value) {
        this.builtWith = value;
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
     *         &lt;element name="baseUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="xslPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="xsdPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="allCategoryNavigationPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="categoryNavigationPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="xPath">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="detailUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "baseUrl",
        "xslPath",
        "xsdPath",
        "allCategoryNavigationPath",
        "categoryNavigationPath",
        "xPath"
    })
    public static class Alexa {

        @XmlElement(required = true)
        protected String baseUrl;
        @XmlElement(required = true)
        protected String xslPath;
        @XmlElement(required = true)
        protected String xsdPath;
        @XmlElement(required = true)
        protected String allCategoryNavigationPath;
        @XmlElement(required = true)
        protected String categoryNavigationPath;
        @XmlElement(required = true)
        protected Config.Alexa.XPath xPath;

        /**
         * Gets the value of the baseUrl property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBaseUrl() {
            return baseUrl;
        }

        /**
         * Sets the value of the baseUrl property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBaseUrl(String value) {
            this.baseUrl = value;
        }

        /**
         * Gets the value of the xslPath property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXslPath() {
            return xslPath;
        }

        /**
         * Sets the value of the xslPath property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXslPath(String value) {
            this.xslPath = value;
        }

        /**
         * Gets the value of the xsdPath property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXsdPath() {
            return xsdPath;
        }

        /**
         * Sets the value of the xsdPath property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXsdPath(String value) {
            this.xsdPath = value;
        }

        /**
         * Gets the value of the allCategoryNavigationPath property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAllCategoryNavigationPath() {
            return allCategoryNavigationPath;
        }

        /**
         * Sets the value of the allCategoryNavigationPath property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAllCategoryNavigationPath(String value) {
            this.allCategoryNavigationPath = value;
        }

        /**
         * Gets the value of the categoryNavigationPath property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCategoryNavigationPath() {
            return categoryNavigationPath;
        }

        /**
         * Sets the value of the categoryNavigationPath property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCategoryNavigationPath(String value) {
            this.categoryNavigationPath = value;
        }

        /**
         * Gets the value of the xPath property.
         * 
         * @return
         *     possible object is
         *     {@link Config.Alexa.XPath }
         *     
         */
        public Config.Alexa.XPath getXPath() {
            return xPath;
        }

        /**
         * Sets the value of the xPath property.
         * 
         * @param value
         *     allowed object is
         *     {@link Config.Alexa.XPath }
         *     
         */
        public void setXPath(Config.Alexa.XPath value) {
            this.xPath = value;
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
         *         &lt;element name="detailUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "detailUrl",
            "domain",
            "category"
        })
        public static class XPath {

            @XmlElement(required = true)
            protected String detailUrl;
            @XmlElement(required = true)
            protected String domain;
            @XmlElement(required = true)
            protected String category;

            /**
             * Gets the value of the detailUrl property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDetailUrl() {
                return detailUrl;
            }

            /**
             * Sets the value of the detailUrl property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDetailUrl(String value) {
                this.detailUrl = value;
            }

            /**
             * Gets the value of the domain property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDomain() {
                return domain;
            }

            /**
             * Sets the value of the domain property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDomain(String value) {
                this.domain = value;
            }

            /**
             * Gets the value of the category property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCategory() {
                return category;
            }

            /**
             * Sets the value of the category property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCategory(String value) {
                this.category = value;
            }

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
     *         &lt;element name="baseUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="xslPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="xsdPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="navigationPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "baseUrl",
        "xslPath",
        "xsdPath",
        "navigationPath"
    })
    public static class BuiltWith {

        @XmlElement(required = true)
        protected String baseUrl;
        @XmlElement(required = true)
        protected String xslPath;
        @XmlElement(required = true)
        protected String xsdPath;
        @XmlElement(required = true)
        protected String navigationPath;

        /**
         * Gets the value of the baseUrl property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBaseUrl() {
            return baseUrl;
        }

        /**
         * Sets the value of the baseUrl property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBaseUrl(String value) {
            this.baseUrl = value;
        }

        /**
         * Gets the value of the xslPath property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXslPath() {
            return xslPath;
        }

        /**
         * Sets the value of the xslPath property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXslPath(String value) {
            this.xslPath = value;
        }

        /**
         * Gets the value of the xsdPath property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXsdPath() {
            return xsdPath;
        }

        /**
         * Sets the value of the xsdPath property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXsdPath(String value) {
            this.xsdPath = value;
        }

        /**
         * Gets the value of the navigationPath property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNavigationPath() {
            return navigationPath;
        }

        /**
         * Sets the value of the navigationPath property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNavigationPath(String value) {
            this.navigationPath = value;
        }

    }

}
