<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h1>Ma CARTE</h1>
<h2>${msg}</h2>
<div>
    <table border="1" >
        <thead>
            <tr>
                <th>ID</th>
                <th>nom</th>
                <th>Prix Ht/u</th>
                <th>categorie</th>
                <th>type</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${produits}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.nomProduit}</td>
                    <td><fmt:formatNumber 
                            value="${p.prixHT}"
                            minIntegerDigits="2" 
                            minFractionDigits="2" 
                            maxFractionDigits="2"  /> €</td>
                    <td>${p.categorie.nomCategorie}</td>
                    <td>${p.type.nomType}</td>
                    <td>
                        <c:url value="Controller?section=panier&action=add&id=${p.id}" var="url01" />
                        <a href="${url01}">Ajouter au panier</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

