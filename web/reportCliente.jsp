<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="get" action="sys" class="form-inline">
    <input type="hidden" name="logica" value="LogicaPessoa">
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
            <th>E-mail</th>
            <th>CPF</th>
            <th>CEP</th>
            <th><i class="fa fa-edit"></i></th>
            <th><i class="fa fa-remove"></i></th>
        </tr>

        <c:forEach var="p" items="${pessoas}">
            <tr>
                <td>${p.id}</td>
                <td>${p.nome}</td>
                <td>${p.email}</td>
                <td>${p.cpf}</td>
                <td>${p.endereco.cep}</td>
                <td><a href="sys?action=buscaid&logica=LogicaPessoa&id=${p.id}"><i class="fa fa-edit"></i></a></td>
                <td><a href="sys?action=rem&logica=LogicaPessoa&id=${p.id}"><i class="fa fa-remove"></i></a></td>
            </tr>
        </c:forEach>
    </table>
</div>