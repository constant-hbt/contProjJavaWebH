package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Subeventos;
import model.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Submissao.class)
public class Submissao_ { 

    public static volatile SingularAttribute<Submissao, String> segavaliador;
    public static volatile SingularAttribute<Submissao, Subeventos> subeventos;
    public static volatile SingularAttribute<Submissao, Integer> idsubmissao;
    public static volatile SingularAttribute<Submissao, String> descricaosubmissao;
    public static volatile SingularAttribute<Submissao, Usuarios> usuarios;
    public static volatile SingularAttribute<Submissao, String> priavaliador;

}