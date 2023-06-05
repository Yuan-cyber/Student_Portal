<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Map"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table, th, td {
  border:1px solid black;
  text-align:center;
}
</style>
</head>
<body>

<h3>enrolled students:</h3>
<!-- retrieve courseID from the request scope-->
<p>course:${courseID}</p>

<table width=80%>
 <tr>
  <th>first name</th>
  <th>last name</th>
  <th>add marks</th>
 </tr>

<!-- get students enrolled from the session -->
<%  Map<String, Map<String, String>> students = (Map<String, Map<String, String>>) request.getAttribute("students");
System.out.println("students: " +students);

if(students.size()!=0){
for(String studentID:students.keySet()){
	Map<String,String> studentName=students.get(studentID);   

%>
<tr> 
  <td><%= studentName.get("firstName")%></td>
  <td><%= studentName.get("lastName") %></td>
  <td><a href="addmarks.jsp?studentID=<%=studentID %>&courseID=${courseID}">add</a></td>
</tr>
<%} 

}else{
	%><tr><td>There are no students enrolled in this course.</td></tr><% 
}%>

</table>
</body>
</html>