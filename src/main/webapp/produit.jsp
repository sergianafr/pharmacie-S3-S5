<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.*" %>
<%@ page import="Model.Pays" %> 

<% 
List<Produit> listProduit = (List<Produit>) request.getAttribute("produits");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Laboratoires</title>
    
    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
</head>
<body>
    <%@ include file="sidebar.jsp" %>

    <main id="main" class="main">
        <section class="section">
            <div class="row">
                <table class="table datatable">
                    <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Sur ordonnance</th>
                            <th>Categorie</th>                            <th>Sur ordonnance</th>
                            <th>Prix unitaire</th>
                        </tr>
                    </thead>
                    <tbody>
            <% 
                for(Produit l : listProduit){%>
                        <tr>
                            <td><%=l.getNom()%></td>
                            <td><%=l.getSurOrdonnance()%></td>
                            <td><%=l.getNomCategorie()%></td>
                            <td><%=l.getMontant()%></td>
                        </tr>
            <%  }%>
                    </tbody>
                </table>
            </div> 
        </section>
    </main>
</body>