$(function() {
	$("input[type='radio']").change(function (event) {
		$("input[type='radio']:checked").not(this).prop('checked', false);
	});
})

$('#formItemD').submit(function(){
				
	var valido = true;
				
	if ($('#enunciadoDiscursivo').val().replace(/\s/g, "") === ""){
		$('#enunciadoDiscursivo').toggleClass("is-invalid",true);
		$('#enunciadoDiscursivo').after("<span id='erroEnunciadoD' class='text-danger'>Por favor insira um enunciado</span>");
		valido = false;
	}else{
		$('#enunciadoDiscursivo').toggleClass("is-invalid",false);
		$('#erroEnunciadoD').remove();
 	}
		    	
	if ($('#inputValorD').val() <= 0){
		$('#inputValorD').toggleClass("is-invalid",true);
		$('#inputValorD').after("<span id='erroValorD' class='text-danger'>Por favor insira um valor maior que 0</span>");
		valido = false;
	}else{
		$('#inputValorD').toggleClass("is-invalid",false);
		$('#erroValorD').remove();
	}

	return valido;
		    	
});
			
$('#formItemME').submit(function(){
				
	var valido = true;
	
	$('#erroEnunciadoME').remove();
	$('#erroValorME').remove();
	
	$("#formItemME input[type='text']").each(function(){
		if($.trim($(this).val()).length == 0){
			$(this).toggleClass("is-invalid",true);
			valido = false;
			$(this).focus;
		}
		else
			$(this).toggleClass("is-invalid",false);
	});
	
	if ($('#enunciadoME').val().replace(/\s/g, "") === ""){
		$('#enunciadoME').toggleClass("is-invalid",true);
		$('#enunciadoME').after("<span id='erroEnunciadoME' class='text-danger'>Por favor insira um enunciado</span>");
		valido = false;
	}else{
		$('#enunciadoME').toggleClass("is-invalid",false);
		$('#erroEnunciadoME').remove();
	}								
	
	if($('#inputValorME').val() <= 0){
		$('#inputValorME').toggleClass("is-invalid",true);
		$('#inputValorME').after("<span id='erroValorME' class='text-danger'>Por favor insira um valor maior que 0</span>");
		valido = false;
	}else{
		$('#inputValorME').toggleClass("is-invalid",false);
		$('#erroValorME').remove();
	}
	
	var radio = false;
	$('#erroAlt').remove();
	$("#formItemME input[type='radio']").each(function(){
		if($(this).is(':checked'))
			radio = true;
	});
	if(!radio){
		$('#alt5').after("<br ><span id='erroAlt' class='text-danger'>Você precisa selecionar uma alternativa correta</span>");
		valido = false;
	}
		
	return valido;
});

function itemDiscursivo(id, e) {
	$("#idAtividadeItemDiscursivo").attr("value", id);
	e.preventDefault();
}
function itemMultiplaEscolha(id, e) {
	$("#idAtividadeItemME").attr("value", id);
	e.preventDefault();
}
function editaItemD(id_atv, id_item, enunciado, valor, e) {
	$("#idEdicaoAtividadeID").attr("value", id_atv);
	$("#idEdicaoID").attr("value", id_item);
	$("#editaEnunciadoDiscursivo").val($('#enunciado-'+enunciado).attr("value"));
	$("#editValorD").val(valor);
	e.preventDefault();
}
function editaItemME(id_atv, id_item, enunciado, valor, e) {
	$("#idEdicaoAtividadeME").attr("value", id_atv);
	$("#idEdicaoME").attr("value", id_item);
	$("#editaEnunciadoME").val($('#enunciado-'+enunciado).attr("value"));
	$("#editValorME").val(valor);
	e.preventDefault();
}
function editaAlternativa(id_item, id_alt, enunciado, correta, e) {
	$("#idEditaItemAlt").attr("value", id_item);
	$("#idEditaAlt").attr("value", id_alt);
	$("#editaEnunciadoAlt").val(enunciado);
	if (correta == 'true')
		$("#editaAltCorreta").prop("checked", true);
	else if (correta == 'false')
		$("#editaAltCorreta").prop("checked", false);
	e.preventDefault();
}