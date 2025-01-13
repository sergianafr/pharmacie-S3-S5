<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Laboratoire" %>
<%@ page import="Model.Pays" %> 

<% 
List<Laboratoire> listLaboratoire = (List<Laboratoire>) request.getAttribute("laboratoires");
List<Pays> listPays = (List<Pays>) request.getAttribute("pays");
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
                <div class="col-lg-6">

                    <!-- Insertion Form Section -->
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Insertion laboratoire</h5>

                            <!-- General Form Elements -->
                            <form action="GetLaboratoire" method="POST">
                                <div class="row mb-3">
                                    <label for="inputText" class="col-sm-2 col-form-label">Nom</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="nom" name="nomLaboratoire">
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <label class="col-sm-2 col-form-label">Pays</label>
                                    <div class="col-sm-10">
                                        <select class="form-select" aria-label="Default select example" id="pays" name="idPaysOrigine">
                                            <option selected>Open this select menu</option>
                                            <% 
                                            if (listPays != null && !listPays.isEmpty()) {
                                                for (Pays pays : listPays) {
                                            %>
                                                <option value="<%= pays.getId() %>"><%= pays.getNom() %></option>
                                            <% 
                                                }
                                            } else { 
                                            %>
                                                <option disabled>Aucun pays disponible</option>
                                            <% 
                                            }
                                            %>
                                        </select>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <div class="col-sm-10">
                                        <button type="submit" class="btn btn-primary">Submit Form</button>
                                    </div>
                                </div>
                            </form><!-- End General Form Elements -->
                        </div>
                    </div>

                    <!-- End Insertion Form Section -->
                </div>
            </div>
            <div class="row">
                <table class="table datatable">
                    <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Pays</th>
                        </tr>
                    </thead>
                    <tbody>
            <% 
                for(Laboratoire l : listLaboratoire){%>
                        <tr>
                            <td><%=l.getNom()%></td>
                            <td><%=l.getPays()%></td>
                        </tr>
            <%  }%>
                    </tbody>
                </table>
            </div> 
        </section>
    </main>

    <%@ include file="footer.jsp" %>

    <!-- Vendor JS Files -->
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
    <script src="assets/js/main.js"></script>
</body>
</html>
