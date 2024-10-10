package pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pack.repositories.AdminRepository;
import pack.utils.Views;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	AdminRepository rep;

	@GetMapping("")
	public String index() {
		return Views.ADMIN_INDEX;
	}
	
	@GetMapping("/login")
	public String login() {
		return Views.ADMIN_LOGIN;
	}
	
	@GetMapping("/accounts")
	public String account() {
		return Views.ADMIN_ACCOUNTS;
	}

}
