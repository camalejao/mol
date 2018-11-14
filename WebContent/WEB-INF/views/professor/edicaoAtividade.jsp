<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>MOL - Editar Atividades</title>
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
				<li class="breadcrumb-item"><a href="listarTurmas">Minhas
						Turmas</a></li>
				<li class="breadcrumb-item"><a
					href="listarAtividades-${atividade.turmaDisciplina.id}">Lista
						de Atividades</a></li>
				<li class="breadcrumb-item active">Editar Atividade</li>
			</ol>
			<div class="row">
				<div class="col-6">
					<div class="card mb-3">
						<div class="card-header">
							<i class="fa fa-file-text"></i> Informações Gerais
							<c:if test="${atividade.mudancaNivel == true}">
								<span class="badge badge-primary">Mudança de nível</span>
							</c:if>
						</div>
						<ul class="list-group list-group-flush">
							<li class="list-group-item">
								<form method="POST" action="liberarAtividade"
									onSubmit="return confirm('Após ser liberada para resposta, esta atividade não poderá mais ser alterada. Prosseguir?');">
									<input hidden="true" name="atividade" value="${atividade.id}" />
									<label for="statusAtividade">Status da Atividade
										<span data-toggle="tooltip" data-placement="top" title="Em Construção: Editável, 
											não disponível para os alunos; Liberada para Resposta: Não editável, alunos já podem responder.">
											<i class="fa fa-question-circle" aria-hidden="true"></i>
										</span>
									</label>
									<div class="form-group">
										<div class="card border-secondary mb-3">
											<div class="card-body">
												<h6>${atividade.statusAtividade.statusAtividade}</h6>
											</div>
										</div>
										<c:choose>
											<c:when test="${atividade.valorMaximo > 0}">
												<button class="btn btn-sm btn-primary">Liberar para Resposta</button>
											</c:when>
											<c:otherwise>
												<button disabled class="btn btn-sm btn-primary">Liberar para Resposta</button>
												<span data-toggle="tooltip" data-placement="top" title="Para liberar uma atividade,
													é necessário que o seu valor seja maior que zero. Por favor cadastre itens ou defina o valor
													da submissão por arquivo.">
													<i class="fa fa-question-circle" aria-hidden="true"></i>
												</span>
											</c:otherwise>
										</c:choose>
									</div>
								</form>
							</li>
							<li class="list-group-item">
								<form:form modelAttribute="atividade"
									action="editaAtividade-${atividade.id}" method="POST"
									enctype="multipart/form-data">
									<div class="form-group">
										<div class="form-row">
											<div class="col-md-12">
												<label for="inputTitulo">Título</label>
												<form:input path="titulo" class="form-control"
													id="inputTitulo" type="text" aria-describedby="tituloHelp"
													value="${atividade.titulo}"
													placeholder="Título da Atividade" />
											</div>
	
										</div>
									</div>
									<div class="form-group">
										<div class="form-row">
											<form:input hidden="true" id="inputTurmaDisciplina" path="turmaDisciplina"
													value="${atividade.turmaDisciplina.id}" />
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
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="inputDescricao">Descrição</label>
										<form:textarea value="${atividade.descricao}"
											class="form-control" path="descricao" id="inputDescricao"
											rows="3" type="text" />
									</div>
									<div class="form-group">
										<label for="uploadArquivo">Arquivo Anexo</label>
										<form:input class="form-control-file" path="upload"
											id="uploadArquivo" type="file" />
									</div>
									<div class="form-group">
										<div class="form-row">
											<div class="col-md-6">
												<label for="inputNivelAprendizagem">Nível de Aprendizagem</label>
												<input class="form-control" disabled id="inputNivelAprendizagem"
												value="Nível ${atividade.nivelAprendizagem}" />
											</div>
											<div class="col-md-2">
												<label for="selectUnidade">Unidade</label>
												<form:select path="unidade" class="form-control"
													id="selectUnidade" value="${atividade.unidade}">
													<c:forEach items="${unidades}" var="unidade">
														<form:option value="${unidade}">${unidade.unidade}</form:option>
													</c:forEach>
												</form:select>
											</div>
											<div class="col-md-4">
												<label for="selectStatus">Status</label>
												<form:select path="status" class="form-control"
													id="selectStatus" value="${atividade.status}">
													<c:forEach items="${status}" var="s">
														<form:option value="${s}">${s}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
									</div>
									<div>
										<label for="selectTipoSubmissao">Tipo de Submissão
											<span data-toggle="tooltip" data-placement="top" title="Arquivo: o aluno enviará a resposta via arquivo;
												Itens Online: o aluno responderá questões cadastradas no sistema pelo professor.">
												<i class="fa fa-question-circle" aria-hidden="true"></i>
											</span>
										</label>
										<input class="form-control mb-3" disabled id="inputTipoSubmissao"
												value="${atividade.tipoSubmissao.tipoSubmissao}" />
									</div>
									<button class="btn btn-primary btn-block" type="submit">Salvar</button>
								</form:form>
							</li>
						</ul>
					</div>
				</div>
				<c:if test="${atividade.tipoSubmissao == 'ARQUIVO'}">
					<div class="col-6">
						<div class="card mb-3">
							<div class="card-header">
								<i class="fa fa-file-text"></i> Arquivo de Resposta
							</div>
							<ul class="list-group list-group-flush">
								<li class="list-group-item">
									<h5>Valor Total: ${atividade.valorMaximo} ponto(s)</h5></li>
								<li class="list-group-item">
									<form method="POST" action="valorArquivo">
										<label for="valorArquivo">Valor do arquivo de resposta</label>
										<input id="valorArquivo" name="valor" class="form-control mb-3"
											value="${atividade.valorMaximo}" type="number" step="0.01" min="0" />
										<input hidden="true" name="atividade" value="${atividade.id}" />
										<button class="btn btn-primary" type="submit">Salvar</button>
									</form>	
								</li>
							</ul>
						</div>
					</div>
				</c:if>
				<c:if test="${atividade.tipoSubmissao == 'ITENS'}">
					<div class="col-6">
						<div class="card mb-3">
							<div class="card-header">
								<i class="fa fa-file-text"></i> Itens
							</div>
							<ul class="list-group list-group-flush">
								<li class="list-group-item">
									<div>
										<a class="btn btn-primary" href="" data-toggle="modal"
											data-target="#itemDiscursivoModal"
											onclick="itemDiscursivo(${atividade.id},event)"><i
												class="fa fa-plus-circle"></i> Item Discursivo</a>
											<a class="btn btn-primary" href=""
											data-toggle="modal" data-target="#itemMEModal"
											onclick="itemMultiplaEscolha(${atividade.id},event)"><i
											class="fa fa-plus-circle"></i> Item de Múltipla Escolha</a>
									</div>
								</li>
								<li class="list-group-item">
									<h5>Valor Total: ${atividade.valorMaximo} ponto(s)</h5>
								</li>
								<c:forEach items="${itens}" var="item" varStatus="index">
									<li class="list-group-item">
										<div class="row">
											<div class="col-md-10">
												<h5 style="white-space:pre-wrap">${index.index + 1}. ${item.enunciado} (${item.valor})</h5>
											</div>
											<c:choose>
												<c:when test="${item.tipoItem == 'MULTIPLA_ESCOLHA'}">
													<div class="col-md-2">
														<div class="row">
															<button class="btn btn-sm btn-secondary"
																data-toggle="modal" data-target="#editaItemMEModal" title="Editar"
																onclick="editaItemME(${atividade.id},${item.id},'${item.enunciado}',${item.valor},event)">
																<i class="fa fa-pencil-square" aria-hidden="true"></i>
															</button>
															&nbsp;
															<form action="excluirItem" method="POST"
																onsubmit="return confirm('Confirma a exclusão?');">
																<input name="item" value="${item.id}" type="text"
																	hidden="true" />
																<button class="btn btn-sm btn-danger" title="Excluir" type="submit">
																	<i class="fa fa-trash" aria-hidden="true"></i>
																</button>
															</form>
														</div>
													</div>
												</c:when>
												<c:otherwise>
													<div class="col-md-2">
														<div class="row">
															<button class="btn btn-sm btn-secondary"
																data-toggle="modal" data-target="#editaIDModal" title="Editar"
																onclick="editaItemD(${atividade.id},${item.id},'${item.enunciado}',${item.valor},event)">
																<i class="fa fa-pencil-square" aria-hidden="true"></i>
															</button>
															&nbsp;
															<form action="excluirItem" method="POST"
																onsubmit="return confirm('Confirma a exclusão?');">
																<input name="item" value="${item.id}" type="text"
																	hidden="true" />
																<button class="btn btn-sm btn-danger" title="Excluir" type="submit">
																	<i class="fa fa-trash" aria-hidden="true"></i>
																</button>
															</form>
														</div>
													</div>
												</c:otherwise>
											</c:choose>
										</div> <c:choose>
											<c:when test="${item.tipoItem == 'MULTIPLA_ESCOLHA'}">
												<c:forEach items="${item.alternativas}" var="alt"
													varStatus="index2">
													<h6>
														<c:choose>
															<c:when test="${index2.index == 0}">A)</c:when>
															<c:when test="${index2.index == 1}">B)</c:when>
															<c:when test="${index2.index == 2}">C)</c:when>
															<c:when test="${index2.index == 3}">D)</c:when>
															<c:when test="${index2.index == 4}">E)</c:when>
														</c:choose>
														<c:out value="${alt.enunciado}" />
														<c:if test="${alt.correta == 'true'}">(correta)</c:if>
														<button class="btn btn-sm btn-link text-secondary" data-toggle="modal"
															data-target="#editaAlternativaModal" title="Editar"
															onclick="editaAlternativa(${item.id},${alt.id},'${alt.enunciado}','${alt.correta}',event)">
															<i class="fa fa-pencil-square" aria-hidden="true"></i></button>
													</h6>
												</c:forEach>
											</c:when>
										</c:choose>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</c:if>
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
		<!-- Modal Novo Item Discursivo -->
		<div class="modal fade" id="itemDiscursivoModal" tabindex="-1"
			role="dialog" aria-labelledby="itemDiscursivoModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="itemDiscursivoModalLabel">Adicionar
							Item Discursivo</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">

						<form:form action="adicionarItemDiscursivo" modelAttribute="item"
							method="POST" id="formItemD" >
							<div class="form-group">
								<label for="enunciadoDiscursivo">Enunciado</label>
								<form:textarea class="form-control" path="enunciado"
									maxlength="400" rows="3" type="text" id="enunciadoDiscursivo"
									placeholder="Digite o enunciado do item" />
							</div>
							<div class="form-group">
								<label for="inputValorD">Valor</label>
								<form:input path="valor" id="inputValorD" class="form-control"
									type="number" step="0.25" min="0.25" />

								<form:input path="atividade" id="idAtividadeItemDiscursivo"
									type="text" hidden="true" />

							</div>

							<div class="modal-footer">
								<button class="btn btn-secondary" type="button"
									data-dismiss="modal">Cancelar</button>
								<button class="btn btn-primary" type="submit">Salvar</button>
							</div>

						</form:form>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal EDITAR Item Discursivo -->
		<div class="modal fade" id="editaIDModal" tabindex="-1" role="dialog"
			aria-labelledby="editaIDModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editaIDModalLabel">Editar Item
							Discursivo</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">

						<form:form action="editarItem" modelAttribute="item" method="POST">
							<div class="form-group">

								<label for="enunciadoDiscursivo">Enunciado</label>
								<form:textarea class="form-control" path="enunciado"
									maxlength="400" rows="3" type="text"
									id="editaEnunciadoDiscursivo"
									placeholder="Digite o enunciado do item" />

								<label for="editValorD">Valor</label>
								<form:input path="valor" id="editValorD" class="form-control"
									type="number" step="0.25" min="0.25" />

								<form:input path="atividade" id="idEdicaoAtividadeID"
									type="text" hidden="true" />
								<form:input path="id" id="idEdicaoID" type="text" hidden="true" />

							</div>

							<div class="modal-footer">
								<button class="btn btn-secondary" type="button"
									data-dismiss="modal">Cancelar</button>
								<button class="btn btn-primary" type="submit">Salvar</button>
							</div>

						</form:form>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal Novo Item Múltipla Escolha -->
		<div class="modal fade" id="itemMEModal" tabindex="-1" role="dialog"
			aria-labelledby="itemMEModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="itemMEModalLabel">Adicionar Item
							de Múltipla Escolha</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">

						<form:form action="adicionarItemMultiplaEscolha"
							modelAttribute="item" method="POST" id="formItemME">

							<div class="form-group">
								<label for="enunciadoME">Enunciado</label>
								<form:textarea class="form-control" path="enunciado"
									maxlength="600" rows="3" type="text" id="enunciadoME"
									placeholder="Digite o enunciado do item" />
							</div>
							
							<div class="form-group">
								<label for="inputValorME">Valor</label>
								<form:input path="valor" id="inputValorME" class="form-control"
									type="number" step="0.25" min="0.25" />
							</div>

							<div class="form-group">
								<div class="form-row">
									<label for="alternativaA">A)</label>
									<form:input class="form-control"
										path="alternativas[0].enunciado" type="text" id="alternativaA"
										placeholder="Alternativa A" maxlength="255" />
								</div>

								<div class="form-row">
									<label for="alternativaB">B)</label>
									<form:input class="form-control"
										path="alternativas[1].enunciado" type="text" id="alternativaB"
										placeholder="Alternativa B" maxlength="255" />
								</div>

								<div class="form-row">
									<label for="alternativaC">C)</label>
									<form:input class="form-control"
										path="alternativas[2].enunciado" type="text" id="alternativaC"
										placeholder="Alternativa C" maxlength="255" />
								</div>

								<div class="form-row">
									<label for="alternativaD">D)</label>
									<form:input class="form-control"
										path="alternativas[3].enunciado" type="text" id="alternativaD"
										placeholder="Alternativa D" maxlength="255" />
								</div>
								<div class="form-row">
									<label for="alternativaE">E)</label>
									<form:input class="form-control"
										path="alternativas[4].enunciado" type="text" id="alternativaE"
										placeholder="Alternativa E" maxlength="255" />
								</div>
							</div>

							<div class="form-group" id="radioAlternativas" >
								<span>Selecione a alternativa correta:</span><br /> A
								<form:radiobutton id="alt1" path="alternativas[0].correta" value="true" />
								B
								<form:radiobutton id="alt2" path="alternativas[1].correta" value="true" />
								C
								<form:radiobutton id="alt3" path="alternativas[2].correta" value="true" />
								D
								<form:radiobutton id="alt4" path="alternativas[3].correta" value="true" />
								E
								<form:radiobutton id="alt5" path="alternativas[4].correta" value="true" />
							</div>

							<form:input path="atividade" id="idAtividadeItemME" type="text"
								hidden="true" />

							<div class="modal-footer">
								<button class="btn btn-secondary" type="button"
									data-dismiss="modal">Cancelar</button>
								<button class="btn btn-primary" type="submit">Salvar</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal EDITA Item Múltipla Escolha -->
		<div class="modal fade" id="editaItemMEModal" tabindex="-1"
			role="dialog" aria-labelledby="editaItemMEModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editaIMEModalLabel">Editar Item
							de Múltipla Escolha</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<form:form action="editarItem" modelAttribute="item"
							method="POST">
							<div class="form-group">
								<label for="editaEnunciadoME">Enunciado</label>
								<form:textarea class="form-control" path="enunciado"
									maxlength="600" rows="3" type="text" id="editaEnunciadoME"
									placeholder="Digite o enunciado do item" />

								<label for="editValorME">Valor</label>
								<form:input id="editValorME" path="valor" class="form-control"
									type="number" step="0.01" min="0.25" />
							</div>
							<form:input path="atividade" id="idEdicaoAtividadeME" type="text"
								hidden="true" />
							<form:input path="id" id="idEdicaoME" type="text" hidden="true" />
							<div class="modal-footer">
								<button class="btn btn-secondary" type="button"
									data-dismiss="modal">Cancelar</button>
								<button class="btn btn-primary" type="submit">Salvar</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal EDITA Alternativa -->
		<div class="modal fade" id="editaAlternativaModal" tabindex="-1"
			role="dialog" aria-labelledby="editaAlternativaModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editaAlternativaModalLabel">Editar
							Alternativa</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<form:form action="editarAlternativa" modelAttribute="alternativa"
							method="POST">
							<div class="form-group">
								<label for="editaEnunciadoAlt">Enunciado</label>
								<form:input class="form-control" path="enunciado" type="text"
									id="editaEnunciadoAlt" placeholder="Digite a alternativa"
									maxlength="255" />
							</div>
							<form:input path="item" id="idEditaItemAlt" type="text"
								hidden="true" />
							<form:input path="id" id="idEditaAlt" type="text" hidden="true" />
							<div class="modal-footer">
								<button class="btn btn-secondary" type="button"
									data-dismiss="modal">Cancelar</button>
								<button class="btn btn-primary" type="submit">Salvar</button>
							</div>
						</form:form>
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
							<label for="inputEditarNome">Nome</label> <input class="form-control"
								id="inputEditarNome" name="nome" type="text" value="${sessionScope.usuarioLogado.nome}"
								placeholder="Digite o nome do Usuário" maxlength="50" />
						</div>
						<div class="form-group">
							<label for="inputEditarEmail">Email</label> <input class="form-control"
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
							<button class="btn btn-secondary btn-block" onClick="showDivAlterarSenha()">Alterar Senha</button>
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
		<script
			src="webjars/startbootstrap-sb-admin/4.0.0/js/sb-admin-datatables.min.js"></script>
		<!--  script validacao/edicao de dados do usuario -->
		<script src="resources/scripts/validacaoAjax.js"></script>
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
			})
		</script>
		<!-- scripts para CRUD Itens / Alternativas -->
		<script src="resources/scripts/validacaoFormItens.js"></script>
	</div>
</body>
</html>