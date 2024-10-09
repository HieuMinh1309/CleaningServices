package pack.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import pack.interceptors.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private AdminInterceptor adminInterceptor;

	@Autowired
	private StaffInterceptor staffInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/login", "/user/login", "/user/signup",
				"/staff/login");
		registry.addInterceptor(new UserInterceptor()).addPathPatterns("/user/**").excludePathPatterns("/user/login",
				"/user/signup", "/user/checklogin", "/user/newUser");
		registry.addInterceptor(adminInterceptor).addPathPatterns("admin/**").excludePathPatterns("/admin/login",
				"/admin/checklogin");
		registry.addInterceptor(staffInterceptor).addPathPatterns("staff/**").excludePathPatterns("/staff/login",
				"/staff/checklogin");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("upload", registry);
	}

	private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
		Path uploadDir = Paths.get(dirName);
		String uploadPath = uploadDir.toFile().getAbsolutePath();
		if (dirName.startsWith("../")) {
			dirName = dirName.replace("../", "");
		}
		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
	}
}
