<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>cuisine</title>
        <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <%@include file="templates/header.jsp" %>
        <h1>Cuisine</h1>
        <c:forEach items="${cmd}" var="p">
            ${p.nomProduit}<br>
        </c:forEach>
<%@include file="templates/footer.jsp" %>
