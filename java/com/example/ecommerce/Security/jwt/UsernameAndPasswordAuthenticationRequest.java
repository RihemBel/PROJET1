package com.example.ecommerce.Security.jwt;

import com.example.ecommerce.entities.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public class UsernameAndPasswordAuthenticationRequest {
    private UUID userId;
    private String userName;
    private String password;
    private String login;
    private  String lang;
    private String pays;


    public UsernameAndPasswordAuthenticationRequest() {
    }

    public UsernameAndPasswordAuthenticationRequest(String token, Collection<? extends GrantedAuthority> authorities, UUID userId, String login, String userName, String lang, String pays) {
        this.userId = userId;
        this.login = login;
        this.userName = userName;
        this.lang = lang;
        this.pays = pays;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
}
