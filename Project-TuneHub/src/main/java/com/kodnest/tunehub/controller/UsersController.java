package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.serviceimpl.SongServiceimpl;
import com.kodnest.tunehub.serviceimpl.TunehubServiceimpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {

	@Autowired
	TunehubServiceimpl ts;

	@Autowired
	SongServiceimpl ssl;

	@GetMapping("/register")
	public String addUser(@ModelAttribute User user) {

		//email taken from registeration form
		String email = user.getEmail();

		// checking email as enter in registeration form is present in db
		boolean status = ts.emailExists(email);

		if(status == false) {
			ts.addUser(user);
			System.out.println("User added");
		}
		else {
			System.out.println("User is already exist");
		}
		return "index";
	}


	@GetMapping("/validate")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {

		if(ts.validateUser(email,password) == true){

			String role = ts.getRole(email);

			session.setAttribute("email", email);

			if(role.equalsIgnoreCase("admin")) {
				return "admin";
			}
			else {

				User user = ts.getUser(email);

				boolean premium = user.isPremium();

				List<Song> songList = ssl.fetchAllSongs();
				model.addAttribute("songs",songList);

				model.addAttribute("ispremium", premium);

				return "customer";
			}
		}
		else {
			return "index";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "logout";
	}
	@GetMapping("/registeration")
	public String registeration() {
		return "registeration";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/index")
	public String index() {
		return "index";
	}
}
