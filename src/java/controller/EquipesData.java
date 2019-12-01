/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Equipes;
import model.InscricaoEquipeSub;
import model.Participantes;
import model.Salas;
import model.Status;
import model.Subeventos;
import model.Usuarios;
import servlets.utils.FormatacaoDatas;

/**
 *
 * @author henrique
 */
public class EquipesData extends Conexao{
    public EquipesData() throws Exception {}
    
    public Equipes getEquipeById(int idEquipe) throws Exception{
        Equipes equipe = new Equipes();
        try{
            String sql = "Select * from equipes where idequipe = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEquipe);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                equipe.setIdequipe(rs.getInt("idequipe"));
                equipe.setNome(rs.getString("nome"));
                equipe.setDescricao(rs.getString("descricao"));
                equipe.setIdlider(rs.getInt("idlider"));
                
                Status status = new Status();
                status.setIdstatus(rs.getInt("idStatus"));
                String sqlStatus = "Select descricao FROM STATUS WHERE idstatus = ?";
                PreparedStatement psSattus = getConexao().prepareStatement(sqlStatus);
                psSattus.setInt(1, status.getIdstatus());
                ResultSet rsStatus = psSattus.executeQuery();
                if(rsStatus.next()){
                    status.setDescricao(rsStatus.getString("descricao"));
                }
                equipe.setStatus(status);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return equipe;
    }
    
    public boolean criarEquipe(Equipes equipe, List<Integer> membros) throws Exception{
        getConexao().setAutoCommit(false);
        try{
            String sql = "Insert into equipes (nome, descricao, idlider, idstatus) values (?,?,?,1)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, equipe.getNome());
            ps.setString(2, equipe.getDescricao());
            ps.setLong(3, equipe.getIdlider());
            if(ps.executeUpdate() > 0){
                String sqle = "Select max(idequipe) as max from equipes";
                PreparedStatement pse = getConexao().prepareStatement(sqle);
                ResultSet rse = pse.executeQuery();
                if(rse.next()){
                    int idequipe = rse.getInt("max");
                    for(Integer membro: membros){
                        String sqlPart = "Insert into PARTICIPANTE_EQUIPE (idstatus, idparticipante, idequipe) values (1,?,?)";
                        PreparedStatement psPart = getConexao().prepareStatement(sqlPart);
                        psPart.setInt(1, membro);
                        psPart.setInt(2, idequipe);
                        if(!(psPart.executeUpdate() > 0)){
                            getConexao().rollback();
                            getConexao().setAutoCommit(true);
                            return false;
                        }
                    }
                    getConexao().commit();
                    getConexao().setAutoCommit(true);
                    return true;
                }  
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        getConexao().rollback();
        getConexao().setAutoCommit(true);
        return false;
    }
    
    public boolean inativarMembroEquipe(int idp, int ide) throws Exception{
        getConexao().setAutoCommit(false);
        try{
            String sql = "Update PARTICIPANTE_EQUIPE set idstatus = 2 where idparticipante = ? and idequipe = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idp);
            ps.setInt(2, ide);
            if(ps.executeUpdate() > 0){
                getConexao().commit();
                getConexao().setAutoCommit(true);
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        getConexao().rollback();
        getConexao().setAutoCommit(true);
        return false;
    }
    
    public boolean inativarEquipe(int idEquipe) throws Exception{
        getConexao().setAutoCommit(false);
        try{
            String sql = "Select idparticipante from PARTICIPANTE_EQUIPE where idequipe = ? and idstatus = 1";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEquipe);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(!inativarMembroEquipe(rs.getInt("idparticipante"), idEquipe)){
                    getConexao().rollback();
                    getConexao().setAutoCommit(true);
                    return false;
                }
            }
            String sqle = "Update equipes set idstatus = 2 where idequipe = ?";
            PreparedStatement pse = getConexao().prepareStatement(sqle);
            pse.setInt(1, idEquipe);
            if(pse.executeUpdate() > 0){
                getConexao().commit();
                getConexao().setAutoCommit(true);
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        getConexao().rollback();
        getConexao().setAutoCommit(true);
        return false;
    }
    
    public boolean atualizarEquipe(Equipes equipe, List<Integer> membros) throws Exception{
        getConexao().setAutoCommit(false);
        try{
            String sql = "Update equipes set nome = ?, descricao = ? where idequipe = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, equipe.getNome());
            ps.setString(2, equipe.getDescricao());
            ps.setInt(3, equipe.getIdequipe());
            if(ps.executeUpdate() > 0){
                String sqlpa = "Select idparticipante from participante_equipe where idequipe = ?";
                PreparedStatement pspa = getConexao().prepareStatement(sqlpa);
                pspa.setInt(1, equipe.getIdequipe());
                ResultSet rs = pspa.executeQuery();
                List<Integer> idpart_antigos = new ArrayList<>();
                while(rs.next()){
                    idpart_antigos.add(rs.getInt("idparticipante"));
                }
                String sqlp = "Update PARTICIPANTE_EQUIPE set idstatus = 2 where idequipe = ?";
                PreparedStatement psp = getConexao().prepareStatement(sqlp);
                String sqla = "Update participante_equipe set idstatus = 1 where idequipe = ? and idparticipante = ?";
                PreparedStatement psa = getConexao().prepareStatement(sqla);
                psp.setInt(1, equipe.getIdequipe());
                
                if(psp.executeUpdate() > 0){
                    for(Integer partNovo: membros){
                        boolean flag = false;
                        for(Integer partAntigo: idpart_antigos){
                            if(partNovo == partAntigo){
                                flag = true;
                                psa.setInt(1, equipe.getIdequipe());
                                psa.setInt(2, partAntigo);
                                if(!(psa.executeUpdate() > 0)){
                                    getConexao().rollback();
                                    getConexao().setAutoCommit(true);
                                    return false;
                                }
                            }
                        }
                        if(flag == false){
                            String sqlpi = "Insert into PARTICIPANTE_EQUIPE (idstatus, idparticipante, idequipe) values (1,?,?)";
                            PreparedStatement pspi = getConexao().prepareStatement(sqlpi);
                            pspi.setInt(1, partNovo);
                            pspi.setInt(2, equipe.getIdequipe());
                            if(!(pspi.executeUpdate() > 0)){
                                getConexao().rollback();
                                getConexao().setAutoCommit(true);
                                return false;
                            }
                        }
                    }
                    getConexao().commit();
                    getConexao().setAutoCommit(true);
                    return true;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        getConexao().rollback();
        getConexao().setAutoCommit(true);
        return false;
    }
    
    public List<Usuarios> pesquisarParticipante(String nome, int idEvento) throws Exception{
        List<Usuarios> usuarios = new ArrayList<>();
        try{
            String sql = "Select u.* from participantes p inner join inscricao_part_evento i on (p.idparticipante = i.idparticipante) "
                    + "inner join usuarios u on(p.idusuario = u.idusuario) where i.idevento = ? and i.idstatus = 1 and u.idstatus = 1 and u.nome like '%" + nome + "%' ORDER BY u.nome";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEvento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuarios usuario = new Usuarios();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuarios.add(usuario);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }
    
    public List<String> listarPartEvento(int idEvento, int idp) throws Exception{
        List<String> participantes = new ArrayList<>();
        try{
            String sql = "Select u.nome, p.idparticipante from participantes p inner join inscricao_part_evento i on (p.idparticipante = i.idparticipante) "
                    + "inner join usuarios u on(p.idusuario = u.idusuario) where i.idevento = ? and i.idstatus = 1 and u.idstatus = 1  and i.idparticipante != ? order by u.nome";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEvento);
            ps.setInt(2, idp);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String part = rs.getString("nome") + ";" + rs.getInt("idparticipante");
                participantes.add(part);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return participantes;
    }
    
    public List<String> listarMembrosEquipe(int idEquipe) throws Exception{
        List<String> membros = new ArrayList<>();
        try{
            String sql = "Select u.nome, p.idparticipante from participantes p inner join participante_equipe i on (p.idparticipante = i.idparticipante)" +
                    "inner join usuarios u on(p.idusuario = u.idusuario) where i.idequipe = ? and i.idstatus = 1 and u.idstatus = 1 order by u.nome";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEquipe);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String part = rs.getString("nome") + ";" + rs.getInt("idparticipante");
                membros.add(part);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return membros;
    }
    
    public boolean verificarEquipeInscAlgumSubeventos(int idEquipe) throws Exception{
        try{
            String sql = "select idsubevento from inscricao_equipe_sub where idequipe = ? and idstatus = 1";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEquipe);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Subeventos> listarSubeventosEquipeInsc(int idEquipe) throws Exception{
        List<Subeventos> subeventos = new ArrayList<>();
        try{
            String sql = "SELECT s.* FROM SUBEVENTOS s inner join inscricao_equipe_sub i on (s.idsubevento = i.idsubevento) inner join equipes e on (i.idequipe = e.idequipe) where i.idequipe = ? and i.idstatus = 1";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEquipe);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Subeventos subevento = new Subeventos();
                subevento.setIdsubevento(rs.getInt("idSubevento"));
                subevento.setNome(rs.getString("nome"));
                subevento.setDescricao(rs.getString("descricao"));
                subevento.setDatahorainicio(rs.getString("datahorainicio"));
                subevento.setDatahorafim(rs.getString("datahorafim"));
                subevento.setDatainicioinsc(FormatacaoDatas.formataDataBr(rs.getDate("datainicioinsc")));
                subevento.setDatafiminsc(FormatacaoDatas.formataDataBr(rs.getDate("datafiminsc")));
                subevento.setQtdemin(rs.getInt("qtdemin"));
                subevento.setQtdemax(rs.getInt("qtdemax"));
                subevento.setQtdemaxequipes(rs.getInt("qtdemaxequipes"));

                Status status = new Status();
                status.setIdstatus(rs.getInt("idStatus"));
                String sqlStatus = "Select descricao FROM STATUS WHERE idstatus = ?";
                PreparedStatement psSattus = getConexao().prepareStatement(sqlStatus);
                psSattus.setInt(1, status.getIdstatus());
                ResultSet rsStatus = psSattus.executeQuery();
                if(rsStatus.next()){
                    status.setDescricao(rsStatus.getString("descricao"));
                }
                subevento.setStatus(status);

                Salas sala = new Salas();

                sala.setIdsala(rs.getInt("idsala"));
                String sqlSala = "SELECT * FROM SALAS WHERE IDSTATUS = 1 AND IDSALA = ?";
                PreparedStatement psSala = getConexao().prepareStatement(sqlSala);
                psSala.setInt(1, sala.getIdsala());
                ResultSet rsSala = psSala.executeQuery();
                if(rsSala.next()){
                    sala.setDescricao(rsSala.getString("descricao"));
                    sala.setCapacidadetotal(rsSala.getInt("capacidadetotal"));
                    sala.setCapacidadeocupada(rsSala.getInt("capacidadeocupada"));

                    Status statusSala = new Status();
                    statusSala.setIdstatus(rsSala.getInt("idStatus"));
                    String sqlStatusSala = "Select descricao FROM STATUS WHERE idstatus = 1";
                    PreparedStatement psStatusSala = getConexao().prepareStatement(sqlStatusSala);
                    ResultSet rsStatusSala = psStatusSala.executeQuery();
                    if(rsStatusSala.next()){
                        statusSala.setDescricao(rsStatus.getString("descricao"));
                    }
                    sala.setStatus(statusSala);
                    subevento.setSalas(sala);
                }
                subeventos.add(subevento);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
       return subeventos;
    }
    
    public List<Equipes> pegarEquipesPartInsc(int idPart) throws Exception{
        List<Equipes> equipes = new ArrayList<>();
        try{
            String sql = "Select e.* from equipes e inner join PARTICIPANTE_EQUIPE pe on (e.idequipe = pe.idequipe) where e.idstatus = 1 and pe.idparticipante = ? and pe.idstatus = 1";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idPart);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Equipes equipe = new Equipes();
                equipe.setIdequipe(rs.getInt("idequipe"));
                equipe.setNome(rs.getString("nome"));
                equipe.setDescricao(rs.getString("descricao"));
                equipe.setIdlider(rs.getInt("idlider"));
                
                Status status = new Status();
                status.setIdstatus(rs.getInt("idStatus"));
                String sqlStatus = "Select descricao FROM STATUS WHERE idstatus = ?";
                PreparedStatement psSattus = getConexao().prepareStatement(sqlStatus);
                psSattus.setInt(1, status.getIdstatus());
                ResultSet rsStatus = psSattus.executeQuery();
                if(rsStatus.next()){
                    status.setDescricao(rsStatus.getString("descricao"));
                }
                equipe.setStatus(status);
                equipes.add(equipe);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return equipes;
    }
    
    public List<Subeventos> listarSubeventosParaEquipes(int idEvento) throws Exception{
        List<Subeventos> subeventos = new ArrayList<>();
        try{
            String sql = "SELECT s.* FROM SUBEVENTOS s inner join EVENTOS e on (s.idevento = e.idevento) where e.idstatus = 1 and s.idstatus = 1 and s.qtdemin > 1 and e.idevento = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEvento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Subeventos subevento = new Subeventos();
                subevento.setIdsubevento(rs.getInt("idSubevento"));
                subevento.setNome(rs.getString("nome"));
                subevento.setDescricao(rs.getString("descricao"));
                subevento.setDatahorainicio(rs.getString("datahorainicio"));
                subevento.setDatahorafim(rs.getString("datahorafim"));
                subevento.setDatainicioinsc(FormatacaoDatas.formataDataBr(rs.getDate("datainicioinsc")));
                subevento.setDatafiminsc(FormatacaoDatas.formataDataBr(rs.getDate("datafiminsc")));
                subevento.setQtdemin(rs.getInt("qtdemin"));
                subevento.setQtdemax(rs.getInt("qtdemax"));
                subevento.setQtdemaxequipes(rs.getInt("qtdemaxequipes"));

                Status status = new Status();
                status.setIdstatus(rs.getInt("idStatus"));
                String sqlStatus = "Select descricao FROM STATUS WHERE idstatus = ?";
                PreparedStatement psSattus = getConexao().prepareStatement(sqlStatus);
                psSattus.setInt(1, status.getIdstatus());
                ResultSet rsStatus = psSattus.executeQuery();
                if(rsStatus.next()){
                    status.setDescricao(rsStatus.getString("descricao"));
                }
                subevento.setStatus(status);

                Salas sala = new Salas();

                sala.setIdsala(rs.getInt("idsala"));
                String sqlSala = "SELECT * FROM SALAS WHERE IDSTATUS = 1 AND IDSALA = ?";
                PreparedStatement psSala = getConexao().prepareStatement(sqlSala);
                psSala.setInt(1, sala.getIdsala());
                ResultSet rsSala = psSala.executeQuery();
                if(rsSala.next()){
                    sala.setDescricao(rsSala.getString("descricao"));
                    sala.setCapacidadetotal(rsSala.getInt("capacidadetotal"));
                    sala.setCapacidadeocupada(rsSala.getInt("capacidadeocupada"));

                    Status statusSala = new Status();
                    statusSala.setIdstatus(rsSala.getInt("idStatus"));
                    String sqlStatusSala = "Select descricao FROM STATUS WHERE idstatus = 1";
                    PreparedStatement psStatusSala = getConexao().prepareStatement(sqlStatusSala);
                    ResultSet rsStatusSala = psStatusSala.executeQuery();
                    if(rsStatusSala.next()){
                        statusSala.setDescricao(rsStatus.getString("descricao"));
                    }
                    sala.setStatus(statusSala);
                    subevento.setSalas(sala);
                }
                subeventos.add(subevento);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
       return subeventos;
    }
    
    public boolean verificarSeLiderEquipe(int idPart, int idEquipe) throws Exception{
        try{
            String sql = "Select idlider from equipes where idequipe = ? and idstatus = 1";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEquipe);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getInt("idlider") == idPart){
                    return true;
                }
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
}
