package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Equipes;
import model.Status;
import model.Subeventos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(InscricaoEquipeSub.class)
public class InscricaoEquipeSub_ { 

    public static volatile SingularAttribute<InscricaoEquipeSub, Integer> idequipesub;
    public static volatile SingularAttribute<InscricaoEquipeSub, Equipes> equipes;
    public static volatile SingularAttribute<InscricaoEquipeSub, Subeventos> subeventos;
    public static volatile SingularAttribute<InscricaoEquipeSub, Date> datahora;
    public static volatile SingularAttribute<InscricaoEquipeSub, Status> status;

}