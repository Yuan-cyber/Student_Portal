<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin dashboard</title>
</head>

<body>
  <h1>Welcome!</h1>
  <h3>create a new user</h3>
  <form action="/AssessmentSystem/CreateUserServlet" method="post" style="display: flex; flex-direction: column;  align-items: left;">
  
  <label for="username">username:</label>
  <input type="text" name="username" style="width: 30%;"><br>
  
  <label for="password">password:</label>
  <input type="text" name="password" style="width: 30%;"><br>
  
  <label for="firstname">firstname:</label>
  <input type="text" name="firstname" style="width: 30%;"><br>
  
  <label for="lastname">lastname:</label>
  <input type="text" name="lastname" style="width: 30%;"><br>
  
  <label for="phone">phone:</label>
  <input type="text" name="phone" style="width: 30%;"><br>
  
  <label for="role">role:</label>
  <select id="role" name="role" style="width: 30%;">
    <option value="student">student</option>
    <option value="teacher">teacher</option>
  </select><br>
  
  <input type="submit" value="create" style="width:30%;">
  </form>
  
  <!-- successful message notification -->
  <%
  String sm=(String)request.getSession().getAttribute("successMsg");
  if(sm!=null){
  %>
   <div>
    <%=sm %>
   </div>
  <%
    //clear the success message from the session
    request.getSession().removeAttribute("successMsg");
    }
  %>
  
  <br>
  <h3>create a new course</h3>
  
  <form action="/AssessmentSystem/CreateCourseServlet" method="post" style="display: flex; flex-direction: column; align-items: left;">
  <label for="courseid">courseid:</label>
  <input type="text" name="courseid" style="width: 30%;"><br>
  
  <label for="coursename">coursename:</label>
  <input type="text" name="coursename" style="width: 30%;"><br>
  
  <label for="semester">semester:</label>
  <select id="semester" name="semester" style="width: 30%;">
    <option value="s1">s1</option>
    <option value="s2">s2</option>
  </select><br>
  
  <input type="submit" value="create" style="width:30%;">
  
  </form>
  
  <!-- successful message notification -->
  <%
  String smsg=(String)request.getSession().getAttribute("successMsg");
  if(smsg!=null){
  %>
   <h3>
    <%=smsg %>
   </h3>
  <%
    //clear the success message from the session
    request.getSession().removeAttribute("successMsg");
    }
  %>
</body>
</html>