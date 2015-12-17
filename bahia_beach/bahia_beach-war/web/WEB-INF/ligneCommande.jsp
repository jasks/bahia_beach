<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>les Serveurs</title>
    <title>voir commande</title>
    <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<%@include file="templates/header.jsp" %>
<body>
    Commande : ${numCommande}<br>
    Table : ${numTable}

    <c:if test="${not empty lesLignesCommandes}">
        <table border="1px">
            <tr>
                <td>nom Produit</td>
                <td>Prix Produit</td>
                <td>Categorie</td>
                <td>TVA</td>
                <td>Type</td>                        
                <td>Cuisson</td>
            
            </tr>
            <c:forEach items="${lesLignesCommandes}" var="lc">
                <tr>
                    <td>${lc.getProduit().getNomProduit()}</td>
                    <td>${lc.getProduit().getPrixHT()}</td>
                    <td>${lc.getProduit().categorie.getNomCategorie()}</td>
                    <td>${lc.getProduit().getTva().getTauxTva()}</td>
                    <td>${lc.getProduit().getType().getNomType()}</td>                        
                    <td>${lc.getCuisson()}</td>

                    <td> <INPUT type= "radio" name="${lesLignesCommandes.getEtat()}" value="${lc.getEtat()}" checked=""> en attente</td>
                    <td> <INPUT type= "radio" name="${lesLignesCommandes.getEtat()}" value="${lc.getEtat()}"> servi</td>


                </tr>
            </c:forEach>
        </table>
    </c:if>



</body>
</html>
