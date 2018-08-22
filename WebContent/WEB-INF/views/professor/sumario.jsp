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
<title>MOL - Professor</title>
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
				<li class="breadcrumb-item"><a href="listarTurmas">Minhas
						Turmas</a></li>
				<li class="breadcrumb-item active">Sumário Disciplina</li>
			</ol>
			<div class="mb-0 mt-4">
				<i class="fa fa-list-ul"></i> Sumário - ${turmaDisc.disciplina.nome}
			</div>
			<hr class="mt-2">
			<a href="" class="btn btn-primary" data-toggle="modal"
				data-target="#topicoModal">Adicionar Tópico</a>
			<c:forEach items="${topicos}" var="t">
				<div class="row justify-content-center">
					<div class="col-md-8">
						<div class="card mb-3">
							<div class="card-header">${t.descricao}
								- <a class="btn btn-secondary btn-sm" href=""
									data-toggle="modal" data-target="#editaTopicoModal"
									onclick="editarTopico(${t.id},'${t.descricao}','${t.status}',event)">
									<i class="fa fa-pencil-square"></i> Editar
								</a>
							</div>
							<div class="card-body">
								<c:forEach items="${t.materiaisDidaticos}" var="material">
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
											<a class="btn btn-sm mb-3" href=""
												onclick="editarMaterial(${t.id},${material.id},'${material.titulo}','${material.tipo.tipoMaterial}',
												'${material.descricao}',event)"
												data-toggle="modal" data-target="#editaMaterialModal">
												Editar Material</a>
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
											<a class="btn btn-sm mb-3" href=""
												onclick="editarMaterial(${t.id},${material.id},'${material.titulo}','${material.tipo.tipoMaterial}',
												'${material.descricao}',event)"
												data-toggle="modal" data-target="#editaMaterialModal">
												Editar Material</a>
										</c:when>
										<c:when test="${material.tipo == 'LINK'}">
											<h6>
												<i class="fa fa-link"></i> <a href="${material.link}"
													target="_blank">${material.titulo}</a>
											</h6>
											<hr class="mt-0 mb-0">
											<p>${material.descricao}</p>
											<a class="btn btn-sm mb-3" href=""
												onclick="editarMaterialLink(${t.id},${material.id},'${material.titulo}','${material.tipo.tipoMaterial}',
												'${material.descricao}','${material.link}',event)"
												data-toggle="modal" data-target="#editaMaterialModal">
												Editar Material</a>
										</c:when>
									</c:choose>
								</c:forEach>
								<div>
									<a class="btn btn-sm mb-3" href="" data-toggle="modal"
										data-target="#materialModal"
										onclick="adicionarMaterial(${t.id},event)">Adicionar
										Material Didático</a>
								</div>
								<c:forEach items="${t.subtopicos}" var="subt">
									<!-- <c:if test="${subt.topico == t}"> -->
									<h6>
										<i class="fa fa-info"></i> ${subt.descricao} - <a
											class="btn btn-secondary btn-sm" href="" data-toggle="modal"
											data-target="#editaTopicoModal"
											onclick="editarTopico(${subt.id}, '${subt.descricao}', '${subt.status}', event)"><i
											class="fa fa-pencil-square"></i> Editar</a>
									</h6>
									<hr class="mt-2">
									<c:forEach items="${subt.materiaisDidaticos}" var="material">
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
													<a class="btn btn-sm mb-3" href=""
														onclick="editarMaterial(${subt.id},${material.id},'${material.titulo}','${material.tipo.tipoMaterial}',
															'${material.descricao}',event)"
														data-toggle="modal" data-target="#editaMaterialModal">
														Editar Material</a>
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
													<a class="btn btn-sm mb-3" href=""
														onclick="editarMaterial(${subt.id},${material.id},'${material.titulo}','${material.tipo.tipoMaterial}',
															'${material.descricao}',event)"
														data-toggle="modal" data-target="#editaMaterialModal">
														Editar Material</a>
												</c:when>
												<c:when test="${material.tipo == 'LINK'}">
													<h6>
														<i class="fa fa-link"></i> <a href="${material.link}"
															target="_blank">${material.titulo}</a>
													</h6>
													<hr class="mt-0 mb-0">
													<p>${material.descricao}</p>
													<a class="btn btn-sm mb-3" href=""
														onclick="editarMaterialLink(${subt.id},${material.id},'${material.titulo}','${material.tipo.tipoMaterial}',
															'${material.descricao}','${material.link}',event)"
														data-toggle="modal" data-target="#editaMaterialModal">
														Editar Material</a>
												</c:when>
											</c:choose>
										</div>
									</c:forEach>
									<div class="ml-4 pl-4">
										<a class="btn btn-sm mb-3" href=""
											onclick="adicionarMaterial(${subt.id},event)"
											data-toggle="modal" data-target="#materialModal">Adicionar
											Material Didático</a>
									</div>
									<!-- </c:if> -->
								</c:forEach>
								<a class="btn btn-sm" href="" data-toggle="modal"
									data-target="#subtopicoModal"
									onclick="subtopico(${t.id},event)">Adicionar Subtópico</a>
							</div>
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
		<!-- Modal Cadastro Topico -->
		<div class="modal fade" id="topicoModal" tabindex="-1" role="dialog"
			aria-labelledby="topicoModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="topicoModalLabel">Adicionar
							Tópico</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<form:form action="adicionaTopico" method="post"
							modelAttribute="novoTopico">
							<div class="form-group">
								<label for="inputDescricao">Descrição</label>
								<form:input class="form-control" id="inputDescricao"
									path="descricao" type="text" placeholder="Descrição do tópico" />
								<form:input hidden="true" path="sumario" type="text"
									value="${turmaDisc.sumarioTurma.id}" />
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
		<!-- Modal Cadastro Subopico -->
		<div class="modal fade" id="subtopicoModal" tabindex="-1"
			role="dialog" aria-labelledby="topicoModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="subtopicoModalLabel">Adicionar
							Subtópico</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<form:form action="adicionaTopico" method="post"
							modelAttribute="novoTopico">
							<div class="form-group">
								<label for="inputDescricao">Descrição</label>
								<form:input class="form-control" id="inputDescricao"
									path="descricao" type="text"
									placeholder="Descrição do subtópico" />

								<form:input hidden="true" path="sumario" type="text"
									value="${turmaDisc.sumarioTurma.id}" />

								<form:input id="idtopicopai" hidden="true" path="topico"
									type="text" />
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
		<!-- Modal Edicao Topico -->
		<div class="modal fade" id="editaTopicoModal" tabindex="-1"
			role="dialog" aria-labelledby="editaTopicoModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editaTopicoModalLabel">Editar
							Tópico</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<form action="editaTopico" method="post">
							<div class="form-group">
								<label for="editDescricaoTopico">Descrição</label> <input
									class="form-control" id="editDescricaoTopico" name="descricao"
									type="text" placeholder="Descrição do tópico" /> <label
									for="editStatus">Status</label> <select class="form-control"
									id="editStatus" name="status">
									<c:forEach items="${status}" var="s">
										<option value="${s}">${s}</option>
									</c:forEach>
								</select> <input hidden="true" id="idtopico" name="topico" type="text" />
							</div>
							<div class="modal-footer">
								<button class="btn btn-secondary" type="button"
									data-dismiss="modal">Cancelar</button>
								<button class="btn btn-primary" type="submit">Salvar</button>
							</div>
						</form>
						<div class="modal-footer">
							<form action="excluirTopico" method="post"
								onsubmit="return confirm('Confirma a exclusão?');">
								<input name="topico" id="idTopicoExcluir" type="text"
									hidden="true"> <span class="ml-4"> <input
									class="btn btn-sm btn-danger" type="submit" value="Excluir Tópico">
								</span>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal Cadastro Material -->
		<div class="modal fade" id="materialModal" tabindex="-1" role="dialog"
			aria-labelledby="materialModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="materialModalLabel">Adicionar
							Material Didático</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<form:form action="adicionaMaterialDidatico" method="post"
							modelAttribute="novoMaterialDidatico"
							enctype="multipart/form-data">
							<div class="form-group">
								<label for="inputTitulo">Título</label>
								<form:input class="form-control" id="inputTitulo" path="titulo"
									type="text" placeholder="Título do material" />

								<label for="inputDescricao">Descrição</label>
								<form:input class="form-control" id="inputDescricao"
									path="descricao" type="text"
									placeholder="Descrição do material" />

								<label for="statusMaterial">Status</label>
								<form:select class="form-control" id="statusMaterial"
									path="status">
									<c:forEach items="${status}" var="s">
										<form:option value="${s}">${s}</form:option>
									</c:forEach>
								</form:select>

								<label for="selectTipoMaterial">Tipo</label>
								<form:select path="tipo" class="form-control"
									id="selectTipoMaterial" value="Selecione" onchange="opcoesMd()">
									<form:option value="" selected="true" disable="true"
										hidden="true">Selecione...</form:option>
									<c:forEach items="${tiposMaterial}" var="tipo">
										<form:option value="${tipo}">${tipo.tipoMaterial}</form:option>
									</c:forEach>
								</form:select>

								<div id="divUploadArquivo">
									<label for="uploadArquivo">Selecione o arquivo</label>
									<form:input class="form-control-file" path="upload"
										id="uploadArquivo" type="file" />
								</div>

								<div id="divInputLink">
									<label for="inputLink">Link</label>
									<form:input class="form-control" id="inputLink" path="link"
										type="text" placeholder="Cole o link para o material" />
								</div>

								<form:input id="idtopicomaterial" hidden="true" path="topico"
									type="text" />
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
		<!-- Modal Edicao Material -->
		<div class="modal fade" id="editaMaterialModal" tabindex="-1"
			role="dialog" aria-labelledby="editaMaterialModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editaMaterialModalLabel">Editar
							Material Didático</h5>
						<form action="excluirMaterialDidatico" method="post"
							onsubmit="return confirm('Confirma a exclusão?');">
							<input name="material" id="idMaterialExcluir" type="text"
								hidden="true"> <span class="ml-4"><input
								class="btn btn-sm btn-danger" type="submit"
								value="Excluir Material"></span>
						</form>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<form:form action="editaMaterialDidatico" method="post"
							modelAttribute="novoMaterialDidatico"
							enctype="multipart/form-data">
							<div class="form-group">
								<label for="editTitulo">Título</label>
								<form:input class="form-control" id="editTitulo" path="titulo"
									type="text" placeholder="Título do material" />

								<label for="editDescricaoMaterial">Descrição</label>
								<form:input class="form-control" id="editDescricaoMaterial"
									path="descricao" type="text"
									placeholder="Descrição do material" />

								<label for="editStatusMaterial">Status</label>
								<form:select class="form-control" id="editStatusMaterial"
									path="status">
									<c:forEach items="${status}" var="s">
										<form:option value="${s}">${s}</form:option>
									</c:forEach>
								</form:select>

								<label for="editTipoMaterial">Tipo</label>
								<form:select path="tipo" class="form-control"
									id="editTipoMaterial" value="Selecione"
									onchange="opcoesEditMd()">
									<form:option value="" selected="true" disable="true"
										hidden="true">Selecione...</form:option>
									<c:forEach items="${tiposMaterial}" var="tipo">
										<form:option value="${tipo}">${tipo.tipoMaterial}</form:option>
									</c:forEach>
								</form:select>

								<div id="divEditArquivo">
									<label for="editArquivo">Selecione o arquivo</label>
									<form:input class="form-control-file" path="upload"
										id="editArquivo" type="file" />
								</div>

								<div id="divEditLink">
									<label for="editLink">Link</label>
									<form:input class="form-control" id="editLink" path="link"
										type="text" placeholder="Cole o link para o material" />
								</div>

								<form:input id="editTopicoMaterial" hidden="true" path="topico"
									type="text" />
								<form:input id="idMaterial" hidden="true" path="id" type="text" />
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
		<script>
			function subtopico(id, e){
				$("#idtopicopai").attr("value",id);
				e.preventDefault();
			}
			function editarTopico(id, ds, s, e){
				$("#idtopico").attr("value",id);
				$("#idTopicoExcluir").attr("value",id);
				$("#editDescricaoTopico").attr("value",ds);
				$("#editStatus").attr("value",s);
				e.preventDefault();
			}
		</script>
		<script>
			$(document).ready(function(){
				$("#divUploadArquivo").hide();
				$("#divInputLink").hide();
				$("#divEditArquivo").hide();
				$("#divEditLink").hide();
			});
			function opcoesMd(){
				var tipo = $("#selectTipoMaterial").val();
				if(tipo == "SLIDE" || tipo == "APOSTILA"){
					$("#divUploadArquivo").show();
					$("#divInputLink").hide();
				}else if(tipo == "LINK"){
					$("#divUploadArquivo").hide();
					$("#divInputLink").show();
				}else{
					$("#divUploadArquivo").hide();
					$("#divInputLink").hide();
				}
			}
			function opcoesEditMd(){
				var tipoMd = $("#editTipoMaterial").val();
				if(tipoMd == "SLIDE" || tipoMd == "APOSTILA"){
					$("#divEditArquivo").show();
					$("#divEditLink").hide();
				}else if(tipoMd == "LINK"){
					$("#divEditArquivo").hide();
					$("#divEditLink").show();
				}else{
					$("#divEditArquivo").hide();
					$("#divEditLink").hide();
				}
			}
			function adicionarMaterial(id, e){
				$("#idtopicomaterial").attr("value",id);
				e.preventDefault();
			}
			function editarMaterial(idtopico, id, titulo, tipo, ds, e){
				$("#editTopicoMaterial").attr("value",idtopico);
				$("#idMaterial").attr("value",id);
				$("#idMaterialExcluir").attr("value",id);
				$("#editTitulo").attr("value",titulo);
				$("#editDescricaoMaterial").attr("value",ds)
				$("#editTipoMaterial").val(tipo.toUpperCase());
				$("#editLink").attr("value","");
				$("#divEditLink").hide();
				$("#divEditArquivo").show();
				e.preventDefault();
			}
			function editarMaterialLink(idtopico, id, titulo, tipo, ds, link, e){
				$("#editTopicoMaterial").attr("value",idtopico);
				$("#idMaterial").attr("value",id);
				$("#idMaterialExcluir").attr("value",id);
				$("#editTitulo").attr("value",titulo);
				$("#editDescricaoMaterial").attr("value",ds)
				$("#editTipoMaterial").val(tipo.toUpperCase());
				$("#editLink").attr("value",link);
				$("#divEditLink").show();
				$("#divEditArquivo").hide();
				e.preventDefault();
			}
		</script>
	</div>
</body>
</html>