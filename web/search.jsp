<%-- 
    Document   : search
    Created on : Mar 3, 2024, 5:30:49 PM
    Author     : minht
--%>

<%--<%@page import="trilm.users.UsersDTO"%>
<%@page import="java.util.List"%><!--directives yêu cầu container suppport 1 số thư viện -->--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <!--Lúc tạo file chọn standard syntax-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red"> 
        Welcome, ${sessionScope.USERNAME} <!-- ko đc show username ở đây, phải show fullname -->
        </font> 
        <h1>Search Page</h1>
        <form name="btAction" action="DispatcherServlet">
            Search Value <input type="text" name="txtSearchValue"
                                value="${param.txtSearchValue}" /> <br/>
            <input type="submit" value="Search" name="btAction" /><!--nhớ copy-->
        </form> <br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter"><!--result bây giờ là kiểu string, nên ko có phương thức get-->
                        <form action="DispatcherServlet" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}<!-- index đếm từ 0, count đếm từ 1-->
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    ${dto.password}
                                    <input type="hidden" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullname}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON"
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="DispatcherServlet"><!--value là url đón nhận req để xử lý-->
                                    <!--Tạo URL Rewriting, tên chuỗi url mình tạo ra là biến var, tên là deleteLink, value của nó là tên resource ờ phía server để đc yêu cầu thực hiện, là DispatcherServlet-->
                                        <c:param name="btAction" value="Delete"/><!--thằng đầu tiên tự động biến thành dấu "?"-->
                                        <c:param name="pk" value="${dto.username}"/><!--những thằng còn lại sẽ tự động biến thành dấu "&"-->
                                        <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>                     
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2 color="red">
                No record matched!!!
            </h2>
        </c:if>
    </c:if>
    <%--<%
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
    %>
    <font color="red">Welcome, <%= cookies[cookies.length - 1].getName() %></font>
    <%
            }//end cookis are existed
    %>
    <h1>Search Page</h1>
    <form name="btAction" action="DispatcherServlet">
        Search Value <input type="text" name="txtSearchValue" 
                            value="<%= request.getParameter("txtSearchValue")%>" /> <br/> <!--expression đc gọi là định trị tại thời điểm có request đc gọi là request time, khi người ta nhập giá trị vào bên trong ô search và lấy nút search thì giá trị đó đc đưa về phía server gọi là param, lúc nó chuyển vào thành phần của view parameter vẫn còn tồn tại là do response chưa đc trả về-->
        <input type="submit" value="Search" name="btAction" /><!--nhớ copy-->
    </form><br/>
    <!--quy tắc của scriptlet là dùng để xủ lý, decalration là dùng để khai báo biến và phương thức-->
    <%
        String searchValue = request.getParameter("txtSearchValue");

            if (searchValue != null) {
                List<UsersDTO> result
                        = //nhớ gõ nguyên chữ UsersDTO
                        (List<UsersDTO>) request.getAttribute("SEARCH_RESULT");//để lấy attribute dùng phương thức getAttibute nhưng cần lưu ý là nó sẽ trả cho mình kiểu object nên phải ép kiểu tường minh 

                if (result != null) {//mỗi phần tử của result là DTO
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (UsersDTO dto : result) {
                        String urlRewriting = "DispatcherServlet"
                                + "? btAction=Delete"//giữa các dấu bằng ko có khoảng cách vì nếu có thì trên đường link dẽ tồn tại dấu %20%, trên đường link phải có dấu "?" và 2 dấu "&"
                                + "&username=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;//delete xong r thì mình quay lại trang search
%>
            <form action="DispatcherServlet" method="POST">
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td> 
                        <%= dto.getUsername()%> 
                        <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <%= dto.getPassword()%>
                        <input type="hidden" name="txtPassword" value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getFullname()%>
                    </td>
                    <td>
                        <%= dto.isRole()%>
                        <input type="checkbox" name="chkAdmin" value="ON" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }
                               %>
                               />
                    </td>
                    <td> 
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction" />
                        <input type="hidden" name="lastSearchValue" value="<%= searchValue%>" />
                    </td>
                </tr>
            </form>
            <%
                }//get each dto to process
            %>
        </tbody>
    </table>
    <%
    } else {//search is not found
    %>
    <h2>
        <font color ="red">
        No record is matched!!!
        </font>
    </h2>
    <%//fragment code là chen lẫn java và html nên phải tách 
            }
        }//check first time for searchValue 
%> --%>
</body>
</html>
