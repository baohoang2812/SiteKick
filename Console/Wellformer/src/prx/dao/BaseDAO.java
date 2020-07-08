/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Gia Bảo Hoàng
 * @param <T>
 * @param <PK>
 */
public class BaseDAO<T, PK extends Serializable> implements IGenericDAO<T, PK> {

    protected Class<T> entityClass;
    protected EntityManager entityManager;

    public BaseDAO(EntityManager entityManager, Class<T> klass) {
        this.entityClass = klass;
        this.entityManager = entityManager;
    }

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public T delete(T entity) {
        entityManager.remove(entity);
        return entity;
    }

    @Override
    public T findByID(PK id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> getAll(String namedQuery) {
        List<T> result = entityManager.createNamedQuery(namedQuery, entityClass).getResultList();
        return result;
    }

    @Override
    public List<T> create(List<T> entityList) {
        entityList.forEach(x -> {
            entityManager.persist(entityList);
        });
        return entityList;
    }
}
