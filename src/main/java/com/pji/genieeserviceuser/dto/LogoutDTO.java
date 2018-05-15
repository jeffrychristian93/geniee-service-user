package com.pji.genieeserviceuser.dto;

/**
 * @author Jeffry Christian
 * @since 11/05/2018
 */
public class LogoutDTO {
    public boolean success;
    public String response;
    public String redirect;

    public LogoutDTO(){}

    public LogoutDTO(boolean success, String response, String redirect){
        this.success = success;
        this.response = response;
        this.redirect = redirect;
    }
}
