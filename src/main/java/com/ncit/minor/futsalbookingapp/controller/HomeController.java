package com.ncit.minor.futsalbookingapp.controller;

import com.ncit.minor.futsalbookingapp.model.Booking;
import com.ncit.minor.futsalbookingapp.service.BookingService;
import com.ncit.minor.futsalbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	@Autowired
	BookingService bookingService;

	@GetMapping("/")

	public String index(Model model) {
		List<Booking>bookings= bookingService.findBookings();
		for(Booking b:bookings){
			if(b.getBookDate().getTime()+b.getBookTime().getTime()<new Date().getTime()){
				bookingService.delete(b);
			}
		}
		return "index";
	}

	@GetMapping("/authenticated")
    public String getAuthIndex(Principal principal){
	    System.out.println(userService.findByUsername(principal.getName()).getUserRole());
        if (userService.findByUsername(principal.getName()).getUserRole().equals("ADMIN")) {
            return "redirect:/admin";
        }
        return "redirect:/";
    }
	@GetMapping("/login")
	public String login() {

		return "signin";
	}
}
