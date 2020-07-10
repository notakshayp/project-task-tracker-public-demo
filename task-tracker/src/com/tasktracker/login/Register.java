package com.tasktracker.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.akshay.db.AddUser;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmpass = request.getParameter("confirm-password");

		if (username.length() == 0) {
			request.setAttribute("errorMessage", "Nice try,But why ? Try a valid username");
			RequestDispatcher rd = request.getRequestDispatcher("./register.jsp");
			rd.forward(request, response);
		} else if (password.length() == 0) {
			request.setAttribute("errorMessage", "Seriously? An empty password?");
			RequestDispatcher rd = request.getRequestDispatcher("./register.jsp");
			rd.forward(request, response);
		} else if (confirmpass.equals(password)) {
			AddUser add = new AddUser();
			if (add.addUser(username, password)) {
				request.setAttribute("successMessage", "Account Created. Login here!!");
				RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Account Creation failed. Provide an unique Username!!");
				RequestDispatcher rd = request.getRequestDispatcher("./register.jsp");
				rd.forward(request, response);
			}
		} else {
			request.setAttribute("errorMessage", "Password Mismatch");
			RequestDispatcher rd = request.getRequestDispatcher("./register.jsp");
			rd.forward(request, response);
		}

	}

}
