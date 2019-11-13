/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.Inscricoes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author henrique
 */
@WebServlet("/InscSubevento")
public class InscSubevento extends HttpServlet {

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
            int idSubevento = Integer.parseInt(request.getParameter("idSubevento"));
            String acao = request.getParameter("acao");
            
            
            String msg = "";
            Inscricoes DAO = new Inscricoes();
            int idp = DAO.pegarIdParticipante(idUsuario);
            int idsub = DAO.verificarSubPertEvento(idSubevento, idp);
            
            if(acao.equals("inscrever")){
                if(idsub == idSubevento){
                    if(DAO.inscreverSubEvento(idp, idSubevento)){
                        msg = "Inscrição no sub-evento realizada com sucesso!";
                    }else{
                        msg = "Erro ao realizar a inscrição";
                    }
                }else{
                    msg = "Você precisa estar inscrito(a) no evento para conseguir participar de um subevento dele" + idsub + ", " + idSubevento;
                }
            }else{
                msg = "Ainda não esta feito o desisncrever";
            }
            out.println(msg);
        }catch(Exception e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("Erro");
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
