<%-- 
    Document   : newjsp
    Created on : Jul 19, 2020, 5:36:53 PM
    Author     : Gia Bảo Hoàng
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <%@include file="commonCss.jsp" %>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Login</h1>
        <c:set var="invalidInfo" value="${requestScope.INVALID}"/>
        <c:if test="${not empty invalidInfo}">
            <font color="red">
            <h4>${invalidInfo}</h4>
            </font>
        </c:if>
        <form action="AccountServlet" method="POST">
            <h3>Username: </h3>
            <input type="text" name="txtUsername" required="true"/>
            <br>
            <h3>Password: </h3>
            <input type="password" name="txtPassword" required="true"/>
            <br>
            <input type="submit" name="action" value="Login"/>
        </form>

    </body>
</html>
