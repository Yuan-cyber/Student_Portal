

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		// get parameters from request object
		String userName=req.getParameter("username").trim();
		String password=req.getParameter("password").trim();
		
		// connect to the database
		Connection con = null;
	    PreparedStatement stmt=null;
	    ResultSet rs = null;
	    
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String DB_URL = "jdbc:mysql://localhost/mydatabase";
			String USER = "root";
			String PASS = "zhaoyuan960318";
			
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("connected");
			
			// SQL statement
			String sql="SELECT * FROM user WHERE userID = ? AND password=?";
					
			stmt = con.prepareStatement(sql);
	        stmt.setString(1, userName);
	        stmt.setString(2, password);
	        
	        // Execute the query
	        rs = stmt.executeQuery();
	        	        
	   
	        if(rs.next()) {
	        	System.out.println("login");
	        	
	        	// check the role
	        	String role = rs.getString("role");
	        	
	            if (role != null && role.equals("admin")) {
	            	
	            	//go to admin page
	            	res.sendRedirect("admin.jsp");}
	            	
	            else {
	            	//for students and teachers, the dashboard page should display the courses
	            	
	            	//first get userID from the database and store it into the session
	            	String userID=rs.getString("userID");
	            	
	            	req.getSession().setAttribute("userID", userID);
	            	
	            	// select course information of the user from the database 
	            	String sql2="SELECT user_course.courseID, course.courseName FROM user_course JOIN course "
	            			+ "ON user_course.courseID = course.courseID WHERE user_course.userID = ?";
	            	PreparedStatement ps=con.prepareStatement(sql2);
	            	ps.setString(1, userID);
	            	
	            	ResultSet rs2 = ps.executeQuery();

	            	// use a map to store courses
	            	Map<String, String> courses = new HashMap<String, String>();
	            	
	            	while(rs2.next()){
	            		String courseID=rs2.getString("user_course.courseID");
	            		String courseName=rs2.getString("course.courseName");
	            				
	            		courses.put(courseID, courseName);}
	            	
	            	if(courses.size()!=0) {System.out.println("courses is not empty");}

	            		// store the map into the session
	            		req.getSession().setAttribute("courses",courses);
	            		
	            		rs2.close();
	            		ps.close();
	            		
	            		// create another map to store all courses for users to choose
	            	    Map<String, Map<String, String>> courseList = new HashMap<>(); 
	            	    
	            	   // create statements to get all courses from the database
	            	     String sql3 = "SELECT * FROM course";
	            	     Statement st =con.createStatement();
	            	     ResultSet rs3=st.executeQuery(sql3);
	            	     
	            	     while (rs3.next()) {
	                         String courseId = rs3.getString("courseID");
	                         String courseName = rs3.getString("courseName");
	                         String semester = rs3.getString("semester");
	                         
	                       // use an inner map to store courseName and semester
	                         Map<String, String> courseInfo = new HashMap<>();
	                         courseInfo.put("courseName", courseName);
	                         courseInfo.put("semester", semester);
	                         
	                         // put courseId and inner map into the courseList map
	                         courseList.put(courseId, courseInfo);
	                         
	                 }
	            	     if(courseList.size()!=0) {System.out.println("courseList is not empty");}

	            	 // Set the courseList as a session attribute
	            	     req.getSession().setAttribute("courseList", courseList);  
	            	     
	            	     rs3.close();
	            	     st.close();
	            	     
	            	     //if the role is student, go to the student page
	            	     if (role!=null && role.equals("student")){
	            	    	 res.sendRedirect("student.jsp");
	            	     }else {
	            	    	 //else go to the teacher page
	            	    	 res.sendRedirect("teacher.jsp");
	            	     }
	            }
	            
	        }else {
	        	pw.println("Invalid username or password");
	        }
	    				
		} catch (SQLException e) {	
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
	        // Close the resources
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

}
	
}

