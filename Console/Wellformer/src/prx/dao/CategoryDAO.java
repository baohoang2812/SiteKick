/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.dao;

import java.util.List;
import javax.persistence.EntityManager;
import prx.entity.Category;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class CategoryDAO extends BaseDAO<Category, Integer> {

    private static final Object LOCK = new Object();
    private static CategoryDAO instance;

    public static CategoryDAO getInstance(EntityManager em) {
        synchronized (LOCK) {
            //singleton
            if (instance == null) {
                instance = new CategoryDAO(em);
            }
        }
        return instance;
    }

    public CategoryDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public synchronized Category getFirstCategoryByName(String categoryName) {
        List<Category> result = entityManager.createNamedQuery("Category.findByName", Category.class)
                .setParameter("name", categoryName).getResultList();
        if (result == null || result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public synchronized Category createCategory(String categoryName) {
        Category category = null;
        category = getFirstCategoryByName(categoryName);
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            create(category);
        }
        return category;
    }

    public synchronized List<Category> getAllCategory() {
        return entityManager.createNamedQuery("Category.findAll", Category.class).getResultList();
    }

}
