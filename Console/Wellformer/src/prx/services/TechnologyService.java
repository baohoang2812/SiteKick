/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.services;

import prx.dao.TechnologyDAO;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class TechnologyService {
    TechnologyDAO technologyBLO;

    public TechnologyService(TechnologyDAO technologyBLO) {
        this.technologyBLO = technologyBLO;
    }
    
}
