<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>index</title>
        <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    
<%@include file="../templates/header.jsp" %>

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
                <th>famille</th>
                <th>calorie</th>
                <th>matiere grasse</th>
                <th>acides gras</th>
                <th>glucide</th>
                <th>sucre</th>
                <th>proteine</th>
                <th>sel</th>
            </tr>
        </thead>
        <tbody>
            
                <tr>
                    <td>${description.id}</td>
                    <td>${description.nomProduit}</td>
                    <td><fmt:formatNumber 
                            value="${description.prixHT}"
                            minIntegerDigits="2" 
                            minFractionDigits="2" 
                            maxFractionDigits="2"  /> €</td>
                    <td>${description.categorie.nomCategorie}</td>
                    <td>${description.type.nomType}</td>
                    <td>${description.famille.nom}</td>
                    <td>${description.qualiteNutritive.calorie}</td>
                    <td>${description.qualiteNutritive.matiereGrasse}</td>
                    <td>${description.qualiteNutritive.acidesGras}</td>
                    <td>${description.qualiteNutritive.glucide}</td>
                    <td>${description.qualiteNutritive.sucre}</td>
                    <td>${description.qualiteNutritive.proteine}</td>
                    <td>${description.qualiteNutritive.sel}</td>
                    
                    <td>
                        <c:url value="Controller?section=panier&action=add&id=${description.id}" var="url01" />
                        <a href="${url01}">Ajouter au panier</a>
                    </td>
                </tr>
            
        </tbody>
    </table>
</div>
                    
<%@include file="../templates/footer.jsp" %>

