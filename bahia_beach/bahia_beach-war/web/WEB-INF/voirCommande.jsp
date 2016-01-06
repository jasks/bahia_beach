<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>voir commande</title>
        <link href="css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>

        <link href="css/bootstrap/css/bootstrap-theme.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascipt" src="jScript/script.js"></script>
    </head>
    <%@include file="templates/header.jsp" %>
    <body>

        <h3 class="panel-heading">le serveur</h3>
        <div class="panel-body">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>nom Serveur</th>
                        <th>prenom Serveur</th>

                </thead>
                <tbody>
                    <tr>
                        <td>${serveur.getNom()}</td>
                        <td>${serveur.getPrenom()}</td>
                    </tr> 
                </tbody> 
            </table>
        </div>


        <c:if test="${empty lesCommandes}">
            vous avez pas de commande en cours
        </c:if>

        <div class="panel-body">

            <c:if test="${not empty lesCommandes}">
                <h3 class="panel-heading">les commandes</h3>
                <table class="table table-striped table-bordered">
                    <thead>

                        <tr>
                            <td>Numero Table </td>
                            <td>numero </td>
                            <td>etat  </td>
                            <td>date </td>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${lesCommandes}" var="cm">

                            <tr>
                                <td>${cm.getTable().getNum()}</td>
                                <td>${cm.getNumero()}</td>
                                <td>${cm.getEtat()}</td>
                                <td>${cm.getDate()}</td>

                                <td><c:url value="Controller?section=serveur&action=lignecommande&numCommande=${cm.getNumero()}&numTable=${cm.getTable().getNum()}&propriete=valid" var="url01" />
                                    <a href="${url01}">produits commandés</a></td>
                                <td><c:url value="Controller?section=serveur&action=lignecommande&numCommande=${cm.getNumero()}&numTable=${cm.getTable().getNum()}&propriete=modif" var="url01" />
                                    <a href="${url01}">modifier la commande</a></td>
                            </tr>   

                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
</html>
