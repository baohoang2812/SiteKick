/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import prx.constant.CommonConstant;
import prx.dao.SiteDAO;
import prx.data.TechStack;
import prx.data.TechStack.TechnologyGroup;
import prx.data.TechStack.TechnologyGroup.Technology;
import prx.data.TechStacks;
import prx.entity.EntityContext;
import prx.utils.FileUtils;
import prx.utils.XMLUtils;

import sitekick.constant.CacheConstant;
import sitekick.constant.ConfigConstant;
import sitekick.constant.PathConstant;

/**
 *
 * @author Eden
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // load XSD and XSL
        ServletContext servletContext = sce.getServletContext();
        String configXSDPath = servletContext.getRealPath(ConfigConstant.XSD_PATH);
        servletContext.setAttribute(CacheConstant.CONFIG_XSD, configXSDPath);
        String configXMLPath = servletContext.getRealPath(ConfigConstant.XML_PATH);
        servletContext.setAttribute(CacheConstant.CONFIG_XML, configXMLPath);
        // sites XSL
        String sitesXslPath = servletContext.getRealPath(PathConstant.SITES_XSL);
        // sites Detail XSL
        String siteDetailXslPath = servletContext.getRealPath(PathConstant.SITE_DETAIL_XSL);

        try {
            String sitesXslContent = FileUtils.read(sitesXslPath);
            servletContext.setAttribute(CacheConstant.SITES_XSL, sitesXslContent);

            String siteDetailXslContent = FileUtils.read(siteDetailXslPath);
            servletContext.setAttribute(CacheConstant.SITE_DETAIL_XSL, siteDetailXslContent);
            // Get All Sites
            //TODO load from Service to reuse
            loadAllSites(servletContext);

        } catch (Exception e) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, e.getMessage());
        }

    }

    public void loadAllSites(ServletContext servletContext) throws JAXBException, XMLStreamException {
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
            List<TechnologyGroup> technologyGroup = techEntityList.stream().map(t -> {
                TechnologyGroup techGroup = new TechnologyGroup();
                String groupName = t.getTechnologyGroupId() != null ? t.getTechnologyGroupId().getName() : CommonConstant.EMPTY;
                techGroup.setGroupName(groupName);
                return techGroup;
            }).distinct().collect(Collectors.toList());

            // Get Technology Group
            List<TechnologyGroup> technologyList = technologyGroup.stream().map(g -> {
                //get Technology List in each group
                List<Technology> techList = techEntityList
                        .stream()
                        .filter(tech -> tech.getTechnologyGroupId().getName().equals(g.getGroupName()))
                        .map(t -> {
                            Technology technology = new Technology();
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
        String sitesXML = XMLUtils.marshallToString(techStacks);
        servletContext.setAttribute(CacheConstant.SITES_XML, sitesXML);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // do nothing
    }
}
