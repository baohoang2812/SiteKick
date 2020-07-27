/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.services;

import sitekick.dao.CategoryDAO;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class CategoryService {
    CategoryDAO categoryBLO;

    public CategoryService(CategoryDAO categoryBLO) {
        this.categoryBLO = categoryBLO;
    }
    
}
