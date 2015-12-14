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
                   
 <table border="1">
            <thead>
                <tr>
                   <th>Nom du produit</th>
                   <th>Etat du produit</th>
                   <th>Date</th>
                   <th>Serveur</th>
                   <th>Table</th>
                   <th>Modifier l'état du produit</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${cmd}" var="c">
            <c:forEach items="${c.ligneCommandes}" var="p">
            <tr>
                <td>${p.produit.nomProduit}</td>
                <td>${p.etat}</td>
                <td>${c.date}</td>
                <td>${c.serveur}</td>
                <td>${c.table}</td>
                <td><a href="Controller?section=cuisine&action=modifierEtat&id=${p.id}">modifier Etat du produit</a></td>
            </tr>
            </c:forEach>  
        </c:forEach>        
            </tbody>
        </table>

<%@include file="templates/footer.jsp" %>