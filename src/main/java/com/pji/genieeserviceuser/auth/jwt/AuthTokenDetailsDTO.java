package com.pji.genieeserviceuser.auth.jwt;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import java.util.Date;
import java.util.List;

public class AuthTokenDetailsDTO {
	public String userId;
	public String email;
	public List<String> roleNames;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date expirationDate;
}
