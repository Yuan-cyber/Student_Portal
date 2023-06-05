

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewMarksServlet
 */
@WebServlet("/ViewMarksServlet")
public class ViewMarksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMarksServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		//get the studentID from the session and courseID from the jsp page
		String userID=(String)request.getSession().getAttribute("userID");
		String courseID=request.getParameter("courseID");
		
		//connect to the database
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String DB_URL = "jdbc:mysql://localhost/mydatabase";
			String USER = "root";
			String PASS = "zhaoyuan960318";
			
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("connected");
			
			// SQL statement to retrieve assessment from the database
			String sql="SELECT*FROM assessment where userID=? AND courseID=?";
			
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, userID);
			ps.setString(2, courseID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
			
			String quiz=rs.getString("quiz");
			String assignment=rs.getString("assignment");
			String finalExam=rs.getString("finalExam");
			System.out.println("assessment data:"+quiz+assignment+finalExam);
			
			//store assessment information in the request scope
			request.setAttribute("quiz", quiz);
			request.setAttribute("assignment",assignment);
			request.setAttribute("finalExam",finalExam);
			
			
			//redirect to the marks.jsp page
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewmarks.jsp");
			dispatcher.forward(request, response);
		
			} else {
			//no assessment data found for the given studentID and courseID
			pw.println("There is no assessment data");
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
