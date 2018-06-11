package br.com.lojaweb.logica;

import br.com.lojaweb.controller.CtrlPessoa;
import br.com.lojaweb.model.Pessoa;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogicaPessoa implements Logica {

    public String executa(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("UTF-8");

	String pagina = "index.jsp";
	String action = request.getParameter("action").trim();

	//Login
	//<editor-fold>
	if (action.equals("log")) {
	    String email = request.getParameter("email");
	    String pws = request.getParameter("pws");
	    CtrlPessoa ctrl = new CtrlPessoa();
	    try {
		Pessoa cliente = ctrl.buscarUser(email, pws);
		request.setAttribute("aviso", "Cliente logado");
		HttpSession user = request.getSession();
		//user.setAttribute("cliente", cliente);
		user.setAttribute("id", cliente.getId());
		user.setAttribute("nome", cliente.getNome());

	    } catch (SQLException ex) {
		request.setAttribute("alerta", "Erro ao conectar ao banco");
	    } catch (Exception ex) {
		System.err.println("Erros: " + ex.toString());
		request.setAttribute("erros", "Usuario não encontrado");
	    }
	    pagina = "index.jsp";
	}
	//</editor-fold>

	//Logoff
	//<editor-fold>
	if (action.equals("off")) {
	    HttpSession user = request.getSession();
	    user.removeAttribute("id");
	    user.removeAttribute("nome");
	    //user.invalidate(); // apaga a session
	    pagina = "index.jsp";
	}
	//</editor-fold>

	//Cadastro e Alteração
	//<editor-fold>
	if (action.equals("cad") || action.equals("alt")) {
	    Pessoa cliente = new Pessoa();
	    cliente.setNome(request.getParameter("nome").trim());
	    cliente.setEmail(request.getParameter("email").trim());
	    cliente.setCpf(request.getParameter("cpf").trim());
	    cliente.setNumero(request.getParameter("numero").trim());
	    cliente.setComplemento(request.getParameter("complemento").trim());
	    cliente.getEndereco().setLogradouro(request.getParameter("logradouro").trim());
	    cliente.getEndereco().setBairro(request.getParameter("bairro").trim());
	    cliente.getEndereco().setCidade(request.getParameter("cidade").trim());
	    cliente.getEndereco().setUf(request.getParameter("uf").trim());
	    cliente.getEndereco().setCep(request.getParameter("cep").trim());

	    if (request.getParameter("ativo").equals("0")) {
		cliente.setAtivo(false);
	    }
	    try {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(request.getParameter("dataNasc")));
		System.err.println("Data: " + c.toString());
		cliente.setDataNasc(c);
		CtrlPessoa ctrlCliente = new CtrlPessoa();
		if (action.equals("cad")) {
		    cliente.setPws(request.getParameter("pws1").trim());
		    String pws2 = request.getParameter("pws2").trim();
		    cliente.validar(pws2);
		    ctrlCliente.cadastrar(cliente);
		    request.setAttribute("aviso", "Cadastrado");
		} else {
		    cliente.setId(Long.parseLong(request.getParameter("id")));

		    cliente.validar();
		    ctrlCliente.alterar(cliente);
		    request.setAttribute("aviso", "Atualizado");
		}

	    } catch (SQLException ex) {
		request.setAttribute("alerta", "Erro acessar a base de dados." + ex.toString());
	    } catch (Exception ex) {
		System.err.println("Erros: " + ex.toString());

		request.setAttribute("erros", ex.getMessage().replace("\n", "<br>"));
		request.setAttribute("cliente", cliente);
	    }
	    pagina = "?p=formCliente.jsp";
	}
	//</editor-fold>

	//Buscar
	//<editor-fold>
	if (request.getParameter("action").equals("busca")) {
	    CtrlPessoa ctrlPessoa = new CtrlPessoa();
	    try {
		List<Pessoa> pessoas = ctrlPessoa.buscar(request.getParameter("dados"));
		request.setAttribute("pessoas", pessoas);
	    } catch (Exception ex) {
		request.setAttribute("erros", ex.toString());
	    }
	    pagina = "?p=reportCliente.jsp";
	}

	if (request.getParameter("action").equals("buscaid")) {
	    long id = Long.parseLong(request.getParameter("id"));

	    CtrlPessoa ctrlPessoa = new CtrlPessoa();
	    try {
		Pessoa cliente = ctrlPessoa.buscarID(id);
		request.setAttribute("cliente", cliente);
	    } catch (Exception ex) {
		request.setAttribute("erros", ex.toString());
	    }
	    pagina = "?p=formCliente.jsp";
	}
	//</editor-fold>

	//Remover
	//<editor-fold>
	if (request.getParameter("action").equals("rem")) {
	    CtrlPessoa ctrlPessoa = new CtrlPessoa();
	    try {
		ctrlPessoa.remover(Long.parseLong(request.getParameter("id")));
		request.setAttribute("aviso", "Removido");
	    } catch (Exception ex) {
		request.setAttribute("erros", ex.toString());
	    }
	    pagina = "?p=reportCliente.jsp";
	}
	//</editor-fold>

	return pagina;
    }
}
