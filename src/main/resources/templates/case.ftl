<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>案件上传</title>
    <link rel="stylesheet" type="text/css" href="/sphzb/css/main.css">
</head>
<body>
<div class="form">
    <p class="description">说明：上传文件需是Excel格式，有两列数据，分别为案件编号和案件案情描述。</p>
    <form action="/sphzb/case/upload" method="post" enctype="multipart/form-data">
        <input class="border" type="file" name="cases">
        <input class="border upload" type="submit" value="上传">
    </form>
</div>
</body>
</html>