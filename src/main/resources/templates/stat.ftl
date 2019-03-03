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
						<th>总次数</th>
						<th>选项A</th>
						<th>选A次</th>
						<th>选项B</th>
						<th>选B次</th>
						<th>选项C</th>
                        <th>选C次</th>
                        <th>选项D</th>
                        <th>选D次</th>
					</tr>
					</thead>
					<tbody>

					<#list statPage.content as statVO>
					<tr>
						<td title="${statVO.questionContent}" onclick="showDescription(this, this.title)" style="color: #9d9d9d;"><a>(查看案情，点击显示)</a></td>
						<td>${statVO.totalAnswer}</td>
						<td>${statVO.answerA}</td>
						<td>${statVO.choiceA}</td>
						<td>${statVO.answerB}</td>
                        <td>${statVO.choiceB}</td>
                        <td>${statVO.answerC}</td>
                        <td>${statVO.choiceC}</td>
                        <td>${statVO.answerD}</td>
                        <td>${statVO.choiceD}</td>
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
<script type="text/javascript">
	function showDescription(dom, desr) {
		dom.innerHTML = desr;
    }
</script>
</body>
</html>