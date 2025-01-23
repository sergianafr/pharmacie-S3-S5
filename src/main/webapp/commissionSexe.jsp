<%@page import="java.util.List"%>
<%@page import="Util.*"%>
<%@page import="Model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>liste commission Sexe</title>
  
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
    * License: https://bootstrapmade.com/licant lcean 
    ======================================================== -->
  </head>
</head>
<body>

 <%@ include file="sidebar.jsp" %>
<% ComissionEmploye listeVente = (ComissionEmploye) request.getAttribute("comissionEmploye");%> 

<main id="main" class="main">
    <section class="section">
        <div class="container mt-5">
            <!-- Formulaire de filtrage -->
            <div class="row mb-4">
                <form class="row g-3" action="GetEtatComission" method="get">
                    <div class="col-md-6">
                        <label for="dateDebut" class="form-label">Debut</label>
                        <input type="date" class="form-control" id="dateDebut" name="dateDebut" placeholder="YYYY-MM-DD">
                    </div>
                    <div class="col-md-6">
                        <label for="dateFin" class="form-label">Fin</label>
                        <input type="date" class="form-control" id="dateFin" name="dateFin" placeholder="YYYY-MM-DD">
                    </div>

                    <div class="col-12 text-end">
                        <button type="submit" class="btn btn-primary">Filtrer</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
<div class="row">
    <section class="section" class="col-4">
        <div class="card">
            <div class="card-body">
                <!-- Tableau de données -->
                <table class="table table-striped data-table">
                    <thead>
                        <tr>
                            <th scope="col">Homme</th>
                            <th scope="col">Femme</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><%=listeVente.getComissionHomme()%></td>
                            <td><%=listeVente.getComissionFemme()%></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
    <section class="col-8">
        <canvas id="chart" style="max-height: 400px;"></canvas>
        <script>
            document.addEventListener("DOMContentLoaded", () => {
                // Récupérer les données dynamiques via JSP et les assigner à des variables JavaScript
                var comissionHomme = <%= listeVente.getComissionHomme() %>;
                var comissionFemme = <%= listeVente.getComissionFemme() %>;

                // Créer le graphique avec les données dynamiques
                new Chart(document.querySelector('#chart'), {
                    type: 'pie',
                    data: {
                        labels: ['Homme', 'Femme'],
                        datasets: [{
                            label: 'Commission',
                            data: [comissionHomme, comissionFemme],
                            backgroundColor: [
                                'rgb(255, 99, 132)', // couleur pour 'Homme'
                                'rgb(54, 162, 235)'  // couleur pour 'Femme'
                            ],
                            hoverOffset: 4
                        }]
                    }
                });
            });
        </script>

    </section>
</div>
</main>
<!-- End #main -->

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