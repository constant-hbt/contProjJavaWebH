package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Equipes;
import model.PresencasEquipeSubPK;
import model.Subeventos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(PresencasEquipeSub.class)
public class PresencasEquipeSub_ { 

    public static volatile SingularAttribute<PresencasEquipeSub, Equipes> equipes;
    public static volatile SingularAttribute<PresencasEquipeSub, Subeventos> subeventos;
    public static volatile SingularAttribute<PresencasEquipeSub, PresencasEquipeSubPK> presencasEquipeSubPK;
    public static volatile SingularAttribute<PresencasEquipeSub, Date> datahora;

}