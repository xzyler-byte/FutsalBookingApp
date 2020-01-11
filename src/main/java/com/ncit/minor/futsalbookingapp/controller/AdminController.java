package com.ncit.minor.futsalbookingapp.controller;

import com.ncit.minor.futsalbookingapp.model.User;
import com.ncit.minor.futsalbookingapp.service.UserService;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {
	UserService userService;
	PasswordEncoder passwordEncoder;
	AdminController(UserService userService,PasswordEncoder passwordEncoder)
	{
		this.userService=userService;
		this.passwordEncoder=passwordEncoder;
	}

	@GetMapping("/admin")
	public String showAdminPage(Model model, Principal principal)
	{
		User admin=userService.findByUsername(principal.getName());
		String name="ADMIN";
		if(admin.getUserRole().equals(name)) {
			model.addAttribute("admin", admin);
			return "admin";
		}
		return "error";
	}
	@GetMapping("/admin/update")
	public String showAdminUpdateProfileForm(Model model, Principal principal)
	{
		model.addAttribute("admin",userService.findByUsername(principal.getName()));
		return "updateAdminProfile";
	}
	@PostMapping("/admin/update/{userId}")
	public String updateAdminProfile(@PathVariable Long userId, @ModelAttribute User user, Model model, Principal principal)
	{
		if(!userService.getUsers().contains(userService.findByUsername(user.getUsername()))|| userService.findById(userId).getUsername().equals(user.getUsername())) {
			User currentUser = userService.findByUsername(principal.getName());
			currentUser.setUsername(user.getUsername());
			currentUser.setEmail(user.getEmail());
			currentUser.setFullname(user.getFullname());
			currentUser.setPhoneNo(user.getPhoneNo());

			currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
			userService.save(currentUser);
		}
		else{
			model.addAttribute("error",true);
			return "redirect:/admin/update";
		}
		return "redirect:/";
	}
}
