<%-- 
    Document   : siteDetail
    Created on : Jul 19, 2020, 11:20:26 PM
    Author     : Gia Bảo Hoàng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Site Detail</title>
        <%@include file="commonCss.jsp" %>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Site Detail</h1>
        <x:transform doc="${applicationScope['sitesXML']}" xslt="${applicationScope['siteDetailXSL']}" >
            <x:param name="siteName" value="${param.siteName}"/> 
        </x:transform>
</body>
</html>
