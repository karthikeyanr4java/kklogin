package com.example.kklogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.kklogin.config.service.MyUserDetailsService;
import com.example.kklogin.exception.CustomException;
import com.example.kklogin.model.AuthenticationRequest;
import com.example.kklogin.model.AuthenticationResponse;
import com.example.kklogin.util.JwtUtil;

@CrossOrigin
@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public AuthenticationRequest login(@RequestBody AuthenticationRequest user) {
		return user;
	}
	
	@GetMapping("/login")
	public AuthenticationRequest login() {
		AuthenticationRequest user = new AuthenticationRequest("karthik", "karthik");
		return user;
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello KK !";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.ok(new AuthenticationResponse(null,"Inivalid Credentials"));
		}
		
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
}
