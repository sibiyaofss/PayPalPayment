package com.paypal.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	HttpSession httpSession;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = findUserbyUername(username);

		UserBuilder builder = null;
		if (user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
			builder.roles(user.getRoles());
			httpSession.setAttribute("loggedInUser", user.getUsername());
		} else {
			throw new UsernameNotFoundException("User not found.");
		}

		return builder.build();
	}

	private User findUserbyUername(String username) {
		if (username.equalsIgnoreCase("AP2020002")) {
			return new User(username, "admin123", "USER");
		} else if (username.equalsIgnoreCase("AP2020001")) {
			return new User(username, "admin123", "USER");
		} else if (username.equalsIgnoreCase("AP2020003")) {
			return new User(username, "admin123", "USER");
		}if (username.equalsIgnoreCase("AP2020004")) {
			return new User(username, "admin123", "USER");
		}if (username.equalsIgnoreCase("AP2020005")) {
			return new User(username, "admin123", "USER");
		}
		return null;
	}
}