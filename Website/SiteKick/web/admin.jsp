<%-- 
    Document   : admin
    Created on : Jul 19, 2020, 6:39:01 PM
    Author     : Gia Bảo Hoàng
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <%@include file="commonCss.jsp" %>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Admin Dashboard</h1>
        <c:set var="info" value="${requestScope.INFO}"/>
        <c:if test="${not empty info}">
            <font color="blue">
            <h4>${info}</h4>
            </font>
        </c:if>
        <h3>Crawl Sites</h3>
        <form action="CrawlerServlet">
            <input type="submit" name="action" value="Site"/>
        </form>
        <h3>Crawl Technologies</h3>
        <form action="CrawlerServlet">
            <input type="submit" name="action" value="Tech"/>
        </form>
    </body>
</html>
