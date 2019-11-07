package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Certificados;
import model.Crachas;
import model.Eventos;
import model.InscricaoPartEvento;
import model.InscricaoPartSubeve;
import model.ParticipanteEquipe;
import model.PresencasEve;
import model.PresencasSub;
import model.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Participantes.class)
public class Participantes_ { 

    public static volatile CollectionAttribute<Participantes, InscricaoPartSubeve> inscricaoPartSubeveCollection;
    public static volatile CollectionAttribute<Participantes, PresencasEve> presencasEveCollection;
    public static volatile CollectionAttribute<Participantes, ParticipanteEquipe> participanteEquipeCollection;
    public static volatile CollectionAttribute<Participantes, PresencasSub> presencasSubCollection;
    public static volatile CollectionAttribute<Participantes, Certificados> certificadosCollection;
    public static volatile SingularAttribute<Participantes, Eventos> eventos;
    public static volatile SingularAttribute<Participantes, Integer> idparticipante;
    public static volatile CollectionAttribute<Participantes, InscricaoPartEvento> inscricaoPartEventoCollection;
    public static volatile CollectionAttribute<Participantes, Crachas> crachasCollection;
    public static volatile SingularAttribute<Participantes, Usuarios> usuarios;

}