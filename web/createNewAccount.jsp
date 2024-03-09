<%-- 
    Document   : createNewAccount
    Created on : Mar 8, 2024, 1:38:58 PM
    Author     : minht
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>
            <h1>Registration</h1>
            <form action="DispatcherServlet" method="POST">
                <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
                
                Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 - 20 chars)<br/>
                <c:if test="${not empty errors.usernameLengthError}">
                    <font color="red">
                    ${errors.usernameLengthError}
                    </font><br/>
                </c:if>
                    
                <c:if test="${not empty errors.usernameIsExistedError}">
                    <font color="red">
                    ${errors.usernameIsExistedError}
                    </font><br/>
                </c:if>
                    
                Password* <input type="password" name="txtPassword" value="" />(6 - 30 chars)<br/>  
                <c:if test="${not empty errors.passwordLengthError}">
                    <font color="red">
                    ${errors.passwordLengthError}
                    </font>
                    <br/>
                </c:if>
                    
                Confirm* <input type="password" name="txtConfirm" value="" />(6 - 30 chars)<br/>  
                <c:if test="${not empty errors.confirmLengthError}">
                    <font color="red">
                    ${errors.confirmLengthError}
                    </font>
                    <br/>
                </c:if>
                    
                Full name*<input type="text" name="txtFullname" value="${params.txtFullname}" />2 - 5 chars <br/>
                <c:if test="${not empty errors.fullnameLengthError}">
                    <font color="red">
                    ${errors.fullnameLengthError}
                    </font>
                    <br/>
                </c:if>
                <input type="submit" value="Create New Account" name="btAction" />(2 - 5 chars)<br/>
                <input type="reset" value="Reset" />
            </form>
        </div>
    </body>
</html>

