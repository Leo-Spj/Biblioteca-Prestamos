package com.utp.biblioteca.resources.modelo.login;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class LoginBean {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        if ("admin".equals(username) && "admin".equals(password)) {
            return "welcome.xhtml";
        } else {
            return "login.xhtml";
        }
    }
}
