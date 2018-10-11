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
				<li class="breadcrumb-item"><a href="listarTurmas">Minhas Turmas</a></li>
				<li class="breadcrumb-item active">Lista de Atividades</li>
			</ol>
			<div class="mb-0 mt-4">
				<i class="fa fa-file-text"></i> Atividades -
				${td.turma.identificacao} - ${td.disciplina.nome}
			</div>
			<hr class="mt-2">
			<div class="mb-3">
				<a class="btn btn-primary" href="adicionarAtividade-${td.id}">Adicionar
					Nova Atividade</a>
			</div>
			<div class="card mb-3">
				<div class="card-header">
					<i class="fa fa-file-text"></i> Lista de Atividades
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>Título</th>
									<th>Arquivo</th>
									<th>Status</th>
									<th>Un.</th>
									<th>Nível</th>
									<th>Data Expiração</th>
									<th>Ações</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>Título</th>
									<th>Arquivo</th>
									<th>Status</th>
									<th>Un.</th>
									<th>Nível</th>
									<th>Data Expiração</th>
									<th>Ações</th>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach items="${atividades}" var="atividade">
									<tr>
										<td>${atividade.titulo}</td>
										<c:choose>
											<c:when test="${not empty atividade.nomeDocumento}">
												<td>${atividade.nomeDocumento}<a
													class="btn btn-secondary btn-sm"
													href="downloadDocumento-${atividade.id}"> <i
														class="fa fa-download "></i> Baixar
												</a></td>
											</c:when>
											<c:otherwise>
												<td>-</td>
											</c:otherwise>
										</c:choose>
										<td>${atividade.statusAtividade.statusAtividade}</td>
										<td>${atividade.unidade.unidade}</td>
										<td>${atividade.nivelAprendizagem}</td>
										<fmt:parseDate value="${atividade.dataExpiracao}"
											pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
										<td><fmt:formatDate value="${parsedDateTime}"
												pattern="dd/MM/yyyy HH:mm" /></td>
										<td>
											<a class="btn btn-secondary btn-sm" title="Editar"
												href="editarAtividade-${atividade.id}"> <i class="fa fa-pencil-square "></i></a>
											<a class="btn btn-secondary btn-sm" href="respostasAtividade-${atividade.id}">
												Ver Respostas </a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="card mb-3">
				<div class="card-header">
					<span>Níveis</span>
				</div>
				<div class="card-body">
					<c:forEach var="n" begin="1" end="${td.quantidadeNiveis}">
						<a href="#nivel-${n}" class="btn btn-outline-primary">${n}</a>
					</c:forEach>
				</div>
			</div>
			<c:forEach var="n" begin="1" end="${td.quantidadeNiveis}">
				<div class="card mb-3" id="nivel-${n}">
					<div class="card-header">
						<h5>Nível ${n}</h5>
					</div>
					<ul class="list-group">
						<c:forEach items="${atividades}" var="atividade">
							<c:if test="${atividade.nivelAprendizagem == n}">
								<li class="list-group-item">
									<p>${atividade.titulo} (${atividade.statusAtividade.statusAtividade}) - 
										<c:if test="${not empty atividade.nomeDocumento}">
											<a class="btn btn-secondary btn-sm" title="Download Arquivo"
												href="downloadDocumento-${atividade.id}"> <i
												class="fa fa-download"></i></a>
										</c:if>
										<a class="btn btn-secondary btn-sm" title="Editar"
											href="editarAtividade-${atividade.id}"> <i class="fa fa-pencil-square "></i></a>
										<a class="btn btn-secondary btn-sm" href="respostasAtividade-${atividade.id}">
											Ver Respostas </a>
									</p>
									<fmt:parseDate value="${atividade.dataExpiracao}"
											pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
									<h6>Prazo: <fmt:formatDate value="${parsedDateTime}" pattern="dd/MM/yyyy HH:mm" /></h6>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</c:forEach>

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
		<!-- Page level plugin JavaScript-->
		<script
			src="webjars/startbootstrap-sb-admin/4.0.0/vendor/datatables/jquery.dataTables.js"></script>
		<script
			src="webjars/startbootstrap-sb-admin/4.0.0/vendor/datatables/dataTables.bootstrap4.js"></script>
		<!-- Custom scripts for all pages-->
		<script src="webjars/startbootstrap-sb-admin/4.0.0/js/sb-admin.min.js"></script>
		<!-- Custom scripts for this page-->
		<script src="resources/scripts/datatables-PT-BR.js"></script>
	</div>
</body>
</html>