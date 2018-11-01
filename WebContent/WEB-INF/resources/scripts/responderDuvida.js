function submitResposta(index, duvida, event) {
	$.post("responderDuvida", {
		'resposta' : $('#inputResposta-' + index).val(),
		'duvida' : duvida
	}, function(result) {
		if (result === 'sucesso') {
			location.reload(true);
		} else {
			$("#inputResposta-" + index).toggleClass('is-invalid', true);
			$("#inputResposta-" + index).after(
					"<span id='erro-" + index + "'" + "class='text-danger'>"
							+ result + ".</span>");
		}
	});
}