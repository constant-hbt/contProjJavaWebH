package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Midias;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Categoriamidia.class)
public class Categoriamidia_ { 

    public static volatile SingularAttribute<Categoriamidia, Integer> idcategoriamidia;
    public static volatile CollectionAttribute<Categoriamidia, Midias> midiasCollection;
    public static volatile SingularAttribute<Categoriamidia, String> descricao;

}