/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.dao;

import java.util.List;
import javax.persistence.EntityManager;
import prx.entity.Technology;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class TechnologyDAO extends BaseDAO<Technology, Integer> {

    public TechnologyDAO(EntityManager entityManager) {
        super(entityManager, Technology.class);
    }

    public Technology getTechnologyByName(String name) {
        List<Technology> techList = entityManager.createNamedQuery("Technology.findByName")
                .setParameter("name", name).getResultList();
        return techList.get(0);
    }

}
