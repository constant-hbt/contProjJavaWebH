/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.EquipesData;
import controller.Inscricoes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Equipes;
import model.Subeventos;

/**
 *
 * @author henrique
 */
@WebServlet("/inscequipesub")
public class InscEquipeSub extends HttpServlet {

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
        
        try{
            
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            int idSubevento = Integer.parseInt(request.getParameter("idSubevento"));
            int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
            String acao = request.getParameter("acao");
            
            
            String msg = "";
            Inscricoes DAO = new Inscricoes();
            EquipesData DAOE = new EquipesData();
            int idp = DAO.pegarIdParticipante(idUsuario);
            int idsub = DAO.verificarSubPertEvento(idSubevento, idp);
            int idEvento = DAO.pegarIdEvento(idp);
            Subeventos subeventoAtual = DAO.pegarSubevento(idsub, idEvento);
            Equipes equipe = DAOE.getEquipeById(idEquipe);
            
            if(acao.equals("inscrever")){
                if(idsub == idSubevento){
                    int qtdeMembros = DAOE.pegarQtdeMembrosEquipe(idEquipe);
                    int qtdeEquipes = DAO.pegarQtdeEquipesInscSub(idSubevento);
                    if(qtdeMembros >= subeventoAtual.getQtdemin() && qtdeMembros <= subeventoAtual.getQtdemax()){
                        if(qtdeEquipes <= subeventoAtual.getQtdemaxequipes()){
                            if(DAO.verificarHistInscEquipeSubevento(idEquipe, idSubevento)){
                                if(DAO.atualizarInscEquipeSubevento(idEquipe, idSubevento)){
                                    msg = "1 / Inscrição no sub-evento realizada com sucesso!";
                                }else{
                                    throw new Exception("Erro ao realizar a inscrição");
                                }
                            }else{
                                if(DAO.inscreverEquipeSub(idEquipe, idSubevento)){
                                    msg = "Inscrição no sub-evento realizada com sucesso!";
                                }else{
                                    throw new Exception("Erro ao realizar a inscrição");
                                }
                            }
                        }else{
                            throw new Exception("Não foi possível inscrever a sua equipe no sub-evento, pois já atingiu a quantidade máxima de equipes permitida");
                        }
                    }else{
                        throw new Exception("A quantidade de membros da sua equipe não condiz com a quantidade permitida para o sub-evento");
                    }
                }
            }else{
                if(idsub == idSubevento){
                    if(DAO.verificarEquipeInscSub(idEquipe, idSubevento)){
                        if(DAO.desinscreverEquipeSubevento(idEquipe, idSubevento)){
                            msg = "Desisncrito do sub-evento com sucesso!";
                        }else{
                            throw new Exception("Erro ao se desinscrever!");
                        }
                    }else{
                        throw new Exception("Erro, sua equipe não está inscrito no sub-evento");
                    }
                }else{
                    throw new Exception("Sua equipe não está inscrita no evento! Inscreva a sua equipe primeiramente.");
                }
            }
            //Timestamp datahoraInicioSubA = converterStringParaTimestamp("16/11/2019 15:00");
            //Timestamp datahoraInicio = converterStringParaTimestamp("16/11/2019 17:00");
            //Timestamp datahoraFimSubA = converterStringParaTimestamp("16/11/2019 14:30");
            //Timestamp datahoraFim = converterStringParaTimestamp("16/11/2019 16:30");
            //msg = "datahora no timestamp: " + ((datahoraInicioSubA.compareTo(datahoraFim) <= 0 && datahoraInicioSubA.compareTo(datahoraInicio) >= 0) || 
                        //(datahoraFimSubA.compareTo(datahoraInicio) >= 0 && datahoraFimSubA.compareTo(datahoraFim) <= 0));
            out.println(msg);
        }catch(Exception e){
            out.println("0 / " + e.getMessage());
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
