package edadamanager.controller;

import edadamanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @RequestMapping("/findall")
    public String findallUs(Model model) {
        model.addAttribute("users", userService.findAllUs());
        return "users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("users", user);
        return "addUser.html";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult errors, Model model) {
        if (errors.hasErrors())
            return "addUser.html";
        user.setId(123);
        userService.save(user);
        return "redirect:/users/findall";
    }
    @RequestMapping("/load")
    public String loadAll(){
        return "users";
    }
}

