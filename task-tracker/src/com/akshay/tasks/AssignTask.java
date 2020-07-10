package com.akshay.tasks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.akshay.db.AddTask;
import com.akshay.db.CheckUser;

/**
 * Servlet implementation class AssignTask
 */
@WebServlet("/AssignTask")
public class AssignTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String assignedBy = session.getAttribute("username").toString();
		String assignedTo = request.getParameter("assignedTo");
		String Desc = request.getParameter("taskDesc");

		CheckUser valid = new CheckUser();
		if (valid.isUser(assignedTo)) {

			AddTask taskAdder = new AddTask();
			if (taskAdder.addTaskToDB(Desc, assignedBy, assignedTo)) {

				session.setAttribute("successMessage", "Task was assigned to \"" + assignedTo + "\" .");
				response.sendRedirect("./tasktrackerPages/Assigned.jsp");
			} else {

				session.setAttribute("errorMessage", "Something went wrong!!");

				response.sendRedirect("./tasktrackerPages/Assigned.jsp");
			}
		} else {

			session.setAttribute("errorMessage", "User \"" + assignedTo + "\" does not exist.");

			response.sendRedirect("./tasktrackerPages/Assigned.jsp");
		}
	}

}
