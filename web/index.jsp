<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Loja WEB</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <body class="mt-5 pt-3">
        <c:import url="navbar.jsp"/>   
        <c:if test="${not empty aviso}">
            <div class="alert alert-success">${aviso}</div>
        </c:if>
        <c:if test="${not empty alerta}">
            <div class="alert alert-warning">${alerta}</div>
        </c:if>
        <c:if test="${not empty erros}">
            <div class="alert alert-danger">${erros}</div>
        </c:if>

        <section class="container">
            <c:if test="${not empty param.p}">
                <c:import url="${param.p}"/>
            </c:if>
            <c:if test="${empty param.p}">
                <c:import url="inicio.jsp"/>
            </c:if>
        </section>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"/></script>
    <script src="js/ceps2.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
	$(function () {
	    $("#datepicker").datepicker({
		changeMonth: true,
		changeYear: true,
		maxDate: "+0M +0DD"
	    });
	    $("#datepicker").datepicker("option", "dateFormat", "dd/mm/yy");
	});
    </script>
</body>
</html>
