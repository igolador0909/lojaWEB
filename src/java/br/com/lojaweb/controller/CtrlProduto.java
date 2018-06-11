package br.com.lojaweb.controller;

import br.com.lojaweb.dao.ProdutoDAO;
import br.com.lojaweb.model.Produto;
import java.util.List;

public class CtrlProduto {

    public void cadastrar(Produto produto) throws Exception {
	ProdutoDAO dao = new ProdutoDAO();
	dao.create(produto);
    }

    public void alterar(Produto produto) throws Exception {
	ProdutoDAO dao = new ProdutoDAO();
	dao.edit(produto);
    }

    public List<Produto> buscar(String dados) throws Exception {
	ProdutoDAO dao = new ProdutoDAO();
	return dao.findProdutos(dados);
    }

    public Produto buscarID(Long id) throws Exception {
	ProdutoDAO dao = new ProdutoDAO();
	return dao.findProduto(id);
    }

    public void remover(Long id) throws Exception {
	ProdutoDAO dao = new ProdutoDAO();
	//dao.destroy(id);
	Produto produto = dao.findProduto(id);
	if (produto.isAtivo()) {
	    produto.setAtivo(false);
	    dao.edit(produto);
	}
    }

}
