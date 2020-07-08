/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import prx.dao.SiteDAO;
import prx.data.TechStack;
import prx.entity.EntityContext;
import prx.entity.Site;
import prx.entity.Technology;
import prx.map.TechMap;
import prx.utils.XMLUtils;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class BuiltWithParser extends Parser {

    Set<String> domainSet;

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

    //TODO test this
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
                        group.getTechnology().forEach(tech -> {
                            boolean isExisted = techList.stream().anyMatch(x -> x.getName().equals(tech.getTechName()));
                            if (!isExisted) {
                                TechMap techMap = new TechMap();
                                //find Group Entity
                                Technology technology = techMap.map(tech, group.getGroupName());
                                techList.add(technology);
                                siteEntity.setTechnologyCollection(techList);
                            }
                        });
                    });
                    //merge entity back
                    context.beginTransaction();
                    siteDAO.update(siteEntity);
                    context.commitTransaction();
                } else {
                    //insert new Site
//                    SiteMap siteMap = new SiteMap();
//                    Site newSite = 
//                    
//                    siteDAO.create(site)
                }
            }
        }

    }
}
