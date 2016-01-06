<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<body onload="actualiser();return false">
    <h1 class="text-center page-header">BAHIA BEACH</h1>

        <div class="nav text-center">
            <a href="Controller?section=admin&action=jeuxDonnee">lien vers jeu de donnees</a>
            <a href="Controller?section=cuisine&action=afficherCuisine">lien vers Cuisine</a>
            <a href="Controller?section=serveur&action=voirCommande"> lien vers Voir les Commandes</a>
            <a href="Controller?section=log&action=log">lien vers la connexion log</a>
            <a href="Controller?section=client&action=initTable">lien vers interface client</a>

        </div>    
        <c:if test="${not empty msg}">
            <p class="alert alert-success text-center">${msg}</p>
        </c:if>

    <div class="container">
        


