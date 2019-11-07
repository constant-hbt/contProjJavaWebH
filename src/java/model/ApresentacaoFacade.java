/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author henrique
 */
@Stateless
public class ApresentacaoFacade extends AbstractFacade<Apresentacao> {

    @PersistenceContext(unitName = "Excluir2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApresentacaoFacade() {
        super(Apresentacao.class);
    }
    
}
