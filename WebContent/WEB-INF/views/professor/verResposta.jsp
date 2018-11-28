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
					href="listarAtividades-${resposta.atividade.turmaDisciplina.id}">Lista
						de Atividades</a></li>
				<li class="breadcrumb-item"><a
					href="respostasAtividade-${resposta.atividade.id}">Lista de
						Respostas</a></li>
				<li class="breadcrumb-item active">Visualizar Resposta</li>
			</ol>
			<div class="row">
				<div class="col-md-9">
					<div class="card mb-3">
						<div class="card-header">Avaliar Resposta</div>
						
						<ul class="list-group list-group-flush">
								<c:forEach items="${itensResp}" var="ir" varStatus="index" >
									<li class="list-group-item">
										<h5>${index.index + 1}. ${ir.item.enunciado} (${ir.item.valor})</h5>
										<c:choose>
											<c:when test="${ir.item.tipoItem == 'DISCURSIVO'}">
												<div class="card border-secondary mb-3">
													<div class="card-body">
														<h6>${ir.texto}</h6>
													</div>
												</div>
												<form method="POST" action="avaliarItem">
													<input hidden="true" name="resposta" value="${resposta.id}" type="text" />
													<input hidden="true" name="item" value="${ir.id}" />
													<div class="form-row">
														<div class="col-1">
															<label for="inputNotaItem">Nota: </label>
														</div>
														<div class="col-2">
															<input id="inputNotaItem" name="nota" value="${ir.nota}" class="form-control form-control-sm"
																type="number" step="0.01" min="0" max="${ir.item.valor}" />
														</div>
														<div class="col-md-6">
															<button class="btn btn-sm btn-primary">Avaliar Item</button>
														</div>
													</div>
												</form>
											</c:when>
											<c:otherwise>
												<div class="card border-secondary">
													<div class="card-body">
														<h6>${ir.alternativa.enunciado}</h6>
													</div>
												</div>
												<c:if test="${ir.alternativa.correta == true}">
													<h6><span class="text-success"><i class="fa fa-check" ></i> Correta</span>
														<span> - Nota: ${ir.nota}/${ir.item.valor}</span>
													</h6>
													
												</c:if>
												<c:if test="${ir.alternativa.correta == false}">
													<h6><span class="text-danger"><i class="fa fa-times" ></i> Errada</span>
														<span> - Nota: ${ir.nota}/${ir.item.valor}</span>
													</h6>
												</c:if>
											</c:otherwise>
										</c:choose>
									</li>	
								</c:forEach>
							<c:if test="${resposta.atividade.tipoSubmissao == 'ARQUIVO'}">
								<li class="list-group-item">
									<c:if test="${not empty resposta.nomeDocumentoResposta}">
										<h5>Arquivo de resposta</h5>
										<p id="doc">${resposta.nomeDocumentoResposta}
											<a class="btn btn-secondary btn-sm"
												href="downloadResposta-${resposta.id}"> <i
												class="fa fa-download "></i> Baixar
											</a>
										</p>
									</c:if>
									<form method="POST" action="avaliarArquivo">
										<input hidden="true" name="resposta" value="${resposta.id}" type="text" />
										<div class="form-row">
											<div class="col-1">
												<label for="inputNotaItem">Nota: </label>
											</div>
											<div class="col-2">
												<input id="inputNotaItem" name="nota" value="${resposta.nota}"
													class="form-control form-control-sm" type="number"
													step="0.01" min="0" max="${resposta.atividade.valorMaximo}" />
											</div>
											<div class="col-md-6">
												<button class="btn btn-sm btn-primary">Avaliar</button>
											</div>
										</div>
									</form>
								</li>
							</c:if>
							<li class="list-group-item">
								<form action="avaliarResposta" method="POST">
									<div class="form-group">
										<input hidden="true" name="resposta" class="form-control"
											id="inputResp" value="${resposta.id}" />
										<div class="form-row">
											<div class="col-md-6">
												<h5>Nota: <span id="nota">${resposta.nota}</span> / ${resposta.atividade.valorMaximo}</h5>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="form-row">
											<label for="inputObs">Observações</label>
											<textarea name="observacoes"
												class="form-control" id="inputObs" rows="3"
												placeholder="Observações sobre a resposta"></textarea>
										</div>
									</div>
									<c:choose>
										<c:when test="${not resposta.atividade.verificaExpiracao()}">
											<button class="btn btn-primary btn-block" type="submit">Enviar Avaliação</button>
										</c:when>
										<c:otherwise>
											<div class="row">
												<div class="col-md-10">
													<button disabled class="btn btn-primary btn-block">Enviar Avaliação</button>
												</div>
												<div class="col-md-2">
													<span class="btn btn-block btn-warning" data-toggle="tooltip" data-placement="top" title="A correção poderá ser enviada aos alunos
														somente após o término do prazo da atividade.">
														<i class="fa fa-question-circle" aria-hidden="true"></i>
													</span>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</form>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-md-3">
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
								</div>
							</div>
							<div class="media">
								<div class="media-body"></div>
							</div>
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
		<!--  script validacao/edicao de dados do usuario -->
		<script src="resources/scripts/validacaoAjax.js"></script>
	</div>
</body>
</html>