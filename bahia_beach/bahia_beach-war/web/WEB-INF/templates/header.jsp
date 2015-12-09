<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


    <body>
        <div class="container">
            <h1 class="text-center page-header">BAHIA BEACH</h1>
           
        <div class="well text-center">
        <a href="Controller?section=admin&action=jeuxDonnee">lien vers jeux donnees |</a>
        <a href="Controller?section=cuisine">lien vers Cuisne |</a>
        <a href="Controller?section=carte&action=carte">lien vers ma carte |</a>
        <a href="Controller?section=serveur&action=voirCommande"> lien vers Voir lesCommandes |</a>
        <a href="Controller?section=connexion">lien vers la connexion |</a>
        <a href="Controller?section=panier&action=afficherPanier">lien vers le panier |</a>
      
        </div>    
            <c:if test="${not empty msg}">
    <p class="alert alert-success text-center">${msg}</p>
</c:if>

