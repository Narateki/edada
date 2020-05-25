package edadamanager.controller;

import edadamanager.converter.RecipeWrapper;
import edadamanager.model.Ingredient;
import edadamanager.model.IngredientInRecipe;
import edadamanager.model.Inventory;
import edadamanager.model.Recipe;
import edadamanager.repository.CategoryRepository;
import edadamanager.repository.IngredientRepository;
import edadamanager.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/recipies")
public class RecipeController {
    Map <Ingredient, Double> choosenIng;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/findall")
    public String findall(Model model) {
        model.addAttribute("recipies", recipeService.findAll());
        return "recipies";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addRecipe(Model model) {

        Recipe recipe = new Recipe();
        model.addAttribute("inventory", inventoryRepository.findAll());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("recipies", recipe);
        model.addAttribute("chosenIng", choosenIng);
        return "addRecipe";
    }

//
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String saveRecipe(@Valid Recipe recipe, BindingResult errors, Model model) {
//        if (errors.hasErrors())
//            return "addRecipe";
//        recipeService.save(recipe);
//        return "redirect:/recipies/findall";
//    }

    @RequestMapping(value = "/addjs", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public @ResponseBody String saveRecipeJS(@RequestBody RecipeWrapper recipeWR) {
        System.out.println(recipeWR);
        recipeService.save(recipeWR);
        //return "redirect:/recipies/findall";
        return "ulala";
    }

    @RequestMapping("/getIngredients")
    public @ResponseBody
    List<Ingredient> getIngredients(){
        return ingredientRepository.findAll();
    }

    @RequestMapping("/getInventory")
    public @ResponseBody
    List<Inventory> getInventory(){
        return inventoryRepository.findAll();
    }

    @RequestMapping("/load")
    public String loadAll(){
        return "recipies";
    }
}

