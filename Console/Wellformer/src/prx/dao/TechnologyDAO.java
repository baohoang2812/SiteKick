/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.dao;

import javax.persistence.EntityManager;
import prx.entity.Technology;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class TechnologyDAO extends BaseDAO<Technology, Integer> {

    private static final Object LOCK = new Object();
    private static TechnologyDAO instance;

    public static TechnologyDAO getInstance(EntityManager em) {
        synchronized (LOCK) {
            //singleton
            if (instance == null) {
                instance = new TechnologyDAO(em);
            }
        }
        return instance;
    }

    public TechnologyDAO(EntityManager entityManager) {
        super(entityManager);
    }

}
