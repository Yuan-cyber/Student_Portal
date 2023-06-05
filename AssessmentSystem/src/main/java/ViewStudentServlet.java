

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewStudentServlet
 */
@WebServlet("/ViewStudentServlet")
public class ViewStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewStudentServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//get courseID and store it in the request scope
		String courseID=request.getParameter("courseID");
		System.out.println(courseID);
		
		request.setAttribute("courseID",courseID);
		
		//connection to the database
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String DB_URL = "jdbc:mysql://localhost/mydatabase";
			String USER = "root";
			String PASS = "zhaoyuan960318";
			
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("connected");
			
			// SQL statement
			String sql="SELECT * FROM user_course JOIN user ON user_course.userID = user.userID WHERE user_course.courseID = ? ";
								
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, courseID);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				// use a map to store students
            	Map<String, Map<String, String>> students = new HashMap<String, Map<String, String>>();
				
				do {
					// check the role
		        	String role = rs.getString("user.role");     	
		        	System.out.println(role);
	        	
	        	    if(role.equals("student")) {
	        	
	        			//get student information from the database
		        		String firstName=rs.getString("user.firstName");
		        		String lastName=rs.getString("user.lastName");
		        		String studentID=rs.getString("userID");
		        		
		        		// use an inner map to store firstName and lastName
                        Map<String, String> studentName = new HashMap<>();
	            		studentName.put("firstName", firstName);
	            		studentName.put("lastName", lastName);
	            		
	            		students.put(studentID, studentName);
	            	
	        		}

				}while(rs.next());
	        		
	        		if(students.size()!=0) {
	        			System.out.println("the students map is not empty");
	        			
	        		}
	        	
	        		//store the students in the request scope
           	        request.setAttribute("students", students);  
           	     
           	     RequestDispatcher dispatcher = request.getRequestDispatcher("enrolledstudents.jsp");
  			     dispatcher.forward(request, response);
				
			}
			
			rs.close();
      	    ps.close();
      	    con.close();
						
	}catch (SQLException e) {	
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}

	
}
}
