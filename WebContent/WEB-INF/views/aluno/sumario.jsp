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
					title="Atividades"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseDisciplinas" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-book "></i> <span class="nav-link-text">Disciplinas
					</span></a>
					<ul class="sidenav-second-level collapse" id="collapseDisciplinas">
						<li><a href="disciplinas">Minhas Disciplinas</a></li>
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
				<li class="breadcrumb-item"><a href="disciplinas">Disciplinas</a></li>
				<li class="breadcrumb-item active">Sumário Disciplina</li>
			</ol>
			<div class="mb-0 mt-4">
				<i class="fa fa-list-ul"></i> Sumário - ${turmaDisc.disciplina.nome}
			</div>
			<hr class="mt-2">
			<c:forEach items="${topicos}" var="t" varStatus="i">
				<div class="row justify-content-center">
					<div class="col-md-8">
						<div class="card mb-3">
							<h5 class="card-header">
								<i class="fa fa-info"></i> ${i.index + 1}. ${t.descricao}
							</h5>
							<ul class="list-group list-group-flush">
								<li class="list-group-item"><c:forEach
										items="${t.materiaisDidaticos}" var="material">
										<div class="mb-3 ml-4 pl-4">
											<c:choose>
												<c:when test="${material.tipo == 'SLIDE'}">
													<h6>
														<i class="fa fa-file-o"></i> <a href=""
															onclick="downloadMd(${material.id},event)">${material.titulo}</a>
													</h6>
													<form action="downloadMd" method="POST" id="${material.id}">
														<input type="hidden" name="md" value="${material.id}" />
													</form>
													<hr class="mt-0 mb-0">
													<p>${material.descricao}</p>
												</c:when>
												<c:when test="${material.tipo == 'APOSTILA'}">
													<h6>
														<i class="fa fa-book"></i> <a href=""
															onclick="downloadMd(${material.id},event)">${material.titulo}</a>
													</h6>
													<form action="downloadMd" method="POST" id="${material.id}">
														<input type="hidden" name="md" value="${material.id}" />
													</form>
													<hr class="mt-0 mb-0">
													<p>${material.descricao}</p>
												</c:when>
												<c:when test="${material.tipo == 'LINK'}">
													<h6>
														<i class="fa fa-link"></i> <a href="${material.link}"
															target="_blank">${material.titulo}</a>
													</h6>
													<hr class="mt-0 mb-0">
													<p>${material.descricao}</p>
												</c:when>
											</c:choose>
										</div>
									</c:forEach></li>
								<c:forEach items="${t.subtopicos}" var="subt" varStatus="j">
									<li class="list-group-item">
										<h5>
											<i class="fa fa-info"></i> ${i.index + 1}.${j.index + 1}.
											${subt.descricao}
										</h5>
										<hr class="mt-2"> <c:forEach
											items="${subt.materiaisDidaticos}" var="material">
											<div class="mb-3 ml-4 pl-4">
												<c:choose>
													<c:when test="${material.tipo == 'SLIDE'}">
														<h6>
															<i class="fa fa-file-o"></i> <a href=""
																onclick="downloadMd(${material.id},event)">${material.titulo}</a>
														</h6>
														<form action="downloadMd" method="POST"
															id="${material.id}">
															<input type="hidden" name="md" value="${material.id}" />
														</form>
														<hr class="mt-0 mb-0">
														<p>${material.descricao}</p>
													</c:when>
													<c:when test="${material.tipo == 'APOSTILA'}">
														<h6>
															<i class="fa fa-book"></i> <a href=""
																onclick="downloadMd(${material.id},event)">${material.titulo}</a>
														</h6>
														<form action="downloadMd" method="POST"
															id="${material.id}">
															<input type="hidden" name="md" value="${material.id}" />
														</form>
														<hr class="mt-0 mb-0">
														<p>${material.descricao}</p>
													</c:when>
													<c:when test="${material.tipo == 'LINK'}">
														<h6>
															<i class="fa fa-link"></i> <a href="${material.link}"
																target="_blank">${material.titulo}</a>
														</h6>
														<hr class="mt-0 mb-0">
														<p>${material.descricao}</p>
													</c:when>
												</c:choose>
											</div>
										</c:forEach>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
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
		<!-- Custom scripts for all pages-->
		<script src="webjars/startbootstrap-sb-admin/4.0.0/js/sb-admin.min.js"></script>
		<script>
			function downloadMd(id, e){
				$("#"+id).submit();
				e.preventDefault();
			}
		</script>
	</div>
</body>

</html>