<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p class="well text-center">utilisateur mis en session : ${auth}</p>

<h1 class="page-header text-center">Interface serveur</h1>
<div class="nav text-center">
    <a href="Controller?section=server&action=interface">lien interface serveur |</a>
    <a href="Controller?section=server&action=table">lien voir table |</a>
    <a href="Controller?section=server&action=tableLibre">lien voir table libre |</a>
    <a href="Controller?section=server&action=tableAttribue">lien voir table attribué |</a>
    <a href="Controller?section=server&action=repondreClient">repondre au Client |</a>

    <c:if test="${nombre != null}">
        <a href="Controller?section=server&action=tableAppel">lien voir appel 
            <c:if test="${nombre != 0}">
                <span class="nombre">${nombre}</span>
            </c:if> |
        </a>
    </c:if>

    <c:if test="${auth != null}">
        <a href="Controller?section=server&action=deconnexion">lien vers deconnexion |</a>

    </c:if>



</div>
<div class="nav text-center">
    <div class="well">liste des panier mis en scope application : ${panierServeur} | panier de la table mis en scope session : ${panier}</div>

</div>