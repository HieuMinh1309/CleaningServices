package pack.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pack.repositories.UserRepository;

@Component
public class UserInterceptor implements HandlerInterceptor{
	@Autowired
	UserRepository rep;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		Object username = request.getSession().getAttribute("username");
		Object password = request.getSession().getAttribute("password");
		
		if(username == null && password == null) {
			request.getSession().setAttribute("loginError", "Please enter your username and password.");
			response.sendRedirect("user/login");
			return false;
		}
		
		if(rep.ExistsUserCheck(String.valueOf(username), String.valueOf(password))) {
			return true;
		}
		
		request.getSession().setAttribute("loginError", "Invalid username or password.");
		response.sendRedirect("/user/login");
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
