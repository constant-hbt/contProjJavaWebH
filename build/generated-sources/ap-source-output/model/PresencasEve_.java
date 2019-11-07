package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Eventos;
import model.Participantes;
import model.PresencasEvePK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(PresencasEve.class)
public class PresencasEve_ { 

    public static volatile SingularAttribute<PresencasEve, PresencasEvePK> presencasEvePK;
    public static volatile SingularAttribute<PresencasEve, Eventos> eventos;
    public static volatile SingularAttribute<PresencasEve, Date> datahora;
    public static volatile SingularAttribute<PresencasEve, Participantes> participantes;

}