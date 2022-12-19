package ua.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.cinema.dao.entity.Role;
import ua.cinema.dao.entity.User;
import ua.cinema.dao.service.UserService;
import ua.cinema.login.params.LoginError;
import ua.cinema.login.params.SignUpError;
import ua.cinema.login.request.LoginRequest;
import ua.cinema.login.request.SignUpRequest;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String showLogin(Model model) {
        setLoginError(model, false);
        setSignUpError(model, false);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "loginRequest") LoginRequest loginRequest, Model model,
                        HttpSession session) {

        String login = loginRequest.getLogin();
        String password = loginRequest.getPassword();
        List<User> users = userService.getAll();

        for (User user : users) {
            if (Objects.equals(user.getLogin(), login) && Objects.equals(user.getPassword(), password)) {
                if (user.getRole() == Role.ADMIN) {
                    session.setAttribute("isAdmin", true);
                    session.setAttribute("username", login);
                    return "redirect:/index";
                } else {
                    session.setAttribute("isAdmin", false);
                    session.setAttribute("username", login);
                    return "redirect:/user-schedule";
                }
            }
        }
        setSignUpError(model, false);
        setLoginError(model, true);
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute(name = "signUpRequest") SignUpRequest signUpRequest, Model model,
                         HttpSession session) {
        String login = signUpRequest.getLogin();
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();

        List<User> users = userService.getAll();

        for (User user : users) {
            if (Objects.equals(user.getLogin(), login) || Objects.equals(user.getEmail(), email)) {
                setLoginError(model, false);
                setSignUpError(model, true);
                return "login";
            }
        }
        User user = new User(login, email, password);
        userService.createUser(user);
        session.setAttribute("isAdmin", false);
        session.setAttribute("username", login);
        return "redirect:/user-schedule";
    }

    private void setLoginError(Model model, boolean error) {
        LoginError loginError = new LoginError();
        loginError.setError(error);
        model.addAttribute("loginError", loginError);
    }

    private void setSignUpError(Model model, boolean error) {
        SignUpError signUpError = new SignUpError();
        signUpError.setError(error);
        model.addAttribute("signUpError", signUpError);
    }

}
