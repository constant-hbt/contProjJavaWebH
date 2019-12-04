/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.Inscricoes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Equipes;
import model.Eventos;
import model.Subeventos;
import servlets.utils.FormatacaoDatas;

/**
 *
 * @author henrique
 */
@WebServlet("/InscEvento")
public class InscEvento extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
        
        try{
            
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            int idEvento = Integer.parseInt(request.getParameter("idEvento"));
          
            Inscricoes DAO = new Inscricoes();
            int idp = DAO.pegarIdPart(idUsuario);
            int ide = DAO.verificarInscTodosEventos(idEvento);
            String msg = "";
            
            Eventos eventoAtual = DAO.pegarEvento(idEvento);
            Date dataAtual = new Date();
            msg = "" + idp + "idusuario: " + idUsuario+ "idevento: " + idEvento;
            if(dataAtual.compareTo(eventoAtual.getDatainicioinsc()) >= 0 && dataAtual.compareTo(eventoAtual.getDatafim()) <= 0){
                if(ide == 0){
                    if(DAO.verificarHistEvento(idp, idEvento)){
                        if(DAO.atualizarInscEvento(idp, ide, idEvento)){
                            msg = "Inscrição realizada com sucesso!";
                        }else{
                            throw new Exception("Erro ao realizar a inscrição!");
                        }
                    }else{
                        if(DAO.inscreverEvento(idUsuario, idEvento)){
                            msg = "Inscrição realizada com sucesso!";
                        }else{
                            throw new Exception("Erro ao realizar a inscrição!");
                        }
                    } 
                }else{
                    throw new Exception("Se desinscreva do evento que está inscrito primeiramente!");
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
        //response.setContentType("text/html;charset=UTF-8");
        
        /*
        try{
            
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            int idEvento = Integer.parseInt(request.getParameter("idEvento"));
            PrintWriter out = response.getWriter();
            out.println("idEvento: " + idEvento + "id usuario: " + idUsuario);
            
            Inscricoes DAO = new Inscricoes();
            ArrayList<Integer> ids = DAO.verificarInscEvento(idUsuario);
            String msg = "";
            
            if(ids.get(0) == 0){
                if(DAO.inscreverEvento(idUsuario, idEvento)){
                    msg = "Inscrição realizada com sucesso!";
                    response.getWriter().write(msg);
                    response.setStatus(HttpServletResponse.SC_CREATED);
                }else{
                    msg = "Erro ao realizar a inscrição!";
                    response.getWriter().write(msg);
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            }else{
                if(ids.get(0) > 0 && ids.get(1) > 0){
                    if(DAO.atualizarInscEvento(ids.get(0), idEvento)){
                        response.setStatus(HttpServletResponse.SC_OK);
                        msg = "Inscrito no evento com sucesso! Sua outra inscrição foi inativada!";
                        response.getWriter().write(msg);
                    }else{
                        msg = "Erro ao realizar a inscrição!";
                        response.getWriter().write(msg);
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    }
                }
            }
        }catch(Exception e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
            */
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
