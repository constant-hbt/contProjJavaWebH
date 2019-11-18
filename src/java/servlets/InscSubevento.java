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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Subeventos;
import servlets.utils.FormatacaoDatas;
import static servlets.utils.FormatacaoDatas.converterStringParaTimestamp;

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
    public boolean compararInicioFimSubeventos(List<Subeventos> subeventos, Subeventos subeventoAtual) throws Exception{
        boolean flag = true;
        try{
            Timestamp datahoraInicio;
            Timestamp datahoraInicioSubA = converterStringParaTimestamp(subeventoAtual.getDatahorainicio());
            Timestamp datahoraFim;
            Timestamp datahoraFimSubA = converterStringParaTimestamp(subeventoAtual.getDatahorafim());
            for(Subeventos subevento: subeventos){
                datahoraInicio = converterStringParaTimestamp(subevento.getDatahorainicio());
                datahoraFim = converterStringParaTimestamp(subevento.getDatahorainicio());
                if((datahoraInicioSubA.compareTo(datahoraFim) <= 0 && datahoraInicioSubA.compareTo(datahoraInicio) >= 0) || 
                        (datahoraFimSubA.compareTo(datahoraInicio) >= 0 && datahoraFimSubA.compareTo(datahoraFim) <= 0)){
                    flag = false;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try{
            
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            int idSubevento = Integer.parseInt(request.getParameter("idSubevento"));
            int idEvento = Integer.parseInt(request.getParameter("idEvento"));
            String acao = request.getParameter("acao");
            
            
            String msg = "";
            Inscricoes DAO = new Inscricoes();
            int idp = DAO.pegarIdParticipante(idUsuario);
            int idsub = DAO.verificarSubPertEvento(idSubevento, idp);
            
            if(acao.equals("inscrever")){
                if(idsub == idSubevento){
                    Subeventos subeveAtual = DAO.pegarSubevento(idSubevento, idEvento);
                    List<Subeventos> subeventos = DAO.listarSubeventosPart(idp);
                    if(compararInicioFimSubeventos(subeventos, subeveAtual)){
                        if(DAO.verificarHistInscSubevento(idp, idSubevento)){
                            if(DAO.atualizarInscSubevento(idp, idSubevento)){
                                msg = "Inscrição no sub-evento realizada com sucesso!";
                            }else{
                                throw new Exception("Erro ao realizar a inscrição");
                            }
                        }else{
                            if(DAO.inscreverSubEvento(idp, idSubevento)){
                                msg = "Inscrição no sub-evento realizada com sucesso!";
                            }else{
                                throw new Exception("Erro ao realizar a inscrição");
                            }
                        }
                    }else{
                        msg = "Você já está inscrito em um sub-evento que tem data/horários que entram em conflito";
                    }
                }else{
                    throw new Exception("Você precisa estar inscrito(a) no evento para conseguir participar de um subevento dele" + idsub + ", " + idSubevento);
                }
            }else{
                if(idsub == idSubevento){
                    if(DAO.verificarInscSubevento(idp, idSubevento)){
                        if(DAO.desinscreverSubevento(idp, idSubevento)){
                            msg = "Desisncrito do sub-evento com sucesso!";
                        }else{
                            throw new Exception();
                        }
                    }else{
                        throw new Exception("Erro, você não está inscrito no sub-evento");
                    }
                }else{
                    throw new Exception("Você não está inscrito no evento! Inscreva-se nele primeiramente.");
                }
            }
            String datahoraa = "16/10/2019 14:30";
            //Timestamp datahoraaa = converterStringParaTimestamp(datahoraa);
            //msg = "datahora no timestamp: " + datahoraaa;
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
