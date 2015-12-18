<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    
    
    <%@include file="../templates/header.jsp" %>
    
<%@include file="navBarClient.jsp" %>
    
        <h1>Panier</h1>

         <c:if test="${empty panier}">
        <p>Votre panier est vide</p>
    </c:if>
    <c:if test="${not empty panier}">
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nom</th>
                    <th>Cuisson</th>
                    <th>prixHt</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${panier}" var="ligne">
                    <c:if test="${ligne.produit != null}">
                    <tr>
                        <td>${ligne.identifiant}</td>
                        <td>${ligne.nom}</td>
                        <td>${ligne.cuisson}</td>
                        <td>
                            <fmt:formatNumber 
                                value="${ligne.produit.prixHT}"
                                minIntegerDigits="2" 
                                minFractionDigits="2" 
                                maxFractionDigits="2"  /> €
                        </td>
                       <c:if test="${ligne.commentaire.contenu != null}">
                            <td>
                            <a href="Controller?section=panier&action=modifierCommenter&id=${ligne.identifiant}"> modifier le commentaire </a>
                        </td>
                        <td>
                            <a href="Controller?section=panier&action=supprimerCommentaire&id=${ligne.identifiant}"> supprimer le commentaire </a>
                        </td>
                        </c:if>
                        
                        <c:if test="${ligne.commentaire.contenu == null}">
                            <td>
                            <a href="Controller?section=panier&action=commenter&id=${ligne.identifiant}"> laisser un commentaire </a>
                        </td>
                        </c:if>
                       
                        <td>
                            <a href="Controller?section=panier&action=remove&id=${ligne.identifiant}"> supprimer </a>
                        </td>
                        
                        <c:if test="${ligne.commentaire.contenu != null}">
                            <td class="text-success">${ligne.commentaire.contenu}</td>
                        </c:if>
                    </tr>
                    </c:if>
                    
                    <c:if test="${ligne.menu != null}">
                    <tr>
                        <td>${ligne.identifiant}</td>
                        <td>${ligne.nom} et id : ${ligne.menu.id}<br>
                            <c:forEach var="lc" items="${ligne.menu.ligneCommandes}">
                                <ul>
                                    <li class="text-primary">${lc.nom}</li>
                                </ul> 
                            </c:forEach>
                        </td>
                        
                        <td>${ligne.cuisson}</td>
                        <td>
                            <fmt:formatNumber 
                                value="${ligne.menu.prix}"
                                minIntegerDigits="2" 
                                minFractionDigits="2" 
                                maxFractionDigits="2"  /> €
                        </td>
                       <c:if test="${ligne.commentaire.contenu != null}">
                            <td>
                            <a href="Controller?section=panier&action=modifierCommenter&id=${ligne.identifiant}"> modifier commentaire </a>
                        </td>
                        <td>
                            <a href="Controller?section=panier&action=supprimerCommentaire&id=${ligne.identifiant}"> supprimer le commentaire </a>
                        </td>
                        </c:if>
                        
                        <c:if test="${ligne.commentaire.contenu == null}">
                            <td>
                            <a href="Controller?section=panier&action=commenter&id=${ligne.identifiant}"> laisser un commentaire </a>
                        </td>
                        </c:if>
                       
                        <td>
                            <a href="Controller?section=panier&action=remove&id=${ligne.identifiant}"> supprimer </a>
                        </td>
                        
                        <c:if test="${ligne.commentaire.contenu != null}">
                            <td class="text-success">${ligne.commentaire.contenu}</td>
                        </c:if>
                    </tr>
                    </c:if>
                </c:forEach>
            <tfoot>
                <tr>
                    <td colspan="5"> Total HT : </td>
                    <td>
                        <fmt:formatNumber 
                                value="${total}"
                                minIntegerDigits="2" 
                                minFractionDigits="2" 
                                maxFractionDigits="2"  /> €
                    </td>
                </tr>
            </tfoot>
            </tbody>
        </table>
<p class="text-danger">vous avez la possibilité de laisser une note à chaque produit commandé afin de préciser vos préférences, pour cela cliquer sur "commenter"</p>


<a href="Controller?section=panier&action=commander"><button class="btn btn-primary">valider ma commande <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></button></a>
    
<h3 class="well">test pour identifier si LigneCommande contient un menu ou un produit</h3>
<c:forEach var="ligne" items="${panier}">
        <c:if test="${ligne.produit != null}">
    <p class="text-primary">id ligne commande <span class="text-success">produit</span> ajouté : ${ligne.identifiant}</p>
        </c:if>
        <c:if test="${ligne.menu != null}">
            <p class="text-primary">id ligne commmande <span class="text-success">menu</span> ajouté : ${ligne.identifiant}</p>
        </c:if>
    </c:forEach>
    
    </c:if>
            
            
            <c:if test="${commande != null}">
                <hr>
                temporairement pour phase de test :
                <p class="well">Récapitulatif de la commande: ${commande}</p>
                <hr>
            </c:if>

        
    <%@include file="../templates/footer.jsp" %>