<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form method="get" action="sys" class="form-inline">
    <input type="hidden" name="logica" value="LogicaProduto">
    <input type="hidden" name="action" value="busca">
    <div class="form-group m-2">
        <input type="text" name="dados" class="form-control" placeholder="Digite um nome">
    </div>
    <div class="form-group m-2">
        <button class="btn">Buscar</button>
    </div>
</form>

<div class="table-responsive">
    <table class="table">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Foto 1</th>
            <th>Foto 2</th>
            <th>Foto 3</th>
            <th>Quant.</th>
            <th>Valor</th>
            <th><i class="fa fa-edit"></i></th>
            <th><i class="fa fa-remove"></i></th>
        </tr>

        <c:forEach var="p" items="${produtos}">
            <tr>
                <td>${p.id}</td>
                <td>${p.nome}</td>
                <td>${p.descricao}</td>
                <td> 
		    <c:if test="${not empty p.foto1}">
			<img src="img/produtos/${p.foto1}" alt = "${p.descricao} - Foto: ${p.foto1}" class="img-thumbnail">
		    </c:if>
		</td>
                <td>
		    <c:if test="${not empty p.foto2}">
			<img src="img/produtos/${p.foto2}" alt = "${p.descricao} - Foto: ${p.foto2}" class="img-thumbnail">
		    </c:if>
		</td>
                <td>
		    <c:if test="${not empty p.foto3}">
			<img src="img/produtos/${p.foto3}" alt = "${p.descricao} - Foto: ${p.foto3}" class="img-thumbnail">
		    </c:if>
		</td>

                <td>${p.quant}</td>

                <td>R$ <fmt:formatNumber minFractionDigits="2" currencySymbol="R$">
                        ${p.valor} 
                    </fmt:formatNumber>
                </td>

                <td><a href="sys?action=buscaid&logica=LogicaProduto&id=${p.id}"><i class="fa fa-edit"></i></a></td>
                <td><a href="sys?action=rem&logica=LogicaProduto&id=${p.id}"><i class="fa fa-remove"></i></a></td>
            </tr>
        </c:forEach>
    </table>
</div>