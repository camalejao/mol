<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>MOL - Aluno</title>
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
					title="Atividades"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseAtividades" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-file-text "></i> <span class="nav-link-text">Atividades
					</span></a>
					<ul class="sidenav-second-level collapse" id="collapseAtividades">
						<li><a href="homeAluno">Minhas Atividades</a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Disciplinas"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseDisciplinas" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-book "></i> <span class="nav-link-text">Disciplinas
					</span></a>
					<ul class="sidenav-second-level collapse" id="collapseDisciplinas">
						<li><a href="disciplinas">Minhas Disciplinas</a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Dúvidas"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseDuvidas" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-question "></i> <span class="nav-link-text">Acompanhar Dúvidas
					</span></a>
					<ul class="sidenav-second-level collapse" id="collapseDuvidas">
						<li><a href="minhasDuvidas">Minhas Dúvidas</a></li>
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
				<li class="breadcrumb-item active">Página inicial</li>
			</ol>
			<div class="mb-0 mt-4">
				<i class="fa fa-book"></i> Disciplinas
			</div>
			<hr class="mt-2">
			<div class="row">
				<div class="col-12">
					<div class="card mb-3">
						<div class="list-group list-group-flush small">
							<c:choose>
								<c:when test="${not empty turmasDisc}">
									<c:forEach items="${turmasDisc}" var="td">
										<div class="list-group-item list-group-item-action">
											<div class="media">
												<div class="media-body">
													<h6 class="card-title mb-1">
														<strong>${td.turmaDisciplina.disciplina.nome} | Nível ${td.nivelAtual}/${td.turmaDisciplina.quantidadeNiveis}</strong>
													</h6>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div class="media">
										<div class="media-body">
											<div class="list-group-item list-group-item-action">
												<h6 class="card-title mb-1">Você não está cadastrado em
													nenhuma disciplina.</h6>
											</div>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>


			<div class="mb-0 mt-4">
				<i class="fa fa-file-text"></i> Atividades
			</div>
			<hr class="mt-2">
			<div class="row">
				<div class="col-12">
					<div class="card mb-3">
						<div class="card-header">Atividades Pendentes</div>
						<div class="list-group list-group-flush small">
							<c:choose>
								<c:when test="${not empty naoRespondidas}">
									<c:forEach items="${naoRespondidas}" var="nr">
										<div class="list-group-item list-group-item-action">
											<div class="media">
												<div class="media-body">
													<p class="card-title mb-1">
														<h6><strong>${nr.titulo}</strong> - Nível ${nr.nivelAprendizagem}
															<c:if test="${nr.mudancaNivel == true}">
																<span class="badge badge-primary">Mudança de nível</span>
															</c:if>
														</h6>
														<h6>Disciplina: ${nr.turmaDisciplina.disciplina.nome}</h6>
														<h6>Prazo:
															<fmt:parseDate value="${nr.dataExpiracao}"
																pattern="yyyy-MM-dd'T'HH:mm" var="prazo" type="both" />
															<fmt:formatDate value="${prazo}"
																pattern="dd/MM/yyyy HH:mm" />
														</h6>
														<c:choose>
															<c:when test="${nr.verificaExpiracao()}">
																<a href="responderAtividade-${nr.id}"
																	class="btn btn-sm btn-primary">Responder</a>
															</c:when>
															<c:otherwise>
																<span class="btn btn-sm btn-danger">Expirada</span>
															</c:otherwise>
														</c:choose>
														<a class="btn btn-secondary btn-sm"
															href="verDuvidas-${nr.id}"> Ver Dúvidas </a>
													</p>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div class="media">
										<div class="media-body">
											<div class="list-group-item list-group-item-action">
												<h6 class="card-title mb-1">Não há atividades
													pendentes.</h6>
											</div>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<div class="card mb-3">
						<div class="card-header">Atividades Respondidas</div>
						<div class="list-group list-group-flush small">
							<c:choose>
								<c:when test="${not empty respondidas}">
									<c:forEach items="${respondidas}" var="r">
										<div class="list-group-item list-group-item-action">
											<div class="media">
												<div class="media-body">
													<p class="card-title mb-1">
														<h6><strong>${r.titulo}</strong> - Nível ${r.nivelAprendizagem}
															<c:if test="${nr.mudancaNivel == true}">
																<span class="badge badge-primary">Mudança de nível</span>
															</c:if>
														</h6>
														<h6>Disciplina: ${r.turmaDisciplina.disciplina.nome}</h6>
														<h6>Prazo:
															<fmt:parseDate value="${r.dataExpiracao}"
																pattern="yyyy-MM-dd'T'HH:mm" var="prazo" type="both" />
															<fmt:formatDate value="${prazo}"
																pattern="dd/MM/yyyy HH:mm" />
														</h6>
														<h6>Data de envio: 
															<fmt:parseDate value="${r.dataCadastro}"
																pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
																type="both" />
															<fmt:formatDate value="${parsedDateTime}"
																pattern="dd/MM/yyyy HH:mm" />
														</h6>
														<c:choose>
															<c:when test="${r.verificaExpiracao()}">
																<a href="responderAtividade-${r.id}"
																	class="btn btn-sm btn-primary">Responder Novamente
																</a>
															</c:when>
														</c:choose>
														<a class="btn btn-secondary btn-sm"
															href="verDuvidas-${nr.id}"> Ver Dúvidas </a>
													</p>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div class="media">
										<div class="media-body">
											<div class="list-group-item list-group-item-action">
												<h6 class="card-title mb-1">Não há atividades
													respondidas.</h6>
											</div>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
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
	</div>
</body>

</html>