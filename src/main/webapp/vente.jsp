<%@page import="java.util.List"%>
<%@page import="Util.*"%>
<%@page import="Model.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>liste vente</title>
  
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
<% List<VenteDetail> vd = (List<VenteDetail>)request.getAttribute("ventes");
List<Age> listAge =(List<Age>) request.getAttribute("ages");
List<Administration> listAdministration =(List<Administration>) request.getAttribute("administrations");

%>
<%@ include file="sidebar.jsp" %>

<main id="main" class="main">
    <section class="section">
        <div class="row">
          <div class="col-lg-6">
  
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">Liste Vente</h5>

                <form class="row g-3" action="ListeVente" method="get">
                    <div class="col-md-3">
                        <label for="validationDefault01" class="form-label">Filtrer</label>
                    </div>
                    <div class="col-md-4">
                        <select class="form-select" id="validationDefault04" name="age">
                          <option selected disabled value="">Tranche age</option>
                          <option value="0">-- Sélectionnez une tranche d'âge --</option>
                          <% for (Age m : listAge) { %>
                              <option value="<%= m.getId() %>"><%= m.getNom() %></option>
                          <% } %>
                        </select>
                      </div>
                    <div class="col-md-4">
                        <select class="form-select" id="validationDefault04" name="forme" >
                          <option selected disabled value="">administrations</option>
                          <option value="0">-- Sélectionnez un mode d'administration--</option>
                          <% for (Administration m : listAdministration) { %>
                              <option value="<%= m.getId() %>"><%= m.getNom() %></option>
                          <% } %>
                        </select>
                    </div>
                    <div class="col-12">
                        <button class="btn btn-primary" type="submit">Filtrer</button>
                    </div>

                </form><!-- End General Form Elements -->
                
                <!-- Default Table -->
                <button class="btn btn-primary"><a href="GetVente?type=insertion">Inserer</a></button>

                <table class="table">
                  <thead>
                    <tr>
                      <th scope="col">Produit</th>
                      <th scope="col">Prix unitaire</th>
                      <th scope="col">Date vente</th>
                      <th scope="col">Quantite</th>
                      <th scope="col">Prix total</th>
                    </tr>
                  </thead>
                  <tbody>
<% 
            for(VenteDetail ve : vd){%>
                    <tr>
                      <td><%=ve.getNomProduit()%></td>
                      <td><%=ve.getPu()%></td>
                      <td><%=ve.getDateVente()%></td>
                      <td><%=ve.getQte()%></td>
                      <td><%=ve.getPrixTotal()%></td>
                    </tr>
        <%  }%>
                    
                  </tbody>
                </table>
                <!-- End Default Table Example -->
              </div>
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