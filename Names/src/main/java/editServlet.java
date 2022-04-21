import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/editUrl")
public class editServlet extends HttpServlet {
	private final static String query= "SELECT * FROM `stores_name` WHERE id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get PrintWriter
		
		PrintWriter pw= res.getWriter();
		
		// set content type
		
		res.setContentType("text/html");
		pw.println("<h2>List of different STORES</h2>");
		
		//getting values
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		//load the JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		// connectiong to DB
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///shopping center","root","");
				PreparedStatement ps= con.prepareStatement(query); ){
			//set value
			ps.setInt(1, id);
			//view datas
			ResultSet rs = ps.executeQuery();
			rs.next();
			pw.println("<form action='edit?id="+id+"'method='post'>");
			pw.println("<table>");
					pw.println("<tr>");
					pw.println("<th>Name</th>");
					pw.println("<td><input type='text' name='shopping' value='"+rs.getString(1)+"'></td>");
					pw.println("</tr>");
					pw.println("<tr>");
					pw.println("<th><button type='submit'>Edit</button></th>");
					pw.println("<td><button type='reset'>Cancel</button></td>");
					pw.println("</tr>");
			
			pw.println("</table>");
			pw.println("</form>");
		
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
