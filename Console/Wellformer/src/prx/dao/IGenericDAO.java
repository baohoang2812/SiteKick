/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.dao;

import java.util.List;

/**
 *
 * @author Gia Bảo Hoàng
 */
public interface IGenericDAO<T, PK> {
    public T create(T entity);
    public T findByID(PK id);
    public T update(T entity);
    public T delete(T enitty);
    public List<T> getAll(String namedQuery);
    
}
