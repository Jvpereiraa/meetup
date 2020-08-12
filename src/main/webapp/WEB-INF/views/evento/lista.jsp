<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Meetup-Desafio</title>

</head>
<body>
	<h1>Lista de Eventos</h1>
	
	<div>${sucesso }</div>
	<table>
		<tr>
			<td>Nome</td>
			<td>Data</td>
			<td>Descrição</td>
			<td>Endereço</td>
			<td>Link do evento</td>
		</tr>
		<c:forEach items="${eventos }" var="evento">
			<tr>
				<td>${evento.nome }</td>
				<td>${evento.data }</td>	
				<td>${evento.descricao }</td>	
				<td>${evento.endereco }</td>	
				<td>${evento.linkEvento }</td>			
			</tr>
		</c:forEach>
	</table>
</body>
</html>