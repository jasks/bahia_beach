<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    
    <%@include file="../templates/header.jsp" %>


    <h1>Tables numero ${table.num} servi par le serveur ${table.serveurs[0]}
        <c:forEach var="s" items="${table.serveurs}">
            ${s.nom}
        </c:forEach></h1>
        
    <p class="text-center well">${table}</p>

   
<%@include file="../templates/footer.jsp" %>

