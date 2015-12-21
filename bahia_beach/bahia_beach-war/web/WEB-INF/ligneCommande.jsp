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

<body>
    <c:if test="${empty lesLignesCommandes}">
        votre commande est validé<br>
        <a  href="Controller?section=serveur&action=voirCommande">aller au commande</a>
    </c:if>

    <c:if test="${not empty lesLignesCommandes}">
        Commande : ${numCommande}<br>
        Table : ${numTable}
        <table border="1px">
            <tr>
                <td>nom Produit</td>
                <td>Prix Produit</td>
                <td>Categorie</td>
                <td>TVA</td>
                <td>Type</td>                        
                <td>Cuisson</td>
                <td>etat</td>

            </tr>
            <c:forEach items="${lesLignesCommandes}" var="lc">
                <tr>
                    <td>${lc.getProduit().getNomProduit()}</td>
                    <td>${lc.getProduit().getPrixHT()}</td>
                    <td>${lc.getProduit().categorie.getNomCategorie()}</td>
                    <td>${lc.getProduit().getTva().getTauxTva()}</td>
                    <td>${lc.getProduit().getType().getNomType()}</td>                        
                    <td>${lc.getCuisson()}</td>
                    <td>
                        <c:if test="${lc.getEtat() == '1'}">
                            <input type="radio" name="etat" value="en attente" checked>en attente
                        </c:if>
                        <c:if test="${lc.getEtat() == '2'}">
                            <input type="radio" name="etat" value="servi" checked>servi
                        </c:if>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${propriete == 'valid'}">
        <c:url value="Controller?section=serveur&action=mettreAjourCommande&numCommande=${numCommande}" var="url01"/>
        <a href="${url01}">valider la commande</a>

    </c:if>
    <c:if test="${propriete == 'modif'}">
        <c:url value="Controller?section=serveur&action=modifierCommande&numCommande=${numCommande}" var="url01"/>
        <a href="${url01}" >modifier la commande</a>
    </c:if>
    <script type="text/javascript">
        function verfierLeslignes() {
            alert("verfierLeslignes()");
            var etats = document.getElementsByName("${lc.getEtat()}");
            for (i = 0; i < etats.length; i++) {
                currentEtat = etats[i];
                if (currentEtat.checked) {
                    var selectedEtat = currentEtat.value;
                    alert("etat select " + selectedEtat);
                }
            }
        }
    </script>
</body>
</html>
