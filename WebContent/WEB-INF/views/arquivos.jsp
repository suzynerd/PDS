<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head><title>Arquivos</title>
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
    			
    			<a class="navbar-brand" href="${home}">SysRedIN</a>
  			</div>
  			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    			<ul class="nav navbar-nav">
    				<li><a href="${home}perfil">Perfil</a></li>
      				<li><a href="${home}amigos">Amigos</a></li>
      				<c:if test="${sessionScope.perfilLogado.idTipoPerfil == 2}">
      					<li><a href="${home}turmas">Minhas Turmas</a></li>
      				</c:if>
      				<li class="active"><a href="#">Arquivos</a></li>
      				
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
          					
          					<li><a href="${home}perfil/sair">Sair</a></li>
        		</ul></li></ul>

  				<p class="navbar-text navbar-right">${sessionScope.perfilLogado.nome}</p>
  			</div>
		</nav>
	
	<div>
	
	<c:url value="/" var="home"></c:url>
	<form role="form" action="${home}upload" enctype="multipart/form-data" method="post">
		<div class="form-group">
			<input type="file" name="file"><br/>
			<input class="btn btn-success" type="submit" value="Salvar Arquivo">
		</div>
	</form>
	
	
	<div class="tablea">
	<table border="1" class="table table-hover" width="500px">
		<tr>
			<td>Arquivo</td>
			<td>Tipo</td>
		</tr>
		
		<c:forEach var="arquivo" items="${arquivos}">
			<tr>
				<td>
					${arquivo.nome}
				</td>
				<td>${arquivo.tipo}</td>
				<td>
					<a class="btn btn-success" href="${home}download?idArquivoDownload=${arquivo.idArquivo}">
						Download <b class="glyphicon glyphicon-download-alt"></b>
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	</div>
</body>
</html>