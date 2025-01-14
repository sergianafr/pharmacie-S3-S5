<%@page import="java.util.List"%>
<%@page import="Util.*"%>
<%@page import="Model.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<% 
List<ConseilMois> listProduits = (List<ConseilMois>) request.getAttribute("produits");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>liste produit du mois</title>
  
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
        <div class="row">
  
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">Liste Produit du mois</h5>                
                <!-- Default Table -->
                <button class="btn btn-primary"><a href="GetInsertionProduitsMois">Inserer</a></button>

                    <section class="section">
                        <div class="row">
                            <table class="table datatable">
                                <thead>
                                    <tr>
                                        <th>Nom</th>
                                        <th>Sur ordonnance</th>
                                        <th>Categorie</th>                            
                                        <th>Prix unitaire</th>
                                        <th>Date debut</th>                                        
                                        <th>Date fin</th>


                                    </tr>
                                </thead>
                                <tbody>
                        <% 
                            for(ConseilMois l : listProduits){%>
                                    <tr>
                                        <td><%=l.getNom()%></td>
                                        <td><%=l.surOrdonnance()%></td>
                                        <td><%=l.getNomCategorie()%></td>
                                        <td><%=l.getMontant()%></td>
                                        <td><%=l.getDateDebut()%></td>
                                        <td><%=l.getDateFin()%></td>


                                    </tr>
                        <%  }%>
                                </tbody>
                            </table>
                        </div> 
                    </section>

                    
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