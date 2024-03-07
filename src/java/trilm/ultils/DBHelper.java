/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trilm.ultils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author minht
 */
public class DBHelper implements Serializable {
//    public static Connection getConnection()//các method liên quan đến helper, mang tính chất là library để hỗ trợ xử lý mà ko cần tạo object nên có chữ static để tránh việc cấp phát vùng nhớ

//            throws ClassNotFoundException, SQLException{ 
    //1. load driver, điều kiện tiên quyết là phải có driver: Rightclick Libraries + add sqljdbc4.jar
//    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//add driver r mình lỗi mà ko cái dòng này ko bung thì: Rightclick vào sqljdbc4.jar + remove + add lại
    //2. create connection String
//    String url = "jdbc:sqlserver://localhost:1433;"
//            + "databaseName=PRJ301";
    //3. open connection
//    Connection con = DriverManager.getConnection(url, "sa", "123456");
//    return con;
    
    
    public static Connection getConnection()
            throws /*ClassNotFoundException*/ SQLException, NamingException {//các method liên quan đến helper, mang tính chất là library để hỗ trợ xử lý mà ko cần tạo object nên có chữ static để tránh việc cấp phát vùng nhớ

        //1.get current context
        Context currentContext = new InitialContext();
        //2.Lookup tomcat context
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env"); 
        //3.Lookup DS
        DataSource ds = (DataSource) tomcatContext.lookup("DS007");
        //4.Open connection from DS
        Connection con = ds.getConnection();

        return con;
    }
}
