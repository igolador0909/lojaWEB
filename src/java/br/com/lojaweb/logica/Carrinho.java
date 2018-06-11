package br.com.lojaweb.logica;

import br.com.lojaweb.controller.CtrlItem;
import br.com.lojaweb.controller.CtrlPedido;
import br.com.lojaweb.controller.CtrlProduto;
import br.com.lojaweb.model.Item;
import br.com.lojaweb.model.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Carrinho implements Logica {

    public String executa(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("UTF-8");
	String pagina = "index.jsp";
	String action = request.getParameter("action").trim();
	CtrlProduto ctrlProduto = new CtrlProduto();
	CtrlPedido ctrlPedido = new CtrlPedido();
	CtrlItem ctrlItem = new CtrlItem();

	HttpSession carrinho = request.getSession(false);

	if (action.equals("add")) {
	    Produto produto = ctrlProduto.buscarID(Long.parseLong(request.getParameter("id")));
	    Item item = new Item();
	    item.setProduto(produto);
	    List<Item> itens = (List<Item>) carrinho.getAttribute("itens");
	    if (itens == null){
		itens = new ArrayList<>();
	    }
	    itens.add(item);
	    carrinho.setAttribute("itens", itens);
	    pagina = "?p=carrinho.jsp";
	}

	return pagina;
    }
}
