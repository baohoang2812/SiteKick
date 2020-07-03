/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.blo;

import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class BaseBLO<T> implements Serializable {

    protected EntityManager entityManager;

    public BaseBLO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected T insert(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    protected T update(T entity) {
        entityManager.merge(entity);
        return entity;
    }

    protected T delete(T entity) {
        entityManager.remove(entity);
        return entity;
    }
}
