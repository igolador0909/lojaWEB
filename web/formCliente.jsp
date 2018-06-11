<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="post" action="sys">
    <input type="hidden" name="logica" value="LogicaPessoa">
    <c:if test="${not empty cliente.id}">
        <input type="hidden" name="action" value="alt">
        <input type="hidden" name="id" value="${cliente.id}">
        <h2>Alteração de Cliente</h2>
    </c:if>

    <c:if test="${empty cliente.id}">
        <input type="hidden" name="action" value="cad">
        <h2>Cadastro de Cliente</h2>
    </c:if>

    <div class="form-group">
        <label for="nome">Nome</label>
        <input type="text" name="nome" class="form-control" value="${cliente.nome}">
    </div>

    <div class="form-group">
        <label for="email">E-mail</label>
        <input type="email" name="email" class="form-control" value="${cliente.email}">
    </div>

    <div class="form-group">
        <label for="cpf">CPF</label>
        <input type="number" name="cpf" max="99999999999" min="00000000000" class="form-control" placeholder="Apenas numeros" value="${cliente.cpf}" onblur ="gerarCPF(this, false)">
    </div>
    ${cliente.dataNasc}
    <div class="form-group">
        <label for="dataNasc">Data Nascimento</label>
        <input type="text" name="dataNasc" class="form-control" value="${cliente.dataNasc}" placeholder="dd/mm/aaaa" id="datepicker">
    </div>
    <div class="form-group">
        <label for="numero">Numero</label>
        <input type="text" name="numero" class="form-control" value="${cliente.numero}">
    </div>

    <div class="form-group">
        <label for="complemento">Complemento</label>
        <input type="text" name="complemento" class="form-control" value="${cliente.complemento}">
    </div>

    <div class="form-group">
        <label for="cep">CEP</label><span id="mensagem"></span>
        <input type="text" name="cep" class="form-control"  maxlength="9" value="${cliente.endereco.cep}" id="cep">
    </div>

    <div class="form-group">
        <label for="logradouro">Endereço</label>
        <input type="text" name="logradouro" maxlength="" class="form-control" value="${cliente.endereco.logradouro}" id="rua">
    </div>

    <div class="form-group">
        <label for="bairro">Bairro</label>
        <input type="text" name="bairro" maxlength="" class="form-control" value="${cliente.endereco.bairro}" id="bairro">
    </div>

    <div class="form-group">
        <label for="cidade">Cidade</label>
        <input type="text" name="cidade" maxlength="" class="form-control" value="${cliente.endereco.cidade}" id="cidade">
    </div>

    <div class="form-group">
        <label for="uf">Estado</label>
        <input type="text" name="uf" maxlength="2" class="form-control" value="${cliente.endereco.uf}" id="uf">
    </div>
    <c:if test="${empty cliente.id}">
        <div class="form-group">
            <label for="pws1">Senha</label>
            <input type="password" name="pws1" maxlength="20" class="form-control">
        </div>

        <div class="form-group">
            <label for="pws2">Confirmação de Senha</label>
            <input type="password" name="pws2" maxlength="20" class="form-control">
        </div>
    </c:if>
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




