<%@page import="java.util.List"%>
<%@page import="Util.*"%>
<%@page import="Model.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<% 
List<PrixProduit> listProduits = (List<PrixProduit>) request.getAttribute("historiques");
List<Produit> produits = (List<Produit>) request.getAttribute("produits");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>historique des prix</title>
  
    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  
    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">
  
    <!-- =======================================================
    * Template Name: NiceAdmin
    * Updated: Jan 29 2024 with Bootstrap v5.3.2
    * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
    * Author: BootstrapMade.com
    * License: https://bootstrapmade.com/license/
    ======================================================== -->
  </head>
</head>
<body>

<%@ include file="sidebar.jsp" %>

<main id="main" class="main">
    <section class="section">
    <div class="container mt-5">
        <!-- Formulaire de filtrage -->
        <div>
            <form class="row g-3" action="GetHistoriquePrix" method="get">
                <div class="row">
                    <div class="col-md-6">
                        <label for="dateDebut" class="form-label">Debut</label>
                        <input type="date" class="form-control" id="dateDebut" name="dateDebut" placeholder="YYYY-MM-DD">
                    </div>
                    <div class="col-md-6">
                        <label for="dateFin" class="form-label">Fin</label>
                        <input type="date" class="form-control" id="dateFin" name="dateFin" placeholder="YYYY-MM-DD">
                    </div>
                    <div class="col-md-12">
                        <select class="form-select" aria-label="Default select example" id="produit" name="id_produit">
                            <option value = "0" selected>Open this select menu</option>
                            <% 
                            if (produits != null && !produits.isEmpty()) {
                                for (Produit produit : produits) {
                            %>
                                <option value="<%= produit.getId() %>"><%= produit.getNom() %></option>
                            <% 
                            }
                            } else { 
                            %>
                                <option disabled>Aucun produits disponible</option>
                            <% 
                            }
                            %>
                        </select>
                    </div>
                    <div class="col-md-12 text-end" >
                        <button type="submit" class="btn btn-primary">Rechercher</button>

                    </div>
                </div>
            </form>
        </div>

        <!-- Titre et bouton d'insertion -->
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h5 class="card-title">Historique des prix</h5>
            <%-- <a href="GetInsertionProduitsMois" class="btn btn-success">Ajouter un Produit</a> --%>
        </div>

        <!-- Table des produits -->
        <div class="table-responsive">
            <table class="table table-data">
                <thead class="table-dark">
                    <tr>
                        <th>Nom produit</th>
                        <th>Date</th>
                        <th>Montant</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for (PrixProduit l : listProduits) { 
                    %>
                        <tr>
                            <td><%= l.getNom() %></td>
                            <td><%= l.getDateInsertion() %></td>
                            <td><%= l.getMontant() %></td>
                        </tr>
                    <% 
                        } 
                    %>
                </tbody>
            </table>
        </div>
    </div>
</section>
</main><!-- End #main -->

<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/chart.js/chart.umd.js"></script>
  <script src="assets/vendor/echarts/echarts.min.js"></script>
  <script src="assets/vendor/quill/quill.min.js"></script>
  <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>


</body>
</html>