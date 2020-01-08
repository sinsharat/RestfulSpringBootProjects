package com.sharat.springbootstarter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sharat.springbootstarter.info.User;
import com.sharat.springbootstarter.repository.UserDetailsRepository;
import com.sharat.springbootstarter.service.utils.AppUserDetails;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailsReository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userDetailsReository.findByUserName(username);
		user.orElseThrow(() -> new UsernameNotFoundException("User does not exist : " + username));

		return user.map(AppUserDetails::new).get();
	}

}
