<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de comunidade</title>

</head>
<body>
	<form:form action = ${s:mvcUrl('CC#grava').build() } method="POST" commandName="comunidade">
		<div>
			<label>Nome da comunidade</label>
			<input type="text" name="nome">
			<form:errors path= "nome"/>
		</div>
		<div>
			<label>Linguagem da comunidade</label>
			<input type="text" name="linguagem">
			<form:errors path= "linguagem"/>
		</div>
		<div>
			<label>Email de contato</label>
			<input type="text" name="email">
		</div>
		<button type="submit">Cadastrar</button>
	</form:form>
</body>
</html>