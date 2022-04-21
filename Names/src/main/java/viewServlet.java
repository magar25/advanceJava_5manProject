import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/showdata")
public class viewServlet extends HttpServlet {
	private final static String query= "SELECT * FROM `stores_name`";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get PrintWriter
		
		PrintWriter pw= res.getWriter();
		
		// set content type
		
		res.setContentType("text/html");
		pw.println("<h2>List of different STORES</h2>");
		
		//getting values
		
		
		
		//load the JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		// connectiong to DB
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///shopping center","root","");
				PreparedStatement ps= con.prepareStatement(query); ){
			
			//view datas
			ResultSet rs = ps.executeQuery();
			pw.println("<table>");
					pw.println("<tr>");
					pw.println("<th>ID</th>");
					pw.println("<th>Name</th>");
					pw.println("<th>Edit</th>");
					pw.println("<th>Delete</th>");
					pw.println("</tr>");
					while(rs.next()) {
					pw.println("<tr>");
					pw.println("<td>"+rs.getInt(1)+"</td>");
					pw.println("<td>"+rs.getString(2)+"</td>");
					pw.println("<td><a href='editUrl?id="+rs.getInt(1)+"'>Edit</a></td>");
					pw.println("<td><a href='deleteUrl?id="+rs.getInt(1)+"'>Delete</a></td>");
					pw.println("</tr>");
					}
			
			pw.println("</table>");
		
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
