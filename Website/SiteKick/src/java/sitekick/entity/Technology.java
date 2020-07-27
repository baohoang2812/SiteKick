/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "Technology", catalog = "SiteKick", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Technology.findAll", query = "SELECT t FROM Technology t")
    , @NamedQuery(name = "Technology.findById", query = "SELECT t FROM Technology t WHERE t.id = :id")
    , @NamedQuery(name = "Technology.findByName", query = "SELECT t FROM Technology t WHERE t.name = :name")
    , @NamedQuery(name = "Technology.findByDescription", query = "SELECT t FROM Technology t WHERE t.description = :description")})
public class Technology implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "Name", length = 200)
    private String name;
    @Column(name = "Description", length = 500)
    private String description;
    @ManyToMany(mappedBy = "technologyCollection")
    private Collection<Site> siteCollection;
    @JoinColumn(name = "TechnologyGroupId", referencedColumnName = "Id")
    @ManyToOne
    private TechnologyGroup technologyGroupId;

    public Technology() {
    }

    public Technology(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Site> getSiteCollection() {
        return siteCollection;
    }

    public void setSiteCollection(Collection<Site> siteCollection) {
        this.siteCollection = siteCollection;
    }

    public TechnologyGroup getTechnologyGroupId() {
        return technologyGroupId;
    }

    public void setTechnologyGroupId(TechnologyGroup technologyGroupId) {
        this.technologyGroupId = technologyGroupId;
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
        if (!(object instanceof Technology)) {
            return false;
        }
        Technology other = (Technology) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "prx.entity.Technology[ id=" + id + " ]";
    }
    
}
