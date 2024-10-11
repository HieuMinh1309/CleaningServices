package pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import pack.models.Admin;
import pack.repositories.AdminRepository;
import pack.utils.SecurityUtility;
import pack.utils.Views;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	AdminRepository rep;

	@GetMapping("")
	public String index(HttpServletRequest request, Model model) {
		Admin admin = rep.findAdminById((int) request.getSession().getAttribute("adminId"));
		model.addAttribute("admin", admin);
		return Views.ADMIN_INDEX;
	}

	@GetMapping("/login")
	public String login() {
		return Views.ADMIN_LOGIN;
	}

	@PostMapping("/checklogin")
	public String chklogin(@RequestParam("usrname") String username, @RequestParam("pw") String password,
			HttpServletRequest request, Model model) {
		Admin admin = rep.findAdminbyUsername(username);
		if (admin == null) {
			model.addAttribute("loginError", "Account doesn't exists, please check again!");
			return Views.ADMIN_LOGIN;
		}

		if (!SecurityUtility.compareBcrypt(admin.getPassword(), password)) {
			model.addAttribute("loginError", "Password incorrect!");
			return Views.ADMIN_LOGIN;
		}

		request.getSession().setAttribute("adminId", admin.getId());
		return "redirect:/admin";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/admin/login";
	}

	@GetMapping("/accounts")
	public String account() {
		return Views.ADMIN_ACCOUNTS;
	}

}
