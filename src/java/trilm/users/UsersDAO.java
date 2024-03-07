/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trilm.users;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import trilm.ultils.DBHelper;

/**
 *
 * @author minht
 */
public class UsersDAO implements Serializable {

    private List<UsersDTO> accounts; //là 1 đối tượng để đón nhận data ở bên dưới DB và mapping lên

    public boolean checkLogin(String username, String password)//phương thức để access lấy dữ liệu từ databse
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;//tất cả phải khai báo
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            //1.Connect Database
            con = DBHelper.getConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Select username "//phải có dấu cách, nếu ko có thì 
                        + "FROM Users "
                        + "WHERE username = ? "
                        + "AND password = ?";
                //3.Create SQL Statement
                stm = con.prepareStatement(sql);//khi tạo câu lệnh thì bao gồm luôn cả thiết lập tham số 
                stm.setString(1, username);//có 2 dấu chấm "?" thì 2 tham số, ở DB thì đếm luôn bắt đầu từ 1, ko phải 0
                stm.setString(2, password);
                //4.Execute Query
                rs = stm.executeQuery();//viết code thực thi dựa trên Statement
                //5.Process Result
                if (rs.next()) {
                    result = true;
                }
            }//process when connection is existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();//khai báo trên r thì ở dưới nhớ đóng, khai báo theo chiều thuận
            }
        }
        return result;
    }

    public List<UsersDTO> getAccounts() {//phát sinh getter để lấy dữ liệu
        return accounts;
    }

    public void SearchLastname(String searchValue)
            throws NamingException, SQLException {
        Connection con = null;//tất cả phải khai báo
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Connect Database
            con = DBHelper.getConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Users "
                        + "Where lastname Like ?";
                //3.Create SQL Statement
                stm = con.prepareStatement(sql);//khi tạo câu lệnh thì bao gồm luôn cả thiết lập tham số 
                stm.setString(1, "%" + searchValue + "%");//có 2 dấu chấm "?" thì 2 tham số, ở DB thì đếm luôn bắt đầu từ 1, ko phải 0
                //4.Execute Query
                rs = stm.executeQuery();//viết code thực thi dựa trên Statement
                //5.Process Result
                while (rs.next()) {//nhiều dòng xài while, 1 dòng xài if, mỗi thành phần mình lấy ra là thành phần rs sẽ lấy giá trị
                    String username = rs.getString("username");//phần get mình lấy cột, lấy chính xác tên cột xuống dưới 
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    //map DTO
                    UsersDTO dto = new UsersDTO(username, password, fullname, role);
                    //add vào trong thành phần list và kiểm tra coi list tồn tại hay chưa
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//end account are not existed//có r mới add, ch 
                    this.accounts.add(dto);
                }//end traverse Result Set
            }//process when connection is existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();//khai báo trên r thì ở dưới nhớ đóng, khai báo theo chiều thuận
            }
        }
    }

    public boolean DeleteAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;//tất cả phải khai báo
        PreparedStatement stm = null;
        //ResultSet rs = null;//khi thực hiện các câu lệnh insert delete update, kết quả trả về là số dòng hiệu lực nên sẽ xóa result set
        boolean result = false;

        try {
            //1.Connect Database
            con = DBHelper.getConnection();
            if (con != null) {
                //2.Create SQL String
                String sql = "Delete From Users "
                        + "Where username = ?";//nếu ghi "delete * from" thì nó sẽ ko phát sinh lỗi nhưng sẽ ko có dòng nào hiệu lực
                //3.Create SQL Statement
                stm = con.prepareStatement(sql);//khi tạo câu lệnh thì bao gồm luôn cả thiết lập tham số 
                stm.setString(1, username);//có 2 dấu chấm "?" thì 2 tham số, ở DB thì đếm luôn bắt đầu từ 1, ko phải 0
                //4.Execute Query
                int row = stm.executeUpdate();//viết code thực thi dựa trên Statement
                //5.Process
                if (row > 0) {
                    result = true;
                }
            }//process when connection is existed
        } finally {
            //if (rs != null) {
                //rs.close();
            //}
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();//khai báo trên r thì ở dưới nhớ đóng, khai báo theo chiều thuận
            }
        }
        return result;
    }
}
