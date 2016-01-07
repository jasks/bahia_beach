<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="nav text-center">
    
    <p class="well">table mis en session : ${tableClient}</p>

        <a href="Controller?section=server&action=carte">carte</a>
        <c:if test="${nombre != null}">
            <a href="Controller?section=server&action=afficherPanier">mon panier <c:if test="${nombre != 0}"><span class="nombre">${nombre}</span></c:if>|</a>
        </c:if>
        <c:if test="${nombre == null}">
            <a href="Controller?section=server&action=afficherPanier">mon panier |</a>
        </c:if>
            <a href="Controller?section=server&action=mesCommandes">mes commandes |</a>
            
        </div>  
        
