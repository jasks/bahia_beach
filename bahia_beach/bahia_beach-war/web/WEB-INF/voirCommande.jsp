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

<body>
    <h1>le serveur</h1>
    <table>
        <tr>
            <td>nom Serveur</td>
            <td>prenom Serveur</td>
            <td>code</td>
        </tr>

        <c:forEach items="${serveur}" var="c">
            <tr>
                <td>${c.getNom()}</td>
                <td>${c.getPrenom()}</td>
                <td>${c.getCode()}</td>
            </tr>
        </c:forEach> 
    </table> 
    <table>

        <c:forEach items="${lesProduits.keySet()}" var="pr">

            <tr>
                numero Commande: <td>${pr}</td>
            </tr>
            <table>
                <c:forEach items="${lesProduits.get(pr)}" var="pp">
                    <tr>
                        <td> nom Produit:   ${pp.getNomProduit()}</td> 
                        <td> prix HT Produit:   ${pp.getPrixHT()}</td>
                        <td> prix HT Produit:   ${pp.getDescritption()}</td>
                    </tr> 
                </c:forEach>
            </table>

        </c:forEach>

    </table>


</body>
</html>
