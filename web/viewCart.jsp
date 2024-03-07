<%-- 
    Document   : viewCart
    Created on : Mar 7, 2024, 9:20:21 PM
    Author     : minht
--%>

<%@page import="java.util.Map"%>
<%@page import="trilm.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <h1>Your Cart include:</h1>
        <%
            //1. Customer goes to his/her cart place
            if (session != null) {
                //2. Custiomer takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer takes items from cart
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. show items
        %>
        <form action="DispatcherServlet">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (String key : items.keySet()) {
                    %>
                    <tr>
                        <td>
                            <%= ++count%>
                        </td>
                        <td>
                            <%= key%>
                        </td>
                        <td>
                            <%= items.get(key)%>
                        </td>
                        <td>
                            <input type="checkbox" name="chkItem" value="<%= key%>" /> 
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="onlineShopping.html">Add more Items to Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Selected Items" name="btAction" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <%
                        return;
                    }
                }
            }
        %>
        <h2>
            <font color="red">
            No cart exits!!!
            </font>
        </h2>
    </body>
</html>
