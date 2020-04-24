package com.epam.cdp.maksim.katuranau.module11.controller;

import com.epam.cdp.maksim.katuranau.module11.exception.security.UserAlreadyExistException;
import com.epam.cdp.maksim.katuranau.module11.model.UserAuthorizationDto;
import com.epam.cdp.maksim.katuranau.module11.model.UserRegistrationDto;
import com.epam.cdp.maksim.katuranau.module11.service.RoleService;
import com.epam.cdp.maksim.katuranau.module11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * The type User controller.
 */
@Controller
public class UserController {

    private UserService userService;
    private RoleService roleService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     * @param roleService the role service
     */
    @Autowired
    public UserController(final UserService userService, final RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Gets registration.
     *
     * @param model the model
     * @return the registration
     */
    @GetMapping("/register")
    public String getRegistration(final Model model) {
        model.addAttribute("selectableRoles", roleService.getRoles());
        model.addAttribute("user", new UserRegistrationDto());
        return "registerPage";
    }

    /**
     * Register user string.
     *
     * @param user              the user
     * @param validationResults the validation results
     * @param model             the model
     * @return the string
     */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid final UserRegistrationDto user,
                               final BindingResult validationResults, final Model model) {
        if (validationResults.hasErrors()) {
            setModelAttributeUserRegisterError(model, "Validation exception");
            return "registerPage";
        }
        try {
            String password = user.getPassword();
            userService.save(user);
            userService.autoLogin(user.getLogin(), password);
        } catch (UserAlreadyExistException ex) {
            setModelAttributeUserRegisterError(model, ex.getMessage());
            return "registerPage";
        }
        return "redirect:/goods";
    }

    /**
     * Show authorization page string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/login")
    public String showAuthorizationPage(final Model model) {
        model.addAttribute("user", new UserAuthorizationDto());
        return "loginPage";
    }

    private void setModelAttributeUserRegisterError(final Model model, final String errorMessage) {
        model.addAttribute("user", new UserRegistrationDto());
        model.addAttribute("selectableRoles", roleService.getRoles());
        model.addAttribute("registrationErrorMessage", errorMessage);
    }
}
