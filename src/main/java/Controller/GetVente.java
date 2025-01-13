package Controller;

import java.io.IOException;
import java.util.List;

import DbUtils.Connect;
import Model.Produit;
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
            if(req.getParameter("type").equals("insertion")) {
                List<Produit> produits = Produit.getAll(connect);
                req.setAttribute("produits", produits);
            }
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
