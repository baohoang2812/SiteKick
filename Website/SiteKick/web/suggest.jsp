<%-- 
    Document   : suggest
    Created on : Jul 20, 2020, 7:52:06 PM
    Author     : Gia Bảo Hoàng
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Technology Suggestions</title>
        <%@include file="commonCss.jsp" %>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Technology Suggestion</h1>
        <c:set value="${requestScope.Suggestions}" var="suggestions"/>
        <c:if test="${not empty suggestions}">
            <c:forEach items="${suggestions}" var="suggest" varStatus="counter">
                <c:if test="${not empty suggest.technologyName}">
                    <h3>Technology : ${suggest.technologyName}</h3>
                </c:if>
                <c:if test="${not empty suggest.suggestTechnologyName}">
                    <h3>Suggest Technology : ${suggest.suggestTechnologyName}</h3>
                </c:if>
                <c:if test="${not empty suggest.score}">
                    <h4>Score: ${suggest.score}</h4>
                </c:if>
                <hr>
            </c:forEach>
        </c:if>
    </body>
</html>
