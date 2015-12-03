<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cuisine</title>
    </head>
    <body>
        <h1>Cuisine</h1>
        <c:forEach items="${cmd}" var="lc">
            ${lc.commande.date}<br>
            ${lc.commande.numero}<br>
            ${lc.produit.nomProduit}<br>
            ${lc.commentaire.contenu}<br>
            <div id="${lc.id}">
                <script>getEtat(${lc.etat},${lc.id});</script>
                <script>if (${lc.etat} == 2) {
                        var maRep = "En préparation"
                        var MonEtat = document.getElementById("${lc.id}");
                        MonEtat.innerHTML = maRep;
                    }</script></div><br>
            <button>En Préparation</button>
            <button>Prêt</button>
            <hr>
        </c:forEach>
        <script src="js/monScript.js" type="text/javascript">getEtat(${lc.etat})</script>
    </body>
</html>
