package edadamanager.controller;

import edadamanager.wrapper.RecipeWrapper;
import edadamanager.wrapper.set.IngredientsSet;
import edadamanager.model.Ingredient;
import edadamanager.model.Inventory;
import edadamanager.model.Recipe;
import edadamanager.repository.CategoryRepository;
import edadamanager.repository.IngredientRepository;
import edadamanager.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String findall(Pageable page, Model model) {
//        Sort.Order o = null;
//        if (sort!=null) {
//            o = sort.iterator().next();
//        }
//        model.addAttribute("sort", (sort!=null)?o.getProperty():"");
//        model.addAttribute("dir", (sort!=null)?o.getDirection():"");
        model.addAttribute("page", recipeService.findAllPages(page));
        return "recipesPage";
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

    @RequestMapping(value = "/findByIngr", method = RequestMethod.GET)
    public String findByIngr(Model model) {

        IngredientsSet ingredientSet = new IngredientsSet();
        //Recipe recipe = new Recipe();
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("ingredientSet", ingredientSet);
//        model.addAttribute("category", categoryRepository.findAll());
//        model.addAttribute("recipies", recipe);
//        model.addAttribute("chosenIng", choosenIng);
        return "recipesByIngr";
    }

    @RequestMapping(value = "/findByIngr", method = RequestMethod.POST)
    public ModelAndView saveRecipe(IngredientsSet ingredientsSet, ModelMap model) {
//        model.addAttribute("recipes", recipeService.searchAllByIngredients(ingredientsSet));
        return new ModelAndView("recipies", "recipes", recipeService.searchAllByIngredients(ingredientsSet));
//        return "redirect:/recipies/findby";
    }

    @RequestMapping("/findby")
    public String findby(ModelMap model) {
        return "recipies";
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

