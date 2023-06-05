

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // 
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		// get parameters from request object
		String userId=request.getParameter("username");
		String password=request.getParameter("password");
		String firstName=request.getParameter("firstname");
		String lastName=request.getParameter("lastname");
		String p=request.getParameter("phone");
		int phone=Integer.parseInt(p);
		String role=request.getParameter("role");
		
		//connect to the database
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String DB_URL = "jdbc:mysql://localhost/mydatabase";
			String USER = "root";
			String PASS = "zhaoyuan960318";
			
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("connected");
			
			// create statement for inserting data into the user table
			String sql = "INSERT INTO User (userID, password, firstName, "
					+ "lastName, phone, role) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1,userId);
			st.setString(2, password);
			st.setString(3, firstName);
			st.setString(4, lastName);
			st.setInt(5, phone);
			st.setString(6, role);
			
			//execute
			st.executeUpdate();
			
			st.close();
	        con.close();
	        
	        //after finishing the update, set a success message in a session attribute
	        request.getSession().setAttribute("successMsg", "User created successfully!");

	        //return to the admin page
	        response.sendRedirect("admin.jsp");
	        
			
		}catch (SQLException e) {
			pw.println("Error: " + e.getMessage());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		}
	
	}


