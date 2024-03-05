package com.kodnest.tunehub.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.TunehubRepository;
import com.kodnest.tunehub.service.TunehubService;

@Service
public class TunehubServiceimpl implements TunehubService{

	@Autowired
	TunehubRepository tr;

	public String addUser(User user) {
		tr.save(user);
		return "success";

	}

	// to check the duplicate entries
	public boolean emailExists(String email) {
		if(tr.findByEmail(email) != null) {
			return true;
		}
		else {
			return false;
		}

	}

	public boolean validateUser(String email, String password) {
		//		if(tr.findByEmail(email) != null && tr.findByPassword(password)!=null){
		//			return true;
		//		}
		//		else {
		//			return false;
		//		}

		User byEmail = tr.findByEmail(email);

		if(byEmail != null) {
			String pwd = byEmail.getPassword();

			String dbmail = byEmail.getEmail();

			if(password.equals(pwd) && email.equals(dbmail)) {
				return true;
			}	
		}
		return false;
	}



	public String getRole(String email) {
		User byEmail = tr.findByEmail(email);
		String role = byEmail.getRole();
		return role;
	}

	@Override
	public User getUser(String email) {
		return tr.findByEmail(email);
	}

	@Override
	public void updateUser(User user) {
		tr.save(user);
		
	}
}