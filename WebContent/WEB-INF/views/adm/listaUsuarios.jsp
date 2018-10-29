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
<title>MOL - Administrador</title>
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
						class="fa fa-fw fa-id-card"></i> <span class="nav-link-text">Cadastros
					</span></a>
					<ul class="sidenav-second-level collapse" id="collapseCadastros">
						<li><a href="cadastrarDisciplina">Cadastrar Disciplina</a></li>
						<li><a href="cadastrarUsuario">Cadastrar Usuário</a></li>
					</ul></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Listagens"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseListas" data-parent="#exampleAccordion"> <i
						class="fa fa-fw fa-table"></i> <span class="nav-link-text">Listagens</span></a>
					<ul class="sidenav-second-level collapse" id="collapseListas">
						<li><a href="listarDisciplinas">Listar Disciplinas</a></li>
						<li><a href="listarUsuarios">Listar Usuários</a></li>
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
				<li class="breadcrumb-item"><a href="home">Página
						Inicial</a></li>
				<li class="breadcrumb-item active">Listar Usuários</li>
			</ol>
			<div class="card mb-3">
				<div class="card-header">
					<i class="fa fa-table"></i> Lista de Usuários
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>Nome</th>
									<th>Email</th>
									<th>Tipo</th>
									<th>Status</th>
									<th>Data de cadastro</th>
									<th>Ações</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>Nome</th>
									<th>Email</th>
									<th>Tipo</th>
									<th>Status</th>
									<th>Data de cadastro</th>
									<th>Ações</th>
								</tr>
							</tfoot>
							<tbody>
								<c:forEach items="${usuarios}" var="usuario">
									<tr>
										<td>${usuario.nome}</td>
										<td>${usuario.email}</td>
										<td>${usuario.tipo}</td>
										<td>${usuario.status}</td>
										<fmt:parseDate value="${usuario.dataCadastro}"
											pattern="yyyy-MM-dd" var="parsedDate" type="date" />
										<td><fmt:formatDate value="${parsedDate}"
												pattern="dd/MM/yyyy" /></td>
										<td>
											<div class="row">
												<c:if test="${usuario.tipo == 'ADMINISTRADOR'}">
													<button class="btn btn-sm btn-secondary ml-2 mr-1"
														data-toggle="modal" data-target="#editaAdmModal"
														title="Editar"
														onclick="editaAdm(${usuario.id},'${usuario.nome}','${usuario.email}',event)">
														<i class="fa fa-pencil-square" aria-hidden="true"></i>
													</button>	
												</c:if>
												<c:if test="${usuario.tipo == 'ALUNO' || usuario.tipo == 'PROFESSOR' || usuario.tipo == 'MONITOR'}">
													<button class="btn btn-sm btn-secondary ml-2 mr-1"
														data-toggle="modal" data-target="#editaAlunoProfModal"
														title="Editar"
														onclick="editaAlunoProf(${usuario.id},'${usuario.nome}','${usuario.email}','${usuario.matricula}',event)">
														<i class="fa fa-pencil-square" aria-hidden="true"></i>
													</button>	
												</c:if>
												<form action="excluirUsuario" method="POST"
													onsubmit="return confirm('Confirma a exclusão?');">
													<input name="usuario" value="${usuario.id}" type="text"
															hidden="true" />
													<button class="btn btn-sm btn-danger mr-1" title="Excluir" type="submit">
														<i class="fa fa-trash" aria-hidden="true"></i>
													</button>
												</form>
												<c:if test="${usuario.tipo == 'MONITOR'}">
													<form action="removerMonitor" method="POST"
														onsubmit="return confirm('Confirma a remoção do monitor?');">
														<input name="usuario" value="${usuario.id}" type="text"
															hidden="true" />
														<button class="btn btn-sm btn-danger mr-1" title="remover Monitor"
															type="submit">Remover Monitor</button>
													</form>
												</c:if>
											</div>
										</td>
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
					<div class="modal-body">Selecione "Sair" se deseja encerrar a
						sessão atual.</div>
					<div class="modal-footer">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">Cancelar</button>
						<a class="btn btn-primary" href="logout">Sair</a>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal EDITAR adm -->
		<div class="modal fade" id="editaAdmModal" tabindex="-1"
			role="dialog" aria-labelledby="editaAdmModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editaAdmModalLabel">Editar
							Usuário</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<form action="editarUsuario" method="POST">
							<div class="form-group">
								<label for="inputNomeAdm">Nome</label> <input class="form-control"
									id="inputNomeAdm" name="nome" type="text"
									placeholder="Digite o nome do Usuário" maxlength="50" />
							</div>
							<div class="form-group">
								<label for="inputEmailAdm">Email</label> <input class="form-control"
									id="inputEmailAdm" name="email" type="email" placeholder="email@exemplo.com"
									maxlength="50" />
							</div>
							<input id="inputIdAdm" name="usuario" hidden="true" />
							<input id="inputMatAdm" name="matricula" hidden="true" value="" />
							<div>
								<button class="btn btn-primary btn-block" type="submit">Salvar</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal EDITAR aluno/professor -->
		<div class="modal fade" id="editaAlunoProfModal" tabindex="-1"
			role="dialog" aria-labelledby="editaAlunoProfModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editaAlunoProfModalLabel">Editar
							Usuário</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<form action="editarUsuario" method="POST">
							<div class="form-group">
								<label for="inputNome">Nome</label> <input class="form-control"
									id="inputNome" name="nome" type="text"
									placeholder="Digite o nome do Usuário" maxlength="50" />
							</div>
							<div class="form-group">
								<label for="inputEmail">Email</label> <input class="form-control"
									id="inputEmail" name="email" type="email" placeholder="email@exemplo.com"
									maxlength="50" />
							</div>
							<div class="form-group">
								<label for="inputMatricula">Matrícula</label> <input class="form-control"
									id="inputMatricula" name="matricula" type="text"
									maxlength="10" />
							</div>
							<input id="inputId" name="usuario" hidden="true" />
							<div>
								<button class="btn btn-primary btn-block" type="submit">Salvar</button>
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
		<!-- Page level plugin JavaScript-->
		<script
			src="webjars/startbootstrap-sb-admin/4.0.0/vendor/datatables/jquery.dataTables.js"></script>
		<script
			src="webjars/startbootstrap-sb-admin/4.0.0/vendor/datatables/dataTables.bootstrap4.js"></script>
		<!-- Custom scripts for all pages-->
		<script src="webjars/startbootstrap-sb-admin/4.0.0/js/sb-admin.min.js"></script>
		<!-- Custom scripts for this page-->
		<script
			src="resources/scripts/datatables-PT-BR.js"></script>
		<script>
			function editaAdm(id, nome, email, e){
				$("#inputIdAdm").attr("value", id);
				$("#inputNomeAdm").val(nome);
				$("#inputEmailAdm").val(email);
				e.preventDefault();
			}
			function editaAlunoProf(id, nome, email, matricula, e){
				$("#inputId").attr("value", id);
				$("#inputNome").val(nome);
				$("#inputEmail").val(email);
				$("#inputMatricula").val(matricula);
				e.preventDefault();
			}
		</script>
	</div>
</body>
</html>