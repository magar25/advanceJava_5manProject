import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.*;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/register")

public class RegisterServlet extends HttpServlet {

	private final static String query= "insert into stores_name(names) values (?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get PrintWriter
		
		PrintWriter pw= res.getWriter();
		
		// set content type
		
		res.setContentType("text/html"); 
		
		//getting values
		
		String names = req.getParameter("shopping");
		
		//load the JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		// connectiong to DB
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///shopping center","root","");
				PreparedStatement ps= con.prepareStatement(query); ){
			
			//set the values
			ps.setString(1, names);
			
			//execute the query
			int count = ps.executeUpdate();
			if (count==1) {
				pw.println("<h2> Recorded <h2>");
			}else {
				pw.println("<h2> Failed<h2>");
			}
		}catch(SQLException se) {
			pw.println("<h2>"+se.getMessage()+"<h2>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='home.jsp'><button >HOME</button></a>");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}
}
