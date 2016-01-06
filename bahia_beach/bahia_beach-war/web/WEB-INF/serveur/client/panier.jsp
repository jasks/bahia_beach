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
    
    
    <%@include file="../../templates/header.jsp" %>
    
<%@include file="../navBarServeur.jsp" %>
   
    <%@include file="navBarClient.jsp" %>
    
        <h1>Panier</h1>

         <c:if test="${empty panier}">
        <p>Votre panier est vide</p>
    </c:if>
    <c:if test="${not empty panier}">
        <table class="table table-hover" >
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nom</th>
                    <th>prixHt</th>
                    <th>Action</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${panier}" var="ligne">
                    <c:if test="${ligne.produit != null}">
                    <tr>
                        <td>${ligne.identifiant}</td>
                        <td>${ligne.nom}
                        <c:if test="${ligne.produit.categorie.nomCategorie == 'Viande'}">
                            <br>
                            <form method="get" action="Controller">
       <input type="hidden" name="section" value="server" />
            <input type="hidden" name="action" value="setCuisson" />
            <input type="hidden" name="id" value="${ligne.identifiant}" />
            <label>cuisson </label>
            <select name="cuisson" onchange="this.form.submit()">
        <option value="1">bleu</option>
        <option value="2">saignant</option>
        <option value="3">à point</option>
        <option value="4">cuit</option>
    </select>
       

</form>
                        </c:if>
                            </td>
                        <td>
                            <fmt:formatNumber 
                                value="${ligne.produit.prixHT}"
                                minIntegerDigits="2" 
                                minFractionDigits="2" 
                                maxFractionDigits="2"  /> €
                        </td>
                       <c:if test="${ligne.commentaire.contenu != null}">
                            <td>
                            <a href="Controller?section=server&action=modifierCommenter&id=${ligne.identifiant}"> modifier le commentaire </a>
                        </td>
                        <td>
                            <a href="Controller?section=server&action=supprimerCommentaire&id=${ligne.identifiant}"> supprimer le commentaire </a>
                        </td>
                        </c:if>
                        
                        <c:if test="${ligne.commentaire.contenu == null}">
                            <td>
                            <a href="Controller?section=server&action=commenter&id=${ligne.identifiant}"> laisser un commentaire </a>
                        </td>
                        </c:if>
                       
                        <td>
                            <a href="Controller?section=server&action=remove&id=${ligne.identifiant}"> supprimer </a>
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
                                    <c:if test="${lc.produit.categorie.nomCategorie == 'Viande'}">
                            <br>
                            <form id="formMenu" method="get" action="Controller">
       <input id="section" type="hidden" name="section" value="server" />
            <input id="action" type="hidden" name="action" value="setCuissonMenu" />
            <input id="idMenu" type="hidden" name="idMenu" value="${ligne.identifiant}" />
            <input id="idLc" type="hidden" name="idLc" value="${lc.identifiant}" />
            <label>cuisson </label>
            <select id="cuisson" name="cuisson" onchange="ok()">
        <option value="1">bleu</option>
        <option value="2">saignant</option>
        <option value="3">à point</option>
        <option value="4">cuit</option>
    </select>

</form>
                        </c:if>
                                    
                                </ul> 
                            </c:forEach>
                        </td>
                        
                        <td>
                            <fmt:formatNumber 
                                value="${ligne.menu.prix}"
                                minIntegerDigits="2" 
                                minFractionDigits="2" 
                                maxFractionDigits="2"  /> €
                        </td>
                       <c:if test="${ligne.commentaire.contenu != null}">
                            <td>
                            <a href="Controller?section=server&action=modifierCommenter&id=${ligne.identifiant}"> modifier commentaire </a>
                        </td>
                        <td>
                            <a href="Controller?section=server&action=supprimerCommentaire&id=${ligne.identifiant}"> supprimer le commentaire </a>
                        </td>
                        </c:if>
                        
                        <c:if test="${ligne.commentaire.contenu == null}">
                            <td>
                            <a href="Controller?section=server&action=commenter&id=${ligne.identifiant}"> laisser un commentaire </a>
                        </td>
                        </c:if>
                       
                        <td>
                            <a href="Controller?section=server&action=remove&id=${ligne.identifiant}"> supprimer </a>
                        </td>
                        
                        <c:if test="${ligne.commentaire.contenu != null}">
                            <td class="text-success">${ligne.commentaire.contenu}</td>
                        </c:if>
                    </tr>
                    </c:if>
                </c:forEach>
            <tfoot>
                <tr>
                    <td colspan="4"> Total HT : </td>
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


<a href="Controller?section=server&action=commander"><button class="btn btn-primary">valider ma commande <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></button></a>
    
<h3 class="well">test pour identifier si LigneCommande contient un menu ou un produit</h3>
<c:forEach var="ligne" items="${panier}">
        <c:if test="${ligne.produit != null}">
    <p class="text-primary">id ligne commande <span class="text-success">produit</span> ajouté : ${ligne.identifiant}</p>
        </c:if>
        <c:if test="${ligne.menu != null}">
            <p class="text-primary">id ligne commmande <span class="text-success">menu</span> ajouté : ${ligne.identifiant}</p>
            <c:forEach var="lc" items="${ligne.menu.ligneCommandes}">
                <p class="text-primary">id ligne commmande <span class="text-success">menu.ligneCommande</span> ajouté : ${lc.identifiant} / ayant comme produit: ${lc.produit.nomProduit}</p>
            </c:forEach>
        </c:if>
    </c:forEach>
    
    </c:if>
            
            
            <c:if test="${commande != null}">
                <hr>
                temporairement pour phase de test :
                <p class="well">Récapitulatif de la commande: ${commande}</p>
                <hr>
                
                <div class="col-lg-8">
        <table class="table table-hover" >
            <thead>
                <tr>
                    <th>id</th>
                    <th>nom</th>
                    <th>etat</th>
                    <th>type</th>

                </tr>
            </thead>

            <c:forEach var="ligne" items="${ligneEnCour}">


                <c:if test="${ligne.menu != null}">
                    <c:forEach var="lc" items="${ligne.menu.ligneCommandes}">
                        <tr>
                            <td>${lc.id}</td>
                            <td>menu ${ligne.menu.nom} - ${lc.nom}</td>
                            <c:if test="${lc.etat == 1}">
                                <td>en cuisine</td>
                            </c:if>
                            <c:if test="${lc.etat == 2}">
                                <td>en préparation</td>
                            </c:if>
                            <c:if test="${lc.etat == 3}">
                                <td>servi</td>
                            </c:if>
                            <td>${lc.produit.type.nomType}</td>
                        </tr>
                    </c:forEach>
                </c:if>


                <c:if test="${ligne.produit != null}">
                    <c:if test="${ligne.produit.type.nomType != 'Boisson'}">

                        <tr>
                            <td>${ligne.id}</td>
                            <td>${ligne.nom}</td>
                            <c:if test="${ligne.etat == 1}">
                                <td>en cuisine</td>
                            </c:if>
                            <c:if test="${ligne.etat == 2}">
                                <td>en préparation</td>
                            </c:if>
                            <c:if test="${ligne.etat == 3}">
                                <td>servi</td>
                            </c:if>

                            <td>${ligne.produit.type.nomType}</td>
                        </tr>
                    </c:if>
                </c:if> 

            </c:forEach>



        </table>
    </div>
                
            </c:if>

                <script src="js/jquery.js" type="text/javascript"></script>
                <script>
                    $( document ).ready(function() {
    function ok(){
        
    /*    var section = $('#section');
        var action = $('#action');
        var idMenu = $('#idMenu');
        var idLc = $('#idLc');
        var cuisson = $('#cuisson');
     */   
        $('h1').hide();
  
   

};
    }
                </script>
    <%@include file="../../templates/footer.jsp" %>