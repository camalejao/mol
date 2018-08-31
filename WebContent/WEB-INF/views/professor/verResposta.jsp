<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<title>MOL - Ver Resposta</title>
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
				<li class="breadcrumb-item"><a
					href="listarAtividades-${resposta.atividade.turmaDisciplina.id}">Lista
						de Atividades</a></li>
				<li class="breadcrumb-item"><a
					href="respostasAtividade-${resposta.atividade.id}">Lista de
						Respostas</a></li>
				<li class="breadcrumb-item active">Visualizar Resposta</li>
			</ol>
			<div class="row">
				<div class="col-md-6">
					<div class="card mb-3">
						<div class="card-header">
							<i class="fa fa-file-text"></i> Resposta -
							${resposta.atividade.titulo}
						</div>
						<div class="card-body">
							<div class="media">
								<div class="media-body">
									<h4 class="card-title mb-1"></h4>
									<h6 class="card-title mb-1">
										<strong>Aluno: </strong> ${resposta.aluno.nome}
									</h6>
									<h6 class="card-title mb-1">
										<strong>Data de Envio: </strong>
										<fmt:parseDate value="${resposta.atividade.dataCadastro}"
											pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
										<fmt:formatDate value="${parsedDateTime}"
											pattern="dd/MM/yyyy HH:mm" />
									</h6>
									<label for="comentariosResp"><strong>Comentários
											do Aluno: </strong></label>
									<p id="comentariosResp">${resposta.comentarios}</p>
									<label for="doc"><strong>Arquivo de resposta:
									</strong></label>
									<c:choose>
										<c:when test="${not empty resposta.nomeDocumentoResposta}">
											<p id="doc">${resposta.nomeDocumentoResposta}
												<a class="btn btn-secondary btn-sm"
													href="downloadResposta-${resposta.id}"> <i
													class="fa fa-download "></i> Baixar
												</a>
											</p>
										</c:when>
										<c:otherwise>
											<p id="doc">Não enviado.</p>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="media">
								<div class="media-body"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="card mb-3">
						<div class="card-header">Avaliar Resposta</div>
						
						<ul class="list-group list-group-flush">
								<c:forEach items="${itensResp}" var="ir" varStatus="index" >
									<li class="list-group-item">
										<h5>${index.index + 1}. ${ir.item.enunciado}</h5>
										<c:choose>
											<c:when test="${ir.item.tipoItem == 'DISCURSIVO'}">
												<h6>${ir.texto}</h6>
											</c:when>
											<c:otherwise>
												<h6>${ir.alternativa.enunciado}</h6>
												<c:if test="${ir.alternativa.correta == true}">
													<h6>(correta)</h6>
												</c:if>
											</c:otherwise>
										</c:choose>
									</li>	
								</c:forEach>
							</ul>
						<div class="card-body">
							<form:form modelAttribute="resposta" action="avaliarResposta"
								method="POST">
								<div class="form-group">
									<div class="form-row">
										<div class="col-md-10">
											<form:select hidden="true" path="id" class="form-control"
												id="inputResp">
												<form:option value="${resposta.id}">${resposta.aluno.nome}</form:option>
											</form:select>
										</div>
									</div>
									<div class="form-row">
										<div class="col-md-2">
											<label for="inputNota">Nota</label>
											<form:input path="nota" class="form-control" id="inputNota"
												type="number" min="0"
												max="${resposta.atividade.valorMaximo}"
												aria-describedby="notaHelp" placeholder="" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="form-row">
										<label for="inputObs">Observações</label>
										<form:textarea path="observacoesProfessor"
											class="form-control" id="inputObs" type="text" rows="3"
											aria-describedby="obsHelp"
											placeholder="Observações sobre a resposta" />
									</div>
								</div>
								<c:choose>
									<c:when test="${not resposta.atividade.verificaExpiracao()}">
										<button class="btn btn-primary btn-block" type="submit">Avaliar</button>
									</c:when>
								</c:choose>
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