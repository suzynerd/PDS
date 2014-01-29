<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Nova Turma</title>
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
    			<a class="navbar-brand" href="#">SysRedIN</a>
  			</div>
  			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    			<ul class="nav navbar-nav">
    				<c:url var="home" value="/"/>
    				<li><a href="${home}perfil">Perfil</a></li>
      				<li><a href="${home}amigos">Amigos</a></li>
      				<li class="active"><a href="${home}turmas">Minhas Turmas</a></li>
      				<li><a href="${home}arquivos">Arquivos</a></li>
      				
      				<li class="dropdown">
        				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
        					<b class="glyphicon glyphicon-search"></b></a>
        				<ul class="dropdown-menu">
          					<li><a href="#">Pessoas</a></li>
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

  				<p class="navbar-text navbar-right"></p>
  			</div>
		</nav>
	
	<div class="tablea" method="post">
		<form role="form" action="${home}salvarturma">
			<div class="form-group">
				<label for="nome">Nome da Turma</label>
				<input type="text" name="nome" id="nome" class="form-control" value="${nome}">
			</div>
			<div class="form-group">
				<label for="descricao">Descrição</label>
				<textarea class="form-control" rows="2" type="text" name="descricao" id="descricao" value="${descricao}"></textarea>
			</div>
			
			<input type="submit" class="btn btn-success" value="salvar">
		</form>
	</div>

</body>
</html>