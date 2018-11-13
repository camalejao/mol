function verificaEmail(email) {
	$.post("verificaEmail", {
		'email' : email
	}, function(disp) {
		if (disp == 'true') {
			$("#inputEmail").toggleClass('is-valid',true);
			$("#inputEmail").toggleClass('is-invalid',false);
			$("#emailInd").remove();
			$("#emailDisp").remove();
			$("#inputEmail").after("<span id='emailDisp' class='text-success'>Email disponível</span>");
			
		} else if (disp == 'false'){
			$("#inputEmail").toggleClass('is-invalid',true);
			$("#inputEmail").toggleClass('is-valid',false);
			$("#emailDisp").remove();
			$("#emailInd").remove();
			$("#inputEmail").after("<span id='emailInd' class='text-danger'>Email já cadastrado!</span>");		
		}
	});
}

function verificaMatAluno(mat) {
	$.post("verificaMatAluno", {
		'matricula' : mat
	}, function(disp) {
		if (disp == 'true'){
			$("#inputMatricula").toggleClass('is-valid',true);
			$("#inputMatricula").toggleClass('is-invalid',false);
			$("#matInd").remove();
			$("#matDisp").remove();
			$("#inputMatricula").after("<span id='matDisp' class='text-success'>Matrícula disponível</span>");
			
		}
		else if (disp == 'false'){
			$("#inputMatricula").toggleClass('is-invalid',true);
			$("#inputMatricula").toggleClass('is-valid',false);
			$("#matDisp").remove();
			$("#matInd").remove();
			$("#inputMatricula").after("<span id='matInd' class='text-danger'>Matrícula já cadastrada!</span>");
		}
	});
}

function verificaMatProf(mat) {
	$.post("verificaMatProf", {
		'matricula' : mat
	}, function(disp) {
		if (disp == 'true'){
			$("#inputMatricula").toggleClass('is-valid',true);
			$("#inputMatricula").toggleClass('is-invalid',false);
			$("#matInd").remove();
			$("#matDisp").remove();
			$("#inputMatricula").after("<span id='matDisp' class='text-success'>Matrícula disponível</span>");
		}
		else if (disp == 'false'){
			$("#inputMatricula").toggleClass('is-invalid',true);
			$("#inputMatricula").toggleClass('is-valid',false);
			$("#matDisp").remove();
			$("#matInd").remove();
			$("#inputMatricula").after("<span id='matInd' class='text-danger'>Matrícula já cadastrada!</span>");
		}
	});
}

function verificaSigla(sigla) {
	$.post("verificaSigla", {
		'sigla' : sigla
	}, function(disp) {
		if (disp == 'true'){
			$("#inputSigla").toggleClass('is-valid',true);
			$("#inputSigla").toggleClass('is-invalid',false);
			$("#siglaInd").remove();
			$("#siglaDisp").remove();
			$("#inputSigla").after("<span id='siglaDisp' class='text-success'>Sigla disponível</span>");
		}
		else if (disp == 'false'){
			$("#inputSigla").toggleClass('is-invalid',true);
			$("#inputSigla").toggleClass('is-valid',false);
			$("#siglaInd").remove();
			$("#siglaDisp").remove();
			$("#inputSigla").after("<span id='siglaInd' class='text-danger'>Sigla já cadastrada!</span>");
		}
	});
}

function confirmaSenha(){
	var senha = document.getElementById("inputSenha");
	var confirmacao = document.getElementById("confirmacaoSenha");
	
	if(senha.value.length < 6){
		$("#inputSenha").toggleClass('is-invalid',true);
		$("#erroDigitos").remove();
		$("#inputSenha").after("<span id='erroDigitos' class='text-danger'>A senha deve ter 6 dígitos</span>");
		$("#btnSalvarDados").attr("disabled",true);
	} else{
		$("#inputSenha").toggleClass('is-invalid',false);
		$("#erroDigitos").remove();
		$("#btnSalvarDados").attr("disabled",false);
	}
	
	if(senha.value != confirmacao.value){
			$("#confirmacaoSenha").toggleClass('is-invalid',true);
			$("#naoCorresp").remove();
			$("#confirmacaoSenha").after("<span id='naoCorresp' class='text-danger'>Senhas devem corresponder!</span>");
			$("#btnSalvarDados").attr("disabled",true);
		} else{
			$("#confirmacaoSenha").toggleClass('is-invalid',false);
			$("#naoCorresp").remove();
			$("#btnSalvarDados").attr("disabled",false);
		}
}

function showDivAlterarSenha(){
	$("#divAlterarSenha").attr("hidden", false);
	$("#btnAltSenha").attr("hidden", true);
}

function editarDados(){
	
	var erro = false;
	
	if($("#inputEditarNome").val().trim().length == 0){
		$("#inputEditarNome").toggleClass('is-invalid',true);
		erro = true;
	}else{
		$("#inputEditarNome").toggleClass('is-invalid',false);
	}
	
	if($("#inputEditarEmail").val().trim().length == 0){
		$("#inputEditarEmail").toggleClass('is-invalid',true);
		erro = true;
	}else{
		$("#inputEditarEmail").toggleClass('is-invalid',false);
	}
	
	if(!erro){
		$.post("editarDados", {
			"nome" : $("#inputEditarNome").val(),
			"email" : $("#inputEditarEmail").val(),
			"senha" : $("#inputSenha").val(),
			"confirmacao" : $("#confirmacaoSenha").val(),
			"usuario" : $("#inputId").val()
		}, function(response){
			if(response == 'sucesso'){
				location.reload(true);
			}
		});
	}
}