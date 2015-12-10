<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>index</title>
        <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <%@include file="../templates/header.jsp" %>
    
    <h1 class="page-header text-center">Interface serveur</h1>

    <a href="Controller?section=server&action=interface">lien interface serveur |</a>
        <a href="Controller?section=server&action=table">lien voir table |</a>
        <a href="Controller?section=server&action=tableLibre">lien voir table libre |</a>
    
        <%@include file="../templates/footer.jsp" %>
