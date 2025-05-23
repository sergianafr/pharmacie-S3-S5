<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Insertion medicament</title>
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
<%@page import="java.util.List"%>
<%@page import="Util.*"%>
<%@page import="Model.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%-- +++++++++++++++++++++++++++++++ --%>
<%
List<Forme> formes = (List<Forme>)request.getAttribute("formes");
List<Laboratoire> laboratoires = (List<Laboratoire>)request.getAttribute("laboratoires");
List<TypeObjet> types = (List<TypeObjet>)request.getAttribute("types");
List<Maladie> maladies = (List<Maladie>)request.getAttribute("maladies");
List<Age> ages = (List<Age>)request.getAttribute("ages");
%>
<body>
    <%@ include file="sidebar.jsp" %>
    <main id="main" class="main">
        <section class="section">
             <form action="ListeMedicament" method="post">
                <!-- etape 1 : Informations generales -->
                <h3>Informations sur le medicament</h3>
                <label for="nom">Nom du medicament :</label>
                <input type="text" id="nom" name="nom" required>

                <label for="laboratoire">Laboratoire :</label>
                <select class="laboratoire" name="laboratoire"  required>
                    <option value="">-- Selectionnez un laboratoire --</option>
        <%      for(Laboratoire lab : laboratoires){%>
                    <option value="<%=lab.getId()%>"><%=lab.getNom()%></option>
        <%      }%> 
                </select>
                <label for="maladie">Maladies cible :</label>
                <select name="maladies[]" id="maladies" multiple required>
                    <% for (Maladie mala : maladies) { %>
                        <option value="<%= mala.getId() %>"><%= mala.getNom() %></option>
                    <% } %>
                </select>
                <label for="age">Tranche d'age :</label>
                <select name="ages[]" id="ages" multiple required>
                    <% for (Age age : ages) { %>
                        <option value="<%= age.getId() %>"><%= age.getNom() %></option>
                    <% } %>
                </select>
                <%-- <hr> --%>

                <!-- etape 2 : Forme et mode d'administration -->
                 <h3>Formes et modes d'administration</h3>
                <div id="forme-admin-section">
                    <div class="forme-admin">
                        <label for="forme">Forme :</label>
                        <select class="forme" name="forme[]" onchange="loadModes(this)" required>
                            <option value="">-- Selectionnez une forme --</option>
                <%      for(Forme lab : formes){%>
                            <option value="<%=lab.getId()%>"><%=lab.getNom()%></option>
                <%      }%>
                        </select>

                        <label for="mode">Mode d'administration :</label>
                        <select class="mode" name="mode[]" id="mode" required>
                            <option value="">Selectionnez un mode administration</option>
                            <!-- Options filtrees dynamiquement en fonction de la forme -->
                        </select>
                        <label for="dosage">Dosage :</label>
                        <input type="number" step="0.01" name="dosage" required>

                        <label for="unite">Unite :</label>
                        <select name="unite[]" required>
                            <option value="1">mg</option>
                            <option value="2">ml</option>
                            <option value="3">unite</option>
                            <option value="3">g</option> 
                            <!-- Ajouter d'autres unites si necessaire -->
                        </select>

                    </div>
                </div>
                <button type="button" onclick="addFormeAdmin()">Ajouter une autre forme/mode</button>

                <hr>

                <!-- Bouton de soumission -->
                <button type="submit">Enregistrer le medicament</button>
            </form>
            <%-- <form action="ListeMedicament" method="post" class="container mt-4">
    <h3 class="mb-4">Informations sur le médicament</h3>

    <div class="mb-3">
        <label for="nom" class="form-label">Nom du médicament :</label>
        <input type="text" id="nom" name="nom" class="form-control" required>
    </div>

    <div class="mb-3">
        <label for="laboratoire" class="form-label">Laboratoire :</label>
        <select class="form-select" id="laboratoire" name="laboratoire" required>
            <option value="">-- Sélectionnez un laboratoire --</option>
            <% for(Laboratoire lab : laboratoires) { %>
                <option value="<%=lab.getId()%>"><%=lab.getNom()%></option>
            <% } %>
        </select>
    </div>

    <div class="mb-3">
        <label for="maladies" class="form-label">Maladies cibles :</label>
        <select name="maladies[]" id="maladies" class="form-select" multiple required>
            <% for(Maladie mala : maladies) { %>
                <option value="<%=mala.getId()%>"><%=mala.getNom()%></option>
            <% } %>
        </select>
    </div> --%>

    <%-- <div class="mb-3">
        <label for="ages" class="form-label">Tranche d'âge :</label>
        <select name="ages[]" id="ages" class="form-select" multiple required>
            <% for(Age age : ages) { %>
                <option value="<%=age.getId()%>"><%=age.getNom()%></option>
            <% } %>
        </select>
    </div>

    <hr class="my-4">

    
    <h3 class="mb-4">Formes et modes d'administration</h3>
    <div id="forme-admin-section">
        <div class="forme-admin row g-3">
            <div class="col-md-4">
                <label for="forme" class="form-label">Forme :</label>
                <select class="form-select" name="forme[]" onchange="loadModes(this)" required>
                    <option value="">-- Sélectionnez une forme --</option>
                    <% for(Forme lab : formes) { %>
                        <option value="<%=lab.getId()%>"><%=lab.getNom()%></option>
                    <% } %>
                </select>
            </div>

            <div class="col-md-4">
                <label for="mode" class="form-label">Mode d'administration :</label>
                <select class="form-select" name="mode[]" id="mode" required>
                    <option value="">Sélectionnez un mode d'administration</option>
                    <!-- Options filtrées dynamiquement en fonction de la forme -->
                </select>
            </div>

            <div class="col-md-2">
                <label for="dosage" class="form-label">Dosage :</label>
                <input type="number" step="0.01" name="dosage" class="form-control" required>
            </div>

            <div class="col-md-2">
                <label for="unite" class="form-label">Unité :</label>
                <select name="unite[]" class="form-select" required>
                    <option value="1">mg</option>
                    <option value="2">ml</option>
                    <option value="3">unité</option>
                </select>
            </div>
        </div>
    </div>

    <div class="mt-3">
        <button type="button" class="btn btn-secondary" onclick="addFormeAdmin()">Ajouter une autre forme/mode</button>
    </div>

    <hr class="my-4">

    <!-- Bouton de soumission -->
    <div class="text-center">
        <button type="submit" class="btn btn-primary">Enregistrer le médicament</button>
    </div>
</form> --%>


            
            <script>
            // Fonction pour charger les modes d'administration dynamiquement
                function loadModes(formeSelect) {
                    const selectedForme = formeSelect.value;
                    const modeSelect = formeSelect.closest('.forme-admin').querySelector('.mode');

                    // Réinitialiser les options du mode lié
                    modeSelect.innerHTML = '<option value="">-- Sélectionnez un mode administration --</option>';

                    // Charger les modes disponibles pour cette forme (appel à une API ou traitement côte serveur)
                    if (selectedForme) {
                        fetch("GetAdministration?forme=".concat(selectedForme))
                            .then(response => response.json())
                            .then(data => {
                                console.log(data);
                                data.forEach(mode => {
                                    const option = document.createElement('option');
                                    option.value = mode.id;
                                    option.textContent = mode.nomAdministration;
                                    modeSelect.appendChild(option);
                                });
                            })
                            .catch(error => console.error('Erreur de chargement des modes :', error));
                    }
                }

            // Fonction pour ajouter dynamiquement un autre bloc forme/mode
            function addFormeAdmin() {
                const section = document.getElementById('forme-admin-section');
                const newIndex = section.children.length; // Obtenir le prochain index
                console.log(newIndex);
                const newFormeAdmin = document.querySelector('.forme-admin').cloneNode(true);
                newFormeAdmin.querySelector('select.forme').name = "forme[${newIndex}]";
                newFormeAdmin.querySelector('select.mode').name = "mode[${newIndex}]";

                // Réinitialiser les valeurs des champs
                newFormeAdmin.querySelector('select.forme').value = '';
                newFormeAdmin.querySelector('select.mode').innerHTML = '<option value=""> mode administration --</option>';

                section.appendChild(newFormeAdmin);
            }

            // Fonction pour ajouter dynamiquement un autre bloc de dosage
            function addDosage() {
                const section = document.getElementById('dosage-section');
                const newIndex = section.children.length; // Obtenir le prochain index

                const newDosageEntry = document.querySelector('.dosage-entry').cloneNode(true);
                newDosageEntry.querySelector('input[name="dosage"]').name = `dosage[${newIndex}]`;
                newDosageEntry.querySelector('select[name="unite[]"]').name = `unite[${newIndex}]`;

                // Réinitialiser les valeurs des champs
                newDosageEntry.querySelector('input[name="dosage"]').value = '';
                newDosageEntry.querySelector('select[name="unite[]"]').value = '';

                section.appendChild(newDosageEntry);
            }
        </script>


        </section>
    </main>
</body>