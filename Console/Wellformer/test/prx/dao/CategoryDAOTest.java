/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Test;
import static org.junit.Assert.*;
import prx.entity.Category;
import prx.entity.EntityContext;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class CategoryDAOTest {

    public CategoryDAOTest() {
    }

    /**
     * Test of getFirstCategoryByName method, of class CategoryDAO.
     */
    @Test
    public void testGetFirstCategoryByName() {
//        System.out.println("getFirstCategoryByName");
//        String categoryName = "";
//        CategoryDAO instance = null;
//        Category expResult = null;
//        Category result = instance.getFirstCategoryByName(categoryName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of createCategory method, of class CategoryDAO.
     */
    @Test
    public void testCreateCategory_String() throws IOException {
        System.out.println("createCategory");
        String categoryName = "News";
        EntityContext context = EntityContext.newInstance();
        EntityManager em = context.getEntityManager();
        CategoryDAO instance = new CategoryDAO(em);
        String expCategoryName = "News";
        context.beginTransaction();
        Category result = instance.createCategory(categoryName);
        context.commitTransaction();
        assertNotNull(result);
        assertEquals(expCategoryName, result.getName());
    }

    /**
     * Test of createCategory method, of class CategoryDAO.
     */
    @Test
    public void testCreateCategory_List() throws IOException {
        System.out.println("createCategoryList");
        List<String> categoryList = new ArrayList();
        categoryList.add("Comics");
        categoryList.add("Game");
        categoryList.add("Gambling");
        categoryList.add("Social Network");
        EntityContext context = EntityContext.newInstance();
        EntityManager em = context.getEntityManager();
        CategoryDAO instance = new CategoryDAO(em);
        context.beginTransaction();
        List<Category> result = instance.createCategory(categoryList);
        context.commitTransaction();
        assertFalse(result.isEmpty());
        assertEquals(4, result.size());
    }

    /**
     * Test of getAllCategory method, of class CategoryDAO.
     */
    @Test
    public void testGetAllCategory() {
//        System.out.println("getAllCategory");
//        CategoryDAO instance = null;
//        List<Category> expResult = null;
//        List<Category> result = instance.getAllCategory();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
