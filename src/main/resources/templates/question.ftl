<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>题目</title>
	<link rel="stylesheet" type="text/css" href="/sphzb/css/main.css">
</head>
<body>
	<div class="form">
		<form action="/sphzb/question/commit" method="post">
			<input style="display: none" name="questionId" value="${question.questionId}">
			<p class="description">${question.questionContent}</p>
			<input type="radio" name="answer" value="A"><label>A&nbsp;${question.answerA}</label>&nbsp;&nbsp;&nbsp;
			<input type="radio" name="answer" value="B"><label>B&nbsp;${question.answerB}</label>&nbsp;&nbsp;&nbsp;
			<input type="radio" name="answer" value="C"><label>C&nbsp;${question.answerC}</label>&nbsp;&nbsp;&nbsp;
			<input type="radio" name="answer" value="D"><label>D&nbsp;${question.answerD}</label><br>
			<br>
			<button id="commit" class="upload border">提交</button>
		</form>
	</div>
</body>
</html>