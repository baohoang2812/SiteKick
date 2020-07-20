/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.parser;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import prx.data.Site;
import prx.data.Site.Category;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class AlexaParserTest {

    public AlexaParserTest() {
    }

    /**
     * Test of parse method, of class AlexaParser.
     */
    @Test
    public void testParseDetail() {
        System.out.println("parse");
        AlexaParser instance = new AlexaParser();
        instance.parse();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void insertSiteList() {
        System.out.println("Insert Site List");
        AlexaParser instance = new AlexaParser();
        // prepare 
        List<Site> siteList = new ArrayList();
        Site site1 = new Site();
        site1.setUrl("Test Site 1");
        site1.setCountry("Vietnam");
        site1.setCountryRank(1);
        site1.setGlobalRank(13);
        Category category = new Category();
        category.setName("Test Insert Category 1");
        site1.setCategory(category);
        Site site2 = new Site();
        site2.setUrl("Test Site 2");
        site2.setCountry("Vietnam");
        site2.setCountryRank(2);
        site2.setGlobalRank(14);
        Category category2 = new Category();
        category2.setName("Test Insert Category 2");
        site2.setCategory(category2);
        siteList.add(site1);
        siteList.add(site2);
        //run
        List<prx.entity.Site> result = instance.insertSiteList(siteList);
        //assert 
        Assert.assertNotNull(result);
        Assert.assertNotEquals(0,result.size());
        Assert.assertNotNull(result.get(0).getId());
        Assert.assertNotNull(result.get(1).getId());
    }

}
