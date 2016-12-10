<!DOCTYPE html>
<html>
<head>
  <title>Chat</title>
</head>
<body>

<p>
  ${user}
  <a href="/logout">[ logout ]</a>
</p>

<p>
  <a href="/backup">[ backup ]</a>
</p>

<form action="/post" method="POST">
  <p>
    <input type="text" name="message" value="">
    <input type="submit" value="Post">
  </p>
</form>

<h3>Posts:</h3>
<#list posts as post>
<p><strong>${post.author}:</strong> ${post.message}</p>
</#list>

</body>
</html>
