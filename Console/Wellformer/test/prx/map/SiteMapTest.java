/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.map;

import org.junit.Assert;
import org.junit.Test;
import prx.dao.CategoryDAO;
import prx.data.Site;
import prx.data.Site.Category;
import prx.entity.EntityContext;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class SiteMapTest {

    public SiteMapTest() {
    }

    /**
     * Test of configure method, of class SiteMap.
     */
    @Test
    public void testMapping() {
        // Add test category 
        EntityContext context = EntityContext.newInstance();
        context.beginTransaction();
        CategoryDAO categoryDAO = new CategoryDAO(context.getEntityManager());
        categoryDAO.createCategory("Test Category");
        context.commitTransaction();
        Site site = new Site();
        site.setUrl("www.test.com");
        site.setCountry("Vietnam");
        site.setCountryRank(1);
        site.setGlobalRank(13);
        Category category = new Category();
        category.setName("Test Category");
        site.setCategory(category);
        SiteMap siteMap = new SiteMap();
        prx.entity.Site siteEntity = siteMap.map(site);
        Assert.assertNotNull(siteEntity);
        Assert.assertEquals("www.test.com", siteEntity.getUrl());
        Assert.assertEquals("Vietnam", siteEntity.getCountry());
        Assert.assertEquals(1, siteEntity.getCountryRank().intValue());
        Assert.assertEquals(13, siteEntity.getGlobalRank().intValue());
        Assert.assertNotEquals(null, siteEntity.getCategoryId());
        Assert.assertEquals("Test Category", siteEntity.getCategoryId().getName());
    }

}
