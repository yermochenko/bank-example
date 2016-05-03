package servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Role;
import domain.User;

public class SecurityFilter implements Filter {
	private Map<String, Set<Role>> permissions = new HashMap<>();

	@Override
	public void init(FilterConfig config) throws ServletException {
		try {
			Enumeration<String> uries = config.getInitParameterNames();
			while(uries.hasMoreElements()) {
				String uri = uries.nextElement();
				Set<Role> roles = new HashSet<>();
				for(String role : config.getInitParameter(uri).split(";")) {
					roles.add(Role.valueOf(role));
				}
				permissions.put(uri, roles);
			}
		} catch(IllegalArgumentException e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String uri = ((HttpServletRequest)request).getRequestURI();
		String contextPath = request.getServletContext().getContextPath();
		uri = uri.substring(contextPath.length());
		Set<Role> roles = permissions.get(uri);
		if(roles != null) {
			HttpSession session = ((HttpServletRequest)request).getSession(false);
			boolean accessBlocked = true;
			if(session != null) {
				User user = (User)session.getAttribute("currentUser");
				if(user != null && roles.contains(user.getRole())) {
					accessBlocked = false;
				}
			}
			if(accessBlocked) {
				((HttpServletResponse)response).sendRedirect(contextPath + "/login.html");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}
