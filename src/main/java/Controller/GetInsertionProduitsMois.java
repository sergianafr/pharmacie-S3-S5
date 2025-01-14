package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;
import Model.Laboratoire;
import Model.Produit;
import Model.Vente;
import Model.VenteDetail;
import Model.ConseilMois;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetInsertionProduitsMois")
public class GetInsertionProduitsMois extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connect connect = new Connect();
        try {
            connect.connectToPostgres();
            // if(req.getParameter("type").equals("insertion")) {
                List<Produit> produits = Produit.getAll(connect);
                req.setAttribute("produits", produits);
            // }
            if(req.getAttribute("error")!=null){
                req.setAttribute("error", req.getAttribute("error"));
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("insertionProduitMois.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            connect.closeBD();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connect con = new Connect();
        try {
            con.connectToPostgres();

            // int idProduit = Integer.valueOf(request.getParameter("produit"));
            int idProduit = Integer.valueOf(request.getParameter("produit"));


            ConseilMois conseil = new ConseilMois();
            conseil.setIdProduit(idProduit);

            conseil.create(con);

            response.sendRedirect("GetProduitMois");
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
}
