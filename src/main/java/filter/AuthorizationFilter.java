package filter;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest requestFilter = (HttpServletRequest) request;
		HttpServletResponse responseFilter = (HttpServletResponse) response;
		HttpSession session = requestFilter.getSession(false);

		BigDecimal id = (BigDecimal) session.getAttribute("id");

		if (session != null && id.equals(new BigDecimal(1))) {

			chain.doFilter(requestFilter, responseFilter);
		} else {

			responseFilter.sendRedirect(requestFilter.getContextPath() + "/faces/login.xhtml");
		}
	}

}
