<%@page import="java.util.List"%>
<%@page import="Util.*"%>
<%@page import="Model.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Vente</title>

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

<body>
    <%@ include file="sidebar.jsp" %>
    <% List<Produit> produits = (List<Produit>) request.getAttribute("produits"); %>

    <main id="main" class="main">
        <section class="section">
    <div class="row">
        <div class="col-lg-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Insertion Vente</h5>
                    <!-- General Form Elements -->
                    <form id="venteForm" action="ListeVente" method="post">
                        <div id="produit-form" class="produit-section">
                            <div class="row mb-3 produit-item">
                                <label class="col-sm-2 col-form-label">Produit</label>
                                <div class="col-sm-10">
                                    <select class="form-select" name="produit[]" aria-label="Default select example">
                                        <option selected>Produit</option>
                                        <% for (Produit p : produits) { %>
                                        <option value="<%= p.getId() %>"><%= p.getNom() %></option>
                          <% } %>
                                    </select>
                                </div>
                            </div>
                            <div class="row mb-3 produit-item">
                                <label for="inputText" class="col-sm-2 col-form-label">Quantité</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="quantite[]">
                                </div>
                                <div class="col-sm-3">
                                    <button type="button" class="btn btn-primary add-produit">Ajouter</button>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-10">
                                <button type="submit" class="btn btn-primary">Insérer</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        const produitForm = document.getElementById("produit-form");

        // Fonction pour ajouter un nouvel ensemble de champs produit/quantité
        const addProduit = () => {
            const produitItem = document.createElement("div");
            produitItem.className = "row mb-3 produit-item";

            produitItem.innerHTML = `
                <label class="col-sm-2 col-form-label">Produit</label>
                <div class="col-sm-10">
                    <select class="form-select" name="produit[]" aria-label="Default select example">
                        <option selected>Produit</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                </div>
                <label for="inputText" class="col-sm-2 col-form-label">Quantité</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="quantite[]">
                </div>
                <div class="col-sm-3">
                    <button type="button" class="btn btn-danger remove-produit">Supprimer</button>
                </div>
            `;

            produitForm.appendChild(produitItem);
        };

        // Gestionnaire pour ajouter des produits
        produitForm.addEventListener("click", (event) => {
            if (event.target.classList.contains("add-produit")) {
                addProduit();
            } else if (event.target.classList.contains("remove-produit")) {
                const itemToRemove = event.target.closest(".produit-item");
                produitForm.removeChild(itemToRemove);
            }
        });
    });
</script>

    </main><!-- End #main -->

  <!-- Vendor JS Files -->
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