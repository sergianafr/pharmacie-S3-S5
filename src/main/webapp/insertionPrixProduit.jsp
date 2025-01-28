<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Produit" %>

<% 
List<Produit> listProduits = (List<Produit>) request.getAttribute("Produit");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion Prix Produit</title>
    
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
<% if(request.getAttribute("error") != null){%>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <i class="bi bi-exclamation-octagon me-1"></i>
        <%= request.getAttribute("error") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button><%}%>
    </div>
                 <div class="col-lg-6">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Insertion Prix Produit</h5>

                            <!-- General Form Elements -->
                            <form action="GetPrixProduit" method="POST">
                                <div class="row mb-3">
                                    <label class="col-sm-2 col-form-label">Produits</label>
                                    <div class="col-sm-10">
                                        <select class="form-select" aria-label="Default select example" id="produit" name="produit">
                                            <option selected>Open this select menu</option>
                                            <% 
                                            if (listProduits != null && !listProduits.isEmpty()) {
                                                for (Produit produit : listProduits) {
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
                                </div>

                                <div class="row mb-3">
                                    <label for="inputText" class="col-sm-2 col-form-label">Prix</label>
                                    <div class="col-sm-6">
                                        <input type="number" class="form-control" name="prix">
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
        </section>
    </main>

    <%@ include file="footer.jsp" %>

    <!-- Vendor JS Files -->
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
    <script src="assets/js/main.js"></script>
</body>
</html>
