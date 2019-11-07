package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Subeventos;
import model.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Apresentacao.class)
public class Apresentacao_ { 

    public static volatile SingularAttribute<Apresentacao, String> segavaliador;
    public static volatile SingularAttribute<Apresentacao, Subeventos> subeventos;
    public static volatile SingularAttribute<Apresentacao, Usuarios> usuarios;
    public static volatile SingularAttribute<Apresentacao, String> priavaliador;
    public static volatile SingularAttribute<Apresentacao, String> descricaoapresentacao;
    public static volatile SingularAttribute<Apresentacao, Integer> idapresentacao;

}