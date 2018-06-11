<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark ">
    <a class="navbar-brand" href="index.jsp">Inicio</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="nav-link active">Menu</span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">Produtos</a>
            </li>
	    <li class="nav-item">
                <a class="nav-link" href="#">Lojas</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="index.jsp?p=formProduto.jsp">Cad.Produtos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="index.jsp?p=reportProduto.jsp">Rel.Produtos</a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">

            <li class="nav-item">
                <!--<a class="nav-link" href="sys?action=busca&dados">Rel.Cliente</a>-->
                <a class="nav-link" href="index.jsp?p=reportCliente.jsp">Rel.Cliente</a>
            </li>
            <c:if test="${empty sessionScope.id}">
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp?p=formCliente.jsp">Clientes</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="index.jsp?p=login.jsp">login</a>
                </li>
            </c:if>

            <c:if test="${not empty sessionScope.id}">
                <li class="nav-item">
                    <a class="nav-link" href="#">${nome}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="sys?action=off&logica=LogicaPessoa">logoff</a>
                </li>
            </c:if>


            <li class="nav-item">
                <a class="nav-link" href="?p=carrinho.jsp">Carrinho <span class="badge badge-pill badge-secondary ">${sessionScope.itens.size()} </span></a>
            </li>
        </ul>
    </div>
</nav>