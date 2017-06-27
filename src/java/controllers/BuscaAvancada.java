package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Actor;
import model.Movie;
import model.ResultadoBusca;
import persistence.BuscaAvancadaDAO;

public class BuscaAvancada extends HttpServlet {

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
        
        String[] nome;
        String[] idioma;
        String pagina;
        String url;
        
        nome = request.getParameterValues("nome");
        idioma = request.getParameterValues("idioma");
        pagina = request.getParameter("pg");
        url = request.getQueryString();
        
        BuscaAvancadaDAO buscaDAO = new BuscaAvancadaDAO();
        ResultadoBusca res = buscaDAO.buscaAvancada(nome, idioma, pagina);
        
        request.setAttribute("ResultadoBusca", res);
        request.setAttribute("pagina", pagina);
        request.setAttribute("url", url);
        RequestDispatcher rd = request.getRequestDispatcher("/ResultadoBuscaAvancada.jsp");
        rd.forward(request, response);
    }
}
