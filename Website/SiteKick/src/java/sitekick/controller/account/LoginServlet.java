/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitekick.controller.account;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import prx.dao.AccountDAO;
import prx.entity.Account;
import prx.entity.EntityContext;
import sitekick.controller.CrawlerServlet;

/**
 *
 * @author Gia Bảo Hoàng
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "admin.jsp";
    private static final String INVALID = "login.jsp";

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
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            EntityContext entityContext = EntityContext.newInstance();
            AccountDAO accountDAO = new AccountDAO(entityContext.getEntityManager());
            entityContext.beginTransaction();
            Account account = accountDAO.checkLogin(username, password);
            entityContext.commitTransaction();
            boolean isValid = true;
            if (account == null) {
                isValid = false;
                request.setAttribute("INVALID", "Invalid Username or Password");
            }
            if (isValid) {
                HttpSession session = request.getSession();
                session.setAttribute("Account", account);
                url = SUCCESS;
            } else {
                url = INVALID;
            }
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
