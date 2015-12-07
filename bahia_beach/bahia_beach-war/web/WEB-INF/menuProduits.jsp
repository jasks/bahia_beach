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


<h1>produits du menu </h1>

<div class="row">
<div id="mesPlats" class="col-lg-6">
    <h3 class="text-primary">Nos plats</h3>
    <table border="1" >
        <thead>
            <tr>
                <th>ID</th>
                <th>nom</th>

            </tr>
        </thead>
        <tbody>
            
            <c:forEach items="${produits}" var="p">
                <c:if test="${p.type.nomType == 'Plat'}">
                    <tr>
                    <td>${p.id}</td>
                    <td>${p.nomProduit}</td>
                    <td>
                        <c:url value="Controller?section=carte&action=produits&type=${p.id}" var="url01" />
                        <a href="${url01}">Voir</a>
                    </td>
                </tr>
                </c:if>
                
            </c:forEach>
                <td>
                        <c:url value="Controller?section=panier&action=afficherPanier" var="url01" />
                        <a href="${url01}">Voir</a>
                    </td>
        </tbody>
    </table>
</div>
                    
                    <div id="mesEntrees" class="col-lg-6">
    <h3 class="text-primary">Nos entrees</h3>
    <table border="1" >
        <thead>
            <tr>
                <th>ID</th>
                <th>nom</th>

            </tr>
        </thead>
        <tbody>
            
            <c:forEach items="${produits}" var="p">
                <c:if test="${p.type.nomType == 'Entrée'}">
                    <tr>
                    <td>${p.id}</td>
                    <td>${p.nomProduit}</td>
                    <td>
                        <c:url value="Controller?section=carte&action=produits&type=${p.id}" var="url01" />
                        <a href="${url01}">Voir</a>
                    </td>
                </tr>
                </c:if>
                
            </c:forEach>
                <td>
                        <c:url value="Controller?section=panier&action=afficherPanier" var="url01" />
                        <a href="${url01}">Voir</a>
                    </td>
        </tbody>
    </table>
</div>
                    
                    
      </div>              
                    <%@include file="templates/footer.jsp" %>

