%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
	<title>Cadastro</title>
	<c:url var="src" value="/source"/>
	<c:url var="home" value="/"/>
	<c:url var="salvar" value="/salvarPerfil"/>
	
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${src}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${src}/css-aux/perfil.css" rel="stylesheet" type="text/css">

    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="${src}/js/bootstrap.min.js"></script>
    
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<table class="table table-hover">
    <tr>
      <td>Nome</td><td>Opções</td>
    </tr>
    <c:forEach var="perfil" items="${perfis}">
      <td>${perfil.nome}</td>
      <c:url var="pessoas" value="/pessoas"></c:url>
      <td><a href="${amigos}/adicionarAmigo?id=${perfil.id}" class="btn btn-success">Adicionar Amigo</a></td>
    </c:forEach>
  </table>
</body>
</html>