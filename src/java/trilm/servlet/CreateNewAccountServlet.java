/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trilm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trilm.users.UsersCreateError;
import trilm.users.UsersDAO;
import trilm.users.UsersDTO;

/**
 *
 * @author minht
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createNewAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        //1. get all parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String fullname = request.getParameter("txtFullname");
        String confirm = request.getParameter("txtConfirm");

        boolean foundError = false;
        UsersCreateError errors = new UsersCreateError();

        String url = ERROR_PAGE;
        try {
            //2. check user's error
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundError = true;
                errors.setUsernameLengthError("Username is required typing from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthError("Password is required typing from 6 to 30 characters");
            }
            else if (!confirm.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmLengthError("Confirm must match Password");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 30) {
                foundError = true;
                errors.setFullnameLengthError("Full name is required typing from 6 to 30 characters");
            } 
            if (foundError) {
                //catching a specific attributr then go to error page to show
                request.setAttribute("CREATE_ERRORS", errors);
            } else {//no error
                //3. call DAO | Create Account
                UsersDAO dao = new UsersDAO();
                UsersDTO dto = new UsersDTO(username, password, fullname, foundError);
                boolean result = dao.createAccount(dto);
                //4. process result
                if (result) {
                    url = LOGIN_PAGE;
                }//create new account is success.
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountServlet _ SQL: " + msg);//lỗi hệ thống thì ghi log
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExistedError(username + " is existed!!!!");//errros là 1 attribute, cập nhật giá trị, thay đổi giá trị nên phải setAttribute
                request.setAttribute("CREATE_ERRORS", errors);
            }//username is existed
        } catch (NamingException ex) {
            log("CreateNewAccountServlet _ Naming: " + ex.getMessage());
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
