package Controller;

import java.io.IOException;
import java.util.List;

import DbUtils.Connect;
import Model.EtatStock;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListeStock")
public class ListeStock extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connect co = new Connect();
        try {
            co.connectToPostgres();
            List<EtatStock> stocks = EtatStock.getAll(co);
            req.setAttribute("stocks", stocks);
            req.getRequestDispatcher("listeStock.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            co.closeBD();
        }
    }
}
