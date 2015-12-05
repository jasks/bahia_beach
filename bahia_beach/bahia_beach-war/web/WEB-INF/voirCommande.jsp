<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>voir commande</title>
        <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    
<%@include file="templates/header.jsp" %>

        <h1>Les Commandes</h1>
        <table><tr><td>numero commande</td><td>date emission</td></tr>
        <c:forEach items="${lescommandes}" var="c">
            <tr><td>${c.numero}</td><td>${c.date}</td></tr>
        </c:forEach>
        </table>
        
<%@include file="templates/footer.jsp" %>