/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InscricoesSub extends Conexao{
    public InscricoesSub(){}
    
    public boolean verificaInscSub(int idPart, int idSubevento) throws Exception{
        try{
            String sql = "Select idstatus from inscricao_part_subeve where idparticipante = ? and idsubevento = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idPart);
            ps.setInt(2, idSubevento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getInt("idstatus") == 1){
                    return true;
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    private boolean verificarDataHoraSub(int idPart, int idSubevento) throws Exception{
        String sql = "select s.datahorainicio, s.datahorafim from subeventos s INNER JOIN "
                + "inscricao_part_subeve i ON (s.idsubevento = i.idsubevento) WHERE i.idstatus = 1 AND i.idparticipante = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setInt(1, idPart);
        ResultSet rs = ps.executeQuery();
        String sql2 = "select datahorainicio, datahorafim from subeventos where idsubevento = ?";
        PreparedStatement ps2 = getConexao().prepareStatement(sql2);
        ps2.setInt(1, idSubevento);
        ResultSet rs2 = ps2.executeQuery();
        /*Date dataAtual = new Date();
        SimpleDateFormat d = new SimpleDateFormat("");
        while(rs.next()){
            
        }*/
        return true;
    }
    
    public boolean inscreverSubEvento(int idPart, int idSubevento) throws Exception{
        getConexao().setAutoCommit(false);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String string  = dateFormat.format(new Date());
        String sql = "Insert into inscricao_part_subeve (datahora, idparticipante, idsubevento, idstatus)"
                + " values (?,?,?,1)";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setString(1, string);
        ps.setInt(2, idPart);
        ps.setInt(3, idSubevento);
        if(ps.executeUpdate() > 0){
            getConexao().commit();
            getConexao().setAutoCommit(true);
            return true;
        }
        getConexao().rollback();
        getConexao().setAutoCommit(true);
        return false;
    }
    
    public boolean desisncSubevento(int idPart, int idSubevento) throws Exception{
        getConexao().setAutoCommit(false);
        String sql = "update inscricao_part_subeve set idstatus = 2 where idparticipante = ? and idsubevento = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setInt(1, idPart);
        ps.setInt(2, idSubevento);
        if(ps.executeUpdate() > 0){
            getConexao().commit();
            getConexao().setAutoCommit(true);
            return true;
        }
        getConexao().rollback();
        getConexao().setAutoCommit(true);
        return false;
    }
}
