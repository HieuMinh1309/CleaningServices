package pack.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import pack.Services.OtpService;
import pack.models.Order;
import pack.models.User;
import pack.repositories.UserRepository;
import pack.utils.FileUtility;
import pack.utils.SecurityUtility;
import pack.utils.Views;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	UserRepository rep;

	@Autowired
	OtpService otpService;

	// -------------------- INDEX & ACCOUNT --------------------//

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

	@GetMapping("/accounts")
	public String accounts(HttpServletRequest request, Model model) {
		User user = rep.findUserbyUsername(request.getSession().getAttribute("username").toString());
		List<Order> orderList = rep.OrderList((int) request.getSession().getAttribute("usrId"));
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

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/user/login";
	}

	@GetMapping("/editProfile")
	public String editpage(Model model, HttpServletRequest request) {
		User user = rep.findUserbyId((int) request.getSession().getAttribute("usrId"));
		model.addAttribute("user", user);
		return Views.USER_EDIT_PROFILE;
	}

	@PostMapping("/updatePro")
	public String updatePro(@RequestParam(required = false) MultipartFile image, @ModelAttribute User user, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		try {
			User oldUserInfo = rep.findUserbyId((int) request.getSession().getAttribute("usrId"));
			
			if (user.getPassword() != null && !user.getPassword().equals(user.getConfirmPassword())) {
				model.addAttribute("error", "Password and Confirm Password are not match.");
				return Views.USER_EDIT_PROFILE;
			}
			
			if(user.getPassword() != null && SecurityUtility.compareBcrypt(oldUserInfo.getPassword(), user.getPassword())) {
				model.addAttribute("error", "You have used this password before. Please choose a different one.");
				return Views.USER_EDIT_PROFILE;
			}

			if (image != null && !image.isEmpty()) {
				user.setImage(FileUtility.uploadFileImage(image, "upload"));
			}

			String result = rep.editProfile(user);
			if ("success".equals(result)) {
				redirectAttributes.addFlashAttribute("message", "Profile updated successfully.");
				return "redirect:/user/accounts";
			} else {
				model.addAttribute("error", "Failed to update profile: " + result);
				return Views.USER_EDIT_PROFILE;
			}
		} catch (IllegalArgumentException e) {
			model.addAttribute("error", e.getMessage());
			return Views.USER_EDIT_PROFILE;
		} catch (Exception e) {
			model.addAttribute("error", "An error occurred: " + e.getMessage());
			return Views.USER_EDIT_PROFILE;
		}
	}

	@GetMapping("/forgotpass")
	public String FPassPage() {
		return Views.USER_FORGOT_PASSWORD;
	}

	@PostMapping("/getOTP")
	public String SendOtp(@RequestParam("phone") String phoneNumber, Model model, HttpServletRequest request) {
		try {
			if (!phoneNumber.matches("\\d{10,11}")) {
				model.addAttribute("pageError", "Invalid phone number.");
				return Views.USER_FORGOT_PASSWORD;
			}
			User user = rep.checkPhoneNumberExists(phoneNumber);
			if (user == null) {
				model.addAttribute("pageError", "Invalid phone number.");
				return Views.USER_FORGOT_PASSWORD;
			}
			otpService.generateOTP(phoneNumber);
			request.getSession().setAttribute("phoneNumber", user.getPhone());
			return "redirect:/user/InputOtp";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping("/InputOtp")
	public String validatePage() {
		return Views.USER_VALIDATE;
	}

	@PostMapping("/verification")
	public String verify(@RequestParam String otp, HttpServletRequest request, Model model) {
		String phoneNumber = request.getSession().getAttribute("phoneNumber").toString();
		User user = rep.checkPhoneNumberExists(phoneNumber);
		if (!otpService.validateOtp(phoneNumber, otp)) {
			model.addAttribute("error", "Invalid otp");
			return Views.USER_VALIDATE;
		} else if (otpService.isOtpExpired(phoneNumber)) {
			model.addAttribute("error", "OTP is expired.");
			return Views.USER_VALIDATE;
		}

		request.getSession().setAttribute("usrId", user.getId());
		return "redirect:/user/accounts";
	}
}
