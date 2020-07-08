/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.map;

import prx.data.TechStack;
import prx.entity.Technology;
import prx.entity.TechnologyGroup;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class TechMap {

    public Technology map(TechStack.TechnologyGroup.Technology tech, TechnologyGroup group) {
        Technology technology = new Technology();
        technology.setName(tech.getTechName());
        technology.setDescription(tech.getDescription());
        technology.setTechnologyGroupId(group);
        technology.setSiteCollection(null);
        return technology;
    }

//    public List<Technology> mapList(List<TechStack.TechnologyGroup> jaxbObjectList) {
//        //
//
//    }
}
