package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Participantes;
import model.PresencasSubPK;
import model.Subeventos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(PresencasSub.class)
public class PresencasSub_ { 

    public static volatile SingularAttribute<PresencasSub, PresencasSubPK> presencasSubPK;
    public static volatile SingularAttribute<PresencasSub, Subeventos> subeventos;
    public static volatile SingularAttribute<PresencasSub, Date> datahora;
    public static volatile SingularAttribute<PresencasSub, Participantes> participantes;

}