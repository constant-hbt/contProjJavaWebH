package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Eventos;
import model.Participantes;
import model.Status;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Crachas.class)
public class Crachas_ { 

    public static volatile SingularAttribute<Crachas, Eventos> eventos;
    public static volatile SingularAttribute<Crachas, Integer> idcracha;
    public static volatile SingularAttribute<Crachas, String> url;
    public static volatile SingularAttribute<Crachas, Participantes> participantes;
    public static volatile SingularAttribute<Crachas, Status> status;

}