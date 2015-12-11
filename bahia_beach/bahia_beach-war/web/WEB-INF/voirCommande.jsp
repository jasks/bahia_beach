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

        <tr>
            <td>${serveur.getNom()}</td>
            <td>${serveur.getPrenom()}</td>
            <td>${serveur.getCode()}</td>
        </tr>
    </table> 
    <table>
        <tr>
            <td>numero Commande</td>
            <td>etat Commande</td>
            <td>date Commande</td>
            <!--  table          : <td></td> -->
        </tr>

        <c:forEach items="${lesCommandes}" var="cm">

            <tr>
                <td>${cm.getNumero()}</td>
                <td>${cm.getEtat()}</td>
                <td>${cm.getDate()}</td>
                <td>${cm.getTable().getNum()}</td>
            </tr>
            <table>

            </table>

        </c:forEach>

    </table>
    <table> 
        <tr>
           
            <td>etat ligne Commande</td>
            <td>cuisson</td>
        </tr>

        <c:forEach items="lesLignesCommandes" var="lc">
            <tr>

                <td>${lc.etat}</td>
                <td>${lc.cuisson}</td>
                
            </tr>

        </c:forEach>
    </table>        


</body>
</html>
