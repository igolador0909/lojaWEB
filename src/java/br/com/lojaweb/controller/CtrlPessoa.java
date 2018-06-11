package br.com.lojaweb.controller;

import br.com.lojaweb.dao.EnderecoDAO;
import br.com.lojaweb.dao.PessoaDAO;
import br.com.lojaweb.model.Pessoa;
import java.util.List;

public class CtrlPessoa {

    public void cadastrar(Pessoa cliente) throws Exception {
	PessoaDAO dao = new PessoaDAO();
	EnderecoDAO daoEnd = new EnderecoDAO();
	daoEnd.edit(cliente.getEndereco());
	dao.create(cliente);
    }

    public void alterar(Pessoa cliente) throws Exception {
	PessoaDAO dao = new PessoaDAO();
	EnderecoDAO daoEnd = new EnderecoDAO();
	daoEnd.create(cliente.getEndereco());
	dao.edit(cliente);
    }

    public List<Pessoa> buscar(String dados) throws Exception {
	PessoaDAO dao = new PessoaDAO();
	return dao.findPessoas(dados);
    }

    public Pessoa buscarID(Long id) throws Exception {
	PessoaDAO dao = new PessoaDAO();
	return dao.findPessoa(id);
    }

    public Pessoa buscarUser(String email, String pws) throws Exception {
	PessoaDAO dao = new PessoaDAO();
	return dao.findPessoa(email, pws);
    }

    public void remover(Long id) throws Exception {
	PessoaDAO dao = new PessoaDAO();
	//dao.destroy(id);
	Pessoa pessoa = dao.findPessoa(id);
	if (pessoa.isAtivo()) {
	    pessoa.setAtivo(false);
	    dao.edit(pessoa);
	}
    }

}
