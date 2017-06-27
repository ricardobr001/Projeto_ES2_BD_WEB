package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ResultadoRanking;
import persistence.RankingDAO;

public class Ranking extends HttpServlet {

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
        PrintWriter out = response.getWriter();
    }

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
        String genero1;
        String genero2;
        String pagina;
        String url;
        
        genero1 = request.getParameter("genero1");
        genero2 = request.getParameter("genero2");
        pagina = request.getParameter("pg");
        url = request.getQueryString();
        
        RankingDAO rDAO = new RankingDAO();
        ResultadoRanking res = rDAO.Ranking(genero1, genero2, pagina);
        
        request.setAttribute("ResultadoRanking", res);
        request.setAttribute("pagina", pagina);
        request.setAttribute("url", url);
        RequestDispatcher rd = request.getRequestDispatcher("/ResultadoRanking.jsp");
        rd.forward(request, response);
    }
}
