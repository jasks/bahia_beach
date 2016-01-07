<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p class="well text-center">utilisateur mis en session : ${auth}</p>

<h1 class="page-header text-center">Interface serveur</h1>
<div class="nav text-center">
    <a href="Controller?section=server&action=table">voir table |</a>
    <a href="Controller?section=server&action=tableLibre">voir table libre |</a>
    <a href="Controller?section=server&action=tableAttribue">mes tables |</a>

    <c:if test="${nombreAppel != null}">
        <a href="Controller?section=server&action=tableAppel">mes appel 
            <c:if test="${nombreAppel != 0}">
                <span class="nombre">${nombreAppel}</span>
            </c:if> |
        </a>
    </c:if>

    <c:if test="${auth != null}">
        <a href="Controller?section=server&action=deconnexion">deconnexion |</a>

    </c:if>

</div>