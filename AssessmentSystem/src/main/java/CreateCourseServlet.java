

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateCourseServlet
 */
@WebServlet("/CreateCourseServlet")
public class CreateCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCourseServlet() {
        super();
       
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
				
		// get parameters from request object
			String courseId=request.getParameter("courseid");
			String courseName=request.getParameter("coursename");
			String semester=request.getParameter("semester");
				
		//connect to the database
	try {
		Class.forName("com.mysql.jdbc.Driver");
		
		String DB_URL = "jdbc:mysql://localhost/mydatabase";
		String USER = "root";
		String PASS = "zhaoyuan960318";
		
		Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
		System.out.println("connected");
		
		//statement
		String sql="INSERT INTO course (courseID, courseName, semester)VALUES (?, ?, ?)";
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setString(1, courseId);
		ps.setString(2, courseName);
		ps.setString(3, semester);
		
		ps.executeUpdate();
		
		ps.close();
        con.close();
        
        //after finishing the update, set a success message in a session attribute
        request.getSession().setAttribute("successMsg", "Course created successfully!");
        
        //return to the admin page
        response.sendRedirect("admin.jsp");
		
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		pw.println("Error: " + e.getMessage());
	}

}
}
