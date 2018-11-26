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
						<li><a href="cadastroCurso">Cadastrar Curso</a></li>
						<li><a href="cadastrarPeriodo">Cadastrar Período</a></li>
						<li><a href="cadastroTurma">Cadastrar Turma</a></li>
						<li><a href="cadastrarDisciplina">Cadastrar Disciplina</a></li>
						<li><a href="cadastroTurmaDisciplina">Cadastrar Turma/Disciplina</a></li>
						<li><a href="cadastrarUsuario">Cadastrar Usuário</a></li>
					</ul></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Listagens"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseListas" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-table"></i> <span class="nav-link-text">Listagens</span></a>
					<ul class="sidenav-second-level collapse" id="collapseListas">
						<li><a href="listarCursos">Listar Cursos</a></li>
						<li><a href="listarPeriodos">Listar Períodos</a></li>
						<li><a href="listaTurmas">Listar Turmas</a></li>
						<li><a href="listarDisciplinas">Listar Disciplinas</a></li>
						<li><a href="turmasDisciplinas">Listar Turmas/Disciplinas</a></li>
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
				<li class="breadcrumb-item active">Cadastro de Turma/Disciplina</li>
			</ol>
			<div class="row">
				<div class="col-12">
					<div class="card card-register mx-auto mt-2 mb-2">
						<div class="card-header">Cadastrar Turma/Disciplina</div>
						<div class="card-body">
							<form:form modelAttribute="turmaDisciplina" action="insereTurmaDisciplina"
								method="POST">
								<div class="form-group">
									<div>
										<label for="selectDisciplina">Disciplina</label>
										<form:select required="true" class="selectpicker form-control"
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
										<label for="selectTurma">Turma</label>
										<form:select required="true" class="selectpicker form-control"
											title="Ex: 912-A" data-live-search="true" path="turma"
											type="text" id="selectTurma">
											<c:forEach items="${turmas}" var="t">
												<form:option value="${t.id}">${t.identificacao} - ${t.curso.nome} - ${t.curso.modalidade.descricao} 
												- ${t.periodo.ano}.${t.periodo.entrada.semestre}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<div>
										<label for="selectProfessor">Professor</label>
										<form:select required="true" class="selectpicker form-control"
											data-live-search="true" title="Nome ou matrícula"
											path="professor" id="selectProfessor" type="text">
											<c:forEach items="${professores}" var="p">
												<form:option value="${p.id}">${p.nome} - ${p.matricula}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<div class="form-row">
										<div class="col-md-6">
											<label for="inputCargaHoraria">Carga Horária</label>
											<form:input required="true" class="form-control" type="number" value="40" min="20" step="10" path="cargaHoraria" />
										</div>
										<div class="col-md-6">
											<label for="selectTipoCalculo">Tipo de Cálculo</label>
											<form:select class="form-control" path="tipoCalculo">
												<c:forEach items="${tiposCalculo}" var="tc">
													<form:option value="${tc}">${tc.tipoCalculo}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>					
								</div>
								
								<form:input value="0" type="number" path="quantidadeNiveis" hidden="true" />
								
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
							<label for="inputEditarNome">Nome</label> <input class="form-control" required="required"
								id="inputEditarNome" name="nome" type="text" value="${sessionScope.usuarioLogado.nome}"
								placeholder="Digite o nome do Usuário" maxlength="50" />
						</div>
						<div class="form-group">
							<label for="inputEditarEmail">Email</label> <input class="form-control" required="required"
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
							<button class="btn btn-secondary btn-block" onClick="showDivAlterarSenha()" id="btnAltSenha">Alterar Senha</button>
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
		<script
			src="webjars/tempusdominus-bootstrap-4/5.0.0/js/tempusdominus-bootstrap-4.min.js"></script>
		<!-- Bootstrap select scripts -->
		<script
			src="webjars/bootstrap-select/1.13.1/dist/js/bootstrap-select.min.js"></script>
		<script
			src="webjars/bootstrap-select/1.13.1/dist/js/i18n/defaults-pt_BR.min.js"></script>
		<!-- Validação com Ajax -->
		<script src="resources/scripts/validacaoAjax.js"></script>
	</div>
</body>

</html>
