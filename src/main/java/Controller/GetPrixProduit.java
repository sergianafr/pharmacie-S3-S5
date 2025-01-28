/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DbUtils.Connect;
import Model.ConseilMois;
import Model.Laboratoire;
import Model.Pays;
import Model.MvtStock;
import Model.Produit;
import Model.PrixProduit;
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
@WebServlet("/GetPrixProduit")
public class GetPrixProduit extends HttpServlet {

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

            RequestDispatcher dispatcher = request.getRequestDispatcher("insertionPrixProduit.jsp");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connect con = new Connect();
        try {
            con.connectToPostgres();

            int prixProduit = Integer.valueOf(request.getParameter("prix"));
            int idProduit = Integer.valueOf(request.getParameter("produit"));

            PrixProduit prix = new PrixProduit(prixProduit,idProduit);
            prix.setIdProduit(idProduit);
            System.out.println(prix.getIdProduit() +"  idProduit");
            System.out.println(prix.getMontant());

            prix.create(con); 
            con.getConnex().commit();
            request.setAttribute("success", "Insertion reussie");
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            doGet(request, response);
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


    @Override
    public String getServletInfo() {
        return "Servlet pour gérer les laboratoires";
    }
}