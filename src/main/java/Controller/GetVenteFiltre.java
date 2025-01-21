package Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;
import Model.Administration;
import Model.Age;
import Model.Client;
import Model.Employe;
import Model.VenteDetail;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/GetVenteFiltre")
public class GetVenteFiltre extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connect con = new Connect();
        try {
            Integer forme = null;
            Integer age = null;
            Integer idClient = null;
            Date date = null;

            System.out.println("mididtraa");
            con.connectToPostgres();
            List<VenteDetail> listeVente = new ArrayList<VenteDetail>();
            List<Age> listeAges = Age.getAll(con);
            List<Administration> admin = Administration.getAll(con);
            List<Client> clients =Client.getAll(con);
            List<Employe> employes = Employe.getAll(con);
            request.setAttribute("employes", employes);

            if(request.getParameter("forme") != null){
                if(!request.getParameter("forme").isEmpty())forme = Integer.parseInt(request.getParameter("forme"));
            }
            if(request.getParameter("age") != null){
                if(!request.getParameter("age").isEmpty())age = Integer.parseInt(request.getParameter("age"));
            }

            request.setAttribute("ages", listeAges);
            request.setAttribute("administrations", admin);
            // if(request.getParameter("client")==null && request.getParameter("date")==null) {
            //     listeVente = VenteDetail.getAll(con);
            // }
            // else {
            if(request.getParameter("client") != null){
                if(!request.getParameter("client").isEmpty())idClient = Integer.parseInt(request.getParameter("client"));
            }if(request.getParameter("date") != null){
                System.out.println("datteeee +"+request.getParameter("date"));
                if(!request.getParameter("date").isEmpty())date = Date.valueOf(request.getParameter("date"));
            }
            listeVente = VenteDetail.filtrerVentes(con, forme, age, idClient, date);
            // }
            request.setAttribute("ventes", listeVente);
            request.setAttribute("clients", clients);
            // request.setAttribute("administrations", admin);
            
            //out.printlin()
            RequestDispatcher dispatcher = request.getRequestDispatcher("ventecop.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
