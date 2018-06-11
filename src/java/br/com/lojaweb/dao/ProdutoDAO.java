package br.com.lojaweb.dao;

import br.com.lojaweb.model.Produto;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

public class ProdutoDAO extends ConectaJPA {

    //Salvar dados
    public void create(Produto dados) throws Exception {
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

    //Alterar Produto
    public void edit(Produto dados) throws Exception {
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
		if (findProduto(id) == null) {

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
	    Produto dados = null;
	    try {
		dados = em.getReference(Produto.class, id);
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
    public Produto findProduto(Long id) {
	try {
	    return em.find(Produto.class, id);
	} finally {
	    em.close();
	}
    }

    //Buscar todos por nomes
    public List<Produto> findProdutos(String dados) {
	try {
	    Query query = em.createQuery("select p from Produto as p where p.nome like :dados and p.ativo = 1");
	    query.setParameter("dados", dados + "%");
	    List<Produto> produtos = query.getResultList();
	    return produtos;
	} finally {
	    em.close();
	}
    }

}
