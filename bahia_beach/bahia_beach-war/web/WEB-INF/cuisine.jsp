<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>cuisine</title>
        <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <%@include file="templates/header.jsp" %>
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
        <div id="content"></div>
        <script src="js/monScript.js" type="text/javascript">getEtat(${lc.etat})</script>
    </body>
</html>
<%@include file="templates/footer.jsp" %>


<!-- ${lc.commande.date}<br>
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
            <hr> -->