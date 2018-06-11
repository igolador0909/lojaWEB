<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="post" action="sys" enctype="multipart/form-data">
    <input type="hidden" name="logica" value="LogicaProduto">
    <c:if test="${not empty produto.id}">
        <input type="hidden" name="action" value="alt">
        <input type="hidden" name="id" value="${produto.id}">
        <h2>Alteração de Produto</h2>
    </c:if>

    <c:if test="${empty produto.id}">
        <input type="hidden" name="action" value="cad">
        <h2>Cadastro de Produto</h2>
    </c:if>

    <div class="form-group">
        <label for="nome">Nome</label>
        <input type="text" name="nome" class="form-control" value="${produto.nome}">
    </div>

    <div class="form-group">
        <label for="descricao">Descrição</label>
        <input type="text" name="descricao" class="form-control" value="${produto.descricao}">
    </div>

    <div class="form-group">
        <label for="quant">Quantidade</label>
        <input type="number" name="quant" min="0" step="0.5" class="form-control" value="${produto.quant}">
    </div>

    <div class="form-group">
        <label for="valor">Valor</label>
        <input type="number" name="valor" min="0" step="0.5" class="form-control" value="${produto.valor}">
    </div>

    <div class="form-group">
        <label for="foto1">Foto 1</label>
        <input type="file" name="foto1" class="form-control" value="${produto.foto1}">
    </div>
    <div class="form-group">
        <label for="foto2">Foto 2</label>
        <input type="file" name="foto2" class="form-control" value="${produto.foto1}">
    </div>
    <div class="form-group">
        <label for="foto3">Foto 3</label>
        <input type="file" name="foto3" class="form-control" value="${produto.foto1}">
    </div>

    <div class="radio">
        <label for="ativo">
            <input type="radio" name="ativo" value="1" checked>
            Ativo
        </label>
    </div>
    <div class="radio">
        <label for="ativo">
            <input type="radio" name="ativo" value="0">
            Bloqueado
        </label>
    </div>

    <div class="form-group mt-3">
        <button class="btn">Salvar</button>
        <button type="reset" class="btn">Cancelar</button>
    </div>
</form>

<script src="js/geradorCPF.js"></script>




