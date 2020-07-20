/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import prx.constant.CommonConstant;
import prx.dao.SiteDAO;
import prx.data.TechStack;
import prx.data.TechStacks;
import prx.entity.EntityContext;
import prx.utils.XMLUtils;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class SiteService {
    SiteDAO siteDAO;

    public SiteService(SiteDAO siteBLO) {
        this.siteDAO = siteBLO;
    }

    public String getAllSitesXMLString() throws JAXBException, XMLStreamException {
        EntityContext entityContext = EntityContext.newInstance();
        SiteDAO siteDAO = new SiteDAO(entityContext.getEntityManager());
        entityContext.beginTransaction();
        List<prx.entity.Site> siteList = siteDAO.getAllSite();
        entityContext.commitTransaction();

        List<TechStack> techStackList = siteList.stream().map(s -> {
            TechStack techStack = new TechStack();
            TechStack.Site site = new TechStack.Site();
            site.setName(s.getUrl());
            techStack.setSite(site);
            List<prx.entity.Technology> techEntityList = new ArrayList(s.getTechnologyCollection());
            // get Distinct Technology Group without Technology from Technology Entity List
            List<TechStack.TechnologyGroup> technologyGroup = techEntityList.stream().map(t -> {
                TechStack.TechnologyGroup techGroup = new TechStack.TechnologyGroup();
                String groupName = t.getTechnologyGroupId() != null ? t.getTechnologyGroupId().getName() : CommonConstant.EMPTY;
                techGroup.setGroupName(groupName);
                return techGroup;
            }).distinct().collect(Collectors.toList());

            // Get Technology Group
            List<TechStack.TechnologyGroup> technologyList = technologyGroup.stream().map(g -> {
                //get Technology List in each group
                List<TechStack.TechnologyGroup.Technology> techList = techEntityList
                        .stream()
                        .filter(tech -> tech.getTechnologyGroupId().getName().equals(g.getGroupName()))
                        .map(t -> {
                            TechStack.TechnologyGroup.Technology technology = new TechStack.TechnologyGroup.Technology();
                            technology.setId(t.getId());
                            technology.setTechName(t.getName());
                            technology.setDescription(t.getDescription());
                            return technology;
                        })
                        .collect(Collectors.toList());
                g.setTechnology(techList);
                return g;
            }).collect(Collectors.toList());
            techStack.setTechnologyGroup(technologyGroup);
            return techStack;
        }).collect(Collectors.toList());

        TechStacks techStacks = new TechStacks();
        techStacks.setStackList(techStackList);
        return XMLUtils.marshallToString(techStacks);
    }
}
