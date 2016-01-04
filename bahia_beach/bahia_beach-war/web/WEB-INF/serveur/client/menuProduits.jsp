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

    
    <%@include file="../../templates/header.jsp" %>
    
<%@include file="../navBarServeur.jsp" %>

    <%@include file="navBarClient.jsp" %>

    <h1>produits du menu </h1>

    <div class="row">


        <form>
            
            <input type="hidden" name="section" value="server" />
            <input type="hidden" name="action" value="ajouterMenu" />

            <div id="mesPlats" class="col-lg-6">
                <h3 class="text-primary">Nos plats</h3>
                <c:forEach items="${produits}" var="p">
                    <c:if test="${p.type.nomType == 'Plat'}">
                        <input required id="${p.id}" type="radio" name="plat" value="${p.id}"><label for="${p.id}">${p.nomProduit}</label>
                        <br>
                    </c:if>
                </c:forEach>
            </div>

            <div id="mesEntrees" class="col-lg-6">
                <h3 class="text-primary">Nos entrees</h3>

                <c:forEach items="${produits}" var="p">
                    <c:if test="${p.type.nomType == 'Entrée'}">
                        <input required id="${p.id}" type="radio" name="entree" value="${p.id}"><label for="${p.id}">${p.nomProduit}</label>
                        <br> 
                    </c:if>

                </c:forEach>
            </div>
            
            <input type="hidden" name="idMenu" value="${param.id}" />
            
            <input class="btn btn-primary" type="submit" value="ajouter à la commande" />

        </form> 







    </div>              
    <%@include file="../../templates/footer.jsp" %>

