package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Eventos;
import model.Status;
import model.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Editais.class)
public class Editais_ { 

    public static volatile SingularAttribute<Editais, Date> dataencerramento;
    public static volatile SingularAttribute<Editais, String> urlarquivo;
    public static volatile SingularAttribute<Editais, Date> dataabertura;
    public static volatile SingularAttribute<Editais, Integer> idedital;
    public static volatile SingularAttribute<Editais, Eventos> eventos;
    public static volatile SingularAttribute<Editais, Usuarios> usuarios;
    public static volatile SingularAttribute<Editais, String> descricao;
    public static volatile SingularAttribute<Editais, Status> status;

}