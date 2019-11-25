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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Equipes;
import model.Subeventos;
import model.Usuarios;

/**
 *
 * @author henrique
 */
@WebServlet("/equipe")
public class Equipe extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
         try{
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            int acao = Integer.parseInt(request.getParameter("acao"));
            
            Inscricoes DAO = new Inscricoes();
            EquipesData DAOE = new EquipesData();
            int idp = DAO.pegarIdParticipante(idUsuario);
            String msg = "";
            
            if(acao == 1){
                String nome = request.getParameter("nome");
                int ide = DAO.verificarInscTodosEventos(idp);
                List<Usuarios> participantesEvento = new ArrayList<>();
                participantesEvento = DAOE.pesquisarParticipante(nome, ide);
                for(Usuarios part: participantesEvento){
                    int idpart = DAO.pegarIdParticipante(part.getIdusuario());
                    msg += idpart + " / " + part.getNome() + " # ";
                }
            }else if(acao == 2){
                String nome = request.getParameter("nome");
                String descricao = request.getParameter("descricao");
                String ids = request.getParameter("idsMembros");
                
                int ide = DAO.verificarInscTodosEventos(idp);
                
                String[] idsM = ids.split(" / ");

                ArrayList<Integer> idsMembros = new ArrayList();
                for(String id: idsM){
                    idsMembros.add(Integer.parseInt(id));
                }
                idsMembros.add(idp);
                
                Equipes equipe = new Equipes();
                equipe.setNome(nome);
                equipe.setDescricao(descricao);
                equipe.setIdlider(idp);
                
                
                if(DAOE.criarEquipe(equipe, idsMembros)){
                    msg = "Equipe criada com sucesso!";
                }else{
                    throw new Exception("Erro ao criar a equipe!");
                }
            }else if(acao == 3){
                int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
                String nome = request.getParameter("nome");
                String descricao = request.getParameter("descricao");
                String ids = request.getParameter("idsMembros");
                
                int ide = DAO.verificarInscTodosEventos(idp);
                
                String[] idsM = ids.split(" / ");

                ArrayList<Integer> idsMembros = new ArrayList();
                for(String id: idsM){
                    idsMembros.add(Integer.parseInt(id));
                }
                idsMembros.add(idp);
                
                Equipes equipe = new Equipes();
                equipe.setIdequipe(idEquipe);
                equipe.setNome(nome);
                equipe.setDescricao(descricao);
                equipe.setIdlider(idp);
                
                List<Subeventos> subeventosEquipeinsc = DAOE.listarSubeventosEquipeInsc(idEquipe);
                for(Subeventos subevento: subeventosEquipeinsc){
                    if(idsMembros.size() < subevento.getQtdemin()){
                        throw new Exception("Alteração não permitida, pois sua equipe está inscrita em um ou mais sub-eventos que necessitam de "
                                + "uma quantidade mínima de " + subevento.getQtdemin() + " membros para participar. Se ainda deseja"
                                        + "diminuir a quantidade de membros, desinscreva sua equipe do(s) sub-evento(s)");
                    }
                    if(idsMembros.size() > subevento.getQtdemax()){
                        throw new Exception("Alteração não permitida, pois sua equipe está inscrita em um ou mais sub-eventos que é permitida a "
                                + "quantidade máxima de " + subevento.getQtdemax() + " membros por grupo. Se ainda deseja"
                                        + " aumentar a quantidade de membros, desinscreva sua equipe do(s) sub-evento(s).");
                    }
                }
                
                if(DAOE.atualizarEquipe(equipe, idsMembros)){
                    msg = "Equipe atualizada com sucesso!";
                }else{
                    throw new Exception("Erro ao atualizar equipe.");
                }
                
            }else if(acao == 4){
                int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
                msg = "Entrou" + idEquipe;
                if(!DAOE.verificarEquipeInscAlgumSubeventos(idEquipe)){
                    if(DAOE.inativarEquipe(idEquipe)){
                        msg = "Equipe excluida com sucesso!";
                    }else{
                        throw new Exception("Erro ao excluir a equipe!");
                    }
                }else{
                    throw new Exception("Sua equipe está inscrita em um ou mais subeventos. "
                            + "Para excluí-la, a desinscreva do(s) sub-evento(s)");
                }  
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
