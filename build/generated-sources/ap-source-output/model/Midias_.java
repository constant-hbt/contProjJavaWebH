package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Categoriamidia;
import model.Eventos;
import model.Status;
import model.Subeventos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Midias.class)
public class Midias_ { 

    public static volatile SingularAttribute<Midias, Integer> idmidia;
    public static volatile SingularAttribute<Midias, String> legenda;
    public static volatile SingularAttribute<Midias, String> pathmidia;
    public static volatile SingularAttribute<Midias, Categoriamidia> categoriamidia;
    public static volatile SingularAttribute<Midias, Subeventos> subeventos;
    public static volatile SingularAttribute<Midias, Eventos> eventos;
    public static volatile SingularAttribute<Midias, Status> status;

}