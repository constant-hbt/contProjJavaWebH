package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Eventos;
import model.InscricaoEquipeSub;
import model.Salas;
import model.Status;
import model.Subeventos;
import servlets.utils.FormatacaoDatas;


public class Inscricoes extends Conexao{
    public Inscricoes() throws Exception {}
    
    public int pegarIdPart(int idUsuario) throws Exception{
        try{
            String sql = "SELECT idparticipante FROM PARTICIPANTES WHERE idusuario = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("idparticipante");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<Eventos> listarEventos() throws Exception{
        List<Eventos> eventos = new ArrayList<Eventos>();
        try{
            String sql = "SELECT * FROM EVENTOS WHERE IDSTATUS = 1";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Eventos evento = new Eventos();
                evento.setIdevento(rs.getInt("idevento"));
                evento.setNome(rs.getString("nome"));
                evento.setDescricao(rs.getString("descricao"));
                evento.setLocal(rs.getString("local"));
                evento.setDatainicio(FormatacaoDatas.formataDataBr(rs.getDate("dataInicio")));
                evento.setDatafim(FormatacaoDatas.formataDataBr(rs.getDate("dataFim")));
                evento.setDatainicioinsc(FormatacaoDatas.formataDataBr(rs.getDate("datainicioinsc")));
                evento.setDatafiminsc(FormatacaoDatas.formataDataBr(rs.getDate("dataFimInsc")));

                Status status = new Status();
                status.setIdstatus(rs.getInt("idStatus"));
                String sqlStatus = "Select descricao FROM STATUS WHERE idstatus = ?";
                PreparedStatement psSattus = getConexao().prepareStatement(sqlStatus);
                psSattus.setInt(1, status.getIdstatus());
                ResultSet rsStatus = psSattus.executeQuery();
                if(rsStatus.next()){
                    status.setDescricao(rsStatus.getString("descricao"));
                }
                evento.setStatus(status);

                eventos.add(evento);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return eventos;
    }
    
    public Eventos pegarEvento(int idEvento) throws Exception{
        Eventos evento = new Eventos();
        try{
            String sql = "SELECT * FROM EVENTOS WHERE IDSTATUS = 1 AND IDEVENTO = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEvento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                evento.setIdevento(rs.getInt("idevento"));
                evento.setNome(rs.getString("nome"));
                evento.setDescricao(rs.getString("descricao"));
                evento.setLocal(rs.getString("local"));
                evento.setDatainicio(rs.getDate("dataInicio"));
                evento.setDatafim(rs.getDate("dataFim"));
                evento.setDatainicioinsc(rs.getDate("datainicioinsc"));
                evento.setDatafiminsc(rs.getDate("dataFimInsc"));

                Status status = new Status();
                status.setIdstatus(rs.getInt("idStatus"));
                String sqlStatus = "Select descricao FROM STATUS WHERE idstatus = ?";
                PreparedStatement psSattus = getConexao().prepareStatement(sqlStatus);
                psSattus.setInt(1, status.getIdstatus());
                ResultSet rsStatus = psSattus.executeQuery();
                if(rsStatus.next()){
                    status.setDescricao(rsStatus.getString("descricao"));
                }
                evento.setStatus(status);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return evento;
    }
    
    public List<Subeventos> listarSubeventos(int idEvento) throws Exception{
        List<Subeventos> subeventos = new ArrayList<>();
        try{
            String sql = "SELECT * FROM SUBEVENTOS WHERE IDEVENTO = ? AND IDSTATUS = 1";
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
    
    
    
    public boolean inscreverEvento(int idUsuario, int idEvento) throws Exception{
        try{
            getConexao().setAutoCommit(false);
                String sql = "INSERT INTO PARTICIPANTES (IDUSUARIO, IDEVENTO) VALUES (?, ?)";
                PreparedStatement ps = getConexao().prepareStatement(sql);
                ps.setInt(1, idUsuario);
                ps.setInt(2, idEvento);
                if(ps.executeUpdate() > 0){
                    String sqlPart = "SELECT IDPARTICIPANTE FROM PARTICIPANTES WHERE IDUSUARIO = ?";
                    PreparedStatement psPart = getConexao().prepareStatement(sqlPart);
                    psPart.setInt(1, idUsuario);
                    ResultSet rsPart = psPart.executeQuery();
                    if(rsPart.next()){
                        int idParticipante = rsPart.getInt("IDPARTICIPANTE");
                        String sqlInsc = "INSERT INTO INSCRICAO_PART_EVENTO(DATAHORA, IDPARTICIPANTE, IDEVENTO, IDSTATUS) VALUES (CURRENT_TIMESTAMP,?,?,1)";
                        PreparedStatement psInsc = getConexao().prepareStatement(sqlInsc);
                        psInsc.setInt(1, idParticipante);
                        psInsc.setInt(2, idEvento);
                        if(psInsc.executeUpdate() > 0){
                            getConexao().commit();
                            getConexao().setAutoCommit(true);
                            return true;
                        }
                    }
                }
            getConexao().rollback();
            getConexao().setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    
    public boolean verificarFoiInscEvento(int idPart, int idEvento) throws Exception{
        try{
            String sql = "Select idstatus from inscricao_part_evento where idparticipante = ? and idevento = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idPart);
            ps.setInt(2, idEvento);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean atualizarInscEvento(int idPart, int ide_antigo, int idEvento) throws Exception{
        try{
            getConexao().setAutoCommit(false);
            if(verificarFoiInscEvento(idPart, idEvento)){
                String sqlupa = "Update inscricao_part_evento set idstatus = 2 where idevento = ? and idparticipante = ?";
                PreparedStatement psupa = getConexao().prepareStatement(sqlupa);
                psupa.setInt(1, ide_antigo);
                psupa.setInt(2, idPart);
                if(psupa.executeUpdate() > 0){
                    String sqlup = "Update inscricao_part_evento set idstatus = 1 where idevento = ? and idparticipante = ?";
                    PreparedStatement psup = getConexao().prepareStatement(sqlup);
                    psup.setInt(1, idEvento);
                    psup.setInt(2, idPart);
                    if(psup.executeUpdate() > 0){
                        String sqlup2 = "Update participante set idevento = ? where idparticipante = ?";
                        PreparedStatement psup2 = getConexao().prepareStatement(sqlup2);
                        psup2.setInt(1, idEvento);
                        psup2.setInt(2, idPart);
                        if(psup2.executeUpdate() > 0){
                            getConexao().commit();
                            getConexao().setAutoCommit(true);
                            return true;
                        }
                    }
                }
            }else{
                String sql = "UPDATE INSCRICAO_PART_EVENTO SET idstatus = 2 WHERE idevento = ? AND idparticipante = ?";
                PreparedStatement ps = getConexao().prepareStatement(sql);
                ps.setInt(1, ide_antigo);
                ps.setInt(2, idPart);
                if(ps.executeUpdate() > 0){
                    String sqli = "INSERT INTO INSCRICAO_PART_EVENTO (datahora, idparticipante, idevento, idstatus) VALUES (CURRENT_TIMESTAMP, ?, ?, 1)";
                    PreparedStatement ps2 = getConexao().prepareStatement(sqli);
                    ps2.setInt(1, idPart);
                    ps2.setInt(2, idEvento);
                    if(ps2.executeUpdate() > 0){
                        String sqlu = "UPDATE PARTICIPANTES SET idevento = ? WHERE idparticipante = ?";
                        PreparedStatement ps3 = getConexao().prepareStatement(sqlu);
                        ps3.setInt(1, idEvento);
                        ps3.setInt(2, idPart);
                        if(ps3.executeUpdate() > 0){
                            getConexao().commit();
                            getConexao().setAutoCommit(true);
                            return true;
                        }
                    }
                }
            }
            getConexao().rollback();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean verificarHistEvento(int idPart, int idEvento) throws Exception{
        getConexao().setAutoCommit(false);
        try{
            String sql = "select idstatus from inscricao_part_evento where idparticipante = ? and idevento = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idPart);
            ps.setInt(2, idEvento);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true; 
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public int verificarInscTodosEventos(int idp) throws Exception{
        int ide = 0;
        try{
            String sql = "SELECT idevento FROM inscricao_part_evento WHERE idparticipante = ? and idstatus = 1";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idp);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ide = rs.getInt("idevento");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ide;
    }
    
    
    public int pegarIdParticipante(int idUsuario) throws Exception{
        int idp = 0;
        try{
            String sqlidp = "Select idparticipante from participantes where idusuario = ?";
            PreparedStatement psidp = getConexao().prepareStatement(sqlidp);
            psidp.setInt(1, idUsuario);
            ResultSet rsidp = psidp.executeQuery();
            while(rsidp.next()){
                idp = rsidp.getInt("idparticipante");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return idp;
    }
    
    public boolean verificarInscEvento(int idUsuario, int idEvento) throws Exception{
        try{
            int idp = pegarIdPart(idUsuario);
            if(idp != 0){
                String sql = "SELECT idstatus FROM INSCRICAO_PART_EVENTO WHERE idparticipante = ? AND idevento = ?";
                PreparedStatement ps = getConexao().prepareStatement(sql);
                ps.setInt(1, idp);
                ps.setInt(2, idEvento);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    if(rs.getInt("idstatus") == 1){
                        return true;
                    }
                }  
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean desinscPartEvento(int idPart, int idEvento) throws Exception{
        getConexao().setAutoCommit(false);
        String sql = "UPDATE INSCRICAO_PART_EVENTO SET idstatus = 2 where idparticipante = ? and idevento = ?";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setInt(1, idPart);
        ps.setInt(2, idEvento);
        if(ps.executeUpdate() > 0){
            getConexao().commit();
            getConexao().setAutoCommit(true);
            return true;
        }
        getConexao().rollback();
        getConexao().setAutoCommit(true);
        return false;
    }
    
//-----------------------------SUBEVENTO----------------------------------------
    public boolean verificaInscSub(int idUsuario, int idSubevento) throws Exception{
        try{
            int idPart = pegarIdParticipante(idUsuario);
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
    
    
    
    public Subeventos pegarSubevento(int idsubevento, int idEvento) throws Exception{
        Subeventos subevento = new Subeventos();
        try{
            String sql = "select * from subeventos where idsubevento = ? and idevento = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idsubevento);
            ps.setInt(2, idEvento);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
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
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return subevento;
    }
    
    public boolean inscreverSubEvento(int idPart, int idSubevento) throws Exception{
        getConexao().setAutoCommit(false);
        try{
            /*SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dataString  = dateFormat.format(new Date().getTime());*/
            String sql = "Insert into inscricao_part_subeve (datahora, idparticipante, idsubevento, idstatus)"
                    + " values (CURRENT_TIMESTAMP,?,?,1)";
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
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean verificarHistInscSubevento(int idPart, int idSubevento) throws Exception{
        getConexao().setAutoCommit(false);
        try{
            String sql = "select idstatus from inscricao_part_subeve where idparticipante = ? and idsubevento = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idPart);
            ps.setInt(2, idSubevento);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getInt("idstatus") == 2){
                    return true;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean atualizarInscSubevento(int idp, int idSubevento) throws Exception{
        try{
            String sql = "update inscricao_part_subeve set idstatus = 1, datahora = CURRENT_TIMESTAMP where idparticipante = ? and idsubevento = ? and idstatus = 2";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idp);
            ps.setInt(2, idSubevento);
            if(ps.executeUpdate() > 0){
                getConexao().commit();
                getConexao().setAutoCommit(true);
                return true;
            }
            getConexao().rollback();
            getConexao().setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }
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
    
    public boolean verificarInscSubevento(int idp, int idSubevento) throws Exception{
        try{
            String sql = "Select idstatus from inscricao_part_subeve where idparticipante = ? and idsubevento = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idp);
            ps.setInt(2, idSubevento);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getInt("idstatus") == 1){
                    return true;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean desinscreverSubevento(int idp, int idSubevento) throws Exception{
        getConexao().setAutoCommit(false);
        try{
            String sql = "Update inscricao_part_subeve set idstatus = 2 where idparticipante = ? and idsubevento = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idp);
            ps.setInt(2, idSubevento);
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
    
    public int verificarSubPertEvento(int idSubevento, int idp) throws Exception{
        int ide = 0;
        try{
            String sql = "Select s.idsubevento from eventos e inner join subeventos s on (e.idevento = s.idevento) inner join inscricao_part_evento ipe on (e.idevento = ipe.idevento) where s.idsubevento = ? and ipe.idparticipante = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idSubevento);
            ps.setInt(2, idp);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ide = rs.getInt("idsubevento");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ide;
    }
    
    public List<Subeventos> listarSubeventosPart(int idpart) throws Exception{
        List<Subeventos> subeventos = new ArrayList<>();
        try{
            String sql = "SELECT s.* FROM SUBEVENTOS s inner join inscricao_part_subeve i on (s.idsubevento = i.idsubevento) where i.idparticipante = ? and i.IDSTATUS = 1";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idpart);
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
    
//------------------------------Inscrição-Equipe-Subevento----------------------
    
    public List<InscricaoEquipeSub> listarTodasInscEquipeSubAtivas(int idEquipe) throws Exception{
        List<InscricaoEquipeSub> inscricoes = new ArrayList<>();
        try{
            String sql = "Select * from INSCRICAO_EQUIPE_SUB where idequipe = ? and idstatus = 1";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEquipe);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                InscricaoEquipeSub inscricao = new InscricaoEquipeSub();
                inscricao.setIdequipesub(rs.getInt("idequipesub"));
                inscricao.setDatahora(rs.getTimestamp("datahora"));
                inscricao.setIdEquipe(idEquipe);
                inscricao.setIdSubevento(rs.getInt(rs.getInt("idsubevento")));
                
                Status status = new Status();
                status.setIdstatus(1);
                String sqlStatus = "Select descricao FROM STATUS WHERE idstatus = 1";
                PreparedStatement psSattus = getConexao().prepareStatement(sqlStatus);
                ResultSet rsStatus = psSattus.executeQuery();
                if(rsStatus.next()){
                    status.setDescricao(rsStatus.getString("descricao"));
                }
                inscricao.setStatus(status);
                inscricoes.add(inscricao);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return inscricoes;
    }
    
    public boolean inscreverEquipeSub(int idEquipe, int idSubevento) throws Exception{
        getConexao().setAutoCommit(false);
        try{
            /*SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dataString  = dateFormat.format(new Date().getTime());*/
            String sql = "Insert into INSCRICAO_EQUIPE_SUB (datahora, idequipe, idsubevento, idstatus)"
                    + " values (CURRENT_TIMESTAMP,?,?,1)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEquipe);
            ps.setInt(2, idSubevento);
            if(ps.executeUpdate() > 0){
                getConexao().commit();
                getConexao().setAutoCommit(true);
                return true;
            }
            getConexao().rollback();
            getConexao().setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean verificarEquipeInscSub(int idEquipe, int idSubevento) throws Exception{
        try{
            String sql = "Select idstatus from INSCRICAO_EQUIPE_SUB where idequipe = ? and idsubevento = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEquipe);
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
    
    public boolean verificarHistInscEquipeSubevento(int idEquipe, int idSubevento) throws Exception{
        getConexao().setAutoCommit(false);
        try{
            String sql = "select idstatus from INSCRICAO_EQUIPE_SUB where idequipe = ? and idsubevento = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEquipe);
            ps.setInt(2, idSubevento);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getInt("idstatus") == 2){
                    return true;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean atualizarInscEquipeSubevento(int idEquipe, int idSubevento) throws Exception{
        try{
            String sql = "update INSCRICAO_EQUIPE_SUB set idstatus = 1, datahora = CURRENT_TIMESTAMP where idequipe = ? and idsubevento = ? and idstatus = 2";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEquipe);
            ps.setInt(2, idSubevento);
            if(ps.executeUpdate() > 0){
                getConexao().commit();
                getConexao().setAutoCommit(true);
                return true;
            }
            getConexao().rollback();
            getConexao().setAutoCommit(true);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean desinscreverEquipeSubevento(int idEquipe, int idSubevento) throws Exception{
        getConexao().setAutoCommit(false);
        try{
            String sql = "Update INSCRICAO_EQUIPE_SUB set idstatus = 2 where idequipe = ? and idsubevento = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idEquipe);
            ps.setInt(2, idSubevento);
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
    
    public int pegarIdEvento(int idPart) throws Exception{
        int idevento = 0;
        try{
            String sql = "Select idevento from inscricao_part_evento where idparticipante = ? and idstatus = 1;";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idPart);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                idevento = rs.getInt("idevento");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return idevento;
    }
    
    public int pegarQtdeEquipesInscSub(int idSubevento) throws Exception{
        int qtde = 0;
        try{
            String sql = "Select idequipe from INSCRICAO_EQUIPE_SUB where idstatus = 1 and idsubevento = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idSubevento);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                qtde++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return qtde;
    }
      
}

