<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>index</title>
        <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    
<%@include file="../../templates/header.jsp" %>

<%@include file="../navBarServeur.jsp" %>

    <%@include file="navBarClient.jsp" %>
<h1>Ma CARTE</h1>

<div>
    <table border="1" >
        <thead>
            <tr>
                <th>ID</th>
                <th>nom</th>
                <th>Prix Ht/u</th>
                <th>categorie</th>
                <th>type</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${produits}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.nomProduit}</td>
                    <td><fmt:formatNumber 
                            value="${p.prixHT}"
                            minIntegerDigits="2" 
                            minFractionDigits="2" 
                            maxFractionDigits="2"  /> €</td>
                    <td>${p.categorie.nomCategorie}</td>
                    <td>${p.type.nomType}</td>
                    
                    <td>
                        <c:url value="Controller?section=server&action=add&id=${p.id}" var="url01" />
                        <a href="${url01}">Ajouter au panier</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="../../templates/footer.jsp" %>

