<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="nav text-center">

        <a href="Controller?section=server&action=carte">lien vers ma carte</a>
        <c:if test="${nombre != null}">
            <a href="Controller?section=server&action=afficherPanier">lien vers le panier <c:if test="${nombre != 0}"><span class="nombre">${nombre}</span></c:if>|</a>
        </c:if>
        <c:if test="${nombre == null}">
            <a href="Controller?section=server&action=afficherPanier">lien vers le panier |</a>
        </c:if>
            
        </div>  
        <p class="well">panier envoy� au serveur : ${panierServeurRequest}</p>
