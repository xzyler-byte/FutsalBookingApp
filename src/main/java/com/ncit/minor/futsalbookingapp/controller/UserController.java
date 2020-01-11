package com.ncit.minor.futsalbookingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ncit.minor.futsalbookingapp.model.User;
import com.ncit.minor.futsalbookingapp.service.impl.UserServiceImpl;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/add")
	public String signUp(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/add")
	public String doSignUp(@ModelAttribute("user") User user, Model model) {
		if(user.getFullname().matches(".*\\d.*"))
		{
			model.addAttribute("isNumber",true);
			return "signup";
		}
		if (userService.findByUsername(user.getUsername()) != null) {
			model.addAttribute("usernameExists", true);
			return "signup";
		}
		else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			model.addAttribute("user", user);
			userService.save(user);
			System.out.println(user);
			return "redirect:/";
		}
	}
	@GetMapping("/update")
	public String showUpdateProfileForm(Model model, Principal principal)
	{
		model.addAttribute("user",userService.findByUsername(principal.getName()));
		return "updateUserProfile";
	}
	@PostMapping("/update/{userId}")
	public String updateUserProfile(@PathVariable Long userId, @ModelAttribute User user,Model model,Principal principal)
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
			return "updateUserProfile";
		}
		return "redirect:/";
	}
}
