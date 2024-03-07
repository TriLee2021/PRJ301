/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trilm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trilm.cart.CartObject;

/**
 *
 * @author minht
 */
@WebServlet(name = "RemoveItemFromCartServlet", urlPatterns = {"/RemoveItemFromCartServlet"})
public class RemoveItemFromCartServlet extends HttpServlet {

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
        try {
            //1. Customer goes to his/her cart
            HttpSession session = request.getSession(false);
            if(session != null){
                //2. Customer takes his/her cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if(cart != null){
                    //3. Customer takes item
                    Map<String, Integer> items = cart.getItems();
                    if(items != null){
                        //4. Get all selected items
                        String[] removedItems = request.getParameterValues("chkItem");
                        if(removedItems != null){
                            //5. remove eah item from cart
                            for (String item : removedItems) {
                                cart.removeItemFromCart(item);
                            }//end traverse each item
                            session.setAttribute("CART", cart);
                        }//end removedItem has choices
                    }//end items have existed
                }//end if cart has existed
            }//session has existed
        } finally {
            String urlRewriting = "DispatcherServlet"
                                + "?btAction=View Your Cart";
            response.sendRedirect(urlRewriting);
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
