<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
	<title>Amigos</title>
	<c:url var="src" value="/source"/>
	<c:url var="home" value="/"/>
	<c:url var="salvar" value="/salvarPerfil"/>
	
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${src}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${src}/css-aux/index.css" rel="stylesheet" type="text/css">

    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="${src}/js/bootstrap.min.js"></script>
    
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  			<div class="navbar-header">
    			<button type="button" class="navbar-toggle" data-toggle="collapse"
    			data-target="#bs-example-navbar-collapse-1">
      				<span class="sr-only">Toggle navigation</span>
      				<span class="glyphicon glyphicon-align-justify"></span>
    			</button>
    			<c:url var="home" value="/"/>
    			<a class="navbar-brand" href="${home}">SysRedIN</a>
  			</div>
  			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    			<ul class="nav navbar-nav">
    				
    				<li><a href="${home}perfil">Perfil</a></li>
      				<li class="active"><a href="${home}amigos">Amigos</a></li>
      				<li><a href="${home}turmas">Minhas Turmas</a></li>
      				<li><a href="${home}arquivos">Arquivos</a></li>
      				
      				<li class="dropdown">
        				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
        					<b class="glyphicon glyphicon-search"></b></a>
        				<ul class="dropdown-menu">
          					<li><a href="${home}pessoas">Pessoas</a></li>
          					<li><a href="#">Turmas</a></li>
        			</ul></li>
    			</ul>
    			<ul class="nav navbar-nav navbar-right">
      				<li class="dropdown">
        				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
        					<b class="glyphicon glyphicon-cog"></b></a>
        				<ul class="dropdown-menu">
          					<li><a href="#">Preferencias</a></li>
          					<li class="divider"></li>
          					<c:url var="profile" value="/perfil"/>
          					<li><a href="${profile}/sair">Sair</a></li>
        		</ul></li></ul>

  				<p class="navbar-text navbar-right">${sessionScope.perfilLogado.nome}</p>
  			</div>
		</nav>


	<table class="table table-hover">
    <tr>
      <td>Nome</td><td>Op��es</td>
    </tr>
    <c:forEach var="amigo" items="${amigos}">
    		<tr>
      			<td>${amigo.nome}</td>
      			<c:url var="amigos" value="/amigos"></c:url>
      			<td><a href="${amigos}/removerAmigo?idRelacao=${amigo.idRelacao}" class="btn btn-danger">Remover Amigo</a></td>
    		</tr>
    </c:forEach>
  </table>
</body>
</html>