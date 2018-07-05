<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="pt-br">

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
					title="Cadastros"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseCadastros" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-file-text "></i> <span class="nav-link-text">Atividades
					</span></a>
					<ul class="sidenav-second-level collapse" id="collapseCadastros">
						<li><a href="gerenciarAtividades">Gerenciar Atividades</a></li>
					</ul></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Listagens"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseListas" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-table"></i> <span class="nav-link-text">Listagens</span></a>
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
				<li class="breadcrumb-item"><a href="home">Página
						Inicial</a></li>
				<li class="breadcrumb-item active">Gerenciar Atividades</li>
			</ol>
			<div class="card mb-3">
				<div class="card-header">
					<i class="fa fa-file-text"></i> Adicionar Atividade
				</div>
				<div class="card-body">
					<form:form modelAttribute="novaAtividade"
						action="cadastraAtividade" method="POST"
						enctype="multipart/form-data">
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-3">
									<label for="inputTitulo">Título</label>
									<form:input path="titulo" class="form-control" id="inputTitulo"
										type="text" aria-describedby="tituloHelp"
										placeholder="Título da Atividade" />
								</div>
								<div class="col-md-1">
									<label for="inputvalorMaximo">Valor</label>
									<form:input path="valorMaximo" class="form-control"
										id="inputvalorMaximo" type="number" step="0.25" min="0.25"
										aria-describedby="valorMaximoHelp" placeholder="Ex: 5" />
								</div>
								<div class="col-md-1">
									<label for="inputPeso">Peso</label>
									<form:input path="peso" class="form-control" id="inputPeso"
										type="number" min="1" max="10" aria-describedby="pesoHelp"
										placeholder="Ex: 2" />
								</div>
								<div class="col-md-1">
									<label for="selectUnidade">Unidade</label>
									<form:select path="unidade" class="form-control"
										id="selectUnidade">
										<c:forEach items="${unidades}" var="unidade">
											<form:option value="${unidade}">${unidade.unidade}</form:option>
										</c:forEach>
									</form:select>
								</div>
								<div class="col-md-2">
									<label for="selectNivel">Nível de Aprendizagem</label>
									<form:select path="nivel" class="form-control" id="selectNivel">
										<c:forEach items="${niveis}" var="n">
											<form:option value="${n}">${n.nivel}</form:option>
										</c:forEach>
									</form:select>
								</div>
								<div class="col-md-2">
									<label for="selectStatus">Status</label>
									<form:select path="status" class="form-control" id="selectStatus">
										<c:forEach items="${status}" var="s">
											<form:option value="${s}">${s}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<label for="selectTurmaDisciplina">Turma/Disciplina</label>
									<form:select path="turmaDisciplina.id" class="form-control"
										id="selectTurmaDisciplina">
										<c:forEach items="${turmaDisciplinas}" var="td">
											<form:option value="${td.id}">${td.turma.identificacao} / ${td.disciplina.sigla}</form:option>
										</c:forEach>
									</form:select>
								</div>
								<div class="col-md-3">
									<label for="inputData">Data e Hora de Expiração</label>
									<form:input class="form-control" path="dataExpiracao"
										id="inputData" type="text" placeholder="dd/MM/aaaa HH:mm" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<label for="inputDescricao">Descrição</label>
									<form:textarea class="form-control" path="descricao"
										id="inputDescricao" rows="3" type="text" />
								</div>
								<div class="col-md-6">
									<label for="uploadArquivo">Selecione o arquivo</label>
									<form:input class="form-control-file" path="upload"
										id="uploadArquivo" type="file" />
								</div>
							</div>
						</div>
						<button class="btn btn-primary btn-block" type="submit">Adicionar</button>
					</form:form>
				</div>
			</div>
			<div class="card mb-3">
				<div class="card-header">
					<i class="fa fa-file-text"></i> Editar Atividades
				</div>
				<div class="card-body">
					<div class="table-responsive">

						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>Título</th>
									<th>Turma/Disciplina</th>
									<th>Arquivo</th>
									<th>Status</th>
									<th>Unidade</th>
									<th>Nível</th>
									<th>Data Expiração</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>Título</th>
									<th>Turma/Disciplina</th>
									<th>Arquivo</th>
									<th>Status</th>
									<th>Unidade</th>
									<th>Nível</th>
									<th>Data Expiração</th>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach items="${atividades}" var="atividade">
									<tr>
										<td>${atividade.titulo} <a
											class="btn btn-secondary btn-sm" href="editarAtividade-${atividade.id}"> <i
												class="fa fa-pencil-square "></i> Editar
										</a> <a
											class="btn btn-secondary btn-sm" href="respostasAtividade-${atividade.id}">Ver Respostas
										</a>
										</td>
										<td>${atividade.turmaDisciplina.turma.identificacao} -
											${atividade.turmaDisciplina.disciplina.sigla}</td>
										<c:choose>
											<c:when test="${not empty atividade.nomeDocumento}">
												<td>${atividade.nomeDocumento} <a
													class="btn btn-secondary btn-sm"
													href="downloadDocumento-${atividade.id}"> <i
														class="fa fa-download "></i> Baixar
												</a></td>
											</c:when>
											<c:otherwise>
												<td>-</td>
											</c:otherwise>
										</c:choose>
										<td>${atividade.status}</td>
										<td>${atividade.unidade.unidade}</td>
										<td>${atividade.nivel.nivel}</td>
										<fmt:parseDate value="${atividade.dataExpiracao}"
											pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
										<td><fmt:formatDate value="${parsedDateTime}"
												pattern="dd/MM/yyyy HH:mm" /></td>
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
		<script
			src="webjars/startbootstrap-sb-admin/4.0.0/js/sb-admin-datatables.min.js"></script>
	</div>
</body>
</html>