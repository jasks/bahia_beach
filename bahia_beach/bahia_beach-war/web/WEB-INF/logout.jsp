<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>deconnexion</title>
    <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>

<form>
    <input type="hidden" name="section" value="client" />
    <input type="hidden" name="action" value="deconnexion" />
    Veuillez saisir un code: <input type="text" name="code"/><br/><br/>
    <input type="submit" value="Valider"/>
</form>