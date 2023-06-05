<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<h1>Login</h1>
<form action="/AssessmentSystem/LoginServlet" method="post">
<label for="username">username:</label>
<input type="text" name="username"><br>

<label for="password">password:</label>
 <input type="password" name="password"><br>
 
 <input type="submit" value="login">
</form>
</body>
</html>