package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Eventos;
import model.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Organizadores.class)
public class Organizadores_ { 

    public static volatile SingularAttribute<Organizadores, String> funcao;
    public static volatile SingularAttribute<Organizadores, Integer> idorganizador;
    public static volatile SingularAttribute<Organizadores, Eventos> eventos;
    public static volatile SingularAttribute<Organizadores, Usuarios> usuarios;

}