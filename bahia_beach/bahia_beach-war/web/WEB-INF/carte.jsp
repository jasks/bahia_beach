<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    
<%@include file="templates/header.jsp" %>


<h1>Ma CARTE</h1>


<div>
    <table border="1" >
        <thead>
            <tr>
                <th>ID</th>
                <th>nom</th>

            </tr>
        </thead>
        <tbody>
            <tr><td>...</td><td>tout selectionner</td>
                <td>
                    <c:url value="Controller?section=carte&action=produits&notype" var="url01" />
                    <a href="${url01}">Voir</a>
                </td>
            </tr>
            <c:forEach items="${types}" var="t">
                <tr>
                    <td>${t.id}</td>
                    <td>${t.nomType}</td>
                    <td>
                        <c:url value="Controller?section=carte&action=produits&type=${t.id}" var="url01" />
                        <a href="${url01}">Voir</a>
                    </td>
                </tr>
            </c:forEach>
                <td>
                        <c:url value="Controller?section=panier&action=afficherPanier" var="url01" />
                        <a href="${url01}">Voir</a>
                    </td>
        </tbody>
    </table>
</div>
                    
                    <%@include file="templates/footer.jsp" %>

