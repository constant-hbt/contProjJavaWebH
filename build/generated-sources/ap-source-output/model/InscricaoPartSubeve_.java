package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Participantes;
import model.Status;
import model.Subeventos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(InscricaoPartSubeve.class)
public class InscricaoPartSubeve_ { 

    public static volatile SingularAttribute<InscricaoPartSubeve, Subeventos> subeventos;
    public static volatile SingularAttribute<InscricaoPartSubeve, Date> datahora;
    public static volatile SingularAttribute<InscricaoPartSubeve, Integer> idinscricao;
    public static volatile SingularAttribute<InscricaoPartSubeve, Participantes> participantes;
    public static volatile SingularAttribute<InscricaoPartSubeve, Status> status;

}