//<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"/></script>

$(document).ready(function () {
    $('#cep').keyup(function () {
	txcep = $("#cep").val();
	if (txcep.length >= 8) {
	    url = 'https://viacep.com.br/ws/' + txcep + '/json/';
	    $.ajax({
		type: 'POST',
		url: url,
		timeout: 5000,
		data: {},
		beforeSend: function () {
		    $("#mensagem").html(" Carregando ");
		},
		success: function (dados) {
		    $("#mensagem").html("");
		    $("#cep").val(dados.cep);
		    $("#rua").val(dados.logradouro);
		    $("#bairro").val(dados.bairro);
		    $("#cidade").val(dados.localidade);
		    $("#uf").val(dados.uf);
		},
		error: function () {
		    //$("#cep").val(txcep);
		    $("#mensagem").html(" Não encontrado! ");
		}
	    });
	} else {
	    $("#mensagem").html("");
	}
    });

});

