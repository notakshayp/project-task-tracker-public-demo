package com.tasktracker.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.akshay.db.AuthUser;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("username");
		String password = request.getParameter("password");
		AuthUser auth = new AuthUser();
		if (auth.verfiyUser(user, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", user);
			response.sendRedirect("./tasktrackerPages/dashboard.jsp");
		} else {
			request.setAttribute("errorMessage", "Login failed please check username/password");
			RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
			rd.forward(request, response);
		}
	}

}
