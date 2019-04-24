<%@ page language="java"  contentType="text/html; charset=UTF-8" %>
<html>
<body>
<h2>This is mmall</h2>

springmvc上传文件
<form name="springmvcform" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="springMVC上传文件">
</form>

富文本图片上传文件
<form name="richtextform" action="/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="富文本上传文件">
</form>

</body>
</html>
