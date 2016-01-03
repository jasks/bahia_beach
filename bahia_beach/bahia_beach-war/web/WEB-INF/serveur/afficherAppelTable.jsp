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


<%@include file="navBarServeur.jsp" %>

<div class="row">
<div id="mesProduits" class="col-lg-6">
    <h3 class="text-primary">Les tables</h3>
    <table border="1" >
        <thead>
            <tr>
                <th>ID</th>
                <th>nom table</th>
                <th>nbre place</th>
                <th>action</th>
                

            </tr>
        </thead>
        <tbody>
            
            <c:forEach items="${tables}" var="t">
                <tr>
                    <td>${t.id}</td>
                    <td>${t.num}</td>
                    <td>${t.nbrPlace}</td>
                    <td>
                        <c:url value="Controller?section=server&action=repondreAppel&id=${t.id}" var="url01" />
                        <a href="${url01}">répondre</a>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</div>
                    
                    
      </div>              
<%@include file="../templates/footer.jsp" %>

