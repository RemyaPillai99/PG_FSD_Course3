package com.businesslogic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginValidation() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uname = request.getParameter("txtUname");
		String pword = request.getParameter("txtPword");
		if (uname.equalsIgnoreCase("Rem") && pword.equals("password@123")) {
			// HttpSession is an interface. Session object is created by Servlet Container
			// -- session id-- getId()
			HttpSession session = request.getSession(true);
			session.setAttribute("Username", uname);
			RequestDispatcher rd = request.getRequestDispatcher("WelcomeServlet");
			rd.forward(request, response);
		} else {
			PrintWriter out = response.getWriter();// out object prints output on Web Browser
			out.println("<h3>Invalid Username and Password</h3>");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);

		}

	}

}
