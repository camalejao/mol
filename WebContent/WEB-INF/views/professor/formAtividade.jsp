<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
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
					href="listarAtividades-${turmaDisciplina.id}">Lista de
						Atividades</a></li>
				<li class="breadcrumb-item active">Nova Atividade</li>
			</ol>
			<div class="row justify-content-center">
				<div class="col-8">
					<div class="card mb-3">
						<div class="card-header">
							<i class="fa fa-file-text"></i> Adicionar Atividade -
							${turmaDisciplina.turma.identificacao} -
							${turmaDisciplina.disciplina.nome}
						</div>
						<div class="card-body justify-content-center">
							<form:form modelAttribute="novaAtividade" onSubmit="verificaStatus();"
								action="cadastraAtividade" method="POST"
								enctype="multipart/form-data">

								<div class="form-group">
									<div class="form-row">
										<div class="col-md-12">
											<label for="inputTitulo">Título</label>
											<form:input path="titulo" class="form-control"
												id="inputTitulo" type="text" aria-describedby="tituloHelp"
												placeholder="Título da Atividade" />
											<form:errors path="titulo" cssClass="text-danger" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="form-row">
										<div class="col-md-12">
											<label for="datetimepicker">Data e Hora de Expiração</label>
											<div class="input-group date" id="datetimepicker"
												data-target-input="nearest">
												<form:input class="form-control" path="dataExpiracao"
													id="datetimepicker" data-target="#datetimepicker"
													type="text" placeholder="dd/MM/aaaa HH:mm" />
												<div class="input-group-append"
													data-target="#datetimepicker" data-toggle="datetimepicker">
													<div class="input-group-text">
														<i class="fa fa-calendar"></i>
													</div>
												</div>
												<form:errors path="dataExpiracao" cssClass="text-danger" />
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="form-row">
										<div class="col-md-12">
											<label for="inputDescricao">Descrição</label>
											<form:textarea class="form-control" path="descricao"
												id="inputDescricao" rows="3" type="text" />
											<form:errors path="descricao" cssClass="text-danger" />
										</div>
									</div>
								</div>
								<div id="divUploadArquivo" class="form-group">
									<div class="form-row">
										<div class="col-md-12">
											<label for="uploadArquivo">Arquivo Anexo</label>
											<form:input class="form-control-file" path="upload"
												id="uploadArquivo" type="file" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="form-row">
										<div class="col-md-6">
											<label for="selectNivelAprendizagem">Nível de Aprendizagem</label>
											<form:select path="nivelAprendizagem"
												class="form-control" id="selectNivelAprendizagem">
												<c:forEach var="n" begin="1" end="${turmaDisciplina.quantidadeNiveis + 1}" >
													<form:option value="${n}">Nível ${n}</form:option>
												</c:forEach>
											</form:select>
										</div>
										<div class="col-md-2">
											<label for="selectUnidade">Unidade</label>
											<form:select path="unidade" class="form-control"
												id="selectUnidade">
												<c:forEach items="${unidades}" var="unidade">
													<form:option value="${unidade}">${unidade.unidade}</form:option>
												</c:forEach>
											</form:select>
										</div>
										<form:input path="valorMaximo" value="0" hidden="true" />
										<div class="col-md-4">
											<label for="selectStatus">Status</label>
											<form:select path="status" class="form-control"
												id="selectStatus">
												<c:forEach items="${status}" var="s">
													<form:option value="${s}">${s}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
								</div>
								<div class="form-check">
									<form:checkbox path="mudancaNivel" id="checkMudancaNivel" class="form-check-input" />
									<label class="form-check-label mb-3" for="checkMudancaNivel">Mudança de Nível
										<span data-toggle="tooltip" data-placement="top" title="Ao responder essa atividade, o aluno poderá passar de nível.">
											<i class="fa fa-question-circle" aria-hidden="true"></i>
										</span>
									</label>
								</div>
								
								<form:select hidden="true" path="turmaDisciplina"
									class="form-control" id="selectTurmaDisciplina">
									<form:option value="${turmaDisciplina.id}">${turmaDisciplina.turma.identificacao} / ${turmaDisciplina.disciplina.sigla}</form:option>
								</form:select>
								
								<div class="form-group">
									<div class="form-row">
										<div class="col-md-6">
											<label for="selectTipoSubmissao">Tipo de Submissão
												<span data-toggle="tooltip" data-placement="top" title="Arquivo: o aluno enviará a resposta via arquivo;
													Itens Online: o aluno responderá questões cadastradas no sistema pelo professor.">
													<i class="fa fa-question-circle" aria-hidden="true"></i>
												</span>
											</label>
											<form:select path="tipoSubmissao"
												class="form-control" id="selectTipoSubmissao">
												<c:forEach items="${tipoSubmissao}" var="ts" >
													<form:option value="${ts}">${ts.tipoSubmissao}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
								</div>
								<button class="btn btn-primary btn-block" type="submit">Adicionar
									Atividade</button>
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
		<!-- Tempus Dominus scripts -->
		<script src="webjars/momentjs/2.22.2/min/moment-with-locales.min.js"></script>
		<script
			src="webjars/tempusdominus-bootstrap-4/5.0.0/js/tempusdominus-bootstrap-4.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$('#datetimepicker').datetimepicker({
					minDate : new Date(),
					locale : 'pt-br',
					sideBySide : true
				});

			});
			
			$(function () {
				  $('[data-toggle="tooltip"]').tooltip()
			});
		</script>
	</div>
</body>
</html>