
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../templates/header.jsp" %>


<body>
    <div id="appelerServeur" name="appelerServeur">
        <form id="">
            Table : ${uneTable.getNum()}<br>
            Serveur:${serveur.getId()}<br>
            Objet demande: <input type="textarea" name ="msg">
            <input type="hidden" name="appelerServeur">
        </form>
        <a href="Controller?section=server&action=repondreClient">appeler Serveur</a>
    </div>
</body>
