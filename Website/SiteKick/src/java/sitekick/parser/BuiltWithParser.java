/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import sitekick.dao.SiteDAO;
import sitekick.dao.TechnologyDAO;
import sitekick.dao.TechnologyGroupDAO;
import sitekick.data.TechStack;
import sitekick.entity.EntityContext;
import sitekick.entity.Site;
import sitekick.entity.Technology;
import sitekick.entity.TechnologyGroup;
import sitekick.map.TechMap;
import sitekick.utils.XMLUtils;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class BuiltWithParser extends Parser {

    protected Set<String> domainSet;
    private TechnologyGroup groupEntity;

    public BuiltWithParser() {
    }

    public BuiltWithParser(Set<String> domainSet) {
        this.domainSet = domainSet;
    }

    public Set<String> getLinkSet() {
        return domainSet;
    }

    public void setLinkSet(Set<String> domainSet) {
        this.domainSet = domainSet;
    }

    @Override
    public void parse() {
        System.out.println("================================================");
        System.out.println("Parsing " + baseURL + " . . .");
        //config transformer
        Transformer transformer = null;
        try {
            transformer = XMLUtils.getTransformer(xslPath);
        } catch (TransformerConfigurationException e) {
            Logger.getLogger(BuiltWithParser.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        List<TechStack> stackList = parsePageSet(TechStack.class, domainSet, transformer);
        insertTechnology(stackList);
        System.out.println("Finish parsing " + baseURL);
        System.out.println("================================================");
    }

    public void insertTechnology(List<TechStack> stackList) {
        EntityContext context = EntityContext.newInstance();
        EntityManager em = context.getEntityManager();
        for (TechStack techStack : stackList) {
            SiteDAO siteDAO = new SiteDAO(em);
            TechStack.Site site = techStack.getSite();
            if (site != null) {
                Site siteEntity = siteDAO.getFirstSiteByURL(site.getName());
                if (siteEntity != null) {
                    List<Technology> techList = new ArrayList(siteEntity.getTechnologyCollection());
                    techStack.getTechnologyGroup().forEach(group -> {
                        //find group exist
                        context.beginTransaction();
                        TechnologyGroupDAO groupDAO = new TechnologyGroupDAO(context.getEntityManager());
                        groupEntity = groupDAO.findByName(group.getGroupName());
                        context.commitTransaction();
                        if (null == groupEntity) {
                            // insert group if not exist
                            groupEntity = new TechnologyGroup();
                            groupEntity.setName(group.getGroupName());
                            context.beginTransaction();
                            groupEntity = groupDAO.create(groupEntity);
                            context.commitTransaction();
                        }
                        group.getTechnology().forEach(tech -> {
                            // check Tech is existed in site 
                            boolean isExisted = techList.stream().anyMatch(x -> x.getName().equals(tech.getTechName()));
                            if (!isExisted) {
                                // Check Tech is existed in Technology Table
                                TechnologyDAO techDAO = new TechnologyDAO(em);
                                context.beginTransaction();
                                Technology technology = techDAO.getTechnologyByName(tech.getTechName());
                                context.commitTransaction();
                                // Tech not existed, create new tech
                                if (technology == null) {
                                    TechMap techMap = new TechMap();
                                    technology = techMap.map(tech, groupEntity);
                                }
                                // Add to site technologyCollection
                                techList.add(technology);
                                siteEntity.setTechnologyCollection(techList);
                            }
                        });
                    });
                    //merge entity back
                    context.beginTransaction();
                    siteDAO.update(siteEntity);
                    context.commitTransaction();
                }
            }
        }

    }
}
