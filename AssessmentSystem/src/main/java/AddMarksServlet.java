

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
 * Servlet implementation class AddMarksServlet
 */
@WebServlet("/AddMarksServlet")
public class AddMarksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMarksServlet() {
        super();
        
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		//get parameters
		String studentID=request.getParameter("studentID");
		String courseID=request.getParameter("courseID");
		String quiz=request.getParameter("quiz");
		String assignment=request.getParameter("assignment");
		String finalExam=request.getParameter("finalExam");
		
		//connect to the database
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String DB_URL = "jdbc:mysql://localhost/mydatabase";
			String USER = "root";
			String PASS = "zhaoyuan960318";
			
			Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("connected");
			
			//statement
			String sql="INSERT INTO assessment (userID,courseID,quiz,assignment,finalExam) VALUES (?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, studentID);
			ps.setString(2,courseID);
			ps.setString(3,quiz);
			ps.setString(4,assignment);
			ps.setString(5,finalExam);
			
			ps.executeUpdate();
			
			ps.close();
	        con.close();
	        
	        //after finishing the update, print a success message
	        pw.println("successfully!");
					
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		pw.println("Error: " + e.getMessage());
	}

}
}
