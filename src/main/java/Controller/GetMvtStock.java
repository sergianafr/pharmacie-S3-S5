/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DbUtils.Connect;
import Model.Laboratoire;
import Model.Pays;
import Model.MvtStock;
import Model.Produit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet pour gérer les laboratoires
 * @author Ny Ando
 */
@WebServlet("/GetMvtStock")
public class GetMvtStock extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connect con = new Connect();
        try {
            con.connectToPostgres();

            List<Produit> produit = Produit.getAll(con);

            request.setAttribute("Produit", produit);

            RequestDispatcher dispatcher = request.getRequestDispatcher("insertionStock.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            if (con != null) {
                try {
                    con.closeBD();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Connect con = new Connect();
        try {
            con.connectToPostgres();

            // Récupération des données du formulaire
            String dateFabricationStr = request.getParameter("dateFabrication");
            String datePeremptionStr = request.getParameter("datePeremption");
            String quantiteStr = request.getParameter("quantite");
            String produitIdStr = request.getParameter("produits");

            // Validation des données
            if (produitIdStr == null || quantiteStr == null) {
                throw new IllegalArgumentException("Le produit et la quantité sont obligatoires.");
            }

            int idProduit = Integer.parseInt(produitIdStr);
            double quantite = Double.parseDouble(quantiteStr);
            java.sql.Date dateFabrication = (dateFabricationStr != null && !dateFabricationStr.isEmpty())
                    ? java.sql.Date.valueOf(dateFabricationStr)
                    : null;
            java.sql.Date datePeremption = (datePeremptionStr != null && !datePeremptionStr.isEmpty())
                    ? java.sql.Date.valueOf(datePeremptionStr)
                    : null;

            // Création et insertion de l'objet MvtStock
            MvtStock mvtStock = new MvtStock();
            mvtStock.setDateFabrication(dateFabrication);
            mvtStock.setDatePeremption(datePeremption);
            mvtStock.setQteEntree(quantite);
            mvtStock.setIdProduit(idProduit);
            mvtStock.insert(con.getConnex());

            con.getConnex().commit();


            // Redirection après succès
            response.sendRedirect("ListeStock");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            if (con != null) {
                try {
                    con.closeBD();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet pour gérer les laboratoires";
    }
}