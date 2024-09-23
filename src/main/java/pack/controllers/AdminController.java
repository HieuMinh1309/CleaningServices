package pack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pack.utils.Views;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	
	
	@GetMapping("")
	public String index() {
		return Views.ADMIN_INDEX;
	}
	
	
}
