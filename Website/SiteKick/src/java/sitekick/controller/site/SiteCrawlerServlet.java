/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.controller.site;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import prx.config.Config;
import sitekick.crawler.SiteKickCrawler;
import sitekick.constant.ConfigConstant;

/**
 *
 * @author Eden
 */
@WebServlet(name = "SiteCrawlerServlet", urlPatterns = {"/SiteCrawlerServlet"})
public class SiteCrawlerServlet extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "index.html";

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
            SiteKickCrawler crawler = new SiteKickCrawler();
            ServletContext servletContext = getServletContext();
            String xsdPath = servletContext.getRealPath(ConfigConstant.XSD_PATH);
            String xmlPath = servletContext.getRealPath(ConfigConstant.XML_PATH);
            Config config = crawler.loadConfiguration(xsdPath, xmlPath);
            if (config != null && config.getAlexa() != null) {
                crawler.parseSite(config.getAlexa(), servletContext);
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
