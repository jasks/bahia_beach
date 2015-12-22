<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>les Serveurs</title>
    <title>voir commande</title>
    <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascipt" src="../js/script.js"></script>
</head>
<%@include file="templates/header.jsp" %>

<h1>le serveur</h1>
<table border="1px">
    <tr>
        <td>nom Serveur </td>
        <td>prenom Serveur</td>
    </tr>

    <tr>
        <td>${lesCommandes.get(0).getServeur().getNom()}</td>
        <td>${lesCommandes.get(0).serveur.getPrenom()}</td>
    </tr>

</table>
<c:if test="${empty lesCommandes}">
    vous avez pas de commande en cours
</c:if>
<c:if test="${not empty lesCommandes}">
    <table border="1px">
        <tr>
            <td>Numero Table </td>
            <td>numero Commande </td>
            <td>etat Commande </td>
            <td>date Commande </td>
        </tr>


        <c:forEach items="${lesCommandes}" var="cm">

            <tr>
                <td>${cm.getTable().getNum()}</td>
                <td>${cm.getNumero()}</td>
                <td>${cm.getEtat()}</td>
                <td>${cm.getDate()}</td>
                <td><c:url value="Controller?section=serveur&action=lignecommande&numCommande=${cm.getNumero()}&numTable=${cm.getTable().getNum()}&propriete=valid" var="url01" />
                    <a href="${url01}">Voir les Lignes de la commande</a></td>
                <td><c:url value="Controller?section=serveur&action=lignecommande&numCommande=${cm.getNumero()}&numTable=${cm.getTable().getNum()}&propriete=modif" var="url01" />
                    <a href="${url01}">modifier la commande</a></td>
            </tr>   
           
        </c:forEach>

    </table>     
</c:if>
</body>
</html>
