/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.services;

import prx.dao.TechnologyGroupDAO;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class TechnologyGroupService {
    TechnologyGroupDAO technologyGroupBLO;

    public TechnologyGroupService(TechnologyGroupDAO technologyGroupBLO) {
        this.technologyGroupBLO = technologyGroupBLO;
    }
    
}
