<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>MOL - Atividades</title>
<!-- Bootstrap core CSS-->
<link
	href="webjars/startbootstrap-sb-admin/4.0.0/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom fonts for this template-->
<link
	href="webjars/startbootstrap-sb-admin/4.0.0/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Page level plugin CSS-->
<link
	href="webjars/startbootstrap-sb-admin/4.0.0/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">
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
					title="Turmas"><a class="nav-link nav-link-collapse collapsed"
					data-toggle="collapse" href="#collapseListas"
					data-parent="#exampleAccordion"> <i class="fa fa-fw fa-table"></i>
						<span class="nav-link-text">Gerenciar Turmas</span></a>
					<ul class="sidenav-second-level collapse" id="collapseListas">
						<li><a href="listarTurmas">Minhas turmas</a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Dúvidas"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseDuvidas" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-question "></i> <span class="nav-link-text">Acompanhar
							Dúvidas </span></a>
					<ul class="sidenav-second-level collapse" id="collapseDuvidas">
						<li><a href="verDuvidas">Todas as Dúvidas</a></li>
					</ul></li>
			</ul>
			<ul class="navbar-nav sidenav-toggler">
				<li class="nav-item"><a class="nav-link text-center"
					id="sidenavToggler"> <i class="fa fa-fw fa-angle-left"></i>
				</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown show">
					<span class="nav-link dropdown-toggle mr-lg-2" data-toggle="dropdown">
						<c:out value="${sessionScope.usuarioLogado.nome}"/></span>
					<div class="dropdown-menu dropdown-menu-right">
						<h6 class="dropdown-header">${sessionScope.usuarioLogado.tipo.tipoUsuario}</h6>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="" data-toggle="modal" data-target="#editarDadosModal">
							<i class="fa fa-fw fa-id-card"></i> Editar dados
						</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="" data-toggle="modal" data-target="#exampleModal">
							<i class="fa fa-fw fa-sign-out"></i>Sair
						</a>
					</div>
				</li>
				<li class="nav-item"></li>
			</ul>
		</div>
	</nav>
	<div class="content-wrapper">
		<div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="home">Página Inicial</a></li>
				<li class="breadcrumb-item"><a href="listarTurmas">Minhas Turmas</a></li>
				<li class="breadcrumb-item"><a
					href="listarAtividades-${atividade.turmaDisciplina.id}">Lista
						de Atividades</a></li>
				<li class="breadcrumb-item active">Lista de Respostas</li>
			</ol>
			<div class="mb-0 mt-4">
				<i class="fa fa-file-text"></i> ${atividade.titulo} -
				${atividade.turmaDisciplina.turma.identificacao} -
				${atividade.turmaDisciplina.disciplina.nome}
			</div>
			<hr class="mt-2">
			<div class="card mb-3">
				<div class="card-header">
					<i class="fa fa-file-text"></i> Respostas
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>Aluno</th>
									<th>Data de Envio</th>
									<th>Status</th>
									<th>Resposta</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>Aluno</th>
									<th>Data de Envio</th>
									<th>Status</th>
									<th>Resposta</th>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach items="${respostas}" var="resp">
									<tr>
										<td>${resp.aluno.nome}</td>
										<fmt:parseDate value="${resp.dataCadastro}"
											pattern="yyyy-MM-dd'T'HH:mm" var="envio" type="both" />
										<td><fmt:formatDate value="${envio}"
												pattern="dd/MM/yyyy HH:mm" /></td>
										<td>${resp.statusResposta.statusResposta}</td>
										<td><a href="visualizarResposta-${resp.id}"
											class="btn btn-sm btn-secondary">Visualizar</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
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
		<!-- Page level plugin JavaScript-->
		<script
			src="webjars/startbootstrap-sb-admin/4.0.0/vendor/datatables/jquery.dataTables.js"></script>
		<script
			src="webjars/startbootstrap-sb-admin/4.0.0/vendor/datatables/dataTables.bootstrap4.js"></script>
		<!-- Custom scripts for all pages-->
		<script src="webjars/startbootstrap-sb-admin/4.0.0/js/sb-admin.min.js"></script>
		<!-- Custom scripts for this page-->
		<script src="resources/scripts/datatables-PT-BR.js"></script>
		<!--  script validacao/edicao de dados do usuario -->
		<script src="resources/scripts/validacaoAjax.js"></script>
	</div>
</body>
</html>