<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>marks</title>
<style>
table, th, td {
  border:1px solid black;
  text-align:center;
}
</style>
</head>
<body>
<h3>Your marks:</h3>
<table>

<tr>
<th>quiz</th>
<th>assignment</th>
<th>final exam</th>
</tr>

<tr>
<td><%= request.getAttribute("quiz") %></td>
<td>${assignment}</td>
<td>${finalExam}</td>

</tr>

</table>

</body>
</html>