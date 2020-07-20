<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Site Kick</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@include file="commonCss.jsp" %>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div>
            <h3><a href="AccountServlet?action=Signin">Login</a></h3>
            <h1>Site Kick</h1>
            <h3>
                BoostUp your Business & Get <br>
                top of Search Engine
            </h3>
            <c:set var="invalidInfo" value="${requestScope.Invalid}"/>
            <c:if test="${not empty invalidInfo}">
                <font color="red">
                <h4>${invalidInfo}</h4>
                </font>
            </c:if>
            <form action="SiteProcessServlet">
                <input type="url"  name="txtDomain" placeholder="http://www.yourdomain.com" required/>  
                <input type="submit" name="action" value="Analyze">
            </form>
        </div>
    </body>
</html>
