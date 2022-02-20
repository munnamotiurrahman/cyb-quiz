package project.cyb.quiz.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.cyb.quiz.form.UserLoginForm;
import project.cyb.quiz.models.User;
import project.cyb.quiz.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * viewUserLoginPage Get Mapping controller funtion returns User login page
     * 
     * @param userId
     * @param model
     * @return
     */

    @GetMapping("/login")
    public String viewUserLoginPage(@RequestParam(name = "user_id", required = false, defaultValue = "-1") Long userId,
            Model model) {
        return "login/login";
    }

    /**
     * userLogin Post Mapping controller funtion.
     * 
     * @param userLoginForm
     * @param bindingResult
     * @param model
     * @return
     */

    @PostMapping("/login")
    public String userLogin(@Valid UserLoginForm userLoginForm, BindingResult bindingResult, Model model) {
        model.addAttribute("notification", "Successfully logged in");
        return "redirect:/";
    }

    /**
     * viewUserRegistrationPage is Get Mapping controller funtion for registration
     * funtion
     * 
     * @param username
     * @param user
     * @param model
     * @param authentication
     * @param request
     * @return
     */

    @GetMapping("/registration")
    public String viewUserRegistrationPage(
            @RequestParam(name = "username", required = false, defaultValue = "") String username, User user,
            Model model, Authentication authentication, HttpServletRequest request) {
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("notification", "You are already logged in");
            return "redirect:/";
        }

        model.addAttribute("user", user);
        model.addAttribute("phone", username);
        return "registration/registration";
    }

    /**
     * registerUser is Post Mapping controller funtion for registration
     * 
     * @param email
     * @param password
     * @param confirmPassword
     * @param firstName
     * @param lastName
     * @param model
     * @param redirectAttributes
     * @return
     */

    @PostMapping("/registration")
    public String registerUser(@RequestParam(name = "email", required = false, defaultValue = "") String email,
            @RequestParam(name = "password", required = true, defaultValue = "") String password,
            @RequestParam(name = "confirmPassword", required = true, defaultValue = "") String confirmPassword,
            @RequestParam(name = "firstName", required = true, defaultValue = "") String firstName,
            @RequestParam(name = "lastName", required = true, defaultValue = "") String lastName,

            Model model, RedirectAttributes redirectAttributes) {
        email = email.trim();

        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            redirectAttributes.addFlashAttribute("emailError", "This email is already registered!");
            redirectAttributes.addFlashAttribute("firstName", firstName);
            redirectAttributes.addFlashAttribute("lastName", lastName);
            redirectAttributes.addFlashAttribute("email", email);

            return "redirect:/registration";
        }

        if (!confirmPassword.equals(password)) {
            redirectAttributes.addFlashAttribute("confirmPasswordError",
                    "Password and confirm password does not matched!");
            redirectAttributes.addFlashAttribute("firstName", firstName);
            redirectAttributes.addFlashAttribute("lastName", lastName);
            redirectAttributes.addFlashAttribute("email", email);

            return "redirect:/registration";
        }

        User user = new User();

        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userService.save(user);

        redirectAttributes.addFlashAttribute("notification", "Successfully registered!");

        return "redirect:/";
    }

}
