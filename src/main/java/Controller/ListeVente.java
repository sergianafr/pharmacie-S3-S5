/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DbUtils.Connect;
import Model.Administration;
import Model.Age;
import Model.VenteDetail;
import jakarta.servlet.RequestDispatcher;
 
/**
 *
 * @author SERGIANA
 */
@WebServlet("/ListeVente")

public class ListeVente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListeVente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListeVente at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            int forme = 0;
            int age = 0;
            System.out.println("mididtraa");
            con.connectToPostgres();
            List<VenteDetail> listeVente = new ArrayList<VenteDetail>();
            List<Age> listeAges = Age.getAll(con);
            List<Administration> admin = Administration.getAll(con);
            if(request.getParameter("forme")==null && request.getParameter("age")==null) {
                listeVente = VenteDetail.getAll(con);
            }else {
                if(request.getParameter("forme") != null){
                    forme = Integer.parseInt(request.getParameter("forme"));
                    // if(forme != 0){
                    //     request.setAttribute("maladien", liste.get(idMaladie-1).getNom());
                    // }
                }if(request.getParameter("age") != null){
                    age = Integer.parseInt(request.getParameter("age"));
                    // if(forme != 0){
                    //     request.setAttribute("agen", listeAges.get(forme-1).getNom());
                    // }
                }
                listeVente = VenteDetail.filtrer(con, forme, age);
            }
            request.setAttribute("ventes", listeVente);
            request.setAttribute("ages", listeAges);

            request.setAttribute("administrations", admin);
            
            //out.printlin()
            RequestDispatcher dispatcher = request.getRequestDispatcher("vente.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
