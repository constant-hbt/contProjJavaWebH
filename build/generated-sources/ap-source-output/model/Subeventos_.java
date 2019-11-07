package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Apresentacao;
import model.Eventos;
import model.InscricaoEquipeSub;
import model.InscricaoPartSubeve;
import model.Midias;
import model.PresencasEquipeSub;
import model.PresencasSub;
import model.Salas;
import model.Status;
import model.Submissao;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Subeventos.class)
public class Subeventos_ { 

    public static volatile SingularAttribute<Subeventos, Date> datafiminsc;
    public static volatile SingularAttribute<Subeventos, Integer> qtdemax;
    public static volatile CollectionAttribute<Subeventos, InscricaoEquipeSub> inscricaoEquipeSubCollection;
    public static volatile SingularAttribute<Subeventos, String> nome;
    public static volatile SingularAttribute<Subeventos, Eventos> eventos;
    public static volatile SingularAttribute<Subeventos, Integer> qtdemin;
    public static volatile SingularAttribute<Subeventos, String> datahorafim;
    public static volatile SingularAttribute<Subeventos, String> descricao;
    public static volatile SingularAttribute<Subeventos, Date> datainicioinsc;
    public static volatile SingularAttribute<Subeventos, Integer> qtdemaxequipes;
    public static volatile SingularAttribute<Subeventos, Salas> salas;
    public static volatile CollectionAttribute<Subeventos, InscricaoPartSubeve> inscricaoPartSubeveCollection;
    public static volatile SingularAttribute<Subeventos, Integer> idsubevento;
    public static volatile CollectionAttribute<Subeventos, Apresentacao> apresentacaoCollection;
    public static volatile CollectionAttribute<Subeventos, PresencasSub> presencasSubCollection;
    public static volatile SingularAttribute<Subeventos, String> datahorainicio;
    public static volatile CollectionAttribute<Subeventos, Midias> midiasCollection;
    public static volatile CollectionAttribute<Subeventos, PresencasEquipeSub> presencasEquipeSubCollection;
    public static volatile CollectionAttribute<Subeventos, Submissao> submissaoCollection;
    public static volatile SingularAttribute<Subeventos, Status> status;

}