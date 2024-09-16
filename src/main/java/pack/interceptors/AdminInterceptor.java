package pack.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pack.repositories.AdminRepository;

@Component
public class AdminInterceptor implements HandlerInterceptor{
	@Autowired
	AdminRepository rep;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		Object username = request.getSession().getAttribute("username");
		Object pw = request.getSession().getAttribute("password");
		
		if(username == null || pw == null) {
			request.getSession().setAttribute("loginError", "Please enter your username and password.");
			response.sendRedirect("/admin/login");
			return false;
		}
		
		if(rep.ExistsAdminCheck(String.valueOf(username), String.valueOf(pw))) {
			return true;
		}
		
		request.getSession().setAttribute("loginError", "Invalid username or password.");
		response.sendRedirect("/admin/login");
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
