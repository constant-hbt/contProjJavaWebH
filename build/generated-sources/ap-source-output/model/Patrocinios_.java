package model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Eventos;
import model.Patrocinioimagens;
import model.Status;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Patrocinios.class)
public class Patrocinios_ { 

    public static volatile SingularAttribute<Patrocinios, String> site;
    public static volatile SingularAttribute<Patrocinios, String> segmento;
    public static volatile SingularAttribute<Patrocinios, String> endereco;
    public static volatile SingularAttribute<Patrocinios, Integer> idpatrocinio;
    public static volatile SingularAttribute<Patrocinios, BigDecimal> valor;
    public static volatile CollectionAttribute<Patrocinios, Patrocinioimagens> patrocinioimagensCollection;
    public static volatile SingularAttribute<Patrocinios, String> documento;
    public static volatile SingularAttribute<Patrocinios, String> nome;
    public static volatile SingularAttribute<Patrocinios, Eventos> eventos;
    public static volatile SingularAttribute<Patrocinios, String> descricao;
    public static volatile SingularAttribute<Patrocinios, Status> status;

}