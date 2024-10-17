package pack.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import pack.models.Admin;
import pack.models.Blog;
import pack.repositories.AdminRepository;
import pack.utils.SecurityUtility;
import pack.utils.Views;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	AdminRepository rep;
	// -------------------- INDEX & ACCOUNT --------------------//

	public Admin getAdmin(HttpServletRequest req, Model model) {
		Admin admin = rep.findAdminById((int) req.getSession().getAttribute("adminId"));
		model.addAttribute("admin", admin);
		return admin;
	}

	@GetMapping("")
	public String index(HttpServletRequest req, Model model) {
		Admin admin = getAdmin(req, model);
		return Views.ADMIN_INDEX;
	}

	@GetMapping("/login")
	public String login() {
		return Views.ADMIN_LOGIN;
	}

	@PostMapping("/checklogin")
	public String chklogin(@RequestParam("usrname") String username, @RequestParam("pw") String password,
			HttpServletRequest req, Model model) {
		Admin admin = rep.findAdminbyUsername(username);
		if (admin == null) {
			model.addAttribute("loginError", "Account doesn't exists, please check again!");
			return Views.ADMIN_LOGIN;
		}

		if (!SecurityUtility.compareBcrypt(admin.getPassword(), password)) {
			model.addAttribute("loginError", "Password incorrect!");
			return Views.ADMIN_LOGIN;
		}

		req.getSession().setAttribute("adminId", admin.getId());
		return "redirect:/admin";
	}

	@GetMapping("/accounts")
	public String account() {
		return Views.ADMIN_ACCOUNTS;
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		req.getSession().invalidate();
		return "redirect:/admin/login";
	}

	// -------------------- BLOGS --------------------//

	@GetMapping("/blogs/blogList")
	public String blogList(HttpServletRequest req, Model model) {
		List<Blog> blog = rep.getBlogs();
		model.addAttribute("blogs", blog);
		return Views.ADMIN_BLOGS_LIST;
	}

	@GetMapping("/blogs/blogCreate")
	public String blogCreate(HttpServletRequest req, Model model) {
		model.addAttribute("new_item", new Blog());
		return Views.ADMIN_BLOGS_CREATE;
	}

	@PostMapping("/blogs/create")
	public String writeBlog(@ModelAttribute("new_item") Blog blog, HttpServletRequest req, Model model) {
		try {
			String newBlog = rep.newBlog(blog);
			if (newBlog.equals("success")) {
				return "redirect:/admin/blogs/blogList";
			}
			model.addAttribute("catchError", "Failed to create blog, please try again.");
			return Views.ADMIN_BLOGS_CREATE;
		} catch (Exception e) {
			System.out.println("System error: " + e.getMessage());
			model.addAttribute("catchError", "An unexpected error occurred. Please try again later.");
			return Views.ADMIN_BLOGS_CREATE;
		}
	}

	@GetMapping("/blogs/blogEdit")
	public String blogEdit(int id, Model model) {
		Blog blog = rep.findBlogById(id);
		if (blog != null) {
			model.addAttribute("edit_item", blog);
			return Views.ADMIN_BLOGS_EDIT;
		} else {
			return "redirect:/admin/blogs/blogList";
		}
	}

	@PostMapping("/blogs/edit")
	public String rewriteBlog(@ModelAttribute("edit_item") Blog blog, HttpServletRequest req, Model model) {
		try {
			String editBlog = rep.editBlog(blog);
			if (editBlog.equals("success")) {
				return "redirect:/admin/blogs/blogList";
			}
			model.addAttribute("catchError", "Failed to edit blog, please try again.");
			return Views.ADMIN_BLOGS_EDIT;
		} catch (Exception e) {
			System.out.println("System error: " + e.getMessage());
			model.addAttribute("catchError", "An unexpected error occurred. Please try again later.");
			return Views.ADMIN_BLOGS_EDIT;
		}
	}

	@PostMapping("/blogs/delete")
	public String delete(int id) {
		rep.deleteBlog(id);
		return "redirect:/admin/blogs/blogList";
	}

	// -------------------- ORDERS --------------------//

	@GetMapping("/orders/orderList")
	public String orderList() {
		return Views.ADMIN_ORDERS_LIST;
	}

	// -------------------- STAFFS --------------------//

	@GetMapping("/staffs/staffList")
	public String staffList() {
		return Views.ADMIN_STAFFS_LIST;
	}
}
