<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>MVC2 IS READY</h1>
        <h2>${msg}</h2>
        <a href="Controller?section=admin&action=jeuxDonnee">lien vers jeux donnees</a>
        <a href="Controller?section=page01">lien vers page 01</a>
        <a href="Controller?section=page02">lien vers page 02</a>
        <a href="Controller?section=cuisine">lien vers Cuisne</a>
        <a href="Controller?section=carte&action=produits">lien vers ma carte</a>
        <a href="Controller?section=serveur&action=voirCommande"> lien vers Voir lesCommandes</a>
    </body> 
</html>
