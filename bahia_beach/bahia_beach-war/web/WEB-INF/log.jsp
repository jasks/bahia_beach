<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>connexion</title>
    <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>

<%@include file="templates/header.jsp" %>

<form class="formLog">

    <input type="hidden" name="section" value="log" />
    <input type="hidden" name="action" value="verifierCode" />
    <label for="code">Veuillez saisir un code:</label>
    <input style="background: white" class="form-control" id="code" type="text" name="code"/><br/><br/>
     <input type="submit" class="btn btn-primary" value="Valider"/>
</form>

<%@include file="templates/footer.jsp" %>
