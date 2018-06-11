package br.com.lojaweb.logica;

import br.com.lojaweb.controller.CtrlProduto;
import br.com.lojaweb.model.Produto;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogicaProduto implements Logica {

    public String executa(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("UTF-8");

	String pagina = "index.jsp";
	String action = request.getParameter("action").trim();

	//Cadastro e Alteração
	//<editor-fold>
	if (action.equals("cad") || action.equals("alt")) {
	    Produto produto = new Produto();
	    produto.setNome(request.getParameter("nome").trim());
	    produto.setDescricao(request.getParameter("descricao").trim());
	    if (!request.getParameter("quant").trim().equals("")) {
		produto.setQuant(Integer.parseInt(request.getParameter("quant").trim()));
	    }
	    if (!request.getParameter("valor").trim().equals("")) {
		produto.setValor(Double.parseDouble(request.getParameter("valor").trim()));
	    }
	    //request.getPart -> Pega o vetor do arquivo.
	    produto.setFoto1(request.getPart("foto1").getSubmittedFileName());
	    produto.setFoto2(request.getPart("foto2").getSubmittedFileName());
	    produto.setFoto3(request.getPart("foto3").getSubmittedFileName());

	    if (action.equals("0")) {
		produto.setAtivo(false);
	    }
	    try {
		CtrlProduto ctrlProduto = new CtrlProduto();
		if (action.equals("cad")) {
		    produto.validar();
		    ctrlProduto.cadastrar(produto);
		    request.setAttribute("aviso", "Cadastrado");
		} else {
		    produto.setId(Long.parseLong(request.getParameter("id")));
		    produto.validar();
		    ctrlProduto.alterar(produto);
		    request.setAttribute("aviso", "Atualizado");
		}

	    } catch (SQLException ex) {
		request.setAttribute("alerta", "Erro acessar a base de dados." + ex.toString());
	    } catch (Exception ex) {
		System.err.println("Erros: " + ex.toString());

		request.setAttribute("erros", ex.getMessage().replace("\n", "<br>"));
		request.setAttribute("produto", produto);
	    }
	    pagina = "?p=formProduto.jsp";
	}
	//</editor-fold>

	//Buscar
	//<editor-fold>
	if (action.equals("busca")) {
	    CtrlProduto ctrlProduto = new CtrlProduto();
	    try {
		List<Produto> produtos = ctrlProduto.buscar(request.getParameter("dados"));
		request.setAttribute("produtos", produtos);
	    } catch (Exception ex) {
		request.setAttribute("erros", ex.toString());
	    }
	    pagina = "?p=reportProduto.jsp";
	}
	//</editor-fold>

	//BuscarID
	//<editor-fold>
	if (action.equals("buscaid") || action.equals("desc")) {
	    long id = Long.parseLong(request.getParameter("id"));

	    CtrlProduto ctrlProduto = new CtrlProduto();
	    try {
		Produto produto = ctrlProduto.buscarID(id);
		request.setAttribute("produto", produto);
	    } catch (Exception ex) {
		request.setAttribute("erros", ex.toString());
	    }
	    if (action.equals("desc")) {
		pagina = "?p=descProduto.jsp";
	    } else {
		pagina = "?p=formProduto.jsp";
	    }
	}
	//</editor-fold>

	//Remover
	//<editor-fold>
	if (action.equals("rem")) {
	    CtrlProduto ctrlProduto = new CtrlProduto();
	    try {
		ctrlProduto.remover(Long.parseLong(request.getParameter("id")));
		request.setAttribute("aviso", "Removido");
	    } catch (Exception ex) {
		request.setAttribute("erros", ex.toString());
	    }
	    pagina = "?p=reportProduto.jsp";
	}
	//</editor-fold>

	return pagina;
    }
}
