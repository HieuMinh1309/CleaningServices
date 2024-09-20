package pack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pack.utils.Views;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping("/accounts")
	public String accounts() {
		return Views.USER_ACCOUNTS;
	}

	@GetMapping("/signup")
	public String signup() {
		return Views.USER_SIGNUP;
	}

	@GetMapping("/login")
	public String login() {
		return Views.USER_LOGIN;
	}

}
