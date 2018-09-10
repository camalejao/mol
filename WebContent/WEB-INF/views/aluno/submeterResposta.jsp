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
					href="#collapseCadastros" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-file-text "></i> <span class="nav-link-text">Atividades
					</span></a>
					<ul class="sidenav-second-level collapse" id="collapseAtividades">
						<li><a href="#">Minhas Atividades</a></li>
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
									<label for="doc">Arquivo da atividade:</label>
									<c:choose>
										<c:when test="${not empty atividade.nomeDocumento}">
											<p id="doc">${atividade.nomeDocumento}<a
													class="btn btn-secondary btn-sm"
													href="downloadAtividade-${atividade.id}"> <i
													class="fa fa-download "></i> Baixar
												</a>
											</p>
										</c:when>
										<c:otherwise>
											<p id="doc">Não disponibilizado.</p>
										</c:otherwise>
									</c:choose>
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
												<div class="col-md-6">
													<label for="uploadArquivo">Selecione o arquivo de
														resposta </label>
													<form:input class="form-control-file" path="upload"
														id="uploadArquivo" type="file" />
												</div>
											</div>
										</div>
										<c:choose>
											<c:when test="${not empty itens}" >
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
											<c:when test="${itens.size() == respostaItens.size()}">
												<button class="btn btn-primary btn-block mt-3" type="submit">Finalizar Resposta</button>
											</c:when>
											<c:otherwise>
												<button class="btn btn-disabled btn-block mt-3" disabled type="submit">Finalizar Resposta</button>
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
						<h5 id="enunciado"></h5>
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
							</div>
						</form>
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
			$('#itemModal').on("hide.bs.modal", function (e) {
				if(!confirm("Realmente deseja fechar? Suas alterações serão descartadas.")) return false;
			});
		</script>
		<script>
			function getItem(idItem, index, e){
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
		</script>
	</div>
</body>

</html>