<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cuisine</title>
    </head>
    <body>
        <h1>Cuisine</h1>
        <c:forEach items="${cmd}" var="lc">
            ${lc.produit.nomProduit}<br>
            <hr>
        </c:forEach>
    </body>
</html>
