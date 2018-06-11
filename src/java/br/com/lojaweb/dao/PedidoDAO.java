package br.com.lojaweb.dao;

import br.com.lojaweb.model.Pedido;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

public class PedidoDAO extends ConectaJPA {

    //Salvar pedido
    public void create(Pedido pedido) throws Exception {
	et = em.getTransaction();
	try {
	    et.begin();
	    em.persist(pedido);
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

    //Alterar Pedido
    public void edit(Pedido pedido) throws Exception {
	et = em.getTransaction();
	try {
	    et.begin();
	    pedido = em.merge(pedido);
	    et.commit();
	} catch (Exception ex) {
	    try {
		et.rollback();
	    } catch (Exception re) {

	    }
	    String msg = ex.getLocalizedMessage();
	    if (msg == null || msg.length() == 0) {
		Long id = pedido.getId();
		if (findPedido(id) == null) {

		}
	    }
	    throw ex;
	} finally {
	    if (em != null) {
		em.close();
	    }
	}
    }

    //Apagar pedido
    public void destroy(Long id) throws Exception {
	et = em.getTransaction();
	try {
	    et.begin();
	    Pedido pedido = null;
	    try {
		pedido = em.getReference(Pedido.class, id);
		pedido.getId();
	    } catch (EntityNotFoundException enfe) {

	    }
	    em.remove(pedido);
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
    public Pedido findPedido(Long id) {
	try {
	    return em.find(Pedido.class, id);
	} finally {
	    em.close();
	}
    }

    //Buscar todos por nomes
    public List<Pedido> findPedidos(String dados) {
	try {
	    Query query = em.createQuery("select p from Pedido as p");
	    //query.setParameter("dados", dados + "%");
	    List<Pedido> pedidos = query.getResultList();
	    return pedidos;
	} finally {
	    em.close();
	}
    }

}
