<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>MOL - Cadastro</title>
<!-- Bootstrap core CSS-->
<link
	href="webjars/startbootstrap-sb-admin/4.0.0/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom fonts for this template-->
<link
	href="webjars/startbootstrap-sb-admin/4.0.0/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Custom styles for this template-->
<link href="webjars/startbootstrap-sb-admin/4.0.0/css/sb-admin.css"
	rel="stylesheet">
</head>
<body class="bg-dark">
	<div class="container">
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">Cadastro de professor(a)</div>
			<div class="card-body">
				<form:form action="cadastraProfessor" modelAttribute="professor" method="POST">
					<div class="form-group">
						<div class="form-row">
							<div class="col-md-6">
								<label for="inputNome">Nome</label> <form:input path="nome"
									class="form-control" id="inputNome" type="text" required="true"
									aria-describedby="nameHelp" placeholder="Digite seu nome" />
								<form:errors path="nome" cssClass="text-danger" />
							</div>
							<div class="col-md-6">
								<label for="inputMatricula">Matrícula</label> <form:input
									path="matricula" class="form-control" id="inputMatricula"
									type="text" aria-describedby="nameHelp" maxlength="8" required="true"
									placeholder="Ex: 12345678"  onchange="verificaMatProf(this.value)"/>
								<form:errors path="matricula" cssClass="text-danger" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail">Email</label> <form:input path="email"
							class="form-control" id="inputEmail" type="email" required="true"
							aria-describedby="emailHelp" placeholder="Ex: exemplo@email.com"
							onchange="verificaEmail(this.value)" />
						<form:errors path="email" cssClass="text-danger" />
					</div>
					<button class="btn btn-primary btn-block" type="submit">Cadastrar</button>
				</form:form>
				<div class="text-center">
					<a class="d-block small mt-3" href="login">Cancelar</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script
		src="webjars/startbootstrap-sb-admin/4.0.0/vendor/jquery/jquery.min.js"></script>
	<script
		src="webjars/startbootstrap-sb-admin/4.0.0/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script
		src="webjars/startbootstrap-sb-admin/4.0.0/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script src="resources/scripts/validacaoAjax.js"></script>
</body>
</html>