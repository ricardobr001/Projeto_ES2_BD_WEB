/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author ricardo
 */
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuscaAvancada</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuscaAvancada at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
        String nome;
        String idioma;
        
        nome = request.getParameter("nome");
        idioma = request.getParameter("idioma");
        
        BuscaAvancadaDAO buscaDAO = new BuscaAvancadaDAO();
        ResultadoBusca res = buscaDAO.buscaAvancada(nome, idioma); 
        
        request.setAttribute("ResultadoBusca", res);
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/ResultadoBuscaAvancada.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //System.out.println("Chegou aqui");
        String nome;
        String idioma;
        
        nome = request.getParameter("nome");
        idioma = request.getParameter("idioma");
        System.out.println(request.getParameter("nome"));
        System.out.println(request.getParameter("idioma"));
        
        //System.out.println("Chegou aqui");
        BuscaAvancadaDAO buscaDAO = new BuscaAvancadaDAO();
        //Vector res = new Vector();
        //Vector movies = new Vector();
        
        
        ResultadoBusca res = buscaDAO.buscaAvancada(nome, idioma);
        
        request.setAttribute("ResultadoBusca", res);
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/ResultadoBuscaAvancada.jsp");
        rd.forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
