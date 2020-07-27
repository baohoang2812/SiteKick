/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import sitekick.dao.SiteDAO;
import sitekick.entity.EntityContext;
import sitekick.services.SiteService;
import sitekick.utils.FileUtils;

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
            EntityContext entityContext = EntityContext.newInstance();
            SiteDAO siteDAO = new SiteDAO(entityContext.getEntityManager());
            SiteService siteService = new SiteService(siteDAO);
            String sitesXML = siteService.getAllSitesXMLString();
            servletContext.setAttribute(CacheConstant.SITES_XML, sitesXML);
        } catch (Exception e) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, e.getMessage());
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // do nothing
    }
}
