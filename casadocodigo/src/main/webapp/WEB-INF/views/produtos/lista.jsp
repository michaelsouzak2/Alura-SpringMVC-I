<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Livro de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do c�digo</title>
	</head>
	<body>
		
		<h1>Lista de produtos</h1>
		
		<div>${sucesso}</div>
				
		<table>
			<tr>
				<td>T�tulo</td>
				<td>Descri��o</td>
				<td>P�ginas</td>
			</tr>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td>${produto.titulo}</td>
					<td>${produto.descricao}</td>
					<td>${produto.paginas}</td>
				</tr>
			</c:forEach>
		</table>
			
	</body>
</html>