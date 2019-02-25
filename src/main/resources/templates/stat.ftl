<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>答题统计</title>
	<link rel="stylesheet" type="text/css" href="/sphzb/css/bootstrap.min.css">
</head>
<body>
<#--主要内容content-->
<div id="page-content-wrapper" style="margin-top: 20px">
	<div class="container-fluid">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<table class="table table-bordered table-condensed" summary="答题结果统计">
					<thead>
					<tr>
						<th>题目</th>
						<th>答题总次数</th>
						<th>选项A</th>
						<th>选A次数</th>
						<th>选项B</th>
						<th>选B次数</th>
						<th>选项C</th>
                        <th>选C次数</th>
                        <th>选项D</th>
                        <th>选D次数</th>
					</tr>
					</thead>
					<tbody>

					<#list statPage.content as statVO>
					<tr>
						<td>${statVO.questionContent}</td>
						<td>${statVO.totalAnswer}</td>
						<td>${statVO.answerA}</td>
						<td>${statVO.choiceA}</td>
						<td>${statVO.answerB}</td>
                        <td>${statVO.choiceB}</td>
                        <td>${statVO.answerB}</td>
                        <td>${statVO.choiceC}</td>
                        <td>${statVO.answerC}</td>
                        <td>${statVO.choiceD}</td>
                        <td>${statVO.answerD}</td>
					</tr>
					</#list>
					</tbody>
				</table>
			</div>

			<#--分页-->
			<div class="col-md-12 column">
				<ul class="pagination pull-right">
					<#if currentPage lte 1>
					<li class="disabled"><a href="#">上一页</a></li>
					<#else>
					<li><a href="/sphzb/question/stat?page=${currentPage - 1}&size=${size}">上一页</a></li>
					</#if>

					<#list 1..statPage.getTotalPages() as index>
						<#if currentPage == index>
							<li class="disabled"><a href="#">${index}</a></li>
						<#else>
							<li><a href="/sphzb/question/stat?page=${index}&size=${size}">${index}</a></li>
						</#if>
					</#list>

					<#if currentPage gte statPage.getTotalPages()>
						<li class="disabled"><a href="#">下一页</a></li>
					<#else>
						<li><a href="/sphzb/question/stat?page=${currentPage + 1}&size=${size}">下一页</a></li>
					</#if>
				</ul>
			</div>
		</div>
	</div>
</div>
</body>
</html>