<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Notificações</title>
	<c:url var="src" value="/source"/>
	
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
    			<a class="navbar-brand" href="${home}">SysRedIN</a>
  			</div>
  			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    			<ul class="nav navbar-nav">
    				<c:url var="home" value="/"/>
    				<li><a href="${home}perfil">Perfil</a></li>
      				<li><a href="${home}amigos">Amigos</a></li>
      				<c:if test="${sessionScope.perfilLogado.idTipoPerfil == 2}">
      					<li class="active"><a href="#">Minhas Turmas</a></li>
      				</c:if>
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
		
		<div class="jumbotron">
  		<h1>${turma.nome}</h1>
  		<p>${turma.descricao}</p>
  		<p class="button"><a class="btn btn-primary btn-lg" role="button" href="${home}turma/novoAluno">Adicionar Aluno</a></p>
		</div>


<c:if test="${sessionScope.perfilLogado.idTipoPerfil == 2}">
	<div class="Notify">
		<table class="table table-hover">
			<tr>
				<td>Aluno</td>
				<td>Turma</td>
				<td>Opções</td>
			</tr>
			<tr>
				<c:forEach var="noti" items="${nots}">
					<td>${noti.aluno}</td>
					<td>${noti.turma}</td>
					<td>
						<a class="btn btn-success" href="${home}aceitarSolicitacao?idNotificacao=${noti.id}">Aceitar</a>
						<a class="btn btn-danger" href="${home}recusarSolicitacao?idNotificacao=${noti.id}">Recusar</a>
					</td>
				</c:forEach>
			</tr>
		</table>
	</div>
	</c:if>
</body>
</html>