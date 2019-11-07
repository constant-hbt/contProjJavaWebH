package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Certificados;
import model.Crachas;
import model.Editais;
import model.Equipes;
import model.Eventos;
import model.InscricaoEquipeSub;
import model.InscricaoPartEvento;
import model.InscricaoPartSubeve;
import model.Midias;
import model.ParticipanteEquipe;
import model.Patrocinioimagens;
import model.Patrocinios;
import model.Salas;
import model.Subeventos;
import model.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Status.class)
public class Status_ { 

    public static volatile CollectionAttribute<Status, InscricaoEquipeSub> inscricaoEquipeSubCollection;
    public static volatile CollectionAttribute<Status, Patrocinioimagens> patrocinioimagensCollection;
    public static volatile CollectionAttribute<Status, Certificados> certificadosCollection;
    public static volatile CollectionAttribute<Status, InscricaoPartEvento> inscricaoPartEventoCollection;
    public static volatile CollectionAttribute<Status, Subeventos> subeventosCollection;
    public static volatile SingularAttribute<Status, String> descricao;
    public static volatile CollectionAttribute<Status, Patrocinios> patrociniosCollection;
    public static volatile CollectionAttribute<Status, Equipes> equipesCollection;
    public static volatile CollectionAttribute<Status, InscricaoPartSubeve> inscricaoPartSubeveCollection;
    public static volatile SingularAttribute<Status, Integer> idstatus;
    public static volatile CollectionAttribute<Status, Usuarios> usuariosCollection;
    public static volatile CollectionAttribute<Status, ParticipanteEquipe> participanteEquipeCollection;
    public static volatile CollectionAttribute<Status, Salas> salasCollection;
    public static volatile CollectionAttribute<Status, Eventos> eventosCollection;
    public static volatile CollectionAttribute<Status, Crachas> crachasCollection;
    public static volatile CollectionAttribute<Status, Midias> midiasCollection;
    public static volatile CollectionAttribute<Status, Editais> editaisCollection;

}