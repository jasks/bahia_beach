<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="page-header text-center">Interface client</h1>
 <p class="text-center well">table mis en session : ${table}</p>
<div class="nav text-center">
        
    <c:if test="${table==null}">
        <h4 class="alert alert-danger text-center">il faut acceder � une table avant de parcourir l'interface client</h4>
    </c:if>
        <a href="Controller?section=carte&action=carte">lien vers ma carte</a>
        <a href="Controller?section=panier&action=afficherPanier">lien vers le panier</a>

        </div>  
