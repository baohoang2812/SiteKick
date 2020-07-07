/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.dao;

import javax.persistence.EntityManager;
import prx.entity.TechnologyGroup;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class TechnologyGroupDAO extends BaseDAO<TechnologyGroup, Integer> {

    private static final Object LOCK = new Object();
    private static TechnologyGroupDAO instance;

    public static TechnologyGroupDAO getInstance(EntityManager em) {
        synchronized (LOCK) {
            //singleton
            if (instance == null) {
                instance = new TechnologyGroupDAO(em);
            }
        }
        return instance;
    }

    public TechnologyGroupDAO(EntityManager entityManager) {
        super(entityManager);
    }

}
