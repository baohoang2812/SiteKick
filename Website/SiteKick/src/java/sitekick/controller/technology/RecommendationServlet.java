/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.controller.technology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sitekick.dao.TechnologyDAO;
import sitekick.data.TechnologySuggestion;
import sitekick.entity.EntityContext;
import sitekick.services.TechnologyService;
import sitekick.constant.ConfigConstant;
import sitekick.controller.CrawlerServlet;

/**
 *
 * @author Gia Bảo Hoàng
 */
@WebServlet(name = "RecommendationServlet", urlPatterns = {"/RecommendationServlet"})
public class RecommendationServlet extends HttpServlet {

    private static final String SUCCESS = "suggest.jsp";
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
            String[] txtTechIds = request.getParameterValues("txtTechIds");
            List<Integer> idList = new ArrayList();

            if (txtTechIds != null && txtTechIds.length > 0) {
                for (String txtId : txtTechIds) {
                    idList.add(Integer.valueOf(txtId));
                }
            }
            EntityContext entityContext = EntityContext.newInstance();
            TechnologyDAO techDAO = new TechnologyDAO(entityContext.getEntityManager());
            TechnologyService techService = new TechnologyService(techDAO);
            List<TechnologySuggestion> suggestions = techService.suggestTechnology(idList, ConfigConstant.BASE_API_URL);
            request.setAttribute("Suggestions", suggestions);
            url = SUCCESS;
        } catch (Exception e) {
            url = ERROR;
            Logger.getLogger(CrawlerServlet.class.getName()).log(Level.SEVERE, e.getMessage());
            request.setAttribute("Error", e.getMessage());
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
