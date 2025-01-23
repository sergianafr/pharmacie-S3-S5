package Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;
import Model.ComissionEmploye;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetEtatComission")
public class GetEtatComission extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connect co = new Connect();
        try {
            co.connectToPostgres();
            Date dateDebut = null;
            Date dateFin = null;
            if (req.getParameter("dateDebut")!=null) {
                if(!req.getParameter("dateDebut").isEmpty()){
                    dateDebut = Date.valueOf(req.getParameter("dateDebut"));
                }
            }if (req.getParameter("dateFin")!=null) {
                if(!req.getParameter("dateFin").isEmpty()){
                    dateFin = Date.valueOf(req.getParameter("dateFin"));
                }
            }
            ComissionEmploye comm = ComissionEmploye.getByGenre(co, dateDebut, dateFin);
            req.setAttribute("comissionEmploye", comm);
            req.getRequestDispatcher("commissionSexe.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            co.closeBD();
        }
    }

    
}
