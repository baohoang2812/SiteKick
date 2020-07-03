/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.blo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class SiteBLO extends BaseBLO {

    public SiteBLO(EntityManager entityManager) {
        super(entityManager);
    }
    
}
