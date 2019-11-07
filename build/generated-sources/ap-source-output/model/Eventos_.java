package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Certificados;
import model.Crachas;
import model.Editais;
import model.InscricaoPartEvento;
import model.Midias;
import model.Organizadores;
import model.Participantes;
import model.Patrocinios;
import model.PresencasEve;
import model.Status;
import model.Subeventos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Eventos.class)
public class Eventos_ { 

    public static volatile SingularAttribute<Eventos, Date> datainicio;
    public static volatile SingularAttribute<Eventos, Integer> idevento;
    public static volatile SingularAttribute<Eventos, Date> datafiminsc;
    public static volatile SingularAttribute<Eventos, Date> datafim;
    public static volatile CollectionAttribute<Eventos, PresencasEve> presencasEveCollection;
    public static volatile SingularAttribute<Eventos, String> nome;
    public static volatile CollectionAttribute<Eventos, Certificados> certificadosCollection;
    public static volatile CollectionAttribute<Eventos, InscricaoPartEvento> inscricaoPartEventoCollection;
    public static volatile SingularAttribute<Eventos, String> local;
    public static volatile CollectionAttribute<Eventos, Subeventos> subeventosCollection;
    public static volatile SingularAttribute<Eventos, String> descricao;
    public static volatile CollectionAttribute<Eventos, Patrocinios> patrociniosCollection;
    public static volatile SingularAttribute<Eventos, Date> datainicioinsc;
    public static volatile CollectionAttribute<Eventos, Organizadores> organizadoresCollection;
    public static volatile CollectionAttribute<Eventos, Crachas> crachasCollection;
    public static volatile CollectionAttribute<Eventos, Midias> midiasCollection;
    public static volatile CollectionAttribute<Eventos, Editais> editaisCollection;
    public static volatile CollectionAttribute<Eventos, Participantes> participantesCollection;
    public static volatile SingularAttribute<Eventos, Status> status;

}