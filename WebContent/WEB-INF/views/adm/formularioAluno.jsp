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
<meta name="description" content="">
<meta name="author" content="">
<title>MOL - Administrador</title>
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

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<a class="navbar-brand" href="home">Monitoria On-line</a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Cadastros"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseCadastros" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-id-card"></i> <span class="nav-link-text">Cadastros
					</span></a>
					<ul class="sidenav-second-level collapse" id="collapseCadastros">
						<li><a href="cadastrarDisciplina">Cadastrar Disciplina</a></li>
						<li><a href="cadastrarUsuario">Cadastrar Usuário</a></li>
					</ul></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Listagens"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseListas" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-table"></i> <span class="nav-link-text">Listagens</span></a>
					<ul class="sidenav-second-level collapse" id="collapseListas">
						<li><a href="listarDisciplinas">Listar Disciplinas</a></li>
						<li><a href="listarUsuarios">Listar Usuários</a></li>
					</ul></li>
			</ul>
			<ul class="navbar-nav sidenav-toggler">
				<li class="nav-item"><a class="nav-link text-center"
					id="sidenavToggler"> <i class="fa fa-fw fa-angle-left"></i>
				</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><span class="navbar-text mr-5">Bem-vindo(a),
						<c:out value="${sessionScope.usuarioLogado.nome}" />
				</span></li>
				<li class="nav-item"><a class="nav-link" data-toggle="modal"
					data-target="#exampleModal"> <i class="fa fa-fw fa-sign-out"></i>Sair
				</a></li>
			</ul>
		</div>
	</nav>
	<div class="content-wrapper">
		<div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="home">Página Inicial</a></li>
				<li class="breadcrumb-item"><a href="cadastrarUsuario">Cadastro
						de Usuário</a></li>
				<li class="breadcrumb-item active">Cadastro de Aluno</li>
			</ol>
			<div class="row">
				<div class="col-12">
					<div class="card card-register mx-auto mt-5">
						<div class="card-header">Cadastro de Aluno</div>
						<div class="card-body">
							<form:form modelAttribute="aluno" action="insereAluno"
								method="POST">
								<div class="form-group">
									<div class="form-row">
										<div class="col-md-6">
											<label for="inputNome">Nome</label>
											<form:input path="nome" class="form-control" id="inputNome"
												type="text" aria-describedby="nameHelp"
												placeholder="Digite seu nome" />
											<form:errors path="nome" cssClass="text-danger" />
										</div>
										<div class="col-md-6">
											<label for="inputMatricula">Matrícula</label>
											<form:input path="matricula" class="form-control"
												id="inputMatricula" type="text" aria-describedby="nameHelp"
												placeholder="Ex: 12345678" maxlength="8"
												onchange="verificaMatAluno(this.value)" />
											<form:errors path="matricula" cssClass="text-danger" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail">Email</label>
									<form:input path="email" class="form-control" id="inputEmail"
										type="email" aria-describedby="emailHelp"
										placeholder="Ex: exemplo@email.com"
										onchange="verificaEmail(this.value)" />
									<form:errors path="email" cssClass="text-danger" />
								</div>
								<button class="btn btn-primary btn-block" type="submit">Cadastrar</button>
							</form:form>
							<div class="text-center">
								<a class="d-block small mt-3" href="cadastrarUsuario">Cancelar</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- /.container-fluid-->
		<!-- /.content-wrapper-->
		<footer class="sticky-footer">
			<div class="container">
				<div class="text-center">
					<small>Copyright © 2018</small>
				</div>
			</div>
		</footer>
		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fa fa-angle-up"></i>
		</a>
		<!-- Logout Modal-->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Já vai?</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">Selecione "sair" se deseja encerrar a
						sessão atual.</div>
					<div class="modal-footer">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">Cancelar</button>
						<a class="btn btn-primary" href="logout">Sair</a>
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
		<!-- Custom scripts for all pages-->
		<script src="webjars/startbootstrap-sb-admin/4.0.0/js/sb-admin.min.js"></script>
		<!-- Validação com Ajax -->
		<script src="resources/scripts/validacaoAjax.js"></script>
	</div>
</body>

</html>
