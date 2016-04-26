package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserStorage;

public class UserDeleteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> ids = new ArrayList<>();
		for(String id : request.getParameterValues("id")) {
			try {
				ids.add(Integer.parseInt(id));
			} catch(NumberFormatException e) {}
		}
		UserStorage.delete(ids);
		response.sendRedirect(request.getContextPath() + "/index.html");
	}
}
