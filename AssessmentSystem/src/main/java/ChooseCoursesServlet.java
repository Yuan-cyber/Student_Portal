

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class ChooseCoursesServlet
 */
@WebServlet("/ChooseCoursesServlet")
public class ChooseCoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChooseCoursesServlet() {
        super();
        
    }
    
    
    /**
     * 
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		//get courseID from client side and retrieve studentID from the session
		String[] courseIDs = request.getParameterValues("courseID[]");
		System.out.println(courseIDs);
	    String userID = (String) request.getSession().getAttribute("userID");
	    
	    //connect to the database
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
			String DB_URL = "jdbc:mysql://localhost/mydatabase";
			String USER = "root";
			String PASS = "zhaoyuan960318";
			
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("connected");
	        
			//statement to add new record into user_course table
	        String sql = "INSERT INTO user_course (userID, courseID) VALUES (?, ?)";
	       
	        PreparedStatement ps = con.prepareStatement(sql);
	        
	        //use for loop to add each record into the batch
	        for(String courseID:courseIDs) {
	       
	        ps.setString(1, userID);
	        ps.setString(2, courseID);
	        ps.addBatch();}
	        
	        //execute the batch
	        ps.executeBatch();
	        
	        pw.println("successfully!");
	        	            
	       	        
	        ps.close(); 
	        con.close();
	        
	       
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        
	    }
	}

}
