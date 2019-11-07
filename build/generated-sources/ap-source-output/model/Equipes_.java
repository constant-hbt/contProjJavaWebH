package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.InscricaoEquipeSub;
import model.ParticipanteEquipe;
import model.PresencasEquipeSub;
import model.Status;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Equipes.class)
public class Equipes_ { 

    public static volatile SingularAttribute<Equipes, Integer> idequipe;
    public static volatile CollectionAttribute<Equipes, InscricaoEquipeSub> inscricaoEquipeSubCollection;
    public static volatile CollectionAttribute<Equipes, ParticipanteEquipe> participanteEquipeCollection;
    public static volatile SingularAttribute<Equipes, String> nome;
    public static volatile CollectionAttribute<Equipes, PresencasEquipeSub> presencasEquipeSubCollection;
    public static volatile SingularAttribute<Equipes, String> descricao;
    public static volatile SingularAttribute<Equipes, Long> idlider;
    public static volatile SingularAttribute<Equipes, Status> status;

}