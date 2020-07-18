<%-- 
    Document   : error
    Created on : Jul 12, 2020, 2:13:05 PM
    Author     : Gia Bảo Hoàng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR Page</title>
    </head>
    <body>
        <h1>Oops... There is an error. Contact administrator for more information!</h1>
        <h3>
            Error description: 
        </h3>
        <p>
            <font color="red">
            ${requestScope.Error}
            </font>
        </p>
    </body>
</html>
