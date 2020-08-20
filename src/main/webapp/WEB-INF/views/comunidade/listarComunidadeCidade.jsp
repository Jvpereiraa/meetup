<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html class="h-100" lang="pt-br">
<head>
 <link rel="stylesheet" href="../resources/main.css">
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<title>Meetups online de Joinville e regiao</title>
</head>
<body class="d-flex flex-column h-100">
	<div>
		<header class="header-margin">
		  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		    <a class="navbar-brand" href="#">Desafio Meetup</a>
		    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>

		    <div class="collapse navbar-collapse" id="navbarCollapse">
		      <ul class="navbar-nav mr-auto">
		        <li class="nav-item ">
		          <a class="nav-link" href="${s:mvcUrl('HC#listar').build() }">Home <span class="sr-only">(atual)</span></a>
		        </li>
		      </ul>
		      <form:form action="${s:mvcUrl('CC#listarComunidadeCidade').build() }" method="get" 
    commandName="comunidade" class="form-inline mt-2 mt-md-0">
		        <form:input path="cidade" class="form-control mr-sm-2" type="text" placeholder="Meetup por regiao" aria-label="Search" value=""/>
		        <button id="btn-div" class="btn btn-outline-success my-2 my-sm-0" type="submit">Pesquisar</button>
		      </form:form>
		    </div>
		  </nav>
		</header>
		<div class="teste-desafio-display" id="${show }">
			<div class="alert alert-warning alert-dismissible fade show">
			    <button type="button" class="close" data-dismiss="alert">&times;</button>
			    <strong>Warning!</strong> ${falha }
			</div>
		</div>
		<div>
			<c:forEach items="${comunidades }" var="comunidade">
				<div class="card card-display" style="width: 18rem;">
				  <img src="data:image/png;base64,${comunidade.logo}" class="card-img-top">
				  <div class="card-body">
				    <h5 class="card-title">${comunidade.nome }</h5>
				    <p class="card-text">${comunidade.cidade }</p>
				    <form:form action="${s:mvcUrl('CC#listarEventos').build() }" method="get" 
	    commandName="comunidade" class="form-inline mt-2 mt-md-0">
				   		<!--  <a href="#" class="btn btn-primary">Mais meetups de ${comunidade.nome }</a>-->
				   		<form:input path="id" class="form-control mr-sm-2 display-none" type="text" aria-label="Search" value="${comunidade.id }"/>
				   		<button class="btn btn-primary" type="text" type="submit">Mais meetups de ${comunidade.nome }</button>
				    </form:form>
				  </div>
				</div>
			</c:forEach>
		</div>
	</div>
	<footer class="footer mt-auto py-3 footer-css">
      <div class="container">
        <span class="text-muted">Desafio-MeetUp.</span>
      </div>
    </footer>
	<script src="../resources/main.js"></script>
</body>
</html>