package Controller;
import DbUtils.Connect;
import Model.Laboratoire;
import Model.Pays;
import Model.Produit;
import Model.Vente;
import Model.ComissionEmploye;
import Model.Employe;
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
 * @author Sergiana
 */
@WebServlet("/ListeCommission")
public class ListeCommission extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Connect c = new Connect();
        try {
            c.connectToPostgres();
            Integer idEmploye = null;
            Date dateDebut = null;
            Date dateFin = null;
            if(req.getParameter("idEmploye")!=null) {
                if(!req.getParameter("idEmploye").isEmpty()){
                    System.out.println(req.getParameter("idEmploye"));
                    idEmploye = Integer.parseInt(req.getParameter("idEmploye"));
                }
            }if (req.getParameter("dateDebut")!=null) {
                if(!req.getParameter("dateDebut").isEmpty()){
                    dateDebut = Date.valueOf(req.getParameter("dateDebut"));
                }
            }if (req.getParameter("dateFin")!=null) {
                if(!req.getParameter("dateFin").isEmpty()){
                    dateFin = Date.valueOf(req.getParameter("dateFin"));
                }
            }
            ComissionEmploye comm = ComissionEmploye.getByGenre(c, dateDebut, dateFin);
            req.setAttribute("comissionEmploye", comm);
            List<Employe> employees = Employe.getAll(c);
            List<Vente> list = Vente.getCommission(c, idEmploye, dateDebut, dateFin);
            req.setAttribute("listCommission", list);  
            req.setAttribute("employes", employees);  
            RequestDispatcher dispatcher = req.getRequestDispatcher("listeCommission.jsp");  
            dispatcher.forward(req, resp);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            c.closeBD();
        }
    }
}
