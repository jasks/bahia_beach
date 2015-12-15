<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>recapCommande</title>
    <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<%@include file="templates/header.jsp" %>
<body>
    Récapitulatif de la commande: ${commande}
    <div id="commande" hidden="">${commande.id}</div>
    <script src="js/monScript.js" type="text/javascript"></script>  
</body>

<%@include file="templates/footer.jsp" %>