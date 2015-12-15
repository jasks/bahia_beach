    <%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <p class="well text-center">utilisateur mis en session : ${auth}</p>
    
    <h1 class="page-header text-center">Interface serveur</h1>
    <div class="nav text-center">
    <a href="Controller?section=server&action=interface">lien interface serveur |</a>
        <a href="Controller?section=server&action=table">lien voir table |</a>
        <a href="Controller?section=server&action=tableLibre">lien voir table libre |</a>
        <a href="Controller?section=server&action=tableAttribue">lien voir table attribué |</a>
                <c:if test="${auth != null}">
            <a href="Controller?section=server&action=deconnexion">lien vers deconnexion |</a>
        </c:if>
    </div>
