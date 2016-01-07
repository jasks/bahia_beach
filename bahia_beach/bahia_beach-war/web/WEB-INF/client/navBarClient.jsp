<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1 class="page-header text-center">Interface client</h1>
 <p class="text-center well">table mis en session : ${table}</p>
<div class="nav text-center">
        
    <c:if test="${table==null}">
        <h4 class="alert alert-danger text-center">il faut acceder à une table avant de parcourir l'interface client</h4>
    </c:if>
        <a href="Controller?section=carte&action=carte">la carte</a>
        <c:if test="${nombre != null}">
            <a href="Controller?section=panier&action=afficherPanier">mon panier <c:if test="${nombre != 0}"><span class="nombre">${nombre}</span></c:if>|</a>
        </c:if>
        <c:if test="${nombre == null}">
            <a href="Controller?section=panier&action=afficherPanier">mon panier |</a>
        </c:if>
        <a href="Controller?section=carte&action=appel">appel serveur |</a>
        <a href="Controller?section=panier&action=mesCommandes">mes commandes |</a>
        </div>  
