/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trilm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trilm.users.UsersDAO;
import trilm.users.UsersDTO;

/**
 *
 * @author minht
 */
@WebServlet(name = "SearchLastNameServlet", urlPatterns = {"/SearchLastNameServlet"})
public class SearchLastNameServlet extends HttpServlet {//1 servlet chỉ làm đúng 1 chức năng, là nơi chuyển xử lý chứ ko xử lý, servlet k phải là nơi trình bày giao diện, trang html là 1 trang tĩnh

    private final String SEARCH_PAGE = "search.html";//nếu như ko nhập gì cả thì sẽ quay lại trang search 
    private final String SEARCH_RESULT_PAGE = "search.jsp";//trang dữ liệu động

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

        String searchValue = request.getParameter("txtSearchValue");
        String url = SEARCH_PAGE;

        try {
            if (searchValue.trim().length() > 0) {//search trừ khi có trim nhằm trường hợp value có whitespace và ko nhập gì cả
                //call DAO
                UsersDAO dao = new UsersDAO();
                
                dao.SearchLastname(searchValue);
                List<UsersDTO> result = dao.getAccounts();//lấy kết quả search 
                
                request.setAttribute("SEARCH_RESULT", result);//send cho search.jsp theo dạng địa chỉ cho 
                url = SEARCH_RESULT_PAGE;
            }//end if searchValue has value 
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);//để chuyển từ controller đi qua trang search mà vẫn giữ nguyên giá trị của result
            rd.forward(request, response);//dùng forward nhưng tại sao không dùng send redirect vì request object bị hủy mà attribute thuộc về reqobj
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
