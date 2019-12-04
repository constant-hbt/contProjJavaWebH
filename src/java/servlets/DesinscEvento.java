/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.Inscricoes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Eventos;

/**
 *
 * @author henrique
 */

public class DesinscEvento extends HttpServlet {

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
        response.setContentType("text;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try{
            
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            int idEvento = Integer.parseInt(request.getParameter("idEvento"));
            
            
            Inscricoes DAO = new Inscricoes();
            Eventos evento = DAO.pegarEvento(idEvento);
            int idp = DAO.pegarIdPart(idUsuario);
            
            String msg = "";
            Date dataAtual = new Date();
            
            if(dataAtual.compareTo(evento.getDatainicioinsc()) >= 0 && dataAtual.compareTo(evento.getDatafim()) <= 0){
                if(DAO.verificarInscEvento(idUsuario, idEvento)){
                    if(DAO.desinscPartEvento(idp, idEvento)){
                        msg = "Desinscrito(a) do evento com sucesso!";
                    }else{
                        throw new Exception("Erro ao se desinscrever do evento!");
                    }
                }else{
                    throw new Exception("Erro, você não está inscrito(a) no evento!");
                }
            }else{
                throw new Exception("Erro, você não pode se inscrever/desinscrever no evento, pois não está dentro do período permitido");
            }
            out.println(msg);
        }catch(Exception e){
            out.println(e.getMessage());
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
