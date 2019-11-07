package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Eventos;
import model.Participantes;
import model.Status;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Certificados.class)
public class Certificados_ { 

    public static volatile SingularAttribute<Certificados, Integer> idcertificado;
    public static volatile SingularAttribute<Certificados, Eventos> eventos;
    public static volatile SingularAttribute<Certificados, Date> datahora;
    public static volatile SingularAttribute<Certificados, String> url;
    public static volatile SingularAttribute<Certificados, Participantes> participantes;
    public static volatile SingularAttribute<Certificados, Status> status;

}