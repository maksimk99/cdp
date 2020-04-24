package com.epam.cdp.maksim.katuranau.module11.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UserRegistrationDto {

    @NotBlank(message = "user login should not be empty")
    private String login;
    @NotBlank(message = "user password should not be empty")
    private String password;
    @NotNull
    @NotEmpty(message = "user roles should not be empty")
    private List<Integer> roles;

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(final List<Integer> roles) {
        this.roles = roles;
    }
}
