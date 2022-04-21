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
@WebServlet("/edit")
public class editRecordsServlet extends HttpServlet {
	private final static String query= "UPDATE `stores_name` SET `names`=? WHERE id=? ";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get PrintWriter
		
		PrintWriter pw= res.getWriter();
		
		// set content type
		
		res.setContentType("text/html"); 
		
		//getting values
		
		int id = Integer.parseInt(req.getParameter("id"));
		String names= req.getParameter("shopping");
		
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
			
			ps.setInt(1, id);
			ps.setString(2, names);
			
			//execute the query
			int count = ps.executeUpdate();
			if (count==1) {
				pw.println("<h2> Record Edited <h2>");
			}else {
				pw.println("<h2> Failed to Edit<h2>");
			}
		}catch(SQLException se) {
			pw.println("<h2>"+se.getMessage()+"<h2>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='home.jsp'><button >HOME</button></a>");
		pw.println("<a href='showdata'><button >Lists</button></a>");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}
}
