<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addmarks</title>
</head>
<body>

  <h3>add marks:</h3>
  <form action="/AssessmentSystem/AddMarksServlet" method="post" style="display: flex; flex-direction: column;  align-items: left;">
  
  <label for="quiz">quiz:</label>
  <input type="text" name="quiz" style="width: 30%;"><br>
  
  <label for="assignment">assignment:</label>
  <input type="text" name="assignment" style="width: 30%;"><br>
  
  <label for="finalExam">final exam:</label>
  <input type="text" name="finalExam" style="width: 30%;"><br>
  
  <input type="hidden" name="studentID" value="${param.studentID}">
  <input type="hidden" name="courseID" value="${param.courseID}">
  
  <input type="submit" value="Add">
  
  </form>
</body>
</html>