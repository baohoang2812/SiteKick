/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import prx.entity.Site;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class SiteDAO extends BaseDAO<Site, Integer> {

    public SiteDAO(EntityManager entityManager) {
        super(entityManager, Site.class);
    }

    public Site getFirstSiteByURL(String url) {
        List<Site> result = entityManager.createNamedQuery("Site.findByUrl", Site.class)
                .setParameter("url", url).getResultList();
        if (result == null || result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public Site create(Site site) {
        if (site != null) {
            Site foundSite = getFirstSiteByURL(site.getUrl());
            if (foundSite == null) {
                return super.create(site);
            }
            return foundSite;
        }
        return null;
    }

    @Override
    public List<Site> create(List<Site> entityList) {
        List<Site> result = new ArrayList();
        entityList.forEach(x -> {
            result.add(create(x));
        });
        return result;
    }

    public List<Site> getAllSite() {
        return entityManager.createNamedQuery("Site.findAll", Site.class).getResultList();
    }

    public int getAllSiteCount() {
        return ((Number) entityManager.createNamedQuery("Site.countAll").getSingleResult()).intValue();
    }
    
    public List<Site> getAllSite(int noOfRecords, int pageIndex) {
        return entityManager.createNamedQuery("Site.findAll", Site.class)
                .setMaxResults(noOfRecords)
                .setFirstResult(noOfRecords * pageIndex)
                .getResultList();
    }
    
    public List<String> getAllSiteUrl(){
        return entityManager.createNamedQuery("Site.getAllUrl", String.class).getResultList();
    }
}
