package com.pji.genieeserviceuser.dto;

// TODO Add fields to this class that the client can use, such as userId, email, roles
public class AuthTokenDTO {

	public AuthData data;

	public AuthTokenDTO(){
		data = new AuthData();
	}

	public static class AuthData {
		public String token;
		public String messages;
		public String errors;
	}
}
