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
public class SiteDAO extends BaseDAO<Site, Integer> {

    private static SiteDAO instance;
    private static final Object LOCK = new Object();

    public static SiteDAO getInstance(EntityManager em) {
        synchronized (LOCK) {
            //singleton
            if (instance == null) {
                instance = new SiteDAO(em);
            }
        }
        return instance;
    }

    public SiteDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public synchronized Site getFirstSiteByURL(String url) {
        List<Site> result = entityManager.createNamedQuery("Site.findByUrl", Site.class)
                .setParameter("url", url).getResultList();
        if (result == null || result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public synchronized Site createSite(Site site) {
        if (site != null) {
            Site foundSite = getFirstSiteByURL(site.getUrl());
            if (foundSite == null) {
                return create(site);
            }
            return site;
        }
        return null;
    }

    public synchronized List<Site> getAllSite() {
        return entityManager.createNamedQuery("Site.findAll", Site.class).getResultList();
    }

    public synchronized int getAllSiteCount() {
        return ((Number) entityManager.createNamedQuery("Site.countAll").getSingleResult()).intValue();
    }
}
