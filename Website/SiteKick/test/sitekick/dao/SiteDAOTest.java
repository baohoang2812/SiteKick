/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.dao;

import javax.persistence.EntityManager;
import org.junit.Test;
import static org.junit.Assert.*;
import sitekick.entity.Site;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class SiteDAOTest {

    EntityManager em;
    EntityContext context;
    SiteDAO siteDAO;

    public SiteDAOTest() {
        context = EntityContext.newInstance();
        em = context.getEntityManager();
        siteDAO = new SiteDAO(em);
    }

    /**
     * Test of getFirstSiteByURL method, of class SiteDAO.
     */
    @Test
    public void getFirstSiteByURL() {
        System.out.println("Test Get First Site By Url");
        //prepare
        String url = "food.com";
        //run
        Site result = siteDAO.getFirstSiteByURL(url);
        //assert
        assertNotNull(result);
        assertEquals(url, result.getUrl());
    }

    /**
     * Test of create method, of class SiteDAO.
     */
    @Test
    public void testCreate_Site() {
//        System.out.println("create");
//        Site site = null;
//        SiteDAO instance = null;
//        Site expResult = null;
//        Site result = instance.create(site);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSite method, of class SiteDAO.
     */
    @Test
    public void testGetAllSite() {
//        System.out.println("getAllSite");
//        SiteDAO instance = null;
//        List<Site> expResult = null;
//        List<Site> result = instance.getAllSite();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSiteCount method, of class SiteDAO.
     */
    @Test
    public void testGetAllSiteCount() {
//        System.out.println("getAllSiteCount");
//        SiteDAO instance = null;
//        int expResult = 0;
//        int result = instance.getAllSiteCount();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
