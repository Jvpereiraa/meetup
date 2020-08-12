<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de comunidade</title>

</head>
<body>
	<form action="/meetup/comunidades" method="post">
		<div>
			<label>Nome da comunidade</label>
			<input type="text" name="nome">
		</div>
		<div>
			<label>Linguagem da comunidade</label>
			<input type="text" name="linguagem">
		</div>
		<div>
			<label>Email de contato</label>
			<input type="text" name="email">
		</div>
		<button type="submit">Cadastrar</button>
	</form>
</body>
</html>