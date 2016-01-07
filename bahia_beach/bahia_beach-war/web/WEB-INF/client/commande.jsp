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

    
    <%@include file="../templates/header.jsp" %>
    
<%@include file="navBarClient.jsp" %>

<c:if test="${ligneEnCour != null}">

    <p class="well">Récapitulatif de la commande: ${commande.id}</p>
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
    <%@include file="../templates/footer.jsp" %>

