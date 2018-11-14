<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
				<li class="breadcrumb-item active">Responder Atividade</li>
			</ol>
			<div class="row">
				<div class="col-12">
					<div class="card mb-3">
						<div class="card-header">
							<i class="fa fa-file-text"></i> Responder Atividade
						</div>
						<div class="card-body">
							<div class="media">
								<div class="media-body">
									<h4 class="card-title mb-1">${atividade.titulo}</h4>
									<h6 class="card-title mb-1">Disciplina:
										${atividade.turmaDisciplina.disciplina.nome}</h6>
									<h6 class="card-title mb-1">
										Prazo:
										<fmt:parseDate value="${atividade.dataExpiracao}"
											pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
										<fmt:formatDate value="${parsedDateTime}"
											pattern="dd/MM/yyyy HH:mm" />
									</h6>
									<label for="descricaoAtv">Descrição:</label>
									<p id="descricaoAtv">${atividade.descricao}</p>
									<c:if test="${not empty atividade.nomeDocumento}" >
										<label for="doc">Arquivo da atividade:</label>
										<p id="doc">${atividade.nomeDocumento}<a
											class="btn btn-secondary btn-sm"
											href="downloadAtividade-${atividade.id}"> <i
											class="fa fa-download "></i> Baixar
											</a>
										</p>
									</c:if>
								</div>
							</div>
							<div class="media">
								<div class="media-body">
									<form:form modelAttribute="resposta" action="enviarResposta"
										method="POST" enctype="multipart/form-data">
										<div class="form-group">
											<div class="form-row">
												<div class="col-md-6">
													<label for="inputObs">Comentários</label>
													<form:textarea class="form-control" path="comentarios"
														id="inputObs" rows="3" type="text" />
												</div>
												<c:if test="${atividade.tipoSubmissao == 'ARQUIVO'}">
													<div class="col-md-6">
														<label for="uploadArquivo">Selecione o arquivo de
															resposta </label>
														<form:input class="form-control-file" path="upload"
															id="uploadArquivo" type="file" />
														<a href="" class="btn btn-warning mt-2 mb-2" data-toggle="modal"
															data-target="#duvidaModal">Dúvida</a>
													</div>
												</c:if>
											</div>
										</div>
										<c:choose>
											<c:when test="${not empty itens && atividade.tipoSubmissao=='ITENS'}">
												<h5>Itens</h5>
												<c:forEach items="${itens}" var="item" varStatus="i">
													<c:forEach items="${respostaItens}" var="itemResp" varStatus="j">
														<c:if test="${item.id == itemResp.item.id}">
															<c:set var="respondido" scope="page" value="true"/>
														</c:if>
													</c:forEach>
													<c:choose>
														<c:when test="${respondido == true}">
															<a href="" class="btn btn-outline-success" data-toggle="modal"
																onclick="getItemResposta(${item.id}, ${sessionScope.usuarioLogado.id}, ${i.index+1}, event)"
																data-target="#itemModal">${i.index+1}</a>
														</c:when>
														<c:otherwise>
															<a href="" class="btn btn-outline-primary" data-toggle="modal"
																onclick="getItem(${item.id}, ${i.index+1}, event)"
																data-target="#itemModal">${i.index+1}</a>
														</c:otherwise>
													</c:choose>
													<c:set var="respondido" scope="page" value="false"/>
												</c:forEach>
											</c:when>
										</c:choose>
										<c:choose>
											<c:when test="${itens.size() == respostaItens.size() || atividade.tipoSubmissao=='ARQUIVO'}">
												<button class="btn btn-primary btn-block mt-5" type="submit">Finalizar Resposta</button>
											</c:when>
											<c:otherwise>
												<button class="btn btn-disabled btn-block mt-5" disabled type="submit">Finalizar Resposta</button>
											</c:otherwise>
										</c:choose>
									</form:form>
								</div>
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
		
		<!-- Duvida Modal-->
		<div class="modal fade" id="duvidaModal" tabindex="-1" role="dialog"
			aria-labelledby="duvidaModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="duvidaModalLabel">Dúvida</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<form:form modelAttribute="novaDuvida" action="adicionarDuvida" method="POST">
							<div class="form-group">
								<label for="inputDuvida">Descreva sua dúvida</label>
								<form:textarea id="inputDuvida" class="form-control" path="duvida" ></form:textarea>
							</div>
							<form:input value="${atividade.id}" hidden="true" path="item.atividade" />
							<form:input value="${atividade.descricao}" hidden="true" path="item.enunciado" />
							<form:input value="1" hidden="true" path="item.valor" />
							<div class="form-group">									<label for="selectVisibilidade">Visibilidade</label>
								<form:select path="visibilidade" id="selectVisibilidade" class="form-control">
									<c:forEach items="${visibilidade}" var="v">
										<form:option value="${v}">${v.visibilidadeDuvida}</form:option>
									</c:forEach>
								</form:select>
							</div>
							<div class="modal-footer">
								<form:button type="submit" class="btn btn-primary">Enviar Dúvida</form:button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Modal Responder Item -->
		<div class="modal fade" id="itemModal" tabindex="-1" role="dialog"
			aria-labelledby="itemModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="itemModalLabel">Item</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body" id="formItem">
						<h5 style="white-space:pre-wrap" id="enunciado"></h5>
						<form action="salvarRespostaItem" method="POST">
							<input id="idItem" name="item" type="text" value="" hidden="true" />
							<textarea id="respDisc" class="form-control" name="texto" ></textarea>
							<div id="alternativas">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="alternativa" id="alt0" value="A" />
									<label id="label0" class="form-check-label" for="alt0"></label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="alternativa" id="alt1" value="B" />
									<label id="label1" class="form-check-label" for="alt1"></label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="alternativa" id="alt2" value="C" />
									<label id="label2" class="form-check-label" for="alt2"></label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="alternativa" id="alt3" value="D" />
									<label id="label3" class="form-check-label" for="alt3"></label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="alternativa" id="alt4" value="E" />
									<label id="label4" class="form-check-label" for="alt4"></label>
								</div>
							</div>
							<div class="modal-footer">
								<button class="btn btn-secondary" type="button"
									data-dismiss="modal">Cancelar</button>
								<button class="btn btn-primary" type="submit">Salvar</button>
								<button onClick="mostrarFormDuvida(event)" class="btn btn-warning">Dúvida</button>
							</div>
						</form>
						<div id="formDuvida" hidden="true">
							<form:form modelAttribute="novaDuvida" action="adicionarDuvida" method="POST">
								<div class="form-group">
									<label for="inputDuvida">Descreva sua dúvida</label>
									<form:textarea id="inputDuvida" class="form-control" path="duvida" ></form:textarea>
								</div>
								<form:input id="itemDuvida" hidden="true" path="item" />
								<div class="form-group">
									<label for="selectVisibilidade">Visibilidade</label>
									<form:select path="visibilidade" id="selectVisibilidade" class="form-control">
										<c:forEach items="${visibilidade}" var="v">
											<form:option value="${v}">${v.visibilidadeDuvida}</form:option>
										</c:forEach>
									</form:select>
								</div>
								<div class="modal-footer">
									<form:button type="submit" class="btn btn-primary">Enviar Dúvida</form:button>
								</div>
							</form:form>
						</div>
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
		<!-- Custom scripts for all pages-->
		<script src="webjars/startbootstrap-sb-admin/4.0.0/js/sb-admin.min.js"></script>
		<!--  script validacao/edicao de dados do usuario -->
		<script src="resources/scripts/validacaoAjax.js"></script>
		<script>
			$('#itemModal').on("hide.bs.modal", function (e) {
				if(!confirm("Realmente deseja fechar? Suas alterações serão descartadas.")) return false;
			});
		</script>
		<script>
			function getItem(idItem, index, e){
				$("#formDuvida").attr("hidden", true);
				$("#idItem").val(idItem);
				$.post("requisitaItem", {'idItem' : idItem}, function(item){
					$("#itemModalLabel").html('Item ' + index);
					$("#enunciado").text(item.enunciado);
					if(item.tipoItem==='DISCURSIVO'){
						$("#respDisc").show();
						$("#respDisc").attr("disabled",false);
						$("#alternativas").hide();
					}
					else if(item.tipoItem==='MULTIPLA_ESCOLHA'){
						$("#respDisc").hide();
						$("#respDisc").attr("disabled",true);
						$("#alternativas").show();
						for(var i=0; i<5; i++){
							$("#label"+i).html(item.alternativas[i].enunciado);
							$("#alt"+i).attr("value",item.alternativas[i].id);
							$("#alt"+i).attr("checked",false);
						}
					}
				});
				e.preventDefault();
			}
			function getItemResposta(idItem, idAluno, index, e){
				$("#formDuvida").attr("hidden", true);
				$("#idItem").val(idItem);
				$.post("requisitaItemResposta", {'idItem':idItem, 'idAluno':idAluno}, function(itemResp){
					$("#itemModalLabel").html('Item ' + index);
					$("#enunciado").text(itemResp.item.enunciado);
					if(itemResp.item.tipoItem==='DISCURSIVO'){
						$("#respDisc").val(itemResp.texto);
						$("#respDisc").show();
						$("#respDisc").attr("disabled",false);
						$("#alternativas").hide();
					}
					else if(itemResp.item.tipoItem==='MULTIPLA_ESCOLHA'){
						$("#respDisc").val('');
						$("#respDisc").hide();
						$("#respDisc").attr("disabled",true);
						$("#alternativas").show();
						for(var i=0; i<5; i++){
							$("#label"+i).html(itemResp.item.alternativas[i].enunciado);
							$("#alt"+i).attr("value",itemResp.item.alternativas[i].id);
							if(itemResp.alternativa.id === itemResp.item.alternativas[i].id){
								$("#alt"+i).attr("checked",true);
							}
						}
					}
				});
				e.preventDefault();
			}
			function mostrarFormDuvida(event){
				$("#formDuvida").attr("hidden", false);
				$("#itemDuvida").attr("value", $("#idItem").val());
				event.preventDefault();
			}
		</script>
	</div>
</body>

</html>