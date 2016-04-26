package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.UserStorage;

public class UserEditServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		try {
			user = UserStorage.findById(Integer.parseInt(request.getParameter("id")));
		} catch(NumberFormatException e) {}
		request.setAttribute("user", user);
		getServletContext().getRequestDispatcher("/WEB-INF/edit.html").forward(request, response);
	}
}
