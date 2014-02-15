<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>SysRedIN</title>
	<c:url var="src" value="/source"/>
	<c:url var="home" value="/"/>
	
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
    			<ul class="nav navbar-nav navbar-right">
    				<li><a href="${home}cadastrar">Cadastrar</a></li>
      				<li class="dropdown">
        					<a href="#" class="dropdown-toggle" data-toggle="dropdown">Entrar <b class="caret"></b></a>
        					<ul class="dropdown-menu">
								<form role="form" method="post" action="${home}login" class="cadastro">
        							<div class="form-group">
        								<input value="${usuario.email}" name="email" type="email" class="form-control" placeholder="E-mail">
        							</div>
        							<div>
        								<input value="${usuario.senha}" name="senha" type="password" class="form-control" placeholder="Senha">
        							</div>
        							<button type="submit" class="btn btn-success" value="login">Confirmar</button>
         					</form>
        				</ul>
      				</li>
      			</ul>
  			</div>
		</nav>
</body>
</html>