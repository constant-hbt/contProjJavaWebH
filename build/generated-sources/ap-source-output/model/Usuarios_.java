package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Apresentacao;
import model.Editais;
import model.Organizadores;
import model.Participantes;
import model.Status;
import model.Submissao;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-07T17:24:11")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, String> telefone;
    public static volatile SingularAttribute<Usuarios, String> endereco;
    public static volatile SingularAttribute<Usuarios, String> nome;
    public static volatile SingularAttribute<Usuarios, Date> datanascimento;
    public static volatile SingularAttribute<Usuarios, Integer> idusuario;
    public static volatile SingularAttribute<Usuarios, String> senha;
    public static volatile SingularAttribute<Usuarios, String> rg;
    public static volatile CollectionAttribute<Usuarios, Apresentacao> apresentacaoCollection;
    public static volatile CollectionAttribute<Usuarios, Organizadores> organizadoresCollection;
    public static volatile SingularAttribute<Usuarios, String> cpf;
    public static volatile CollectionAttribute<Usuarios, Editais> editaisCollection;
    public static volatile CollectionAttribute<Usuarios, Participantes> participantesCollection;
    public static volatile CollectionAttribute<Usuarios, Submissao> submissaoCollection;
    public static volatile SingularAttribute<Usuarios, String> email;
    public static volatile SingularAttribute<Usuarios, Status> status;

}