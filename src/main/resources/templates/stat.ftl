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
                        <th style="width: 50%">题目</th>
                        <th style="width: 10%">选项</th>
                        <th style="width: 10%">选项内容</th>
                        <th style="width: 10%">次数</th>
                        <th style="width: 20%">结论</th>
                    </tr>
                    </thead>
                    <tbody>

					<#list statPage.content as statVO>
                    <tr>
                        <td rowspan="2"><font style="color: #8a6d3b;font-weight: bold">原题:</font>${statVO.casesDescription}</td>
                        <td>A</td>
                        <td>${statVO.answerA}</td>
                        <td>${statVO.choiceA}</td>

                        <#if statVO.remark??>
                        <td rowspan="4">${statVO.remark}</td>
                        </#if>
                    </tr>
                    <tr>
                        <td>B</td>
                        <td>${statVO.answerB}</td>
                        <td>${statVO.choiceB}</td>
                    </tr>
                    <tr>
                        <td rowspan="2"><span style="color: #23527c">
                            <font style="font-weight: bold">打码题:</font>
							${statVO.questionContent}</span></td>
                        <td>C</td>
                        <td>${statVO.answerC}</td>
                        <td>${statVO.choiceC}</td>
                    </tr>
                    <tr>
                        <td>D</td>
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
						<li><a href="/sphzb/question/stat2?page=${currentPage + 1}&size=${size}">下一页</a></li>
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