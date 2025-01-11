/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DbUtils.Connect;
import Model.Age;
import Model.Forme;
import Model.Laboratoire;
import Model.Maladie;
import Model.TypeMedicament;
import Model.TypeObjet;
import Util.Queries;
import jakarta.servlet.RequestDispatcher;

/**
 *
 * @author SERGIANA
 */
@WebServlet("/GetMedicament")

public class GetMedicament extends HttpServlet {

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
            out.println("<title>Servlet GetMedicament</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetMedicament at " + request.getContextPath() + "</h1>");
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
        Connect c = new Connect();
        try {
            c.connectToPostgres();
            if(request.getParameter("type").equals("insertion")) {
                List<Forme> listeForme = Forme.getAll(c);
                // System.out.print(listeForme+" formes ");
                List<Laboratoire> laboratoires = Laboratoire.getAll(c);
                List<TypeObjet> types = new TypeMedicament().getAll(c);
                List<Maladie> maladies = Maladie.getAll(c);
                List<Age> ages = Age.getAll(c);
                request.setAttribute("ages", ages);
                request.setAttribute("laboratoires", laboratoires);
                request.setAttribute("formes", listeForme);
                request.setAttribute("types", types);
                request.setAttribute("maladies", maladies);


                RequestDispatcher dispatcher = request.getRequestDispatcher("insertMedicament.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            c.closeBD();
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
