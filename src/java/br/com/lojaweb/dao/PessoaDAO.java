package br.com.lojaweb.dao;

import br.com.lojaweb.model.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PessoaDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lojaWEBPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = null;

    //Salvar dados
    public void create(Pessoa dados) throws Exception {
	et = em.getTransaction();
	try {
	    et.begin();
	    em.persist(dados);
	    et.commit();
	} catch (Exception ex) {
	    try {
		et.rollback();
	    } catch (Exception re) {
		re.toString();
	    }
	    throw ex;
	} finally {
	    if (em != null) {
		em.close();
	    }
	}
    }

    //Alterar Pessoa
    public void edit(Pessoa dados) throws Exception {
	et = em.getTransaction();
	try {
	    et.begin();
	    dados = em.merge(dados);
	    et.commit();
	} catch (Exception ex) {
	    try {
		et.rollback();
	    } catch (Exception re) {

	    }
	    String msg = ex.getLocalizedMessage();
	    if (msg == null || msg.length() == 0) {
		Long id = dados.getId();
		if (findPessoa(id) == null) {

		}
	    }
	    throw ex;
	} finally {
	    if (em != null) {
		em.close();
	    }
	}
    }

    //Apagar dados
    public void destroy(Long id) throws Exception {
	et = em.getTransaction();
	try {
	    et.begin();
	    Pessoa dados = null;
	    try {
		dados = em.getReference(Pessoa.class, id);
		dados.getId();
	    } catch (EntityNotFoundException enfe) {

	    }
	    em.remove(dados);
	    et.commit();
	} catch (Exception ex) {
	    try {
		et.rollback();
	    } catch (Exception re) {

	    }
	    throw ex;
	} finally {
	    if (em != null) {
		em.close();
	    }
	}
    }

    //Busca cliete pelo id
    public Pessoa findPessoa(Long id) {
	try {
	    return em.find(Pessoa.class, id);
	} finally {
	    em.close();
	}
    }

    //Buscar todos por nomes
    public List<Pessoa> findPessoas(String dado) {
	try {
	    Query query = em.createQuery("select p from Pessoa as p where p.nome like :dados and p.ativo = 1");
	    query.setParameter("dados", dado + "%");
	    List<Pessoa> dadoss = query.getResultList();
	    return dadoss;
	} finally {
	    em.close();
	}
    }

    //Busca dados pela email e senha
    public Pessoa findPessoa(String email, String pws) {
	try {
	    Query query = em.createQuery(""
		    + "select p from Pessoa as p "
		    + "where p.email = :email and p.pws = :pws");
	    query.setParameter("email", email);
	    query.setParameter("pws", pws);
	    return (Pessoa) query.getSingleResult();
	} finally {
	    em.close();
	}
    }

}
