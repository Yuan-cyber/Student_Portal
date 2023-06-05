<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Map"  import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>student</title>
<style>
table, th, td {
  border:1px solid black;
  text-align:center;
}
</style>
</head>
<body>


<h1>Welcome! :)</h1>

<h3>enrolled courses:</h3>

<table width=80%>
 <tr>
  <th>course id</th>
  <th>course name</th>
  <th>marks</th>
 </tr>

<!-- check if the student is enrolled in courses -->
<%  Map<String, String> courses = (Map<String, String>) session.getAttribute("courses");
System.out.println("courses: " + courses);
if(courses.size()!=0){%>

<!-- use for loop to iterate through the set of courseIDs, 
assigning each ID to the courseID variable. -->

<% for(String courseID:courses.keySet()){ %>
 
 <tr> 
  <td><%=courseID %></td>
  <td><%=courses.get(courseID) %></td>
  <td><a href="/AssessmentSystem/ViewMarksServlet?courseID=<%=courseID %>">view marks</a></td>
 </tr>

<%} %>


<% }else {%>
<tr>
<td>You are not enrolled in any courses.</td>
</tr>
</table>
  
<h3>list of courses:</h3>
  
<form action="/AssessmentSystem/ChooseCoursesServlet" method="post">
  
  <table width=50%>
   <tr>
    <th>select</th>
    <th>course name</th>
    <th>semester</th>
   </tr>
 
  <!-- retrieve all the courses for the student to choose-->
  <% Map<String,Map<String,String>> courseListRetrieved=(Map<String,Map<String,String>>)session.getAttribute("courseList");
  
  System.out.println("courseListRetrieved"+courseListRetrieved);
  
  if(courseListRetrieved.size()!=0){
   for(String courseID:courseListRetrieved.keySet()){
  Map<String,String> course=courseListRetrieved.get(courseID);   
  %>  
   <tr>
      <td><input type="checkbox" name="courseID[]" value="<%= courseID %>"></td>
      <td><%= course.get("courseName")%></td>
      <td><%= course.get("semester")%></td>      
  </tr>
<% }
   } else{ %>
   <tr>
   <td>There are no courses to enroll in.</td>
   </tr>
   
   <% } %>
   </table>
   <input style="width:20%" type="submit" value="Enroll">
   
</form>

<% } %>

</body>
</html>