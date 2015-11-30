<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page Admin</title>
    </head>
    <body>
        <c:url value="ControleurAdmin?action=creerDonnees" var="url01" />
        <a href="${url01}">Creer le jeux de test</a>
    </body>
</html>
