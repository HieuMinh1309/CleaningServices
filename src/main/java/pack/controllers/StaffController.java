package pack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pack.utils.Views;

@RequestMapping("/staff")
@Controller
public class StaffController {

	
	
	@GetMapping("")
	public String index() {
		return Views.STAFF_INDEX;
	}
	
}