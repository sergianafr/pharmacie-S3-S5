package Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import DbUtils.Connect;
import Model.ComissionEmploye;
import Model.PrixProduit;
import Model.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetHistoriquePrix")
public class GetHistoriquePrix extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Connect co = new Connect();
        try {
            co.connectToPostgres();
            Date dateDebut = null;
            Date dateFin = null;
            int id_produit = 0;
            if(req.getParameter("id_produit") != null){
                id_produit = Integer.parseInt(req.getParameter("id_produit"));
            }
            if (req.getParameter("dateDebut")!=null) {
                if(!req.getParameter("dateDebut").isEmpty()){
                    dateDebut = Date.valueOf(req.getParameter("dateDebut"));
                }
            }if (req.getParameter("dateFin")!=null) {
                if(!req.getParameter("dateFin").isEmpty()){
                    dateFin = Date.valueOf(req.getParameter("dateFin"));
                }
            }
            System.out.println(id_produit+ "idddd"+ dateFin + " " + dateDebut);
            List<PrixProduit> comm = PrixProduit.getAll(id_produit, dateDebut, dateFin, co);
            List<Produit> produits = Produit.getAll(co);
            req.setAttribute("produits",produits);
            req.setAttribute("historiques", comm);
            req.getRequestDispatcher("historiquePrix.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            co.closeBD();
        }
    }
}
