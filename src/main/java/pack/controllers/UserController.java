package pack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import pack.models.Order;
import pack.models.User;
import pack.repositories.UserRepository;
import pack.utils.SecurityUtility;
import pack.utils.Views;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	UserRepository rep;

	@GetMapping("/accounts")
	public String accounts(HttpServletRequest request, Model model) {
		 User user = rep.findUserbyUsername(request.getSession().getAttribute("username").toString());
		 List<Order> orderList = rep.OrderList((int)request.getSession().getAttribute("usrId"));
		model.addAttribute("user", user);
		model.addAttribute("orders", orderList);
		return Views.USER_ACCOUNTS;
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("new_item", new User());
		return Views.USER_SIGNUP;
	}

	@PostMapping("/newUser")
	public String createUser(@ModelAttribute("new_item") User user, HttpServletRequest request, Model model) {
		if (!user.getPhone().matches("\\d{10,11}")) {
			model.addAttribute("error", "Phone number must contain only digits and be 10 or 11 digits long.");
			return Views.USER_SIGNUP;
		}
		if (!user.getConfirmPassword().equals(user.getPassword())) {
			model.addAttribute("error", "The password and confirm password do not match.");
			return Views.USER_SIGNUP;
		}
		try {
			String result = rep.newUser(user);
			if (result.equals("success")) {
				return "redirect:/user/login";
			}
			model.addAttribute("error", "Failed to create user, please try again.");
			return Views.USER_SIGNUP;
		} catch (IllegalArgumentException e) {
			model.addAttribute("error", "Some information(username, email, phone) may already exists.");
			return Views.USER_SIGNUP;
		} catch (Exception e) {
			System.out.println("System error: " + e.getMessage());
			model.addAttribute("error", "An unexpected error occurred. Please try again later.");
			return Views.USER_SIGNUP;
		}
	}

	@GetMapping("/login")
	public String login() {
		return Views.USER_LOGIN;
	}

	@PostMapping("/checklogin")
	public String chcklogin(@RequestParam("usrname") String username, @RequestParam("pw") String password,
			HttpServletRequest request, Model model) {
		User user = rep.findUserbyUsername(username);

		if (user == null) {
			model.addAttribute("loginError", "Username does not exist.");
			return Views.USER_LOGIN;
		}

		if (!SecurityUtility.compareBcrypt(user.getPassword(), password)) {
			model.addAttribute("loginError", "Incorrect password.");
			return Views.USER_LOGIN;
		}

		request.getSession().setAttribute("usrId", user.getId());
		request.getSession().setAttribute("username", user.getUsername());
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
	    request.getSession().invalidate();
	    return "redirect:/user/login";
	}
	
}
