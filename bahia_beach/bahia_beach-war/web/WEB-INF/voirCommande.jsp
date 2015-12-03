<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>les Commandes</title>
    </head>
    <body>
        <h1>Les Commandes</h1>
        <table><tr><td>numero commande</td><td>date emission</td></tr>
        <c:forEach items="${lescommandes}" var="c">
            <tr><td>${c.numero}</td><td>${c.date}</td></tr>
        </c:forEach>
        </table>
    </body>
</html>