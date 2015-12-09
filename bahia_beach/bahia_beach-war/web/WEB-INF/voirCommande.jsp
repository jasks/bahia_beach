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
        <h1>les Serveurs</h1>
        <table><tr><td>nom Serveur</td><td>prenom Serveur</td><td>code</td></tr>
        <c:forEach items="${lesServeurs}" var="c">
            <tr><td>${c.getNom()}</td><td>${c.getPrenom()}</td><td>${c.getCode()}</td></tr>
            

        </c:forEach>
        </table>
    </body>
</html>
