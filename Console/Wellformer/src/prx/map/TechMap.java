/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.map;

import prx.dao.TechnologyGroupDAO;
import prx.data.TechStack;
import prx.entity.EntityContext;
import prx.entity.Technology;
import prx.entity.TechnologyGroup;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class TechMap {

    public Technology map(TechStack.TechnologyGroup.Technology tech, String groupName) {
        Technology technology = new Technology();
        technology.setName(tech.getTechName());
        technology.setDescription(tech.getDescription());
        //find group exist
        EntityContext context = EntityContext.newInstance();
        context.beginTransaction();
        TechnologyGroupDAO groupDAO = new TechnologyGroupDAO(context.getEntityManager());
        TechnologyGroup groupEntity = groupDAO.findByName(groupName);
        context.commitTransaction();
        if (null == groupEntity) {
            TechnologyGroup group = new TechnologyGroup();
            group.setName(groupName);
            technology.setTechnologyGroupId(group);
        }else{
            technology.setTechnologyGroupId(groupEntity);
        }
        technology.setSiteCollection(null);
        return technology;
    }

//    public List<Technology> mapList(List<TechStack.TechnologyGroup> jaxbObjectList) {
//        //
//
//    }
}
