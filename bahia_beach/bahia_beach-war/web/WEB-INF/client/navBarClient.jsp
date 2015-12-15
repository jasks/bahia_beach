<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="well text-center">
        
    <c:if test="${table==null}">
        <h4 class="alert alert-danger text-center">il faut acceder à une table avant de parcourir l'interface client</h4>
    </c:if>
        <a href="Controller?section=carte&action=carte">lien vers ma carte |</a>
        <a href="Controller?section=panier&action=afficherPanier">lien vers le panier |</a>

        </div>  
