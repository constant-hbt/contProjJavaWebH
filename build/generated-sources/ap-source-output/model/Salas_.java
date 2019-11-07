package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Status;
import model.Subeventos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Salas.class)
public class Salas_ { 

    public static volatile SingularAttribute<Salas, Integer> capacidadetotal;
    public static volatile SingularAttribute<Salas, Integer> capacidadeocupada;
    public static volatile SingularAttribute<Salas, Integer> idsala;
    public static volatile CollectionAttribute<Salas, Subeventos> subeventosCollection;
    public static volatile SingularAttribute<Salas, String> descricao;
    public static volatile SingularAttribute<Salas, Status> status;

}