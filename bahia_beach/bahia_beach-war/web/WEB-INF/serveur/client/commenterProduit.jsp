<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap/css/bootstrapPaper.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    
<%@include file="../../templates/header.jsp" %>

<%@include file="../navBarServeur.jsp" %>

    <%@include file="navBarClient.jsp" %>
    
        <h1>laisser un commentaire</h1>
        <form action="Controller">
            Laisser votre commentaire: <br>
            <textarea name="commentaire" placeholder="...">${contenu}</textarea>
            <input type="hidden" name="section" value="server" />
            <input type="hidden" name="action" value="setCommentaire" />
            <input type="hidden" name="id" value="${param.id}" />
            <input type="submit" />
        </form>
        
   <%@include file="../../templates/footer.jsp" %>
