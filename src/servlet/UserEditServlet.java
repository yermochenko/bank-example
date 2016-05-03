package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Role;
import domain.User;
import service.UserService;

public class UserEditServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = null;
		try {
			service = new UserService();
			User user = null;
			try {
				user = service.findById(Integer.parseInt(request.getParameter("id")));
			} catch(NumberFormatException e) {}
			request.setAttribute("user", user);
			if(user != null && user.getRole() == Role.CLIENT) {
				request.setAttribute("roles", Arrays.asList(Role.CLIENT));
			} else {
				request.setAttribute("roles", Role.employees());
			}
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
		} catch(SQLException e) {
			throw new ServletException(e);
		} finally {
			if(service != null) {
				service.close();
			}
		}
	}
}
