<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2> Meu carrinho </h2> 
<form action="sys" method="post" id="carrinho">
    <input type="hidden" name="logica" value="Carrinho">
    <input type="hidden" name="action" value="compra">
    <div class="row">
	<p class=" col-md-6">
	    <a href="index.jsp">
		<button type="button" class="btn btn-primary"> Escolher + Produto</button>
	    </a>
	</p>
	<p class=" col-md-6 text-right">
	    <button class="btn btn-danger">Realizar Pagamento</button>
	    <input type="hidden" value='<f:formatNumber minFractionDigits="2" currencySymbol="R$">${total}</f:formatNumber>' name="total" class="form-control campo" id="total" disabled> 

            </p>
        </div>

	<div class="table-responsive">
	    <table class="table">
		<tr class=''>
		    <th class="">Produto</th>
		    <th class="">Quantidade</th>
		    <th class="">Valor Unit.</th>
		    <th class="">Valor Total</th>
		    <th class=""><i class="fa fa-remove"></i></th>
		</tr>

	    <c:set var="index" value="-1"></c:set>
	    <c:forEach items="${itens}" var="i"> 
		<input type="hidden" value="${i.produto.id}" name="id"> 
		<tr>
		    <td class=""> 
			<div class="col-6">
			    <img src="img/produtos/${i.produto.foto1}" title="" alt="" class="img-responsive produtopq">
			</div>
			<div class="col-6">${i.produto.nome}</div>
		    </td>

		    <td class="">
			<input type="number" name="quant" value="${i.quant}" min="1" max="${i.produto.quant}" step="1" class="form-control" onchange="execute(this.form)">
		    </td>

		    <td class=""> 
			<input type="text" name="valor" min="0.00" step="0.01" value='<f:formatNumber minFractionDigits="2" currencySymbol="R$">${i.produto.valor}</f:formatNumber>' disabled class="form-control campo">
			</td>

			<td class="">
			    <input type="text" name="valorItem" min="0.00" step="0.01" value='<f:formatNumber minFractionDigits="2" currencySymbol="R$">${i.valorItens}</f:formatNumber>' disabled class="form-control campo valores">
			</td>

			<td class=""><a href="sys?logica=Carrinho&action=remove&index=${index=index+1}"><i class="fa fa-remove"></i></a></td>
		</tr>
	    </c:forEach>
	</table>      
    </div>
</form> 

<div class="row">
    <div class="col-md-8">
        <form action="sys?logica=Carrinho&action=frete" method="get" class="form-inline">
            <p> Simule o prazo de entrega e o frete para seu CEP abaixo:</p>
            <div class="form-group">
                <input type="text" name="cep" class="form-control">
                <button class="btn"> OK </button>
            </div>
            <p>
                <a href="sys?logica=Carrinho&action=cep"> N�o sei meu CEP </a>
            </p>
            <div class="well">
                <h4>Aten��o:</h4>
                <p>
                    O prazo come�a a contar a partir da aprova��o do pagamento.<br>
                    Os produtos podem ser entregues separadamente.<br>
                </p>
            </div>
        </form>
    </div>
    <div class="col-md-4 well">
        <strong>
            <p>Produtos: <f:formatNumber minFractionDigits="2" currencySymbol="R$">${total}</f:formatNumber></p>
            <p>Frete(?): <f:formatNumber minFractionDigits="2" currencySymbol="R$">${frete}</f:formatNumber></p>
	    </strong>
	    <h3 style="border-top: solid 2px #222;padding: 10px 0">Total:<f:formatNumber minFractionDigits="2" currencySymbol="R$">${total+frete}</f:formatNumber></h3>

	<p><strong>Possui cupom ou vale? </strong>Voc� poder� us�-los na etapa de pagamento.</p>
    </div>

</div>

<script>
    // window.onload(execute(this.form));
    function execute(frm) {
	frm.action = "sys?logica=Carrinho&action=calcular";
	frm.submit();
    }
</script>
