package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;

public class StartPageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("currentUser") != null) {
			User user = (User)session.getAttribute("currentUser");
			switch(user.getRole()) {
				case ADMIN:
					response.sendRedirect(request.getContextPath() + "/user/index.html");
					break;
				case MANAGER:
				case CASHIER:
					response.sendRedirect(request.getContextPath() + "/client/index.html");
					break;
				case CLIENT:
					response.sendRedirect(request.getContextPath() + "/account/index.html");
					break;
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login.html");
		}
	}
}
