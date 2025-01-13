package Controller;

import java.io.IOException;
import java.util.List;
import java.util.*;
import DbUtils.Connect;
import Model.Produit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetProduit")
public class GetProduit extends HttpServlet{
    
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            // TODO Auto-generated method stub
            Connect con = new Connect();
            try {
                con.connectToPostgres();
                List<Produit> list = Produit.getAll(con);
                req.setAttribute("produits", list);
                RequestDispatcher dispatcher = req.getRequestDispatcher("produit.jsp");
                dispatcher.forward(req, resp);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
}   
