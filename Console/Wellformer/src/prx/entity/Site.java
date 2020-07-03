/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gia Bảo Hoàng
 */
@Entity
@Table(name = "Site", catalog = "SiteKick", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Site.findAll", query = "SELECT s FROM Site s")
    , @NamedQuery(name = "Site.findById", query = "SELECT s FROM Site s WHERE s.id = :id")
    , @NamedQuery(name = "Site.findByUrl", query = "SELECT s FROM Site s WHERE s.url = :url")
    , @NamedQuery(name = "Site.findByFavicon", query = "SELECT s FROM Site s WHERE s.favicon = :favicon")
    , @NamedQuery(name = "Site.findByDescription", query = "SELECT s FROM Site s WHERE s.description = :description")
    , @NamedQuery(name = "Site.findByGlobalRank", query = "SELECT s FROM Site s WHERE s.globalRank = :globalRank")
    , @NamedQuery(name = "Site.findByCountryName", query = "SELECT s FROM Site s WHERE s.countryName = :countryName")
    , @NamedQuery(name = "Site.findByCountryRank", query = "SELECT s FROM Site s WHERE s.countryRank = :countryRank")
    , @NamedQuery(name = "Site.findByCategoryRank", query = "SELECT s FROM Site s WHERE s.categoryRank = :categoryRank")
    , @NamedQuery(name = "Site.findByTotalVisit", query = "SELECT s FROM Site s WHERE s.totalVisit = :totalVisit")})
public class Site implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "Url", length = 1073741823)
    private String url;
    @Column(name = "Favicon", length = 1073741823)
    private String favicon;
    @Column(name = "Description", length = 500)
    private String description;
    @Column(name = "GlobalRank")
    private Integer globalRank;
    @Column(name = "CountryName", length = 150)
    private String countryName;
    @Column(name = "CountryRank")
    private Integer countryRank;
    @Column(name = "CategoryRank")
    private Integer categoryRank;
    @Column(name = "TotalVisit", length = 200)
    private String totalVisit;
    @JoinTable(name = "SiteTech", joinColumns = {
        @JoinColumn(name = "SiteId", referencedColumnName = "Id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "TechnologyId", referencedColumnName = "Id", nullable = false)})
    @ManyToMany
    private Collection<Technology> technologyCollection;
    @JoinColumn(name = "CategoryId", referencedColumnName = "Id")
    @ManyToOne
    private Category categoryId;

    public Site() {
    }

    public Site(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGlobalRank() {
        return globalRank;
    }

    public void setGlobalRank(Integer globalRank) {
        this.globalRank = globalRank;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getCountryRank() {
        return countryRank;
    }

    public void setCountryRank(Integer countryRank) {
        this.countryRank = countryRank;
    }

    public Integer getCategoryRank() {
        return categoryRank;
    }

    public void setCategoryRank(Integer categoryRank) {
        this.categoryRank = categoryRank;
    }

    public String getTotalVisit() {
        return totalVisit;
    }

    public void setTotalVisit(String totalVisit) {
        this.totalVisit = totalVisit;
    }

    @XmlTransient
    public Collection<Technology> getTechnologyCollection() {
        return technologyCollection;
    }

    public void setTechnologyCollection(Collection<Technology> technologyCollection) {
        this.technologyCollection = technologyCollection;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Site)) {
            return false;
        }
        Site other = (Site) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "prx.entity.Site[ id=" + id + " ]";
    }
    
}
