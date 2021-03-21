package com.example.kklogin.model;

public class AuthenticationResponse {

	private String jwt;
	private String message;

	public AuthenticationResponse() {
		super();
	}

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}
	
	public AuthenticationResponse(String jwt, String message) {
		super();
		this.jwt = jwt;
		this.message = message;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
