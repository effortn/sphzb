<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>答案提交</title>
    <link rel="stylesheet" type="text/css" href="/sphzb/css/main.css">
</head>
<body>
<div class="form">
    <p class="description">${result.msg}</p>
    <p class="description"><strong id="second">2</strong>秒后跳至答题页面<a href="/sphzb/question/">点击直接跳转</a></p>
</div>
<script type="text/javascript">
    setInterval(function () {
        document.getElementById("second").innerHTML = document.getElementById("second").innerHTML - 1;
    }, 1000);
    setInterval(function () {
        javascript:location.href='/sphzb/question/'
    }, 2000);
</script>
</body>
</html>