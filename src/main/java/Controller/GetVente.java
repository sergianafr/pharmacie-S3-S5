package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;
import Model.Client;
import Model.Produit;
import Model.Vente;
import Model.VenteDetail;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetVente")
public class GetVente extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connect connect = new Connect();
        try {
            connect.connectToPostgres();
            // if(req.getParameter("type").equals("insertion")) {
                List<Produit> produits = Produit.getAll(connect);
                req.setAttribute("produits", produits);
                List<Client> clients = Client.getAll(connect);
                req.setAttribute("clients", clients);

            // }
            if(req.getAttribute("error")!=null){
                req.setAttribute("error", req.getAttribute("error"));
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("insertVente.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            connect.closeBD();
        }
    }
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connect con = new Connect();
        try {
            con.connectToPostgres();
            String[] produits = request.getParameterValues("produit[]");
            System.out.println(produits.length);
            String[] quantites = request.getParameterValues("quantite[]");
            System.out.println(quantites.length);

            String idClient = request.getParameter("client");

            List<VenteDetail> listeVente = new ArrayList<VenteDetail>();
            for(int i=0; i<produits.length; i++) {
                VenteDetail vente = new VenteDetail();
                vente.setIdProduit(Integer.parseInt(produits[i]));
                vente.setQte(Integer.parseInt(quantites[i]));
                listeVente.add(vente);
            }
            Vente v = new Vente();
            v.setIdClient(Integer.parseInt(idClient));
            v.setVenteDetails(listeVente);
            v.insertWDetails(con);

        } catch (Exception e) {
            
            request.setAttribute("error", e.getMessage());
            // doGet(request, response);
        }
        finally{
            con.closeBD();
        }
        doGet(request, response);

    }
}
