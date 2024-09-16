package pack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import pack.interceptors.*;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Autowired
	private AdminInterceptor adminInterceptor;
	private StaffInterceptor staffInterceptor;
	private UserInterceptor userInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/login", "user/login", "staff/login");
		registry.addInterceptor(userInterceptor).addPathPatterns("/user/**").excludePathPatterns("/user/login", "/user/checklogin");
		registry.addInterceptor(adminInterceptor).addPathPatterns("admin/**").excludePathPatterns("/admin/login", "/admin/checklogin");
		registry.addInterceptor(staffInterceptor).addPathPatterns("staff/**").excludePathPatterns("/staff/login", "/staff/checklogin");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
