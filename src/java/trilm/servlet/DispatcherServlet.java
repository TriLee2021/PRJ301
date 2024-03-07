/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trilm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author minht
 */
@WebServlet(name = "DispatcherServlet", urlPatterns = {"/DispatcherServlet"})
public class DispatcherServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastNameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteServlet";
    private final String PROCESS_REQUEST_CONTROLLER = "ProcessRequestSevlet";
    private final String ADD_ITEM_TO_YOUR_CART = "AddItemToCartServlet";
    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemFromCartServlet";
    private final String VIEW_YOUR_CART = "viewCart.jsp";
    

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
        
        String url = LOGIN_PAGE;
        //which button did user click?
        String button = request.getParameter("btAction");//giá trị này là giá trị tất cả, phải copy 
        try {
            if(button == null){
                url = PROCESS_REQUEST_CONTROLLER;
            }else if(button.equals("Login")){
                url = LOGIN_CONTROLLER;
            } else if(button.equals("Search")){
                url = SEARCH_LASTNAME_CONTROLLER;
            } else if (button.equals("Delete")){
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Add Item To Your Cart")){
                url = ADD_ITEM_TO_YOUR_CART;
            } else if (button.equals("View Your Cart")){
                url = VIEW_YOUR_CART;
            } else if (button.equals("Remove Selected Items")){
                url = REMOVE_ITEM_FROM_CART_CONTROLLER;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
