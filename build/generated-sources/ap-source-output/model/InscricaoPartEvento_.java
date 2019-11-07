package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Eventos;
import model.Participantes;
import model.Status;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(InscricaoPartEvento.class)
public class InscricaoPartEvento_ { 

    public static volatile SingularAttribute<InscricaoPartEvento, Eventos> eventos;
    public static volatile SingularAttribute<InscricaoPartEvento, Date> datahora;
    public static volatile SingularAttribute<InscricaoPartEvento, Integer> idinscricao;
    public static volatile SingularAttribute<InscricaoPartEvento, Participantes> participantes;
    public static volatile SingularAttribute<InscricaoPartEvento, Status> status;

}