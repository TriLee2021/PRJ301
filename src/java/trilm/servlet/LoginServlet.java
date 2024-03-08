/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trilm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trilm.users.UsersDAO;

/**
 *
 * @author minht
 */
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";//tất cả những URL liên quan đến server ko đc nhúng ở bên trong thành phần lập trình theo dạng chuỗi mà tất cả phải define bằng biến hằng để lúc cần cập nhật thì dễ thay đổi, tên biến hằng tất cả phải viết bằng chữ in
    //private final String SEARCH_PAGE = "search.html";
    private final String SEARCH_PAGE = "search.jsp";

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

        response.setContentType("text/html;charset=UTF-8");//1 khi response đc trả về thì nó phải xác định coi response đang trả về ở định dạng gì và setContentType() giúp cho Request Message biết rằng nó đưa về dạng gì và browser dựa trên dạng này để xử lý, dạng text/html và format theo dạng charset=UTF-8

        //PrintWriter out = response.getWriter();//Print Writter thực chất đang thực hiện hành động setValue, muốn setValue vào bên trong thành phần của HTML String thì dùng đối tượng tên là printWriter, lấy từ responseObject với phương thức getWriter(), out là thành phần để giúp đưa giá trị vào bên trong thành phần của responseObject - đưa nó trờ thành HTML String
        //bỏ luôn dòng này vì ko cần trình bày giao diện trên servlet
        
        String username = request.getParameter("txtUsername");//get theo name để lấy value, case sensitive, phải copy chỗ này chứ ko đc ghi tay, sai thì nullPonterException vì nó ko tồn tại
        String password = request.getParameter("txtPassword");
        String button = request.getParameter("btAction");
        String url = INVALID_PAGE;

        try {
//            out.println("User " + username + " _ password " + password + " _ button " + button);
//            System.out.println("User" + username + " _ password " + password + " _ button " + button);
            //if (button.equals("Login")) {//những chữ mà reference từ bên ngoài vào thì phải copy
            //điều phối thì chỉ xủ 7 lý chức năng thôi chứ ko cần biết 
            
            //1. Call Model/DAO
            UsersDAO dao = new UsersDAO();
            boolean result = dao.checkLogin(username, password);

            if (result) {
                url = SEARCH_PAGE;
                HttpSession session = request.getSession();
                session.setAttribute("USERNAME", username);
                //get fullname from username via DAO
                //session.setAttribute("FULLNAME", fullname);
                
                //add cookie to client using resObj
//                url = SEARCH_PAGE;
//                Cookie cookies = new Cookie(username, password);
//                cookies.setMaxAge(60*3);
//                response.addCookie(cookies);
            }//end if user and password are matched!!!
        }//end if user has click Login button
        catch (SQLException ex) {//mọi catch lỗi bắt ở đây, mình là người xử lý lỗi, chứ ko phải ai khác
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            //response.sendRedirect(url);
            //out.close();//ko cần tạo và cũng ko cần cấp phát nên xóa luôn dòng này
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
