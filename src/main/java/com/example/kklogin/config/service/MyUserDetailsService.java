package com.example.kklogin.config.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.kklogin.entity.Users;
import com.example.kklogin.repo.UsersRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users  user = usersRepository.findByUsername(username).get();
		System.out.println("kk>>> {}" + user.toString());
		return new User(user.getUsername(),user.getPassword(), new ArrayList<>());
	}

}
