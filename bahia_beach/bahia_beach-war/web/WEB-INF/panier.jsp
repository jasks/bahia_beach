
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Panier</h1>
        <h2>${msg}</h2>
         <c:if test="${empty panier}">
        <p>Votre panier est vide</p>
    </c:if>
    <c:if test="${not empty panier}">
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nom</th>
                    <th>Cuisson</th>
                    <th>prixHt</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${panier}" var="ligne">
                    <tr>
                        <td>${ligne.id}</td>
                        <td>${ligne.produit.nomProduit}</td>
                        <td>${ligne.cuisson}</td>
                        <td>
                            <fmt:formatNumber 
                                value="${ligne.produit.prixHT}"
                                minIntegerDigits="2" 
                                minFractionDigits="2" 
                                maxFractionDigits="2"  /> €
                        </td>
                       
                        <td>
                            <a href="Controller?section=panier&action=remove&id=${ligne.id}"> supprimer </a>
                        </td>
                    </tr>
                </c:forEach>
            <tfoot>
                <tr>
                    <td colspan="5"> Total HT : </td>
                    <td>${total}</td>
                </tr>
            </tfoot>
            </tbody>
        </table>
    </c:if>
        
    </body>
</html>
