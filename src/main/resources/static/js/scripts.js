$(".answer-write input[type=submit]").click(addAnswer);

function addAnswer(e) {
	e.preventDefault();

	var queryString = $(".answer-write").serialize();
	var url = $(".answer-write").attr("action");

	$.ajax({
		type: 'POST',
		url: url,
		data: queryString,
		dataType: 'json',
		error: onError,
		success: onSuccess
	});
}

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined'
			? args[number]
			: match
			;
	});
};

function onError() {

}

function onSuccess(data, status) {
	console.log(data);

	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(data.writer.name, data.formattedCreatedDate, data.contents, data.seq, data.seq);
	$("div.qna-comment-slipp-articles").append(template);

	$("form.answer-write textarea[name=contents]").val('');
}