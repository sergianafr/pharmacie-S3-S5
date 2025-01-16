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
import Model.Produit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * Servlet pour gérer les laboratoires
 * @author Ny Ando
 */
@WebServlet("/GetProduitMois")
public class GetProduitMois extends HttpServlet {

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
            Date dateMin = null;
            Date dateMax = null;
            if(request.getParameter("dateMin")!=null ){
                if(! request.getParameter("dateMin").isEmpty()){
                    dateMin = Date.valueOf(request.getParameter("dateMin"));
                }
            }
            if(request.getParameter("dateMax")!=null){
                if(!request.getParameter("dateMax").isEmpty()){
                    dateMax = Date.valueOf(request.getParameter("dateMax"));
                }
            }
            List<ConseilMois> listProduit = ConseilMois.filtre(con, dateMin, dateMax);
            if(request.getParameter("annee")!=null){
                if(!request.getParameter("annee").isEmpty()){
                    listProduit = ConseilMois.getByAnnee(con, Integer.parseInt(request.getParameter("annee")));
                }

            }
            request.setAttribute("produits", listProduit);

            RequestDispatcher dispatcher = request.getRequestDispatcher("listeProduitsMois.jsp");
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
    // @Override
    // protected void doPost(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
    //     Connect con = new Connect();
    //     try {
    //         con.connectToPostgres();

    //         String nomLaboratoire = request.getParameter("nomLaboratoire");
    //         int idPaysOrigine = Integer.parseInt(request.getParameter("idPaysOrigine"));

    //         Laboratoire laboratoire = new Laboratoire();
    //         laboratoire.setNom(nomLaboratoire);
    //         laboratoire.setId_pays_origine(idPaysOrigine);

    //         laboratoire.insert(con);

    //         response.sendRedirect("GetLaboratoire");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    //     } finally {
    //         if (con != null) {
    //             try {
    //                 con.closeBD();
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     }
    // }

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