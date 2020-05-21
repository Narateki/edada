package edadamanager.controller;

import edadamanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {
//
    @Autowired
    private UserService userService;

    @RequestMapping("/findall")
    public String findallUs(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }


    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String goLogin() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult errors, Model model) {
        if (errors.hasErrors())
            return "register";
        user.setRole("ROLE_USER");
        userService.save(user);

        //Program authentication
        Authentication auth = new UsernamePasswordAuthenticationToken(
                user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/";
    }
//    @RequestMapping("/load")
//    public String loadAll(){
//        return "users";
//    }
}

