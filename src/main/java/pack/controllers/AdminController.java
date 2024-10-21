package pack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import pack.models.Admin;
import pack.models.Blog;
import pack.models.Order;
import pack.models.Service;
import pack.models.Staff;
import pack.repositories.AdminRepository;
import pack.utils.SecurityUtility;
import pack.utils.Views;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	AdminRepository rep;
	// -------------------- INDEX & ACCOUNT --------------------//

	@GetMapping("")
	public String index() {
		return Views.ADMIN_INDEX;
	}

	@GetMapping("/login")
	public String login() {
		return Views.ADMIN_LOGIN;
	}

	@PostMapping("/checklogin")
	public String chklogin(@RequestParam("usrname") String username, @RequestParam("pw") String password,
			HttpServletRequest req, Model model) {
		Admin find = rep.findAdminByUsername(username);
		if (find == null) {
			model.addAttribute("loginError", "Account doesn't exists, please check again!");
			return Views.ADMIN_LOGIN;
		}

		if (!SecurityUtility.compareBcrypt(find.getPassword(), password)) {
			model.addAttribute("loginError", "Password incorrect!");
			return Views.ADMIN_LOGIN;
		}

		req.getSession().setAttribute("adminId", find.getId());
		return "redirect:/admin";
	}

	@GetMapping("/accounts")
	public String account(HttpServletRequest req, Model model) {
		String find = (String) req.getSession().getAttribute("username");
		Admin admin = rep.findAdminByUsername(find);
		model.addAttribute("admin", admin);
		return Views.ADMIN_ACCOUNTS;
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		req.getSession().invalidate();
		return "redirect:/admin/login";
	}

	// -------------------- BLOGS --------------------//

	@GetMapping("/blogs/blogList")
	public String blogList(Model model) {
		List<Blog> list = rep.getBlogs();
		model.addAttribute("blogs", list);
		return Views.ADMIN_BLOGS_LIST;
	}

	@GetMapping("/blogs/blogCreate")
	public String blogCreate(Model model) {
		model.addAttribute("new_item", new Blog());
		return Views.ADMIN_BLOGS_CREATE;
	}

	@PostMapping("/blogs/create")
	public String create_blog(@ModelAttribute("new_item") Blog blog, Model model) {
		try {
			String create = rep.newBlog(blog);
			if (create.equals("success")) {
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
		try {
			Blog find = rep.findBlogById(id);
			if (find != null) {
				model.addAttribute("edit_item", find);
				return Views.ADMIN_BLOGS_EDIT;
			} else {
				return "redirect:/admin/blogs/blogList";
			}
		} catch (Exception e) {
			System.out.println("System error: " + e.getMessage());
			model.addAttribute("catchError", "An unexpected error occurred. Please try again later.");
			return Views.ADMIN_BLOGS_EDIT;
		}
	}

	@PostMapping("/blogs/edit")
	public String edit_blog(@ModelAttribute("edit_item") Blog blog, Model model) {
		try {
			String edit = rep.editBlog(blog);
			if (edit.equals("success")) {
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
	public String blog_delete(int id) {
		rep.deleteBlog(id);
		return "redirect:/admin/blogs/blogList";
	}

	// -------------------- SERVICES --------------------//
	@GetMapping("/services/serviceList")
	public String serviceList(Model model) {
		List<Service> list = rep.getServices();
		model.addAttribute("services", list);
		return Views.ADMIN_SERVICES_LIST;
	}

	@GetMapping("/services/serviceCreate")
	public String serviceCreate(Model model) {
		model.addAttribute("new_item", new Service());
		return Views.ADMIN_SERVICES_CREATE;
	}

	@PostMapping("/services/create")
	public String create_service(@ModelAttribute("new_item") Service ser, Model model) {
		try {
			String create = rep.newService(ser);
			if (create.equals("success")) {
				return "redirect:/admin/services/serviceList";
			}
			model.addAttribute("catchError", "Failed to create service, please try again.");
			return Views.ADMIN_SERVICES_CREATE;
		} catch (Exception e) {
			System.out.println("System error: " + e.getMessage());
			model.addAttribute("catchError", "An unexpected error occurred. Please try again later.");
			return Views.ADMIN_SERVICES_CREATE;
		}
	}

	@GetMapping("/services/serviceEdit")
	public String serviceEdit(int id, Model model) {
		try {
			Service find = rep.findServiceById(id);
			if (find != null) {
				model.addAttribute("edit_item", find);
				return Views.ADMIN_SERVICES_EDIT;
			} else {
				return "redirect:/admin/blogs/blogList";
			}
		} catch (Exception e) {
			System.out.println("System error: " + e.getMessage());
			model.addAttribute("catchError", "An unexpected error occurred. Please try again later.");
			return Views.ADMIN_SERVICES_EDIT;
		}
	}

	@PostMapping("/services/edit")
	public String edit_service(@ModelAttribute("edit_item") Service ser, Model model) {
		try {
			String edit = rep.editService(ser);
			if (edit.equals("success")) {
				return "redirect:/admin/services/serviceList";
			}
			model.addAttribute("catchError", "Failed to edit blog, please try again.");
			return Views.ADMIN_SERVICES_EDIT;
		} catch (Exception e) {
			System.out.println("System error: " + e.getMessage());
			model.addAttribute("catchError", "An unexpected error occurred. Please try again later.");
			return Views.ADMIN_SERVICES_EDIT;
		}
	}

	// -------------------- ORDERS --------------------//

	@GetMapping("/orders/orderList")
	public String orderList(Model model) {
		List<Order> list = rep.getOrders();
		model.addAttribute("orders", list);
		return Views.ADMIN_ORDERS_LIST;
	}

	// -------------------- STAFFS --------------------//

	@GetMapping("/staffs/staffList")
	public String staffList(Model model) {
		List<Staff> list = rep.getStaffs();
		model.addAttribute("staffs", list);
		return Views.ADMIN_STAFFS_LIST;
	}

	@GetMapping("/staffs/staffInfo")
	public String staff_info() {
		return Views.ADMIN_STAFFS_INFO;
	}

	@PostMapping("/staffs/assignJob")
	public String assign() {
		return "";
	}

	@PostMapping("/staffs/replaceStaff")
	public String replace() {
		return "";
	}
}
