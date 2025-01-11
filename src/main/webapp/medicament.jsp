<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Medicament</title>
  <meta content="" name="description">
  <meta content="" name="keywords">
  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

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
<%@ page contentType="text/html; charset=UTF-8" %>

<%@page import="java.util.List"%>
<%@page import="Util.*"%>
<%@page import="Model.*"%>
<%@page import="java.util.HashMap"%>
<% 
List<Maladie> listMaladie = (List<Maladie>)request.getAttribute("maladies");
List<Age> listAge =(List<Age>) request.getAttribute("ages");
HashMap<Integer, List<Maladie>> maladieMedicament = (HashMap<Integer, List<Maladie>>) request.getAttribute("maladieMedicament");
HashMap<Integer, List<Age>> ageMedicament = (HashMap<Integer, List<Age>>) request.getAttribute("ageMedicament");

%>
<body>
    <%@ include file="sidebar.jsp" %>
    <main id="main" class="main">
        <!-- Vertically centered Modal -->
            <%-- <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#verticalycentered">
            Vertically centered
            </button>
            <div class="modal fade" id="verticalycentered" tabindex="-1">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Vertically Centered</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Non omnis incidunt qui sed occaecati magni asperiores est mollitia. Soluta at et reprehenderit. Placeat autem numquam et fuga numquam. Tempora in facere consequatur sit dolor ipsum. Consequatur nemo amet incidunt est facilis. Dolorem neque recusandae quo sit molestias sint dignissimos.
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </div>
            </div><!-- End Vertically centered Modal--> --%>

        <%-- <form action="ListeMedicament" method="get">
            <select name="idMaladie" id="maladie" >
                <option value="0">Maladies</option>
    <% for(Maladie m : listMaladie){%>
                <option value="<%=m.getId()%>"><%=m.getNom()%></option>
    <%  } %>
            </select>
            <select name="idAge" id="age" >
                <option value="0">Tranche age</option>
    <% for(Age m : listAge){%>
                <option value="<%=m.getId()%>"><%=m.getNom()%></option>
    <%  } %>
            </select>
            <button type="submit">Valider</button>
        </form> --%>
                
        <div class="card">
    <div class="card-body">
        <h5 class="card-title">Filtre</h5>

        <!-- Formulaire Filtre -->
        <form class="row g-3" action="ListeMedicament" method="get">
            <div class="col-md-6">
                <label for="texte" class="form-label">Nom du Médicament</label>
                <input type="text" class="form-control" id="texte" name="nomMedicament" placeholder="Exemple : Paracétamol">
            </div>

            <!-- Champ Numérique avec Virgule -->
            <div class="col-md-6">
                <label for="prix" class="form-label">Dosage</label>
                <input type="number" step="0.01" class="form-control" id="prix" name="prix" placeholder="Exemple : 12,50">
            </div>
            <!-- Maladies -->
            <div class="col-md-6">
                <label for="maladie" class="form-label">Maladies</label>
                <select name="idMaladie" id="maladie" class="form-select">
                    <option value="0">-- Sélectionnez une maladie --</option>
                    <% for (Maladie m : listMaladie) { %>
                        <option value="<%= m.getId() %>"><%= m.getNom() %></option>
                    <% } %>
                </select>
            </div>

            <!-- Tranche d'âge -->
            <div class="col-md-6">
                <label for="age" class="form-label">Tranche d'âge</label>
                <select name="idAge" id="age" class="form-select">
                    <option value="0">-- Sélectionnez une tranche d'âge --</option>
                    <% for (Age m : listAge) { %>
                        <option value="<%= m.getId() %>"><%= m.getNom() %></option>
                    <% } %>
                </select>
            </div>

            <!-- Champ Texte -->

            <!-- Boutons -->
            <div class="col-12 text-center">
                <button type="submit" class="btn btn-primary">Valider</button>
            </div>
        </form>
    </div>
</div>


        <section class="section">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Medicaments</h5>
                    <%      if(request.getAttribute("agen")!= null){
                                out.println("Tranche age: "+(String)request.getAttribute("agen")+" <br>");
                            }if(request.getAttribute("maladien")!= null){
                                out.println("Maladie: "+(String)request.getAttribute("maladien"));
                            }%>
                            <%-- <p>Add lightweight datatables to your project with using the <a href="https://github.com/fiduswriter/Simple-DataTables" target="_blank">Simple DataTables</a> library. Just add <code>.datatable</code> class name to any table you wish to conver to a datatable. Check for <a href="https://fiduswriter.github.io/simple-datatables/demos/" target="_blank">more examples</a>.</p> --%>
                            <button class="btn btn-primary"><a href="GetMedicament?type=insertion">Inserer</a></button>
                            <!-- Table with stripped rows -->
                            <table class="table datatable">
                                <thead>
                                    <tr>
                                        <th>Nom</th>
                                        <th>Laboratoire</th>
                                        <th data-type="date" data-format="YYYY/DD/MM">Date insertion </th>
                                        <th>Maladie</th>
                                        <th>Age</th>
                                    </tr>
                                </thead>
                                <tbody>

                        <% List<Medicament> meds = (List<Medicament>)request.getAttribute("medicament");
                            for(Medicament medicament : meds){%>
                                    <tr>
                                        <td><%=medicament.getNom()%></td>
                                        <td><%=medicament.getNomLaboratoire()%></td>
                                        <td><%=medicament.getDateInsertion()%></td>
                                        <td><% List<Maladie> ml = maladieMedicament.get(medicament.getId());
                                            for(Maladie mal : ml){
                                                out.println(mal.getNom()+"|");
                                            }%></td>
                                        <td><% List<Age> agemed = ageMedicament.get(medicament.getId());
                                            for(Age ag : agemed){
                                                out.println(ag.getNom()+"|");
                                            }%></td>
                                    </tr>
                        <%  }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <%@ include file="footer.jsp" %>
</body>