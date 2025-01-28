<!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
      <a href="index.jsp" class="logo d-flex align-items-center">
        <img src="assets/img/logo.png" alt="">
        <span class="d-none d-lg-block">Pharmacie</span>
      </a>
      <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->

    <div class="search-bar">
      <form class="search-form d-flex align-items-center" method="POST" action="#">
        <input type="text" name="query" placeholder="Search" title="Enter search keyword">
        <button type="submit" title="Search"><i class="bi bi-search"></i></button>
      </form>
    </div><!-- End Search Bar -->

    <nav class="header-nav ms-auto">
      <ul class="d-flex align-items-center">

        <li class="nav-item d-block d-lg-none">
          <a class="nav-link nav-icon search-bar-toggle " href="#">
            <i class="bi bi-search"></i>
          </a>
        </li><!-- End Search Icon-->

      </ul>
    </nav><!-- End Icons Navigation -->

  </header><!-- End Header -->

  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

      <li class="nav-item">
        <a class="nav-link collapsed"  href="index.jsp">
          <i class="house"></i>
          <span>Accueil</span>
        </a>
      </li><!-- End Dashboard Nav -->
      <li class="nav-item">
        <a class="nav-link collapsed"  href="GetVenteFiltre">
          <i class="shop"></i>
          <span>Vente</span>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link collapsed"  href="GetLaboratoire">
          <i class="bi bi-grid"></i>
          <span>Laboratoires</span>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link collapsed"  href="ListeStock">
          <i class="bi bi-grid"></i>
          <span>Stock</span>
        </a>
      </li>
      <%-- Section produit --%>
      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-menu-button-wide"></i><span>Produits</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="components-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="GetProduit">
              <i class="bi bi-circle"></i><span>Tous</span>
            </a>
          </li>
          <li>
            <a href="ListeMedicament">
              <i class="bi bi-circle"></i><span>Medicaments</span>
            </a>
          </li>
          <li>
            <a href="components-accordion.html">
              <i class="bi bi-circle"></i><span>Hygienes</span>
            </a>
          </li>
          <%-- <li>
            <a href="GetMvtStock">
              <i class="bi bi-circle"></i><span>Stock</span>
            </a>
          </li> --%>
          <li>
            <a href="GetProduitMois">
              <i class="bi bi-circle"></i><span>Produit du mois</span>
            </a>
          </li>
        </ul>
      </li>
      <li class="nav-item">
        <a class="nav-link collapsed" href="ListeCommission">
          <i class="bi bi-dash-circle"></i>
          <span>Commissions</span>
        </a>
      </li>
            <li class="nav-item">
        <a class="nav-link collapsed" href="GetPrixProduit">
          <i class="bi bi-dash-circle"></i>
          <span>Prix Produits</span>
        </a>
      </li>
      
      <%-- <li class="nav-item">
        <a href="ListeVente">
          <i class="bi bi-grid"></i>
          <span>Vente</span>
        </a>
      </li> --%>
      <%-- End Section produit  --%>
    </ul>

  </aside><!-- End Sidebar-->