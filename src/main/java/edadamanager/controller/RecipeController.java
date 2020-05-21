package edadamanager.controller;

import edadamanager.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
@RequestMapping("/recipies")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @RequestMapping("/findall")
    public String findall(Model model) {
        model.addAttribute("recipies", recipeService.findAll());
        return "recipies";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addRecipe(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipies", recipe);
        return "addRecipe";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveRecipe(@Valid Recipe recipe, BindingResult errors, Model model) {
        if (errors.hasErrors())
            return "addRecipe";
        recipeService.save(recipe);
        return "redirect:/recipies/findall";
    }
    @RequestMapping("/load")
    public String loadAll(){
        return "recipies";
    }
}

