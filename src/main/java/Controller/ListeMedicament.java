/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DbUtils.Connect;
import Model.Age;
import Model.FormeAdministration;
import Model.Maladie;
import Model.Medicament;
import Model.QuantiteMedoc;
import Model.TypeObjet;

import jakarta.servlet.RequestDispatcher;

/**
 *
 * @author SERGIANA
 */
@WebServlet("/ListeMedicament")

public class ListeMedicament extends HttpServlet {

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
            out.println("<title>Servlet ListeMedicament</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListeMedicament at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        Connect co = new Connect();
        try {
            co.connectToPostgres();
            List<Medicament> listeMedicament = new ArrayList<Medicament>();
            List<Age> listeAges = Age.getAll(co);
            List<Maladie> listeMaladies = Maladie.getAll(co);
            request.setAttribute("ages", listeAges);
            request.setAttribute("maladies", listeMaladies);
            if(request.getParameter("idMaladie") == null && request.getParameter("idAge")==null) {
                listeMedicament = Medicament.getWithDetails(co);
            } else {
                Integer idMaladie = 0;
                Integer idAge = 0;
                if(request.getParameter("idMaladie") != null){
                    idMaladie = Integer.parseInt(request.getParameter("idMaladie"));
                    if(idMaladie != 0){
                        request.setAttribute("maladien", listeMaladies.get(idMaladie-1).getNom());
                    }
                }if(request.getParameter("idAge") != null){
                    idAge = Integer.parseInt(request.getParameter("idAge"));
                    if(idAge != 0){
                        request.setAttribute("agen", listeAges.get(idAge-1).getNom());
                    }
                }
                listeMedicament = Medicament.getMedicaments(co, idMaladie, idAge);
            }
            HashMap<Integer, List<Maladie>> maladieMedicament = Maladie.getByListMedicament(co, listeMedicament);
            HashMap<Integer, List<Age>> ageMedicament = Age.getByListMedicament(co, listeMedicament);
            request.setAttribute("maladieMedicament", maladieMedicament);
            request.setAttribute("ageMedicament", ageMedicament);

            request.setAttribute("medicament", listeMedicament);

            RequestDispatcher dispatcher = request.getRequestDispatcher("medicament.jsp");
            dispatcher.forward(request, response);
            //request.getRequestDispatcher("medicament.jsp").forward(request, response);
        } catch (Exception e) {
           e.printStackTrace();
        }
        finally{
            co.closeBD();
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
        String nom = request.getParameter("nom");
        String laboratoire = request.getParameter("laboratoire");

        String[] formes = request.getParameterValues("forme[]");
        String[] modes = request.getParameterValues("mode[]");

        String[] dosages = request.getParameterValues("dosage");

        String[] unites = request.getParameterValues("unite[]");

        String[] maladies = request.getParameterValues("maladies[]");

        String[] ages = request.getParameterValues("ages[]");


        Connect connect = new Connect();
        try {
            connect.connectToPostgres();
            if (nom == null || laboratoire == null || formes == null || modes == null || dosages == null || unites == null) {
                throw new IllegalArgumentException("Certaines données sont manquantes ou invalides.");
            }

            // Enregistrer les données dans la base (simulation ou utilisation d'un DAO)
            Medicament medicament = new Medicament();
            medicament.setNom(nom);
            medicament.setIdLaboratoire(Integer.parseInt(laboratoire));

            List<FormeAdministration> formesAdmin = new ArrayList<FormeAdministration>();
            List<QuantiteMedoc> quantites = new ArrayList<QuantiteMedoc>();
            List<Maladie> listeMal = new ArrayList<Maladie>();
            List<Age> listeAge = new ArrayList<Age>();
            for (String maladie : maladies) {
                System.out.println(maladie+" maladie");

                Maladie mal = new Maladie();
                mal.setId(Integer.parseInt(maladie));
                listeMal.add(mal);
            }
            for(String age : ages){
                System.out.println(age+" age");

                Age a = new Age();
                a.setId(Integer.parseInt(age));
                listeAge.add(a);
            }
            for (int i = 0; i < formes.length; i++) {
                System.out.println(modes[i]+" mode "+formes[i]+" forme");

                FormeAdministration formeAdmin = new FormeAdministration();
                formeAdmin.setId(Integer.parseInt(modes[i]));

                QuantiteMedoc qt = new QuantiteMedoc();
                qt.setQte(Double.parseDouble(dosages[i]));
                qt.setIdUnite(Integer.parseInt(unites[i]));
                quantites.add(qt);
                formesAdmin.add(formeAdmin);
                
            }
            medicament.setMaladies(listeMal);
            medicament.setFormesAdmin(formesAdmin);
            medicament.setQuantites(quantites);
            medicament.setAges(listeAge);

            medicament.validerInsert(connect);
            connect.getConnex().commit();
            doGet(request, response);
            // response.sendRedirect("confirmation.jsp");
        } catch (Exception e) {
            if(connect.getConnex()!=null){connect.rollback();}
            e.printStackTrace();
            // En cas d'erreur, rediriger vers une page d'erreur ou afficher un message
            // request.setAttribute("error", "Erreur lors de l'enregistrement : " + e.getMessage());
            // request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        finally{
            if(connect!=null) {
                connect.closeBD();
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
        return "Short description";
    }// </editor-fold>

}
