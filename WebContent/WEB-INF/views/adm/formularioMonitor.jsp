<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<!-- Bootstrap select CSS -->
<link
	href="webjars/bootstrap-select/1.13.1/dist/css/bootstrap-select.min.css"
	rel="stylesheet">
<!-- Tempus Dominus CSS -->
<link
	href="webjars/tempusdominus-bootstrap-4/5.0.0/css/tempusdominus-bootstrap-4.min.css"
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
				<li class="nav-item dropdown show"><span
					class="nav-link dropdown-toggle mr-lg-2" data-toggle="dropdown">
						<c:out value="${sessionScope.usuarioLogado.nome}" />
				</span>
					<div class="dropdown-menu dropdown-menu-right">
						<h6 class="dropdown-header">${sessionScope.usuarioLogado.tipo.tipoUsuario}</h6>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="" data-toggle="modal"
							data-target="#editarDadosModal"> <i
							class="fa fa-fw fa-id-card"></i> Editar dados
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="" data-toggle="modal"
							data-target="#exampleModal"> <i class="fa fa-fw fa-sign-out"></i>Sair
						</a>
					</div></li>
				<li class="nav-item"></li>
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
				<li class="breadcrumb-item active">Cadastro de Monitor</li>
			</ol>
			<div class="row">
				<div class="col-12">
					<div class="card card-register mx-auto mt-5">
						<div class="card-header">Cadastrar Monitor</div>
						<div class="card-body">
							<form:form modelAttribute="monitor" action="insereMonitor"
								method="POST">
								<div class="form-group">
									<div>
										<label for="selectDisciplina">Disciplina</label>
										<form:select class="selectpicker form-control"
											title="Sigla ou nome" data-live-search="true" path="disciplina"
											type="text" id="selectDisciplina">
											<c:forEach items="${disciplinas}" var="d">
												<form:option value="${d.sigla}">${d.sigla} - ${d.nome}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<div>
										<label for="selectAluno">Aluno</label>
										<form:select class="selectpicker form-control"
											data-live-search="true" title="Nome ou matrícula"
											path="aluno" id="selectAluno" type="text">
											<c:forEach items="${alunos}" var="a">
												<form:option value="${a.matricula}">${a.nome} - ${a.matricula}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
								<div class="form-row">
									<div class="form-group col-md-6">
										<label for="dateTermino">Data de início</label>
										<div class="input-group date" id="datetimepickerInicio"
											data-target-input="nearest">
											<form:input type="text" path="dataInicioContrato"
												class="form-control datetimepicker-input"
												data-target="#datetimepickerInicio" id="dateInicio" />
											<div class="input-group-append"
												data-target="#datetimepickerInicio"
												data-toggle="datetimepicker">
												<div class="input-group-text">
													<i class="fa fa-calendar"></i>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label for="dateTermino">Data de término</label>
										<div class="input-group date" id="datetimepickerTermino"
											data-target-input="nearest">
											<form:input type="text" path="dataTerminoContrato"
												class="form-control datetimepicker-input"
												data-target="#datetimepickerTermino" id="dateTermino" />
											<div class="input-group-append"
												data-target="#datetimepickerTermino"
												data-toggle="datetimepicker">
												<div class="input-group-text">
													<i class="fa fa-calendar"></i>
												</div>
											</div>
										</div>
									</div>
								</div>

								<button class="btn btn-primary btn-block" type="submit">Cadastrar</button>
							</form:form>
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
		<!-- Modal editar dados -->
		<div class="modal fade" id="editarDadosModal" tabindex="-1"
			role="dialog" aria-labelledby="editarDadosModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editarDadosModalLabel">Editar
							Usuário</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="inputEditarNome">Nome</label> <input class="form-control"
								id="inputEditarNome" name="nome" type="text" value="${sessionScope.usuarioLogado.nome}"
								placeholder="Digite o nome do Usuário" maxlength="50" />
						</div>
						<div class="form-group">
							<label for="inputEditarEmail">Email</label> <input class="form-control"
								id="inputEditarEmail" name="email" type="email" placeholder="email@exemplo.com"
								maxlength="50" onChange="verificaEmail(this.value)" value="${sessionScope.usuarioLogado.email}" />
						</div>
						<input id="inputId" name="usuario" hidden="true" value="${sessionScope.usuarioLogado.id}" />
						<div class="form-group" id="divAlterarSenha" hidden="true">
							<div class="form-row">
								<div class="col-md-6">
									<label for="inputSenha">Nova Senha</label>
									<input id="inputSenha" onKeyUp="confirmaSenha()" type="password" class="form-control" />
								</div>
								<div class="col-md-6">
									<label for="confirmacaoSenha">Confirmar Senha</label>
									<input id="confirmacaoSenha" onKeyUp="confirmaSenha()" type="password" class="form-control" />
								</div>
							</div>
						</div>
						<div>
							<button class="btn btn-secondary btn-block" onClick="showDivAlterarSenha()">Alterar Senha</button>
							<button class="btn btn-primary btn-block" onClick="editarDados()" id="btnSalvarDados">Salvar</button>
						</div>
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
		<!-- Tempus Dominus scripts -->
		<script src="webjars/momentjs/2.22.2/min/moment-with-locales.min.js"></script>
		<script
			src="webjars/tempusdominus-bootstrap-4/5.0.0/js/tempusdominus-bootstrap-4.min.js"></script>
		<!-- Bootstrap select scripts -->
		<script
			src="webjars/bootstrap-select/1.13.1/dist/js/bootstrap-select.min.js"></script>
		<script
			src="webjars/bootstrap-select/1.13.1/dist/js/i18n/defaults-pt_BR.min.js"></script>
		<!-- Validação com Ajax -->
		<script src="resources/scripts/validacaoAjax.js"></script>
		<script type="text/javascript">
			$(function() {
				$('#datetimepickerInicio').datetimepicker({
					minDate : new Date(),
					format : 'L',
					locale : 'pt-br'
				});
				$('#datetimepickerTermino').datetimepicker({
					minDate : new Date(),
					format : 'L',
					locale : 'pt-br',
					useCurrent : false
				});
				$("#datetimepickerInicio").on(
						"change.datetimepicker",
						function(e) {
							$('#datetimepickerTermino').datetimepicker(
									'minDate', e.date);
						});
				$("#datetimepickerTermino").on(
						"change.datetimepicker",
						function(e) {
							$('#datetimepickerInicio').datetimepicker(
									'maxDate', e.date);
						});
			});
		</script>
	</div>
</body>

</html>
