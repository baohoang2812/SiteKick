
package prx.data;

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
 *         &lt;element name="favicon" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="globalRank" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="countryRank" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="category">
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
 *         &lt;element name="categoryRank" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalVisit" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "favicon",
    "url",
    "description",
    "globalRank",
    "country",
    "countryRank",
    "category",
    "categoryRank",
    "totalVisit"
})
@XmlRootElement(name = "site")
public class Site {

    @XmlElement(required = true)
    protected String favicon;
    @XmlElement(required = true)
    protected String url;
    @XmlElement(required = true)
    protected String description;
    protected int globalRank;
    @XmlElement(required = true)
    protected String country;
    protected int countryRank;
    @XmlElement(required = true)
    protected Site.Category category;
    protected int categoryRank;
    @XmlElement(required = true)
    protected String totalVisit;

    /**
     * Gets the value of the favicon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFavicon() {
        return favicon;
    }

    /**
     * Sets the value of the favicon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFavicon(String value) {
        this.favicon = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
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

    /**
     * Gets the value of the globalRank property.
     * 
     */
    public int getGlobalRank() {
        return globalRank;
    }

    /**
     * Sets the value of the globalRank property.
     * 
     */
    public void setGlobalRank(int value) {
        this.globalRank = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the countryRank property.
     * 
     */
    public int getCountryRank() {
        return countryRank;
    }

    /**
     * Sets the value of the countryRank property.
     * 
     */
    public void setCountryRank(int value) {
        this.countryRank = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link Site.Category }
     *     
     */
    public Site.Category getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link Site.Category }
     *     
     */
    public void setCategory(Site.Category value) {
        this.category = value;
    }

    /**
     * Gets the value of the categoryRank property.
     * 
     */
    public int getCategoryRank() {
        return categoryRank;
    }

    /**
     * Sets the value of the categoryRank property.
     * 
     */
    public void setCategoryRank(int value) {
        this.categoryRank = value;
    }

    /**
     * Gets the value of the totalVisit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalVisit() {
        return totalVisit;
    }

    /**
     * Sets the value of the totalVisit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalVisit(String value) {
        this.totalVisit = value;
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
    public static class Category {

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

}
