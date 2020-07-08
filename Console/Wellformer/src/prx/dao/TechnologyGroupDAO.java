/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.dao;

import java.util.List;
import javax.persistence.EntityManager;
import prx.entity.TechnologyGroup;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class TechnologyGroupDAO extends BaseDAO<TechnologyGroup, Integer> {

    public TechnologyGroupDAO(EntityManager entityManager) {
        super(entityManager, TechnologyGroup.class);
    }

    public TechnologyGroup findByName(String name) {
        List<TechnologyGroup> groupList = entityManager.createNamedQuery("TechnologyGroup.findByName", TechnologyGroup.class)
                .setParameter("name", name).getResultList();
        if (groupList == null || groupList.isEmpty()) {
            return null;
        }
        return groupList.get(0);
    }
    
}
