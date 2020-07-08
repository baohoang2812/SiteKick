/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.dao;

import java.util.List;
import javax.persistence.EntityManager;
import prx.entity.Site;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class SiteDAO extends BaseDAO<Site,Integer> {

    public SiteDAO(EntityManager entityManager) {
        super(entityManager,Site.class);
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
                return create(site);
            }
            return site;
        }
        return null;
    }

    @Override
    public List<Site> create(List<Site> entityList) {
       entityList.forEach(x -> {
           create(x);
       });
       return entityList;
    }

    public List<Site> getAllSite() {
        return entityManager.createNamedQuery("Site.findAll", Site.class).getResultList();
    }

    // named Query not created yet
    public int getAllSiteCount() {
        return ((Number) entityManager.createNamedQuery("Site.countAll").getSingleResult()).intValue();
    }
}
