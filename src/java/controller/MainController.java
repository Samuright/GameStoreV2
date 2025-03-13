/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GameDAO;
import dao.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.GameDTO;
import model.UsersDTO;

/**
 *
 * @author Quoc Bao
 */
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String HOME_PAGE = "homePage.jsp";
    private static final String REGITER_PAGE = "registerPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        String action = request.getParameter("action");
        try {
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                switch (action) {
                    case "login":
                        url = processLogin(request, response);
                        break;
                    case "logout":
                        url = processLogout(request, response);
                        break;                        
                    case "register":
                        url = REGITER_PAGE;
                        break;                    
                    case "insertNewUser":
                        url = processRegister(request, response);
                        break;
                    case"loadtop5":
                        processTop5Sell(request,response);
                        url="top5.jsp";
                    default: 
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    protected String processLogin(HttpServletRequest request, HttpServletResponse response) {
        String url = LOGIN_PAGE;
        String title = "";
        String username = request.getParameter("txtmail");
        String password = request.getParameter("txtpassword");
        if (username != null && password != null) {
            UsersDTO user = null;
            UsersDAO d = new UsersDAO();
            user = d.checkLogin(username, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                url = processList(request, response);
            } else {
                request.setAttribute("ERROR", "Email or password invalid!");
            }
        }
        return url;
    }

    protected String processLogout(HttpServletRequest request, HttpServletResponse response) {
        String url = HOME_PAGE;
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            session.invalidate();
            request.setAttribute("NOTI", "Logout successfully!!!");
            url = LOGIN_PAGE;
        }
        return url;
    }
    
    protected String processRegister(HttpServletRequest request, HttpServletResponse response){
        String url = REGITER_PAGE;
        String email = request.getParameter("txtmail");
        String username = request.getParameter("txtusername");
        String password = request.getParameter("txtpassword");
        UsersDAO d = new UsersDAO();
        UsersDTO user = new UsersDTO();
        
        int userId = d.getMaxID();
        userId++;
        
        user.setUserId(userId);
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setIsAdmin(0);
        user.setIsBlocked(0);
        user.setDateOfBirth("2005-09-20");
        user.setAddress("o dau do");
        
        if(d.addNewUser(user)){
            url = LOGIN_PAGE;
            request.setAttribute("NOTI", "Register Successfully!");
        }
        return url;
    }
    
    protected String processList(HttpServletRequest request, HttpServletResponse response){
        String title = "";
        String url = LOGIN_PAGE;
        GameDAO d = new GameDAO();
        ArrayList<GameDTO> list = d.list(title);
        HttpSession session = request.getSession();
        session.setAttribute("listGame", list);
        url = HOME_PAGE;
        return url;
    }
    protected void processTop5Sell(HttpServletRequest request, HttpServletResponse response){
        GameDAO dao = new GameDAO();
        dao.loadTop5();
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
