/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.controller.site;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import prx.config.Config;
import prx.dao.SiteDAO;
import prx.entity.EntityContext;
import prx.entity.Site;
import sitekick.constant.ConfigConstant;
import sitekick.crawler.SiteKickCrawler;

/**
 *
 * @author Eden
 */
@WebServlet(name = "AnalysisServlet", urlPatterns = {"/AnalysisServlet"})
public class AnalysisServlet extends HttpServlet {
    private static final String SUCCESS = "siteDetail.jsp";
    private static final String ERROR = "error.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String siteUrl = request.getParameter("txtUrl");
            if (siteUrl != null && !siteUrl.trim().isEmpty()) {
                URL urlObj = new URL(siteUrl);
                String domain = urlObj.getHost();
                String siteURL = domain.replace("www.", "");
                EntityContext entityContext = EntityContext.newInstance();
                SiteDAO siteDAO = new SiteDAO(entityContext.getEntityManager());
                Site site = siteDAO.getFirstSiteByURL(siteURL);
                if (null != site) {
                    request.setAttribute("SITE", site);
                } else {
                    Site newSite = new Site();
                    newSite.setUrl(siteURL);
                    entityContext.beginTransaction();
                    siteDAO.create(site);
                    entityContext.commitTransaction();
                    SiteKickCrawler crawler = new SiteKickCrawler();
                    ServletContext servletContext= request.getServletContext();
                    String xsdPath = servletContext.getRealPath(ConfigConstant.XSD_PATH);
                    String xmlPath = servletContext.getRealPath(ConfigConstant.XML_PATH);
                    
                    Config config = crawler.loadConfiguration(xsdPath, xmlPath);
                    Set<String> domainSet = new HashSet();
                    domainSet.add(siteURL);
                    crawler.parseBuiltWith(domainSet, config.getBuiltWith(), servletContext);
                    request.setAttribute("SITE", site);
                }
                url = SUCCESS;
            }

        } catch (Exception e) {
            Logger.getLogger(SiteCrawlerServlet.class.getName()).log(Level.SEVERE, e.getMessage());
            request.setAttribute("Error", e.getMessage());
            url = ERROR;
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
