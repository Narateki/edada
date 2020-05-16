package edadamanager.controller;

import edadamanager.model.Recipe;
import edadamanager.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

//    @Autowired
//    private RecipeService recipeService;

    @Autowired
    private RecipeRepository repo;

    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("title", "Поехали!");
        Recipe recipe = new Recipe("rec1");
        repo.save(recipe);
        return "home";
    }


}

