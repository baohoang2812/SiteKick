/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import sitekick.entity.Category;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class CategoryDAO extends BaseDAO<Category, Integer> {

    public CategoryDAO(EntityManager entityManager) {
        super(entityManager, Category.class);
    }

    public Category getFirstCategoryByName(String categoryName) {
        List<Category> result = entityManager.createNamedQuery("Category.findByName", Category.class)
                .setParameter("name", categoryName).getResultList();
        if (result == null || result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public Category createCategory(String categoryName) {
        Category category = getFirstCategoryByName(categoryName);
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            create(category);
        }
        return category;
    }

    //set Batch Size before use
    public List<Category> createCategory(List<String> categoryList) {
        List<Category> result = new ArrayList();
        for (String categoryName : categoryList) {
            Category category = getFirstCategoryByName(categoryName);
            if (category == null) {
                category = new Category();
                category.setName(categoryName);
                result.add(create(category));
            }
        }
        return result;
    }

    public List<Category> getAllCategory() {
        return entityManager.createNamedQuery("Category.findAll", Category.class).getResultList();
    }
}
