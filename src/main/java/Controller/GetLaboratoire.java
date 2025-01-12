/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DbUtils.Connect;
import Model.Laboratoire;
import Model.Pays;
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
@WebServlet("/GetLaboratoire")
public class GetLaboratoire extends HttpServlet {

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

            List<Laboratoire> listLaboratoire = Laboratoire.getAll(con);
            List<Pays> listPays = Pays.getAll(con);

            request.setAttribute("laboratoires", listLaboratoire);
            request.setAttribute("pays", listPays);

            RequestDispatcher dispatcher = request.getRequestDispatcher("InsertionLaboratoire.jsp");
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

            String nomLaboratoire = request.getParameter("nomLaboratoire");
            int idPaysOrigine = Integer.parseInt(request.getParameter("idPaysOrigine"));

            Laboratoire laboratoire = new Laboratoire();
            laboratoire.setNom(nomLaboratoire);
            laboratoire.setId_pays_origine(idPaysOrigine);

            laboratoire.insert(con);

            response.sendRedirect("GetLaboratoire");
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
