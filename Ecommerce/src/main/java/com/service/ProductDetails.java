package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductDetails")
public class ProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductDetails() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		try {
			int id = Integer.parseInt(request.getParameter("txtProductID"));
		
			out.println("<html><body>");

			InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
			Properties props = new Properties();
			props.load(in);

			DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("userid"),
					props.getProperty("password"));
			PreparedStatement ps = conn.getConnection().prepareStatement("Select * from eproduct where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				out.println("<table border=2>");
				out.println("<tr><td>" + rs.getInt(1) + "</td><td> " + rs.getString(2) + "</td><td> "
						+ rs.getBigDecimal(3) + "</td><td> " + rs.getDate(4) + "</td></tr>");
				out.println("</table>");

			}else {
				out.println("<h3>Invalid ProductID </h3>");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);
			}
			ps.close();

			out.println("</body></html>");
			conn.closeConnection();

		} catch(NumberFormatException ne) {
			out.println("<h3> Please enter valid id. </h3>");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
