package edadamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

//    @Autowired
//    private RecipeService recipeService;

    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("title", "Поехали!");
        return "home";
    }


}

