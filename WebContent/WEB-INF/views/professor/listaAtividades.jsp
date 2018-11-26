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
					<span>Níveis</span>
				</div>
				<div class="card-body">
					<c:forEach var="n" begin="1" end="${td.quantidadeNiveis}">
						<a href="#nivel-${n}" class="btn btn-outline-primary">${n}</a>
					</c:forEach>
				</div>
			</div>
			<jsp:useBean id="alertas" class="java.util.HashMap"/>
			<c:forEach var="n" begin="1" end="${td.quantidadeNiveis}">
				<c:set target="${alertas}" property="${n}" value="true" />
			</c:forEach>
			<c:forEach var="n" begin="1" end="${td.quantidadeNiveis}">
				<div class="card mb-3" id="nivel-${n}">
					<div class="card-header">
						<h5>Nível ${n} &nbsp; 
							<span id="alertaMudancaNivel-${n}" class="badge badge-warning" hidden="true">
								Não há atividades de mudança de nível!
							</span>
						</h5>
					</div>
					<ul class="list-group">
						<c:forEach items="${atividades}" var="atividade">
							<c:if test="${atividade.nivelAprendizagem == n}">
								<li class="list-group-item">
									<p><strong>${atividade.titulo}</strong>
										<c:if test="${atividade.statusAtividade == 'LIBERADA'}">
											<span class="badge badge-primary">${atividade.statusAtividade.statusAtividade}</span>
										</c:if>
										<c:if test="${atividade.statusAtividade == 'CONSTRUCAO'}">
											<span class="badge badge-warning">${atividade.statusAtividade.statusAtividade}</span>
										</c:if>
										<c:if test="${atividade.mudancaNivel == true}">
											<span class="badge badge-primary">Mudança de nível</span>
										</c:if>
										<h6>Unidade ${atividade.unidade.unidade}</h6>
										<fmt:parseDate value="${atividade.dataExpiracao}"
											pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
										<h6>Prazo: <fmt:formatDate value="${parsedDateTime}" pattern="dd/MM/yyyy HH:mm" /></h6>
									</p>
									<p>
										<a class="btn btn-secondary btn-sm" title="Editar"
											href="editarAtividade-${atividade.id}"> <i class="fa fa-pencil-square "></i></a>
										<a class="btn btn-secondary btn-sm" href="respostasAtividade-${atividade.id}">
											Ver Respostas </a>
										<a class="btn btn-secondary btn-sm" href="duvidasAtividade-${atividade.id}">
											Ver Dúvidas </a>
										<c:if test="${not empty atividade.nomeDocumento}">
											<a class="btn btn-secondary btn-sm" title="Download Arquivo"
												href="downloadDocumento-${atividade.id}"> <i
												class="fa fa-download"></i></a>
										</c:if>
										<a class="btn btn-secondary btn-sm" href="graficoAtividade-${atividade.id}">
											Ver Gráfico</a>
									</p>
								</li>
							</c:if>
							<c:if test="${atividade.mudancaNivel == true && atividade.nivelAprendizagem == n}">
								<c:set target="${alertas}" property="${n}" value="false" />
							</c:if>
						</c:forEach>
					</ul>
					<c:forEach items="${alertas}" var="alerta">
						<c:if test="${alerta.key == n && alerta.value == true}">
							<div id="${n}" title="alerta"></div>
						</c:if>
					</c:forEach>
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
		<!--  script validacao/edicao de dados do usuario -->
		<script src="resources/scripts/validacaoAjax.js"></script>
		<!-- Custom scripts for this page-->
		<script src="resources/scripts/datatables-PT-BR.js"></script>
		<script type="text/javascript">
			$(function(){
				$("div[title='alerta']").each(function(){
					var n = $(this).attr("id");
					$("#alertaMudancaNivel-"+n).attr("hidden", false);
				});
			})
		</script>
	</div>
</body>
</html>